package com.cozary.tintedcampfires.datagen;

import com.cozary.tintedcampfires.TintedCampfires;
import com.cozary.tintedcampfires.init.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider, TintedCampfires.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(
                        ModBlocks.BLACK_CAMPFIRE.get(),
                        ModBlocks.BLUE_CAMPFIRE.get(),
                        ModBlocks.BROWN_CAMPFIRE.get(),
                        ModBlocks.GREEN_CAMPFIRE.get(),
                        ModBlocks.RED_CAMPFIRE.get(),
                        ModBlocks.WHITE_CAMPFIRE.get(),
                        ModBlocks.YELLOW_CAMPFIRE.get(),
                        ModBlocks.LIGHT_BLUE_CAMPFIRE.get(),
                        ModBlocks.LIGHT_GRAY_CAMPFIRE.get(),
                        ModBlocks.LIME_CAMPFIRE.get(),
                        ModBlocks.MAGENTA_CAMPFIRE.get(),
                        ModBlocks.ORANGE_CAMPFIRE.get(),
                        ModBlocks.PINK_CAMPFIRE.get(),
                        ModBlocks.CYAN_CAMPFIRE.get(),
                        ModBlocks.GRAY_CAMPFIRE.get(),
                        ModBlocks.PURPLE_CAMPFIRE.get()
                );

        tag(BlockTags.CAMPFIRES)
                .add(
                        ModBlocks.BLACK_CAMPFIRE.get(),
                        ModBlocks.BLUE_CAMPFIRE.get(),
                        ModBlocks.BROWN_CAMPFIRE.get(),
                        ModBlocks.GREEN_CAMPFIRE.get(),
                        ModBlocks.RED_CAMPFIRE.get(),
                        ModBlocks.WHITE_CAMPFIRE.get(),
                        ModBlocks.YELLOW_CAMPFIRE.get(),
                        ModBlocks.LIGHT_BLUE_CAMPFIRE.get(),
                        ModBlocks.LIGHT_GRAY_CAMPFIRE.get(),
                        ModBlocks.LIME_CAMPFIRE.get(),
                        ModBlocks.MAGENTA_CAMPFIRE.get(),
                        ModBlocks.ORANGE_CAMPFIRE.get(),
                        ModBlocks.PINK_CAMPFIRE.get(),
                        ModBlocks.CYAN_CAMPFIRE.get(),
                        ModBlocks.GRAY_CAMPFIRE.get(),
                        ModBlocks.PURPLE_CAMPFIRE.get()
                );

    }
}