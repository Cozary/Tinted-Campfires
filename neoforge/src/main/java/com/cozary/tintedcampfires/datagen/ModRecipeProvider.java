package com.cozary.tintedcampfires.datagen;

import com.cozary.tintedcampfires.init.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ModRecipeProvider extends RecipeProvider {

    protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    public static final Supplier<List<FloralEntry>> TINTED_CAMPFIRES = () -> List.of(
            new FloralEntry(ModItems.BLACK_CAMPFIRE_ITEM.get(), Items.BLACK_DYE),
            new FloralEntry(ModItems.BLUE_CAMPFIRE_ITEM.get(),  Items.BLUE_DYE),
            new FloralEntry(ModItems.BROWN_CAMPFIRE_ITEM.get(),  Items.BROWN_DYE),
            new FloralEntry(ModItems.GREEN_CAMPFIRE_ITEM.get(),  Items.GREEN_DYE),
            new FloralEntry(ModItems.RED_CAMPFIRE_ITEM.get(), Items.RED_DYE),
            new FloralEntry(ModItems.WHITE_CAMPFIRE_ITEM.get(), Items.WHITE_DYE),
            new FloralEntry(ModItems.YELLOW_CAMPFIRE_ITEM.get(), Items.YELLOW_DYE),
            new FloralEntry(ModItems.LIGHT_BLUE_CAMPFIRE_ITEM.get(), Items.LIGHT_BLUE_DYE),
            new FloralEntry(ModItems.LIGHT_GRAY_CAMPFIRE_ITEM.get(), Items.LIGHT_GRAY_DYE),
            new FloralEntry(ModItems.LIME_CAMPFIRE_ITEM.get(), Items.LIME_DYE),
            new FloralEntry(ModItems.MAGENTA_CAMPFIRE_ITEM.get(), Items.MAGENTA_DYE),
            new FloralEntry(ModItems.ORANGE_CAMPFIRE_ITEM.get(), Items.ORANGE_DYE),
            new FloralEntry(ModItems.PINK_CAMPFIRE_ITEM.get(), Items.PINK_DYE),
            new FloralEntry(ModItems.CYAN_CAMPFIRE_ITEM.get(), Items.CYAN_DYE),
            new FloralEntry(ModItems.GRAY_CAMPFIRE_ITEM.get(), Items.GRAY_DYE),
            new FloralEntry(ModItems.PURPLE_CAMPFIRE_ITEM.get(), Items.PURPLE_DYE)
   );

    @Override
    protected void buildRecipes() {

        for (FloralEntry entry : TINTED_CAMPFIRES.get()) {
            shapeless(RecipeCategory.DECORATIONS, entry.item)
                    .requires(entry.dye)
                    .requires(Items.CAMPFIRE)
                    .unlockedBy("has_campfire", has(entry.dye))
                    .save(output);
        }

    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
            super(packOutput, provider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new ModRecipeProvider(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "Tinted Campfires Recipes";
        }
    }

    public record FloralEntry(Item item, Item dye) {
    }

}
