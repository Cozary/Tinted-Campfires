package com.cozary.tintedcampfires.campfire;

import com.cozary.tintedcampfires.init.ModBlockEntities;
import com.mojang.logging.LogUtils;
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
import net.minecraft.util.ProblemReporter;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.Optional;

public class TintedCampfireBlockEntity extends BlockEntity implements Clearable {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final int BURN_COOL_SPEED = 2;
    private static final int NUM_SLOTS = 4;
    private final NonNullList<ItemStack> items;
    private final int[] cookingProgress;
    private final int[] cookingTime;

    public TintedCampfireBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.TINTED_CAMPFIRE.get(), pos, blockState);
        this.items = NonNullList.withSize(4, ItemStack.EMPTY);
        this.cookingProgress = new int[4];
        this.cookingTime = new int[4];
    }

    public static void cookTick(ServerLevel level, BlockPos pos, BlockState state, TintedCampfireBlockEntity entity, RecipeManager.CachedCheck<SingleRecipeInput, CampfireCookingRecipe> recipeCache) {
        boolean changed = false;

        for (int slot = 0; slot < entity.items.size(); ++slot) {
            ItemStack itemStack = (ItemStack) entity.items.get(slot);
            if (!itemStack.isEmpty()) {
                changed = true;
                int var10002 = entity.cookingProgress[slot]++;
                if (entity.cookingProgress[slot] >= entity.cookingTime[slot]) {
                    SingleRecipeInput input = new SingleRecipeInput(itemStack);
                    ItemStack result = (ItemStack) recipeCache.getRecipeFor(input, level).map((r) -> ((CampfireCookingRecipe) r.value()).assemble(input)).orElse(itemStack);
                    if (result.isItemEnabled(level.enabledFeatures())) {
                        Containers.dropItemStack(level, (double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), result);
                        entity.items.set(slot, ItemStack.EMPTY);
                        level.sendBlockUpdated(pos, state, state, 3);
                        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(state));
                    }
                }
            }
        }

        if (changed) {
            setChanged(level, pos, state);
        }

    }

    public static void cooldownTick(Level level, BlockPos pos, BlockState state, TintedCampfireBlockEntity entity) {
        boolean changed = false;

        for (int slot = 0; slot < entity.items.size(); ++slot) {
            if (entity.cookingProgress[slot] > 0) {
                changed = true;
                entity.cookingProgress[slot] = Mth.clamp(entity.cookingProgress[slot] - 2, 0, entity.cookingTime[slot]);
            }
        }

        if (changed) {
            setChanged(level, pos, state);
        }

    }

    public static void particleTick(Level level, BlockPos pos, BlockState state, TintedCampfireBlockEntity entity) {
        RandomSource random = level.getRandom();
        if (random.nextFloat() < 0.11F) {
            for (int i = 0; i < random.nextInt(2) + 2; ++i) {
                TintedCampfireBlock.makeParticles(level, pos, state, (Boolean) state.getValue(CampfireBlock.SIGNAL_FIRE), false);
            }
        }

        int rotation = ((Direction) state.getValue(CampfireBlock.FACING)).get2DDataValue();

        for (int slot = 0; slot < entity.items.size(); ++slot) {
            if (!((ItemStack) entity.items.get(slot)).isEmpty() && random.nextFloat() < 0.2F) {
                Direction direction = Direction.from2DDataValue(Math.floorMod(slot + rotation, 4));
                float distanceFromCenter = 0.3125F;
                double x = (double) pos.getX() + (double) 0.5F - (double) ((float) direction.getStepX() * 0.3125F) + (double) ((float) direction.getClockWise().getStepX() * 0.3125F);
                double y = (double) pos.getY() + (double) 0.5F;
                double z = (double) pos.getZ() + (double) 0.5F - (double) ((float) direction.getStepZ() * 0.3125F) + (double) ((float) direction.getClockWise().getStepZ() * 0.3125F);

                for (int i = 0; i < 4; ++i) {
                    level.addParticle(ParticleTypes.SMOKE, x, y, z, (double) 0.0F, 5.0E-4, (double) 0.0F);
                }
            }
        }

    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.items.clear();
        ContainerHelper.loadAllItems(input, this.items);
        input.getIntArray("CookingTimes").ifPresentOrElse((cookingTimes) -> System.arraycopy(cookingTimes, 0, this.cookingProgress, 0, Math.min(this.cookingTime.length, cookingTimes.length)), () -> Arrays.fill(this.cookingProgress, 0));
        input.getIntArray("CookingTotalTimes").ifPresentOrElse((cookingTimes) -> System.arraycopy(cookingTimes, 0, this.cookingTime, 0, Math.min(this.cookingTime.length, cookingTimes.length)), () -> Arrays.fill(this.cookingTime, 0));
    }

    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, this.items, true);
        output.putIntArray("CookingTimes", this.cookingProgress);
        output.putIntArray("CookingTotalTimes", this.cookingTime);
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        try (ProblemReporter.ScopedCollector reporter = new ProblemReporter.ScopedCollector(this.problemPath(), LOGGER)) {
            TagValueOutput output = TagValueOutput.createWithContext(reporter, registries);
            ContainerHelper.saveAllItems(output, this.items, true);
            return output.buildResult();
        }
    }

    public boolean placeFood(ServerLevel serverLevel, @Nullable LivingEntity sourceEntity, ItemStack placeItem) {
        for (int slot = 0; slot < this.items.size(); ++slot) {
            ItemStack item = (ItemStack) this.items.get(slot);
            if (item.isEmpty()) {
                Optional<RecipeHolder<CampfireCookingRecipe>> recipe = serverLevel.recipeAccess().getRecipeFor(RecipeType.CAMPFIRE_COOKING, new SingleRecipeInput(placeItem), serverLevel);
                if (recipe.isEmpty()) {
                    return false;
                }

                this.cookingTime[slot] = ((CampfireCookingRecipe) ((RecipeHolder) recipe.get()).value()).cookingTime();
                this.cookingProgress[slot] = 0;
                this.items.set(slot, placeItem.consumeAndReturn(1, sourceEntity));
                serverLevel.gameEvent(GameEvent.BLOCK_CHANGE, this.getBlockPos(), GameEvent.Context.of(sourceEntity, this.getBlockState()));
                this.markUpdated();
                return true;
            }
        }

        return false;
    }

    private void markUpdated() {
        this.setChanged();
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    public void clearContent() {
        this.items.clear();
    }

    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        if (this.level != null) {
            Containers.dropContents(this.level, pos, this.getItems());
        }

    }

    protected void applyImplicitComponents(DataComponentGetter components) {
        super.applyImplicitComponents(components);
        ((ItemContainerContents) components.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY)).copyInto(this.getItems());
    }

    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        super.collectImplicitComponents(components);
        components.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(this.getItems()));
    }

    public void removeComponentsFromTag(ValueOutput output) {
        output.discard("Items");
    }
}
