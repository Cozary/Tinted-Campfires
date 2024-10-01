package com.cozary.tintedcampfires;

import com.cozary.tintedcampfires.init.ModBlocks;
import com.cozary.tintedcampfires.init.ModItems;
import com.cozary.tintedcampfires.init.particles.ModParticles;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static net.minecraft.world.level.block.CampfireBlock.FACING;
import static net.minecraft.world.level.block.CampfireBlock.LIT;

public class TintedCampfiresFabric implements ModInitializer {

    private static final ResourceKey<CreativeModeTab> ITEM_GROUP = ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(TintedCampfires.MOD_ID, TintedCampfires.MOD_ID + "_tab"));

    @Override
    public void onInitialize() {

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ITEM_GROUP, FabricItemGroup.builder()
                .title(Component.translatable("itemGroup.tintedcampfires"))
                .icon(() -> new ItemStack(ModBlocks.PINK_CAMPFIRE.get()))
                .displayItems((parameters, output) -> ModItems.CREATIVE_TAB_ITEMS.forEach((item) -> output.accept(item.get())))
                .build()
        );

        TintedCampfires.init();

        rightClickBlock();
    }

    public void rightClickBlock() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            ItemStack itemstack = player.getItemInHand(InteractionHand.MAIN_HAND);
            Level level = player.getCommandSenderWorld();
            Item item = itemstack.getItem();
            BlockPos pos = hitResult.getBlockPos();
            BlockState state = level.getBlockState(pos);

            if (state.getBlock() instanceof CampfireBlock) {
                Direction direction1 = state.getValue(FACING);
                Boolean isLit = state.getValue(LIT);

                Random rand = new Random();
                double d0 = rand.nextGaussian() * 0.02D;
                double d1 = rand.nextGaussian() * 0.02D;
                double d2 = rand.nextGaussian() * 0.02D;

                Map<Item, CampfireData> campfireMap = new HashMap<>();
                campfireMap.put(Items.BLACK_DYE, new CampfireData(ModBlocks.BLACK_CAMPFIRE.get(), ModParticles.BLACK_LAVA.get()));
                campfireMap.put(Items.RED_DYE, new CampfireData(ModBlocks.RED_CAMPFIRE.get(), ModParticles.RED_LAVA.get()));
                campfireMap.put(Items.GREEN_DYE, new CampfireData(ModBlocks.GREEN_CAMPFIRE.get(), ModParticles.GREEN_LAVA.get()));
                campfireMap.put(Items.BROWN_DYE, new CampfireData(ModBlocks.BROWN_CAMPFIRE.get(), ModParticles.BROWN_LAVA.get()));
                campfireMap.put(Items.BLUE_DYE, new CampfireData(ModBlocks.BLUE_CAMPFIRE.get(), ModParticles.BLUE_LAVA.get()));
                campfireMap.put(Items.PURPLE_DYE, new CampfireData(ModBlocks.PURPLE_CAMPFIRE.get(), ModParticles.PURPLE_LAVA.get()));
                campfireMap.put(Items.CYAN_DYE, new CampfireData(ModBlocks.CYAN_CAMPFIRE.get(), ModParticles.CYAN_LAVA.get()));
                campfireMap.put(Items.LIGHT_GRAY_DYE, new CampfireData(ModBlocks.LIGHT_GRAY_CAMPFIRE.get(), ModParticles.LIGHT_GRAY_LAVA.get()));
                campfireMap.put(Items.GRAY_DYE, new CampfireData(ModBlocks.GRAY_CAMPFIRE.get(), ModParticles.GRAY_LAVA.get()));
                campfireMap.put(Items.PINK_DYE, new CampfireData(ModBlocks.PINK_CAMPFIRE.get(), ModParticles.PINK_LAVA.get()));
                campfireMap.put(Items.LIME_DYE, new CampfireData(ModBlocks.LIME_CAMPFIRE.get(), ModParticles.LIME_LAVA.get()));
                campfireMap.put(Items.YELLOW_DYE, new CampfireData(ModBlocks.YELLOW_CAMPFIRE.get(), ModParticles.YELLOW_LAVA.get()));
                campfireMap.put(Items.LIGHT_BLUE_DYE, new CampfireData(ModBlocks.LIGHT_BLUE_CAMPFIRE.get(), ModParticles.LIGHT_BLUE_LAVA.get()));
                campfireMap.put(Items.MAGENTA_DYE, new CampfireData(ModBlocks.MAGENTA_CAMPFIRE.get(), ModParticles.MAGENTA_LAVA.get()));
                campfireMap.put(Items.ORANGE_DYE, new CampfireData(ModBlocks.ORANGE_CAMPFIRE.get(), ModParticles.ORANGE_LAVA.get()));
                campfireMap.put(Items.WHITE_DYE, new CampfireData(ModBlocks.WHITE_CAMPFIRE.get(), ModParticles.WHITE_LAVA.get()));

                if (!level.isClientSide && campfireMap.containsKey(item)) {
                    CampfireData campfireData = campfireMap.get(item);
                    Block campfireBlock = campfireData.block();
                    SimpleParticleType particle = campfireData.particle();

                    level.setBlockAndUpdate(pos, campfireBlock.defaultBlockState().setValue(CampfireBlock.FACING, direction1).setValue(CampfireBlock.LIT, isLit));

                    ((ServerLevel) player.getCommandSenderWorld()).sendParticles(particle, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 50, d1, d2, d0, 0.1);

                    if (!player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }
                }
            }
            return InteractionResult.PASS;
        });
    }

    public record CampfireData(Block block, SimpleParticleType particle) {
    }
}
