package com.cozary.tintedcampfires.campfire;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Clearable;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Optional;

//CampfireBlockEntity
public abstract class AbstractTintedCampfireBlockEntity extends BlockEntity implements Clearable {
    protected static final int BURN_COOL_SPEED = 2;
    protected static final int NUM_SLOTS = 4;
    protected final NonNullList<ItemStack> items;
    protected final int[] cookingProgress;
    protected final int[] cookingTime;

    public AbstractTintedCampfireBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.items = NonNullList.withSize(NUM_SLOTS, ItemStack.EMPTY);
        this.cookingProgress = new int[NUM_SLOTS];
        this.cookingTime = new int[NUM_SLOTS];
    }

    public static void cookTick(ServerLevel level, BlockPos blockPos, BlockState blockState, AbstractTintedCampfireBlockEntity abstractTintedCampfireBlockEntity, RecipeManager.CachedCheck<SingleRecipeInput, CampfireCookingRecipe> recipeCachedCheck) {
        boolean hasChanges = false;

        for (int i = 0; i < abstractTintedCampfireBlockEntity.items.size(); ++i) {
            ItemStack stack = abstractTintedCampfireBlockEntity.items.get(i);
            if (!stack.isEmpty()) {
                hasChanges = true;
                abstractTintedCampfireBlockEntity.cookingProgress[i]++;
                if (abstractTintedCampfireBlockEntity.cookingProgress[i] >= abstractTintedCampfireBlockEntity.cookingTime[i]) {
                    SingleRecipeInput input = new SingleRecipeInput(stack);
                    ItemStack result = (ItemStack) recipeCachedCheck.getRecipeFor(input, level)
                            .map(recipe -> {
                                return ((CampfireCookingRecipe) recipe.value()).assemble(input, level.registryAccess());
                            }).orElse(stack);
                    if (result.isItemEnabled(level.enabledFeatures())) {
                        Containers.dropItemStack(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), result);
                        abstractTintedCampfireBlockEntity.items.set(i, ItemStack.EMPTY);
                        level.sendBlockUpdated(blockPos, blockState, blockState, 3);
                        level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(blockState));
                    }
                }
            }
        }

        if (hasChanges) {
            setChanged(level, blockPos, blockState);
        }
    }

    public static void cooldownTick(Level level, BlockPos pos, BlockState state, AbstractTintedCampfireBlockEntity abstractTintedCampfireBlockEntity) {
        boolean hasChanges = false;

        for (int i = 0; i < abstractTintedCampfireBlockEntity.items.size(); ++i) {
            if (abstractTintedCampfireBlockEntity.cookingProgress[i] > 0) {
                hasChanges = true;
                abstractTintedCampfireBlockEntity.cookingProgress[i] = Mth.clamp(abstractTintedCampfireBlockEntity.cookingProgress[i] - BURN_COOL_SPEED, 0, abstractTintedCampfireBlockEntity.cookingTime[i]);
            }
        }

        if (hasChanges) {
            setChanged(level, pos, state);
        }
    }

    public static void particleTick(Level level, BlockPos pos, BlockState state, AbstractTintedCampfireBlockEntity abstractTintedCampfireBlockEntity) {
        RandomSource random = level.random;

        if (random.nextFloat() < 0.11F) {
            for (int i = 0; i < random.nextInt(2) + 2; ++i) {
                CampfireBlock.makeParticles(level, pos, state.getValue(CampfireBlock.SIGNAL_FIRE), false);
            }
        }

        int baseDir = state.getValue(CampfireBlock.FACING).get2DDataValue();

        for (int i = 0; i < abstractTintedCampfireBlockEntity.items.size(); ++i) {
            if (!abstractTintedCampfireBlockEntity.items.get(i).isEmpty() && random.nextFloat() < 0.2F) {
                Direction direction = Direction.from2DDataValue(Math.floorMod(i + baseDir, 4));
                float offset = 0.3125F;
                double dx = pos.getX() + 0.5 - direction.getStepX() * offset + direction.getClockWise().getStepX() * offset;
                double dy = pos.getY() + 0.5;
                double dz = pos.getZ() + 0.5 - direction.getStepZ() * offset + direction.getClockWise().getStepZ() * offset;

                for (int j = 0; j < 4; ++j) {
                    level.addParticle(ParticleTypes.SMOKE, dx, dy, dz, 0.0, 5.0E-4, 0.0);
                }
            }
        }
    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.items.clear();
        ContainerHelper.loadAllItems(tag, this.items, registries);

        tag.getIntArray("CookingTimes").ifPresentOrElse((savedCookingProgress) -> {
            System.arraycopy(savedCookingProgress, 0, this.cookingProgress, 0, Math.min(this.cookingTime.length, savedCookingProgress.length));
        }, () -> {
            Arrays.fill(this.cookingProgress, 0);
        });
        tag.getIntArray("CookingTotalTimes").ifPresentOrElse((savedCookingTotalTimes) -> {
            System.arraycopy(savedCookingTotalTimes, 0, this.cookingTime, 0, Math.min(this.cookingTime.length, savedCookingTotalTimes.length));
        }, () -> {
            Arrays.fill(this.cookingTime, 0);
        });
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        ContainerHelper.saveAllItems(tag, this.items, true, registries);
        tag.putIntArray("CookingTimes", this.cookingProgress);
        tag.putIntArray("CookingTotalTimes", this.cookingTime);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        ContainerHelper.saveAllItems(tag, this.items, true, registries);
        return tag;
    }

    public boolean placeFood(ServerLevel level, @Nullable LivingEntity entity, ItemStack stack) {
        for (int i = 0; i < this.items.size(); ++i) {
            ItemStack itemStack = (ItemStack) this.items.get(i);
            if (itemStack.isEmpty()) {
                Optional<RecipeHolder<CampfireCookingRecipe>> recipeHolder = level.recipeAccess().getRecipeFor(RecipeType.CAMPFIRE_COOKING, new SingleRecipeInput(stack), level);

                if (recipeHolder.isEmpty()) {
                    return false;
                }

                this.cookingTime[i] = ((CampfireCookingRecipe) ((RecipeHolder) recipeHolder.get()).value()).cookingTime();
                this.cookingProgress[i] = 0;
                this.items.set(i, stack.consumeAndReturn(1, entity));
                level.gameEvent(GameEvent.BLOCK_CHANGE, this.getBlockPos(), GameEvent.Context.of(entity, this.getBlockState()));
                this.markUpdated();
                return true;
            }
        }
        return false;
    }

    protected void markUpdated() {
        this.setChanged();
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    @Override
    public void preRemoveSideEffects(BlockPos blockPos, BlockState blockState) {
        if (this.level != null) {
            Containers.dropContents(this.level, blockPos, this.getItems());
        }

    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter input) {
        super.applyImplicitComponents(input);
        ((ItemContainerContents)input.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY)).copyInto(this.getItems());
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder builder) {
        super.collectImplicitComponents(builder);
        builder.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(this.getItems()));
    }

    @Override
    public void removeComponentsFromTag(CompoundTag p_332690_) {
        p_332690_.remove("Items");
    }
}

