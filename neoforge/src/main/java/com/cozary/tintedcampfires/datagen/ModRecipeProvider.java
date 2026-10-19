package com.cozary.tintedcampfires.datagen;

import com.cozary.tintedcampfires.init.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;

import java.util.List;
import java.util.function.Supplier;

public class ModRecipeProvider extends RecipeProvider {

    public static final Supplier<List<FloralEntry>> TINTED_CAMPFIRES = () -> List.of(
            new FloralEntry(ModItems.BLACK_CAMPFIRE_ITEM.get(), Items.DYE.black()),
            new FloralEntry(ModItems.BLUE_CAMPFIRE_ITEM.get(), Items.DYE.blue()),
            new FloralEntry(ModItems.BROWN_CAMPFIRE_ITEM.get(), Items.DYE.brown()),
            new FloralEntry(ModItems.GREEN_CAMPFIRE_ITEM.get(), Items.DYE.green()),
            new FloralEntry(ModItems.RED_CAMPFIRE_ITEM.get(), Items.DYE.red()),
            new FloralEntry(ModItems.WHITE_CAMPFIRE_ITEM.get(), Items.DYE.white()),
            new FloralEntry(ModItems.YELLOW_CAMPFIRE_ITEM.get(), Items.DYE.yellow()),
            new FloralEntry(ModItems.LIGHT_BLUE_CAMPFIRE_ITEM.get(), Items.DYE.lightBlue()),
            new FloralEntry(ModItems.LIGHT_GRAY_CAMPFIRE_ITEM.get(), Items.DYE.lightGray()),
            new FloralEntry(ModItems.LIME_CAMPFIRE_ITEM.get(), Items.DYE.lime()),
            new FloralEntry(ModItems.MAGENTA_CAMPFIRE_ITEM.get(), Items.DYE.magenta()),
            new FloralEntry(ModItems.ORANGE_CAMPFIRE_ITEM.get(), Items.DYE.orange()),
            new FloralEntry(ModItems.PINK_CAMPFIRE_ITEM.get(), Items.DYE.pink()),
            new FloralEntry(ModItems.CYAN_CAMPFIRE_ITEM.get(), Items.DYE.cyan()),
            new FloralEntry(ModItems.GRAY_CAMPFIRE_ITEM.get(), Items.DYE.gray()),
            new FloralEntry(ModItems.PURPLE_CAMPFIRE_ITEM.get(), Items.DYE.purple()));

    public ModRecipeProvider(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
    }

    @Override
    protected void buildRecipes() {
        for (FloralEntry entry : TINTED_CAMPFIRES.get()) {
            shapeless(RecipeCategory.DECORATIONS, entry.item)
                    .requires(entry.dye)
                    .requires(Items.CAMPFIRE)
                    .unlockedBy("has_campfire", has(entry.dye))
                    .save(this.output);
        }
    }

    public record FloralEntry(Item item, Item dye) {
    }
}

