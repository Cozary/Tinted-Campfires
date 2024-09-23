package com.cozary.tintedcampfires.init;

import com.cozary.tintedcampfires.TintedCampfires;
import com.cozary.tintedcampfires.campfire.colorsBlockEntity.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;


public class ModBlockEntities {

    public static final RegistrationProvider<BlockEntityType<?>> TILE_ENTITIES = RegistrationProvider.get(Registries.BLOCK_ENTITY_TYPE, TintedCampfires.MOD_ID);

    public static void loadClass() {
    }

    public static final RegistryObject<BlockEntityType<BlackCampfireBlockEntity>> BLACK_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "black_campfire_tile", () -> BlockEntityType.Builder.of(BlackCampfireBlockEntity::new, ModBlocks.BLACK_CAMPFIRE.get()).build(null));

    public static final RegistryObject<BlockEntityType<BlueCampfireBlockEntity>> BLUE_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "blue_campfire_tile", () -> BlockEntityType.Builder.of(BlueCampfireBlockEntity::new, ModBlocks.BLUE_CAMPFIRE.get()).build(null));

    public static final RegistryObject<BlockEntityType<BrownCampfireBlockEntity>> BROWN_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "brown_campfire_tile", () -> BlockEntityType.Builder.of(BrownCampfireBlockEntity::new, ModBlocks.BROWN_CAMPFIRE.get()).build(null));

    public static final RegistryObject<BlockEntityType<CyanCampfireBlockEntity>> CYAN_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "cyan_campfire_tile", () -> BlockEntityType.Builder.of(CyanCampfireBlockEntity::new, ModBlocks.CYAN_CAMPFIRE.get()).build(null));

    public static final RegistryObject<BlockEntityType<GrayCampfireBlockEntity>> GRAY_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "gray_campfire_tile", () -> BlockEntityType.Builder.of(GrayCampfireBlockEntity::new, ModBlocks.GRAY_CAMPFIRE.get()).build(null));

    public static final RegistryObject<BlockEntityType<GreenCampfireBlockEntity>> GREEN_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "green_campfire_tile", () -> BlockEntityType.Builder.of(GreenCampfireBlockEntity::new, ModBlocks.GREEN_CAMPFIRE.get()).build(null));

    public static final RegistryObject<BlockEntityType<LightBlueCampfireBlockEntity>> LIGHT_BLUE_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "light_blue_campfire_tile", () -> BlockEntityType.Builder.of(LightBlueCampfireBlockEntity::new, ModBlocks.LIGHT_BLUE_CAMPFIRE.get()).build(null));

    public static final RegistryObject<BlockEntityType<LightGrayCampfireBlockEntity>> LIGHT_GRAY_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "light_gray_campfire_tile", () -> BlockEntityType.Builder.of(LightGrayCampfireBlockEntity::new, ModBlocks.LIGHT_GRAY_CAMPFIRE.get()).build(null));

    public static final RegistryObject<BlockEntityType<LimeCampfireBlockEntity>> LIME_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "lime_campfire_tile", () -> BlockEntityType.Builder.of(LimeCampfireBlockEntity::new, ModBlocks.LIME_CAMPFIRE.get()).build(null));

    public static final RegistryObject<BlockEntityType<MagentaCampfireBlockEntity>> MAGENTA_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "magenta_campfire_tile", () -> BlockEntityType.Builder.of(MagentaCampfireBlockEntity::new, ModBlocks.MAGENTA_CAMPFIRE.get()).build(null));

    public static final RegistryObject<BlockEntityType<OrangeCampfireBlockEntity>> ORANGE_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "orange_campfire_tile", () -> BlockEntityType.Builder.of(OrangeCampfireBlockEntity::new, ModBlocks.ORANGE_CAMPFIRE.get()).build(null));

    public static final RegistryObject<BlockEntityType<PinkCampfireBlockEntity>> PINK_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "pink_campfire_tile", () -> BlockEntityType.Builder.of(PinkCampfireBlockEntity::new, ModBlocks.PINK_CAMPFIRE.get()).build(null));

    public static final RegistryObject<BlockEntityType<PurpleCampfireBlockEntity>> PURPLE_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "purple_campfire_tile", () -> BlockEntityType.Builder.of(PurpleCampfireBlockEntity::new, ModBlocks.PURPLE_CAMPFIRE.get()).build(null));

    public static final RegistryObject<BlockEntityType<RedCampfireBlockEntity>> RED_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "red_campfire_tile", () -> BlockEntityType.Builder.of(RedCampfireBlockEntity::new, ModBlocks.RED_CAMPFIRE.get()).build(null));

    public static final RegistryObject<BlockEntityType<WhiteCampfireBlockEntity>> WHITE_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "white_campfire_tile", () -> BlockEntityType.Builder.of(WhiteCampfireBlockEntity::new, ModBlocks.WHITE_CAMPFIRE.get()).build(null));

    public static final RegistryObject<BlockEntityType<YellowCampfireBlockEntity>> YELLOW_CAMPFIRE_TILE = TILE_ENTITIES.register(
            "yellow_campfire_tile", () -> BlockEntityType.Builder.of(YellowCampfireBlockEntity::new, ModBlocks.YELLOW_CAMPFIRE.get()).build(null));


}
