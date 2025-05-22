package com.cozary.tintedcampfires.util;

import com.cozary.tintedcampfires.TintedCampfires;
import com.cozary.tintedcampfires.init.ModBlocks;
import com.cozary.tintedcampfires.init.particles.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
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
import net.minecraft.core.Direction;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CampfireDyeHandler {

    private static final Map<Item, CampfireData> CAMPFIRE_MAP = new HashMap<>();

    static {
        CAMPFIRE_MAP.put(Items.BLACK_DYE, new CampfireData(ModBlocks.BLACK_CAMPFIRE, ModParticles.BLACK_LAVA::get));
        CAMPFIRE_MAP.put(Items.RED_DYE, new CampfireData(ModBlocks.RED_CAMPFIRE, ModParticles.RED_LAVA::get));
        CAMPFIRE_MAP.put(Items.GREEN_DYE, new CampfireData(ModBlocks.GREEN_CAMPFIRE, ModParticles.GREEN_LAVA::get));
        CAMPFIRE_MAP.put(Items.BROWN_DYE, new CampfireData(ModBlocks.BROWN_CAMPFIRE, ModParticles.BROWN_LAVA::get));
        CAMPFIRE_MAP.put(Items.BLUE_DYE, new CampfireData(ModBlocks.BLUE_CAMPFIRE, ModParticles.BLUE_LAVA::get));
        CAMPFIRE_MAP.put(Items.PURPLE_DYE, new CampfireData(ModBlocks.PURPLE_CAMPFIRE, ModParticles.PURPLE_LAVA::get));
        CAMPFIRE_MAP.put(Items.CYAN_DYE, new CampfireData(ModBlocks.CYAN_CAMPFIRE, ModParticles.CYAN_LAVA::get));
        CAMPFIRE_MAP.put(Items.LIGHT_GRAY_DYE, new CampfireData(ModBlocks.LIGHT_GRAY_CAMPFIRE, ModParticles.LIGHT_GRAY_LAVA::get));
        CAMPFIRE_MAP.put(Items.GRAY_DYE, new CampfireData(ModBlocks.GRAY_CAMPFIRE, ModParticles.GRAY_LAVA::get));
        CAMPFIRE_MAP.put(Items.PINK_DYE, new CampfireData(ModBlocks.PINK_CAMPFIRE, ModParticles.PINK_LAVA::get));
        CAMPFIRE_MAP.put(Items.LIME_DYE, new CampfireData(ModBlocks.LIME_CAMPFIRE, ModParticles.LIME_LAVA::get));
        CAMPFIRE_MAP.put(Items.YELLOW_DYE, new CampfireData(ModBlocks.YELLOW_CAMPFIRE, ModParticles.YELLOW_LAVA::get));
        CAMPFIRE_MAP.put(Items.LIGHT_BLUE_DYE, new CampfireData(ModBlocks.LIGHT_BLUE_CAMPFIRE, ModParticles.LIGHT_BLUE_LAVA::get));
        CAMPFIRE_MAP.put(Items.MAGENTA_DYE, new CampfireData(ModBlocks.MAGENTA_CAMPFIRE, ModParticles.MAGENTA_LAVA::get));
        CAMPFIRE_MAP.put(Items.ORANGE_DYE, new CampfireData(ModBlocks.ORANGE_CAMPFIRE, ModParticles.ORANGE_LAVA::get));
        CAMPFIRE_MAP.put(Items.WHITE_DYE, new CampfireData(ModBlocks.WHITE_CAMPFIRE, ModParticles.WHITE_LAVA::get));
    }

    public static InteractionResult tryReplaceCampfire(Player player, Level level, InteractionHand hand, BlockPos pos) {

        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();
        BlockState state = level.getBlockState(pos);

        if (!(state.getBlock() instanceof CampfireBlock)) {
            return InteractionResult.PASS;
        }

        Direction direction = state.getValue(CampfireBlock.FACING);
        Boolean isLit = state.getValue(CampfireBlock.LIT);

        if (!level.isClientSide && CAMPFIRE_MAP.containsKey(item)) {
            CampfireData data = CAMPFIRE_MAP.get(item);
            level.setBlockAndUpdate(pos, data.blockSupplier().get().defaultBlockState()
                    .setValue(CampfireBlock.FACING, direction)
                    .setValue(CampfireBlock.LIT, isLit));

            RandomSource rand = level.getRandom();
            double dx = rand.nextGaussian() * 0.02D;
            double dy = rand.nextGaussian() * 0.02D;
            double dz = rand.nextGaussian() * 0.02D;

            ((ServerLevel) level).sendParticles(
                    data.particleSupplier().get(),
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

    public record CampfireData(Supplier<Block> blockSupplier, Supplier<ParticleOptions> particleSupplier) {}
}
