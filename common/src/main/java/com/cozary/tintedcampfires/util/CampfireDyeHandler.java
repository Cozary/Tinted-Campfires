package com.cozary.tintedcampfires.util;

import com.cozary.tintedcampfires.campfire.TintedCampfireBlock;
import com.cozary.tintedcampfires.init.ModBlocks;
import com.cozary.tintedcampfires.init.RegistryObject;
import com.cozary.tintedcampfires.init.particles.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.server.level.ServerLevel;
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
        CAMPFIRE_MAP.put(Items.DYE.black(), ModBlocks.BLACK_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.DYE.red(), ModBlocks.RED_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.DYE.green(), ModBlocks.GREEN_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.DYE.brown(), ModBlocks.BROWN_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.DYE.blue(), ModBlocks.BLUE_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.DYE.purple(), ModBlocks.PURPLE_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.DYE.cyan(), ModBlocks.CYAN_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.DYE.lightGray(), ModBlocks.LIGHT_GRAY_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.DYE.gray(), ModBlocks.GRAY_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.DYE.pink(), ModBlocks.PINK_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.DYE.lime(), ModBlocks.LIME_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.DYE.yellow(), ModBlocks.YELLOW_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.DYE.lightBlue(), ModBlocks.LIGHT_BLUE_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.DYE.magenta(), ModBlocks.MAGENTA_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.DYE.orange(), ModBlocks.ORANGE_CAMPFIRE);
        CAMPFIRE_MAP.put(Items.DYE.white(), ModBlocks.WHITE_CAMPFIRE);
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

            if (block instanceof TintedCampfireBlock tintedCampfire) {
                int color = tintedCampfire.getDyeColor().getTextureDiffuseColor();
                ((ServerLevel) level).sendParticles(
                        ColorParticleOption.create(ModParticles.TINTED_LAVA.get(), color),
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        50, 0.25, 0.25, 0.25, 0.1);
            }

            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            return InteractionResult.SUCCESS;
        }

        if (CAMPFIRE_MAP.containsKey(item)) {
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}
