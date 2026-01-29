package com.cozary.tintedcampfires.init;

import com.cozary.tintedcampfires.TintedCampfires;
import com.cozary.tintedcampfires.campfire.TintedCampfireBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.stream.Collectors;


public class ModBlockEntities {

    public static final RegistrationProvider<BlockEntityType<?>> TILE_ENTITIES = RegistrationProvider.get(Registries.BLOCK_ENTITY_TYPE, TintedCampfires.MOD_ID);

    public static void loadClass() {
    }

    public static final RegistryObject<BlockEntityType<TintedCampfireBlockEntity>> TINTED_CAMPFIRE = TILE_ENTITIES.register(
            "tinted_campfire", () -> new BlockEntityType<>(TintedCampfireBlockEntity::new,
                    ModBlocks.BLOCKS.getEntries().stream().map(blockRegistryObject -> blockRegistryObject.get()).collect(Collectors.toSet())));

}
