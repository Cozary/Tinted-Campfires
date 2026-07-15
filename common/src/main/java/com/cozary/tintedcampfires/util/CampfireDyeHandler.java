package com.cozary.tintedcampfires.util;

import com.cozary.tintedcampfires.init.ModBlocks;
import com.cozary.tintedcampfires.init.RegistryObject;
import com.cozary.tintedcampfires.init.particles.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;

public class CampfireDyeHandler {

    private static final Map<Item, RegistryObject<Block>> CAMPFIRE_MAP = new HashMap<>();

    static {
        CAMPFIRE_MAP.put(Items.BLACK_DYE, ModBlocks.BLACK_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.RED_DYE, ModBlocks.RED_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.GREEN_DYE, ModBlocks.GREEN_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.BROWN_DYE, ModBlocks.BROWN_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.BLUE_DYE, ModBlocks.BLUE_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.PURPLE_DYE, ModBlocks.PURPLE_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.CYAN_DYE, ModBlocks.CYAN_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.LIGHT_GRAY_DYE, ModBlocks.LIGHT_GRAY_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.GRAY_DYE, ModBlocks.GRAY_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.PINK_DYE, ModBlocks.PINK_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.LIME_DYE, ModBlocks.LIME_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.YELLOW_DYE, ModBlocks.YELLOW_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.LIGHT_BLUE_DYE, ModBlocks.LIGHT_BLUE_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.MAGENTA_DYE, ModBlocks.MAGENTA_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.ORANGE_DYE, ModBlocks.ORANGE_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.WHITE_DYE, ModBlocks.WHITE_CAMPFIRE);
    }

    public static InteractionResult tryReplaceCampfire(Player player, Level level, InteractionHand hand, BlockPos pos) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();
        BlockState state = level.getBlockState(pos);

        if (!(state.getBlock() instanceof CampfireBlock)) {
            return InteractionResult.PASS;
        }

        if (!state.hasProperty(CampfireBlock.FACING) || !state.hasProperty(CampfireBlock.LIT)) {
            return InteractionResult.PASS;
        }

        Direction direction = state.getValue(CampfireBlock.FACING);
        Boolean isLit = state.getValue(CampfireBlock.LIT);

        if (!level.isClientSide() && CAMPFIRE_MAP.containsKey(item)) {
            Block block = CAMPFIRE_MAP.get(item).get();
            level.setBlockAndUpdate(pos, block.defaultBlockState()
                    .setValue(CampfireBlock.FACING, direction)
                    .setValue(CampfireBlock.LIT, isLit));

            RandomSource rand = level.getRandom();
            double dx = rand.nextGaussian() * 0.02D;
            double dy = rand.nextGaussian() * 0.02D;
            double dz = rand.nextGaussian() * 0.02D;

            ((ServerLevel) level).sendParticles(
                    ModParticles.TINTED_LAVA.get(),
                    pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5,
                    50, dx, dy, dz, 0.1
            );

            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}
