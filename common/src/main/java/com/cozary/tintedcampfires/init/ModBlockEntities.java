package com.cozary.tintedcampfires.init;

import com.cozary.tintedcampfires.TintedCampfires;
import com.cozary.tintedcampfires.campfire.colorsBlockEntity.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Set;


public class ModBlockEntities {

    public static final RegistrationProvider<BlockEntityType<?>> TILE_ENTITIES = RegistrationProvider.get(Registries.BLOCK_ENTITY_TYPE, TintedCampfires.MOD_ID);

    public static void loadClass() {
    }    public static final RegistryObject<BlockEntityType<BlackCampfireBlockEntity>> BLACK_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "black_campfire_tile", () -> new BlockEntityType<>(BlackCampfireBlockEntity::new, Set.of(ModBlocks.BLACK_CAMPFIRE.get())));

    public static final RegistryObject<BlockEntityType<BlueCampfireBlockEntity>> BLUE_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "blue_campfire_tile", () -> new BlockEntityType<>(BlueCampfireBlockEntity::new, Set.of(ModBlocks.BLUE_CAMPFIRE.get())));

    public static final RegistryObject<BlockEntityType<BrownCampfireBlockEntity>> BROWN_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "brown_campfire_tile", () -> new BlockEntityType<>(BrownCampfireBlockEntity::new, Set.of(ModBlocks.BROWN_CAMPFIRE.get())));

    public static final RegistryObject<BlockEntityType<CyanCampfireBlockEntity>> CYAN_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "cyan_campfire_tile", () -> new BlockEntityType<>(CyanCampfireBlockEntity::new, Set.of(ModBlocks.CYAN_CAMPFIRE.get())));

    public static final RegistryObject<BlockEntityType<GrayCampfireBlockEntity>> GRAY_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "gray_campfire_tile", () -> new BlockEntityType<>(GrayCampfireBlockEntity::new, Set.of(ModBlocks.GRAY_CAMPFIRE.get())));

    public static final RegistryObject<BlockEntityType<GreenCampfireBlockEntity>> GREEN_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "green_campfire_tile", () -> new BlockEntityType<>(GreenCampfireBlockEntity::new, Set.of(ModBlocks.GREEN_CAMPFIRE.get())));

    public static final RegistryObject<BlockEntityType<LightBlueCampfireBlockEntity>> LIGHT_BLUE_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "light_blue_campfire_tile", () -> new BlockEntityType<>(LightBlueCampfireBlockEntity::new, Set.of(ModBlocks.LIGHT_BLUE_CAMPFIRE.get())));

    public static final RegistryObject<BlockEntityType<LightGrayCampfireBlockEntity>> LIGHT_GRAY_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "light_gray_campfire_tile", () -> new BlockEntityType<>(LightGrayCampfireBlockEntity::new, Set.of(ModBlocks.LIGHT_GRAY_CAMPFIRE.get())));

    public static final RegistryObject<BlockEntityType<LimeCampfireBlockEntity>> LIME_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "lime_campfire_tile", () -> new BlockEntityType<>(LimeCampfireBlockEntity::new, Set.of(ModBlocks.LIME_CAMPFIRE.get())));

    public static final RegistryObject<BlockEntityType<MagentaCampfireBlockEntity>> MAGENTA_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "magenta_campfire_tile", () -> new BlockEntityType<>(MagentaCampfireBlockEntity::new, Set.of(ModBlocks.MAGENTA_CAMPFIRE.get())));

    public static final RegistryObject<BlockEntityType<OrangeCampfireBlockEntity>> ORANGE_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "orange_campfire_tile", () -> new BlockEntityType<>(OrangeCampfireBlockEntity::new, Set.of(ModBlocks.ORANGE_CAMPFIRE.get())));

    public static final RegistryObject<BlockEntityType<PinkCampfireBlockEntity>> PINK_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "pink_campfire_tile", () -> new BlockEntityType<>(PinkCampfireBlockEntity::new, Set.of(ModBlocks.PINK_CAMPFIRE.get())));

    public static final RegistryObject<BlockEntityType<PurpleCampfireBlockEntity>> PURPLE_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "purple_campfire_tile", () -> new BlockEntityType<>(PurpleCampfireBlockEntity::new, Set.of(ModBlocks.PURPLE_CAMPFIRE.get())));

    public static final RegistryObject<BlockEntityType<RedCampfireBlockEntity>> RED_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "red_campfire_tile", () -> new BlockEntityType<>(RedCampfireBlockEntity::new, Set.of(ModBlocks.RED_CAMPFIRE.get())));

    public static final RegistryObject<BlockEntityType<WhiteCampfireBlockEntity>> WHITE_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "white_campfire_tile", () -> new BlockEntityType<>(WhiteCampfireBlockEntity::new, Set.of(ModBlocks.WHITE_CAMPFIRE.get())));

    public static final RegistryObject<BlockEntityType<YellowCampfireBlockEntity>> YELLOW_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "yellow_campfire_tile", () -> new BlockEntityType<>(YellowCampfireBlockEntity::new, Set.of(ModBlocks.YELLOW_CAMPFIRE.get())));


}
