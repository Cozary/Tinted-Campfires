package com.cozary.tintedcampfires.init;

import com.cozary.tintedcampfires.TintedCampfires;
import com.cozary.tintedcampfires.campfire.colors.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;


public class ModBlocks {
    public static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(Registries.BLOCK, TintedCampfires.MOD_ID);

    public static final RegistryObject<Block> BLACK_CAMPFIRE = BLOCKS.register("black_campfire", () -> new BlackCampfire(true, 1, BlockBehaviour.Properties.of(), "black_campfire"));
    public static final RegistryObject<Block> BLUE_CAMPFIRE = BLOCKS.register("blue_campfire", () -> new BlueCampfire(true, 1, BlockBehaviour.Properties.of(), "blue_campfire"));
    public static final RegistryObject<Block> BROWN_CAMPFIRE = BLOCKS.register("brown_campfire", () -> new BrownCampfire(true, 1, BlockBehaviour.Properties.of(), "brown_campfire"));
    public static final RegistryObject<Block> GREEN_CAMPFIRE = BLOCKS.register("green_campfire", () -> new GreenCampfire(true, 1, BlockBehaviour.Properties.of(), "green_campfire"));
    public static final RegistryObject<Block> RED_CAMPFIRE = BLOCKS.register("red_campfire", () -> new RedCampfire(true, 1, BlockBehaviour.Properties.of(), "red_campfire"));
    public static final RegistryObject<Block> WHITE_CAMPFIRE = BLOCKS.register("white_campfire", () -> new WhiteCampfire(true, 1, BlockBehaviour.Properties.of(), "white_campfire"));
    public static final RegistryObject<Block> YELLOW_CAMPFIRE = BLOCKS.register("yellow_campfire", () -> new YellowCampfire(true, 1, BlockBehaviour.Properties.of(), "yellow_campfire"));
    public static final RegistryObject<Block> LIGHT_BLUE_CAMPFIRE = BLOCKS.register("light_blue_campfire", () -> new LightBlueCampfire(true, 1, BlockBehaviour.Properties.of(), "light_blue_campfire"));
    public static final RegistryObject<Block> LIGHT_GRAY_CAMPFIRE = BLOCKS.register("light_gray_campfire", () -> new LightGrayCampfire(true, 1, BlockBehaviour.Properties.of(), "light_gray_campfire"));
    public static final RegistryObject<Block> LIME_CAMPFIRE = BLOCKS.register("lime_campfire", () -> new LimeCampfire(true, 1, BlockBehaviour.Properties.of(), "lime_campfire"));
    public static final RegistryObject<Block> MAGENTA_CAMPFIRE = BLOCKS.register("magenta_campfire", () -> new MagentaCampfire(true, 1, BlockBehaviour.Properties.of(), "magenta_campfire"));
    public static final RegistryObject<Block> ORANGE_CAMPFIRE = BLOCKS.register("orange_campfire", () -> new OrangeCampfire(true, 1, BlockBehaviour.Properties.of(), "orange_campfire"));
    public static final RegistryObject<Block> PINK_CAMPFIRE = BLOCKS.register("pink_campfire", () -> new PinkCampfire(true, 1, BlockBehaviour.Properties.of(), "pink_campfire"));
    public static final RegistryObject<Block> CYAN_CAMPFIRE = BLOCKS.register("cyan_campfire", () -> new CyanCampfire(true, 1, BlockBehaviour.Properties.of(), "cyan_campfire"));
    public static final RegistryObject<Block> GRAY_CAMPFIRE = BLOCKS.register("gray_campfire", () -> new GrayCampfire(true, 1, BlockBehaviour.Properties.of(), "gray_campfire"));
    public static final RegistryObject<Block> PURPLE_CAMPFIRE = BLOCKS.register("purple_campfire", () -> new PurpleCampfire(true, 1, BlockBehaviour.Properties.of(), "purple_campfire"));

    public static void loadClass() {
    }
}
