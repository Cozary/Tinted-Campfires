package com.cozary.tintedcampfires;

import com.cozary.tintedcampfires.init.ModBlocks;
import com.cozary.tintedcampfires.init.particles.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

import static net.minecraft.world.level.block.CampfireBlock.FACING;
import static net.minecraft.world.level.block.CampfireBlock.LIT;

@EventBusSubscriber(modid = "tintedcampfires")
public class EventHandler {

    private static final Map<Item, CampfireData> campfireMap = new HashMap<>();

    static {
        campfireMap.put(Items.BLACK_DYE, new CampfireData(() -> ModBlocks.BLACK_CAMPFIRE.get(), () -> ModParticles.BLACK_LAVA.get()));
        campfireMap.put(Items.RED_DYE, new CampfireData(() -> ModBlocks.RED_CAMPFIRE.get(), () -> ModParticles.RED_LAVA.get()));
        campfireMap.put(Items.GREEN_DYE, new CampfireData(() -> ModBlocks.GREEN_CAMPFIRE.get(), () -> ModParticles.GREEN_LAVA.get()));
        campfireMap.put(Items.BROWN_DYE, new CampfireData(() -> ModBlocks.BROWN_CAMPFIRE.get(), () -> ModParticles.BROWN_LAVA.get()));
        campfireMap.put(Items.BLUE_DYE, new CampfireData(() -> ModBlocks.BLUE_CAMPFIRE.get(), () -> ModParticles.BLUE_LAVA.get()));
        campfireMap.put(Items.PURPLE_DYE, new CampfireData(() -> ModBlocks.PURPLE_CAMPFIRE.get(), () -> ModParticles.PURPLE_LAVA.get()));
        campfireMap.put(Items.CYAN_DYE, new CampfireData(() -> ModBlocks.CYAN_CAMPFIRE.get(), () -> ModParticles.CYAN_LAVA.get()));
        campfireMap.put(Items.LIGHT_GRAY_DYE, new CampfireData(() -> ModBlocks.LIGHT_GRAY_CAMPFIRE.get(), () -> ModParticles.LIGHT_GRAY_LAVA.get()));
        campfireMap.put(Items.GRAY_DYE, new CampfireData(() -> ModBlocks.GRAY_CAMPFIRE.get(), () -> ModParticles.GRAY_LAVA.get()));
        campfireMap.put(Items.PINK_DYE, new CampfireData(() -> ModBlocks.PINK_CAMPFIRE.get(), () -> ModParticles.PINK_LAVA.get()));
        campfireMap.put(Items.LIME_DYE, new CampfireData(() -> ModBlocks.LIME_CAMPFIRE.get(), () -> ModParticles.LIME_LAVA.get()));
        campfireMap.put(Items.YELLOW_DYE, new CampfireData(() -> ModBlocks.YELLOW_CAMPFIRE.get(), () -> ModParticles.YELLOW_LAVA.get()));
        campfireMap.put(Items.LIGHT_BLUE_DYE, new CampfireData(() -> ModBlocks.LIGHT_BLUE_CAMPFIRE.get(), () -> ModParticles.LIGHT_BLUE_LAVA.get()));
        campfireMap.put(Items.MAGENTA_DYE, new CampfireData(() -> ModBlocks.MAGENTA_CAMPFIRE.get(), () -> ModParticles.MAGENTA_LAVA.get()));
        campfireMap.put(Items.ORANGE_DYE, new CampfireData(() -> ModBlocks.ORANGE_CAMPFIRE.get(), () -> ModParticles.ORANGE_LAVA.get()));
        campfireMap.put(Items.WHITE_DYE, new CampfireData(() -> ModBlocks.WHITE_CAMPFIRE.get(), () -> ModParticles.WHITE_LAVA.get()));
    }

    @SubscribeEvent
    public static void replaceCampfire(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        ItemStack itemstack = player.getItemInHand(InteractionHand.MAIN_HAND);
        Level level = player.getCommandSenderWorld();
        Item item = itemstack.getItem();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);

        if (state.getBlock() instanceof CampfireBlock) {
            Direction direction = state.getValue(FACING);
            Boolean isLit = state.getValue(LIT);

            Random rand = new Random();
            double d0 = rand.nextGaussian() * 0.02D;
            double d1 = rand.nextGaussian() * 0.02D;
            double d2 = rand.nextGaussian() * 0.02D;

            if (!level.isClientSide) {
                CampfireData campfireData = campfireMap.get(item);
                if (campfireData != null) {
                    level.setBlockAndUpdate(pos, campfireData.blockSupplier.get().defaultBlockState().setValue(FACING, direction).setValue(LIT, isLit));
                    ((ServerLevel) player.getCommandSenderWorld()).sendParticles(campfireData.particleSupplier.get(), pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 50, d1, d2, d0, 0.1);
                    if (!event.getEntity().getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }
                }
            }
        }
    }

    public record CampfireData(Supplier<Block> blockSupplier, Supplier<ParticleOptions> particleSupplier) {
    }

}
