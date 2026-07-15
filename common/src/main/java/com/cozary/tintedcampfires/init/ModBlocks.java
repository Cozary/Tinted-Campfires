package com.cozary.tintedcampfires.init;

import com.cozary.tintedcampfires.TintedCampfires;
import com.cozary.tintedcampfires.campfire.TintedCampfireBlock;
import com.cozary.tintedcampfires.init.particles.ModParticles;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {
    public static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(Registries.BLOCK, TintedCampfires.MOD_ID);

    public static final RegistryObject<Block> BLACK_CAMPFIRE = BLOCKS.register("black_campfire", () -> new TintedCampfireBlock(true, 1, BlockBehaviour.Properties.of(), "black_campfire", ModParticles.BLACK_LAVA::get, DyeColor.BLACK));
    public static final RegistryObject<Block> BLUE_CAMPFIRE = BLOCKS.register("blue_campfire", () -> new TintedCampfireBlock(true, 1, BlockBehaviour.Properties.of(), "blue_campfire", ModParticles.BLUE_LAVA::get, DyeColor.BLUE));
    public static final RegistryObject<Block> BROWN_CAMPFIRE = BLOCKS.register("brown_campfire", () -> new TintedCampfireBlock(true, 1, BlockBehaviour.Properties.of(), "brown_campfire", ModParticles.BROWN_LAVA::get, DyeColor.BROWN));
    public static final RegistryObject<Block> GREEN_CAMPFIRE = BLOCKS.register("green_campfire", () -> new TintedCampfireBlock(true, 1, BlockBehaviour.Properties.of(), "green_campfire", ModParticles.GREEN_LAVA::get, DyeColor.GREEN));
    public static final RegistryObject<Block> RED_CAMPFIRE = BLOCKS.register("red_campfire", () -> new TintedCampfireBlock(true, 1, BlockBehaviour.Properties.of(), "red_campfire", ModParticles.RED_LAVA::get, DyeColor.RED));
    public static final RegistryObject<Block> WHITE_CAMPFIRE = BLOCKS.register("white_campfire", () -> new TintedCampfireBlock(true, 1, BlockBehaviour.Properties.of(), "white_campfire", ModParticles.WHITE_LAVA::get, DyeColor.WHITE));
    public static final RegistryObject<Block> YELLOW_CAMPFIRE = BLOCKS.register("yellow_campfire", () -> new TintedCampfireBlock(true, 1, BlockBehaviour.Properties.of(), "yellow_campfire", ModParticles.YELLOW_LAVA::get, DyeColor.YELLOW));
    public static final RegistryObject<Block> LIGHT_BLUE_CAMPFIRE = BLOCKS.register("light_blue_campfire", () -> new TintedCampfireBlock(true, 1, BlockBehaviour.Properties.of(), "light_blue_campfire", ModParticles.LIGHT_BLUE_LAVA::get, DyeColor.LIGHT_BLUE));
    public static final RegistryObject<Block> LIGHT_GRAY_CAMPFIRE = BLOCKS.register("light_gray_campfire", () -> new TintedCampfireBlock(true, 1, BlockBehaviour.Properties.of(), "light_gray_campfire", ModParticles.LIGHT_GRAY_LAVA::get, DyeColor.LIGHT_GRAY));
    public static final RegistryObject<Block> LIME_CAMPFIRE = BLOCKS.register("lime_campfire", () -> new TintedCampfireBlock(true, 1, BlockBehaviour.Properties.of(), "lime_campfire", ModParticles.LIME_LAVA::get, DyeColor.LIME));
    public static final RegistryObject<Block> MAGENTA_CAMPFIRE = BLOCKS.register("magenta_campfire", () -> new TintedCampfireBlock(true, 1, BlockBehaviour.Properties.of(), "magenta_campfire", ModParticles.MAGENTA_LAVA::get, DyeColor.MAGENTA));
    public static final RegistryObject<Block> ORANGE_CAMPFIRE = BLOCKS.register("orange_campfire", () -> new TintedCampfireBlock(true, 1, BlockBehaviour.Properties.of(), "orange_campfire", ModParticles.ORANGE_LAVA::get, DyeColor.ORANGE));
    public static final RegistryObject<Block> PINK_CAMPFIRE = BLOCKS.register("pink_campfire", () -> new TintedCampfireBlock(true, 1, BlockBehaviour.Properties.of(), "pink_campfire", ModParticles.PINK_LAVA::get, DyeColor.PINK));
    public static final RegistryObject<Block> CYAN_CAMPFIRE = BLOCKS.register("cyan_campfire", () -> new TintedCampfireBlock(true, 1, BlockBehaviour.Properties.of(), "cyan_campfire", ModParticles.CYAN_LAVA::get, DyeColor.CYAN));
    public static final RegistryObject<Block> GRAY_CAMPFIRE = BLOCKS.register("gray_campfire", () -> new TintedCampfireBlock(true, 1, BlockBehaviour.Properties.of(), "gray_campfire", ModParticles.GRAY_LAVA::get, DyeColor.GRAY));
    public static final RegistryObject<Block> PURPLE_CAMPFIRE = BLOCKS.register("purple_campfire", () -> new TintedCampfireBlock(true, 1, BlockBehaviour.Properties.of(), "purple_campfire", ModParticles.PURPLE_LAVA::get, DyeColor.PURPLE));

    public static void loadClass() {
    }
}
