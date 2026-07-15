package com.cozary.tintedcampfires.dispenser;

import com.cozary.tintedcampfires.init.ModBlocks;
import com.cozary.tintedcampfires.init.particles.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SetColorDispenseBehavior extends DefaultDispenseItemBehavior {
    private static final Map<Item, BlockState> campfireDataMap = new HashMap<>();

    static {
        campfireDataMap.put(Items.BLACK_DYE, ModBlocks.BLACK_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.RED_DYE, ModBlocks.RED_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.GREEN_DYE, ModBlocks.GREEN_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.BROWN_DYE, ModBlocks.BROWN_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.BLUE_DYE, ModBlocks.BLUE_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.PURPLE_DYE, ModBlocks.PURPLE_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.CYAN_DYE, ModBlocks.CYAN_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.LIGHT_GRAY_DYE, ModBlocks.LIGHT_GRAY_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.GRAY_DYE, ModBlocks.GRAY_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.PINK_DYE, ModBlocks.PINK_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.LIME_DYE, ModBlocks.LIME_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.YELLOW_DYE, ModBlocks.YELLOW_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.LIGHT_BLUE_DYE, ModBlocks.LIGHT_BLUE_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.MAGENTA_DYE, ModBlocks.MAGENTA_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.ORANGE_DYE, ModBlocks.ORANGE_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.WHITE_DYE, ModBlocks.WHITE_CAMPFIRE.get().defaultBlockState());
    }

    private final DefaultDispenseItemBehavior defaultBehavior = new DefaultDispenseItemBehavior();

    @Override
    protected @NotNull ItemStack execute(BlockSource p_123412_, @NotNull ItemStack p_123413_) {
        Level level = p_123412_.level();
        Direction direction = p_123412_.state().getValue(DispenserBlock.FACING);
        BlockPos pos = p_123412_.pos().relative(direction);
        BlockState state = level.getBlockState(pos);
        Item item = p_123413_.getItem();

        if (state.getBlock() instanceof CampfireBlock) {
            Direction direction1 = state.getValue(CampfireBlock.FACING);
            Random rand = new Random();
            double d0 = rand.nextGaussian() * 0.02D;
            double d1 = rand.nextGaussian() * 0.02D;
            double d2 = rand.nextGaussian() * 0.02D;

            if (!level.isClientSide()) {
                BlockState targetState = campfireDataMap.get(item);
                if (targetState != null) {
                    level.setBlockAndUpdate(pos, targetState.setValue(CampfireBlock.FACING, direction1));
                    ((ServerLevel) level).sendParticles(ModParticles.TINTED_LAVA.get(), pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 50, d1, d2, d0, 0.1);
                    p_123413_.shrink(1);
                } else {
                    return this.defaultBehavior.dispense(p_123412_, p_123413_);
                }
            }
        } else {
            return this.defaultBehavior.dispense(p_123412_, p_123413_);
        }

        return p_123413_;
    }
}
