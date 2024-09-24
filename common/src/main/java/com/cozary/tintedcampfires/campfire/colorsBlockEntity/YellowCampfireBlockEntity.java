package com.cozary.tintedcampfires.campfire.colorsBlockEntity;

import com.cozary.tintedcampfires.campfire.colors.YellowCampfire;
import com.cozary.tintedcampfires.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import org.jetbrains.annotations.Nullable;
import java.util.Optional;

public class YellowCampfireBlockEntity extends BlockEntity implements Clearable {

    private static final int BURN_COOL_SPEED = 2;
    private static final int NUM_SLOTS = 4;
    private final NonNullList<ItemStack> items;
    private final int[] cookingProgress;
    private final int[] cookingTime;
    private final RecipeManager.CachedCheck<Container, CampfireCookingRecipe> quickCheck;

    public YellowCampfireBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.YELLOW_CAMPFIRE_TILE.get(), blockPos, blockState);
        this.items = NonNullList.withSize(4, ItemStack.EMPTY);
        this.cookingProgress = new int[4];
        this.cookingTime = new int[4];
        this.quickCheck = RecipeManager.createCheck(RecipeType.CAMPFIRE_COOKING);
    }

    public static void cookTick(Level $$0, BlockPos $$1, BlockState $$2, YellowCampfireBlockEntity $$3) {
        boolean $$4 = false;

        for (int $$5 = 0; $$5 < $$3.items.size(); ++$$5) {
            ItemStack $$6 = (ItemStack) $$3.items.get($$5);
            if (!$$6.isEmpty()) {
                $$4 = true;
                int var10002 = $$3.cookingProgress[$$5]++;
                if ($$3.cookingProgress[$$5] >= $$3.cookingTime[$$5]) {
                    Container $$7 = new SimpleContainer(new ItemStack[]{$$6});
                    ItemStack $$8 = (ItemStack) $$3.quickCheck.getRecipeFor($$7, $$0).map(($$2x) -> {
                        return ((CampfireCookingRecipe) $$2x.value()).assemble($$7, $$0.registryAccess());
                    }).orElse($$6);
                    if ($$8.isItemEnabled($$0.enabledFeatures())) {
                        Containers.dropItemStack($$0, (double) $$1.getX(), (double) $$1.getY(), (double) $$1.getZ(), $$8);
                        $$3.items.set($$5, ItemStack.EMPTY);
                        $$0.sendBlockUpdated($$1, $$2, $$2, 3);
                        $$0.gameEvent(GameEvent.BLOCK_CHANGE, $$1, GameEvent.Context.of($$2));
                    }
                }
            }
        }

        if ($$4) {
            setChanged($$0, $$1, $$2);
        }

    }

    public static void cooldownTick(Level $$0, BlockPos $$1, BlockState $$2, YellowCampfireBlockEntity $$3) {
        boolean $$4 = false;

        for (int $$5 = 0; $$5 < $$3.items.size(); ++$$5) {
            if ($$3.cookingProgress[$$5] > 0) {
                $$4 = true;
                $$3.cookingProgress[$$5] = Mth.clamp($$3.cookingProgress[$$5] - 2, 0, $$3.cookingTime[$$5]);
            }
        }

        if ($$4) {
            setChanged($$0, $$1, $$2);
        }

    }

    public static void particleTick(Level $$0, BlockPos $$1, BlockState $$2, YellowCampfireBlockEntity $$3) {
        RandomSource $$4 = $$0.random;
        int $$6;
        if ($$4.nextFloat() < 0.11F) {
            for ($$6 = 0; $$6 < $$4.nextInt(2) + 2; ++$$6) {
                YellowCampfire.makeParticles($$0, $$1, (Boolean) $$2.getValue(YellowCampfire.SIGNAL_FIRE), false);
            }
        }

        $$6 = ((Direction) $$2.getValue(YellowCampfire.FACING)).get2DDataValue();

        for (int $$7 = 0; $$7 < $$3.items.size(); ++$$7) {
            if (!((ItemStack) $$3.items.get($$7)).isEmpty() && $$4.nextFloat() < 0.2F) {
                Direction $$8 = Direction.from2DDataValue(Math.floorMod($$7 + $$6, 4));
                float $$9 = 0.3125F;
                double $$10 = (double) $$1.getX() + 0.5 - (double) ((float) $$8.getStepX() * 0.3125F) + (double) ((float) $$8.getClockWise().getStepX() * 0.3125F);
                double $$11 = (double) $$1.getY() + 0.5;
                double $$12 = (double) $$1.getZ() + 0.5 - (double) ((float) $$8.getStepZ() * 0.3125F) + (double) ((float) $$8.getClockWise().getStepZ() * 0.3125F);

                for (int $$13 = 0; $$13 < 4; ++$$13) {
                    $$0.addParticle(ParticleTypes.SMOKE, $$10, $$11, $$12, 0.0, 5.0E-4, 0.0);
                }
            }
        }

    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    public void load(CompoundTag $$0) {
        super.load($$0);
        this.items.clear();
        ContainerHelper.loadAllItems($$0, this.items);
        int[] $$2;
        if ($$0.contains("CookingTimes", 11)) {
            $$2 = $$0.getIntArray("CookingTimes");
            System.arraycopy($$2, 0, this.cookingProgress, 0, Math.min(this.cookingTime.length, $$2.length));
        }

        if ($$0.contains("CookingTotalTimes", 11)) {
            $$2 = $$0.getIntArray("CookingTotalTimes");
            System.arraycopy($$2, 0, this.cookingTime, 0, Math.min(this.cookingTime.length, $$2.length));
        }

    }

    protected void saveAdditional(CompoundTag $$0) {
        super.saveAdditional($$0);
        ContainerHelper.saveAllItems($$0, this.items, true);
        $$0.putIntArray("CookingTimes", this.cookingProgress);
        $$0.putIntArray("CookingTotalTimes", this.cookingTime);
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public CompoundTag getUpdateTag() {
        CompoundTag $$0 = new CompoundTag();
        ContainerHelper.saveAllItems($$0, this.items, true);
        return $$0;
    }

    public Optional<RecipeHolder<CampfireCookingRecipe>> getCookableRecipe(ItemStack $$0) {
        return this.items.stream().noneMatch(ItemStack::isEmpty) ? Optional.empty() : this.quickCheck.getRecipeFor(new SimpleContainer(new ItemStack[]{$$0}), this.level);
    }

    public boolean placeFood(@Nullable Entity $$0, ItemStack $$1, int $$2) {
        for (int $$3 = 0; $$3 < this.items.size(); ++$$3) {
            ItemStack $$4 = (ItemStack) this.items.get($$3);
            if ($$4.isEmpty()) {
                this.cookingTime[$$3] = $$2;
                this.cookingProgress[$$3] = 0;
                this.items.set($$3, $$1.split(1));
                this.level.gameEvent(GameEvent.BLOCK_CHANGE, this.getBlockPos(), GameEvent.Context.of($$0, this.getBlockState()));
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

    public void dowse() {
        if (this.level != null) {
            this.markUpdated();
        }

    }
}
