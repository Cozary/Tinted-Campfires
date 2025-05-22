package com.cozary.tintedcampfires.campfire;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public abstract class AbstractTintedCampfireBlockEntity extends BlockEntity implements Clearable {
    protected static final int BURN_COOL_SPEED = 2;
    protected static final int NUM_SLOTS = 4;
    protected final NonNullList<ItemStack> items;
    protected final int[] cookingProgress;
    protected final int[] cookingTime;
    protected final RecipeManager.CachedCheck<SingleRecipeInput, CampfireCookingRecipe> quickCheck;

    public AbstractTintedCampfireBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.items = NonNullList.withSize(NUM_SLOTS, ItemStack.EMPTY);
        this.cookingProgress = new int[NUM_SLOTS];
        this.cookingTime = new int[NUM_SLOTS];
        this.quickCheck = RecipeManager.createCheck(RecipeType.CAMPFIRE_COOKING);
    }

    public static void cookTick(Level level, BlockPos pos, BlockState state, AbstractTintedCampfireBlockEntity blockEntity) {
        boolean hasChanges = false;

        for (int i = 0; i < blockEntity.items.size(); ++i) {
            ItemStack stack = blockEntity.items.get(i);
            if (!stack.isEmpty()) {
                hasChanges = true;
                blockEntity.cookingProgress[i]++;
                if (blockEntity.cookingProgress[i] >= blockEntity.cookingTime[i]) {
                    SingleRecipeInput input = new SingleRecipeInput(stack);
                    ItemStack result = blockEntity.quickCheck.getRecipeFor(input, level)
                            .map(recipe -> recipe.value().assemble(input, level.registryAccess()))
                            .orElse(stack);
                    if (result.isItemEnabled(level.enabledFeatures())) {
                        Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), result);
                        blockEntity.items.set(i, ItemStack.EMPTY);
                        level.sendBlockUpdated(pos, state, state, 3);
                        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(state));
                    }
                }
            }
        }

        if (hasChanges) {
            setChanged(level, pos, state);
        }
    }

    public static void cooldownTick(Level level, BlockPos pos, BlockState state, AbstractTintedCampfireBlockEntity blockEntity) {
        boolean hasChanges = false;

        for (int i = 0; i < blockEntity.items.size(); ++i) {
            if (blockEntity.cookingProgress[i] > 0) {
                hasChanges = true;
                blockEntity.cookingProgress[i] = Mth.clamp(blockEntity.cookingProgress[i] - BURN_COOL_SPEED, 0, blockEntity.cookingTime[i]);
            }
        }

        if (hasChanges) {
            setChanged(level, pos, state);
        }
    }

    public static void particleTick(Level level, BlockPos pos, BlockState state, AbstractTintedCampfireBlockEntity blockEntity) {
        RandomSource random = level.random;

        if (random.nextFloat() < 0.11F) {
            for (int i = 0; i < random.nextInt(2) + 2; ++i) {
                CampfireBlock.makeParticles(level, pos, state.getValue(CampfireBlock.SIGNAL_FIRE), false);
            }
        }

        int baseDir = state.getValue(CampfireBlock.FACING).get2DDataValue();

        for (int i = 0; i < blockEntity.items.size(); ++i) {
            if (!blockEntity.items.get(i).isEmpty() && random.nextFloat() < 0.2F) {
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
    public void clearContent() {
        this.items.clear();
    }

    public Optional<RecipeHolder<CampfireCookingRecipe>> getCookableRecipe(ItemStack stack) {
        return this.items.stream().noneMatch(ItemStack::isEmpty)
                ? Optional.empty()
                : this.quickCheck.getRecipeFor(new SingleRecipeInput(stack), this.level);
    }

    public boolean placeFood(@Nullable LivingEntity entity, ItemStack stack, int cookTime) {
        for (int i = 0; i < this.items.size(); ++i) {
            if (this.items.get(i).isEmpty()) {
                this.cookingTime[i] = cookTime;
                this.cookingProgress[i] = 0;
                this.items.set(i, stack.consumeAndReturn(1, entity));
                this.level.gameEvent(GameEvent.BLOCK_CHANGE, this.getBlockPos(), GameEvent.Context.of(entity, this.getBlockState()));
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

    public void dowse() {
        if (this.level != null) {
            this.markUpdated();
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.items.clear();
        ContainerHelper.loadAllItems(tag, this.items, registries);
        if (tag.contains("CookingTimes", 11)) {
            System.arraycopy(tag.getIntArray("CookingTimes"), 0, this.cookingProgress, 0, NUM_SLOTS);
        }
        if (tag.contains("CookingTotalTimes", 11)) {
            System.arraycopy(tag.getIntArray("CookingTotalTimes"), 0, this.cookingTime, 0, NUM_SLOTS);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        ContainerHelper.saveAllItems(tag, this.items, true, registries);
        tag.putIntArray("CookingTimes", this.cookingProgress);
        tag.putIntArray("CookingTotalTimes", this.cookingTime);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        ContainerHelper.saveAllItems(tag, this.items, true, registries);
        return tag;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void removeComponentsFromTag(CompoundTag tag) {
        tag.remove("Items");
    }

    @Override
    protected void applyImplicitComponents(BlockEntity.DataComponentInput input) {
        super.applyImplicitComponents(input);
        input.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyInto(this.getItems());
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder builder) {
        super.collectImplicitComponents(builder);
        builder.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(this.getItems()));
    }
}

