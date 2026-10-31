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
        campfireDataMap.put(Items.DYE.black(), ModBlocks.BLACK_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.DYE.red(), ModBlocks.RED_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.DYE.green(), ModBlocks.GREEN_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.DYE.brown(), ModBlocks.BROWN_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.DYE.blue(), ModBlocks.BLUE_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.DYE.purple(), ModBlocks.PURPLE_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.DYE.cyan(), ModBlocks.CYAN_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.DYE.lightGray(), ModBlocks.LIGHT_GRAY_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.DYE.gray(), ModBlocks.GRAY_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.DYE.pink(), ModBlocks.PINK_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.DYE.lime(), ModBlocks.LIME_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.DYE.yellow(), ModBlocks.YELLOW_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.DYE.lightBlue(), ModBlocks.LIGHT_BLUE_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.DYE.magenta(), ModBlocks.MAGENTA_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.DYE.orange(), ModBlocks.ORANGE_CAMPFIRE.get().defaultBlockState());
        campfireDataMap.put(Items.DYE.white(), ModBlocks.WHITE_CAMPFIRE.get().defaultBlockState());
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
