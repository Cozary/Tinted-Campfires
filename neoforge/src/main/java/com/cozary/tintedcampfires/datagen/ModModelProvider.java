package com.cozary.tintedcampfires.datagen;

import com.cozary.tintedcampfires.TintedCampfires;
import com.cozary.tintedcampfires.init.ModBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.*;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.Optional;

import static net.minecraft.client.data.models.BlockModelGenerators.*;

public class ModModelProvider extends ModelProvider {

    private static final ModelTemplate TWO_LAYER_ITEM = new ModelTemplate(
            Optional.of(Identifier.parse("item/generated")),
            Optional.empty(),
            TextureSlot.LAYER0,
            TextureSlot.LAYER1
    );

    public ModModelProvider(PackOutput output) {
        super(output, TintedCampfires.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {

        createCampfires(blockModels, itemModels, ModBlocks.BLACK_CAMPFIRE.get());
        createCampfires(blockModels, itemModels, ModBlocks.BLUE_CAMPFIRE.get());
        createCampfires(blockModels, itemModels, ModBlocks.BROWN_CAMPFIRE.get());
        createCampfires(blockModels, itemModels, ModBlocks.GREEN_CAMPFIRE.get());
        createCampfires(blockModels, itemModels, ModBlocks.RED_CAMPFIRE.get());
        createCampfires(blockModels, itemModels, ModBlocks.WHITE_CAMPFIRE.get());
        createCampfires(blockModels, itemModels, ModBlocks.YELLOW_CAMPFIRE.get());
        createCampfires(blockModels, itemModels, ModBlocks.LIGHT_BLUE_CAMPFIRE.get());
        createCampfires(blockModels, itemModels, ModBlocks.LIGHT_GRAY_CAMPFIRE.get());
        createCampfires(blockModels, itemModels, ModBlocks.LIME_CAMPFIRE.get());
        createCampfires(blockModels, itemModels, ModBlocks.MAGENTA_CAMPFIRE.get());
        createCampfires(blockModels, itemModels, ModBlocks.ORANGE_CAMPFIRE.get());
        createCampfires(blockModels, itemModels, ModBlocks.PINK_CAMPFIRE.get());
        createCampfires(blockModels, itemModels, ModBlocks.CYAN_CAMPFIRE.get());
        createCampfires(blockModels, itemModels, ModBlocks.GRAY_CAMPFIRE.get());
        createCampfires(blockModels, itemModels, ModBlocks.PURPLE_CAMPFIRE.get());

    }

    public void createCampfires(BlockModelGenerators blockModels, ItemModelGenerators itemModels, Block... campfireBlocks) {
        MultiVariant multivariantOff = plainVariant(ModelLocationUtils.decorateBlockModelLocation("campfire_off"));

        for (Block block : campfireBlocks) {
            MultiVariant multivariantOn = plainVariant(ModelTemplates.CAMPFIRE.create(block, TextureMapping.campfire(block), blockModels.modelOutput));

            Identifier itemModelLoc = ModelLocationUtils.getModelLocation(block.asItem());
            Identifier overlayTexture = Identifier.fromNamespaceAndPath("tintedcampfires", "item/campfire_item_overlay");

            TextureMapping itemTextures = new TextureMapping()
                    .put(TextureSlot.LAYER0, itemModelLoc)
                    .put(TextureSlot.LAYER1, overlayTexture);

            TWO_LAYER_ITEM.create(itemModelLoc, itemTextures, itemModels.modelOutput);
            itemModels.itemModelOutput.accept(block.asItem(), ItemModelUtils.plainModel(itemModelLoc));

            blockModels.blockStateOutput.accept(
                    MultiVariantGenerator.dispatch(block)
                            .with(createBooleanModelDispatch(BlockStateProperties.LIT, multivariantOn, multivariantOff))
                            .with(ROTATION_HORIZONTAL_FACING_ALT)
            );
        }
    }
}
