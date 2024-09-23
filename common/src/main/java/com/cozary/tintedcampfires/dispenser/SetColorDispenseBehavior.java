package com.cozary.tintedcampfires.dispenser;

import com.cozary.tintedcampfires.init.ModBlocks;
import com.cozary.tintedcampfires.init.particles.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.particles.ParticleOptions;
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
    private static final Map<Item, CampfireData> campfireDataMap = new HashMap<>();

    static {
        campfireDataMap.put(Items.BLACK_DYE, new CampfireData(ModBlocks.BLACK_CAMPFIRE.get().defaultBlockState(), ModParticles.BLACK_LAVA.get()));
        campfireDataMap.put(Items.RED_DYE, new CampfireData(ModBlocks.RED_CAMPFIRE.get().defaultBlockState(), ModParticles.RED_LAVA.get()));
        campfireDataMap.put(Items.GREEN_DYE, new CampfireData(ModBlocks.GREEN_CAMPFIRE.get().defaultBlockState(), ModParticles.GREEN_LAVA.get()));
        campfireDataMap.put(Items.BROWN_DYE, new CampfireData(ModBlocks.BROWN_CAMPFIRE.get().defaultBlockState(), ModParticles.BROWN_LAVA.get()));
        campfireDataMap.put(Items.BLUE_DYE, new CampfireData(ModBlocks.BLUE_CAMPFIRE.get().defaultBlockState(), ModParticles.BLUE_LAVA.get()));
        campfireDataMap.put(Items.PURPLE_DYE, new CampfireData(ModBlocks.PURPLE_CAMPFIRE.get().defaultBlockState(), ModParticles.PURPLE_LAVA.get()));
        campfireDataMap.put(Items.CYAN_DYE, new CampfireData(ModBlocks.CYAN_CAMPFIRE.get().defaultBlockState(), ModParticles.CYAN_LAVA.get()));
        campfireDataMap.put(Items.LIGHT_GRAY_DYE, new CampfireData(ModBlocks.LIGHT_GRAY_CAMPFIRE.get().defaultBlockState(), ModParticles.LIGHT_GRAY_LAVA.get()));
        campfireDataMap.put(Items.GRAY_DYE, new CampfireData(ModBlocks.GRAY_CAMPFIRE.get().defaultBlockState(), ModParticles.GRAY_LAVA.get()));
        campfireDataMap.put(Items.PINK_DYE, new CampfireData(ModBlocks.PINK_CAMPFIRE.get().defaultBlockState(), ModParticles.PINK_LAVA.get()));
        campfireDataMap.put(Items.LIME_DYE, new CampfireData(ModBlocks.LIME_CAMPFIRE.get().defaultBlockState(), ModParticles.LIME_LAVA.get()));
        campfireDataMap.put(Items.YELLOW_DYE, new CampfireData(ModBlocks.YELLOW_CAMPFIRE.get().defaultBlockState(), ModParticles.YELLOW_LAVA.get()));
        campfireDataMap.put(Items.LIGHT_BLUE_DYE, new CampfireData(ModBlocks.LIGHT_BLUE_CAMPFIRE.get().defaultBlockState(), ModParticles.LIGHT_BLUE_LAVA.get()));
        campfireDataMap.put(Items.MAGENTA_DYE, new CampfireData(ModBlocks.MAGENTA_CAMPFIRE.get().defaultBlockState(), ModParticles.MAGENTA_LAVA.get()));
        campfireDataMap.put(Items.ORANGE_DYE, new CampfireData(ModBlocks.ORANGE_CAMPFIRE.get().defaultBlockState(), ModParticles.ORANGE_LAVA.get()));
        campfireDataMap.put(Items.WHITE_DYE, new CampfireData(ModBlocks.WHITE_CAMPFIRE.get().defaultBlockState(), ModParticles.WHITE_LAVA.get()));
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

            if (!level.isClientSide) {
                CampfireData campfireData = campfireDataMap.get(item);
                if (campfireData != null) {
                    level.setBlockAndUpdate(pos, campfireData.blockState.setValue(CampfireBlock.FACING, direction1));
                    ((ServerLevel) level).sendParticles(campfireData.particle, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 50, d1, d2, d0, 0.1);
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

    public record CampfireData(BlockState blockState, ParticleOptions particle) {
    }
}

