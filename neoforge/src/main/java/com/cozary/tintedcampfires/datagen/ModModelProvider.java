package com.cozary.tintedcampfires.datagen;

import com.cozary.tintedcampfires.TintedCampfires;
import com.cozary.tintedcampfires.init.ModBlocks;
import com.cozary.tintedcampfires.init.ModItems;
import com.mojang.math.Quadrant;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.client.renderer.block.model.VariantMutator;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplateBuilder;

import static net.minecraft.client.data.models.BlockModelGenerators.plainVariant;

public class ModModelProvider extends ModelProvider {

    public ModModelProvider(PackOutput output) {
        super(output, TintedCampfires.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {

        blockModels.createCampfires(ModBlocks.BLACK_CAMPFIRE.get());
        blockModels.createCampfires(ModBlocks.BLUE_CAMPFIRE.get());
        blockModels.createCampfires(ModBlocks.BROWN_CAMPFIRE.get());
        blockModels.createCampfires(ModBlocks.GREEN_CAMPFIRE.get());
        blockModels.createCampfires(ModBlocks.RED_CAMPFIRE.get());
        blockModels.createCampfires(ModBlocks.WHITE_CAMPFIRE.get());
        blockModels.createCampfires(ModBlocks.YELLOW_CAMPFIRE.get());
        blockModels.createCampfires(ModBlocks.LIGHT_BLUE_CAMPFIRE.get());
        blockModels.createCampfires(ModBlocks.LIGHT_GRAY_CAMPFIRE.get());
        blockModels.createCampfires(ModBlocks.LIME_CAMPFIRE.get());
        blockModels.createCampfires(ModBlocks.MAGENTA_CAMPFIRE.get());
        blockModels.createCampfires(ModBlocks.ORANGE_CAMPFIRE.get());
        blockModels.createCampfires(ModBlocks.PINK_CAMPFIRE.get());
        blockModels.createCampfires(ModBlocks.CYAN_CAMPFIRE.get());
        blockModels.createCampfires(ModBlocks.GRAY_CAMPFIRE.get());
        blockModels.createCampfires(ModBlocks.PURPLE_CAMPFIRE.get());

    }
}
