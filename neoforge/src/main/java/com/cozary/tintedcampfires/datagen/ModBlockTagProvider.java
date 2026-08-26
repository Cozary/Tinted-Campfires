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
                                                ModBlocks.BLACK_CAMPFIRE.getResourceKey(),
                                                ModBlocks.BLUE_CAMPFIRE.getResourceKey(),
                                                ModBlocks.BROWN_CAMPFIRE.getResourceKey(),
                                                ModBlocks.GREEN_CAMPFIRE.getResourceKey(),
                                                ModBlocks.RED_CAMPFIRE.getResourceKey(),
                                                ModBlocks.WHITE_CAMPFIRE.getResourceKey(),
                                                ModBlocks.YELLOW_CAMPFIRE.getResourceKey(),
                                                ModBlocks.LIGHT_BLUE_CAMPFIRE.getResourceKey(),
                                                ModBlocks.LIGHT_GRAY_CAMPFIRE.getResourceKey(),
                                                ModBlocks.LIME_CAMPFIRE.getResourceKey(),
                                                ModBlocks.MAGENTA_CAMPFIRE.getResourceKey(),
                                                ModBlocks.ORANGE_CAMPFIRE.getResourceKey(),
                                                ModBlocks.PINK_CAMPFIRE.getResourceKey(),
                                                ModBlocks.CYAN_CAMPFIRE.getResourceKey(),
                                                ModBlocks.GRAY_CAMPFIRE.getResourceKey(),
                                                ModBlocks.PURPLE_CAMPFIRE.getResourceKey());

                tag(BlockTags.CAMPFIRES)
                                .add(
                                                ModBlocks.BLACK_CAMPFIRE.getResourceKey(),
                                                ModBlocks.BLUE_CAMPFIRE.getResourceKey(),
                                                ModBlocks.BROWN_CAMPFIRE.getResourceKey(),
                                                ModBlocks.GREEN_CAMPFIRE.getResourceKey(),
                                                ModBlocks.RED_CAMPFIRE.getResourceKey(),
                                                ModBlocks.WHITE_CAMPFIRE.getResourceKey(),
                                                ModBlocks.YELLOW_CAMPFIRE.getResourceKey(),
                                                ModBlocks.LIGHT_BLUE_CAMPFIRE.getResourceKey(),
                                                ModBlocks.LIGHT_GRAY_CAMPFIRE.getResourceKey(),
                                                ModBlocks.LIME_CAMPFIRE.getResourceKey(),
                                                ModBlocks.MAGENTA_CAMPFIRE.getResourceKey(),
                                                ModBlocks.ORANGE_CAMPFIRE.getResourceKey(),
                                                ModBlocks.PINK_CAMPFIRE.getResourceKey(),
                                                ModBlocks.CYAN_CAMPFIRE.getResourceKey(),
                                                ModBlocks.GRAY_CAMPFIRE.getResourceKey(),
                                                ModBlocks.PURPLE_CAMPFIRE.getResourceKey());

        }
}