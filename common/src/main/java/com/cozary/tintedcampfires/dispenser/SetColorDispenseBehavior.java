package com.cozary.tintedcampfires.dispenser;

import com.cozary.tintedcampfires.campfire.TintedCampfireBlock;
import com.cozary.tintedcampfires.init.ModBlocks;
import com.cozary.tintedcampfires.init.particles.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.particles.ColorParticleOption;
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
            Direction facing = state.getValue(CampfireBlock.FACING);

            if (!level.isClientSide()) {
                BlockState targetState = campfireDataMap.get(item);
                if (targetState != null) {
                    level.setBlockAndUpdate(pos, targetState.setValue(CampfireBlock.FACING, facing));
                    if (targetState.getBlock() instanceof TintedCampfireBlock tintedCampfire) {
                        int color = tintedCampfire.getDyeColor().getTextureDiffuseColor();
                        ((ServerLevel) level).sendParticles(
                                ColorParticleOption.create(ModParticles.TINTED_LAVA.get(), color),
                                pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                                50, 0.25, 0.25, 0.25, 0.1);
                    }
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
