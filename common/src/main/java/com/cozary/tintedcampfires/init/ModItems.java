package com.cozary.tintedcampfires.init;

import com.cozary.tintedcampfires.TintedCampfires;
import com.cozary.tintedcampfires.block.BlockItemBase;
import com.google.common.collect.Sets;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class ModItems {

    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registries.ITEM, TintedCampfires.MOD_ID);

    public static LinkedHashSet<RegistryObject<Item>> CREATIVE_TAB_ITEMS = Sets.newLinkedHashSet();

    public static final RegistryObject<Item> BLACK_CAMPFIRE_ITEM = registerWithTab("black_campfire", () -> new BlockItemBase(ModBlocks.BLACK_CAMPFIRE.get()));
    public static final RegistryObject<Item> BLUE_CAMPFIRE_ITEM = registerWithTab("blue_campfire", () -> new BlockItemBase(ModBlocks.BLUE_CAMPFIRE.get()));
    public static final RegistryObject<Item> BROWN_CAMPFIRE_ITEM = registerWithTab("brown_campfire", () -> new BlockItemBase(ModBlocks.BROWN_CAMPFIRE.get()));
    public static final RegistryObject<Item> GREEN_CAMPFIRE_ITEM = registerWithTab("green_campfire", () -> new BlockItemBase(ModBlocks.GREEN_CAMPFIRE.get()));
    public static final RegistryObject<Item> RED_CAMPFIRE_ITEM = registerWithTab("red_campfire", () -> new BlockItemBase(ModBlocks.RED_CAMPFIRE.get()));
    public static final RegistryObject<Item> WHITE_CAMPFIRE_ITEM = registerWithTab("white_campfire", () -> new BlockItemBase(ModBlocks.WHITE_CAMPFIRE.get()));
    public static final RegistryObject<Item> YELLOW_CAMPFIRE_ITEM = registerWithTab("yellow_campfire", () -> new BlockItemBase(ModBlocks.YELLOW_CAMPFIRE.get()));
    public static final RegistryObject<Item> LIGHT_BLUE_CAMPFIRE_ITEM = registerWithTab("light_blue_campfire", () -> new BlockItemBase(ModBlocks.LIGHT_BLUE_CAMPFIRE.get()));
    public static final RegistryObject<Item> LIGHT_GRAY_CAMPFIRE_ITEM = registerWithTab("light_gray_campfire", () -> new BlockItemBase(ModBlocks.LIGHT_GRAY_CAMPFIRE.get()));
    public static final RegistryObject<Item> LIME_CAMPFIRE_ITEM = registerWithTab("lime_campfire", () -> new BlockItemBase(ModBlocks.LIME_CAMPFIRE.get()));
    public static final RegistryObject<Item> MAGENTA_CAMPFIRE_ITEM = registerWithTab("magenta_campfire", () -> new BlockItemBase(ModBlocks.MAGENTA_CAMPFIRE.get()));
    public static final RegistryObject<Item> ORANGE_CAMPFIRE_ITEM = registerWithTab("orange_campfire", () -> new BlockItemBase(ModBlocks.ORANGE_CAMPFIRE.get()));
    public static final RegistryObject<Item> PINK_CAMPFIRE_ITEM = registerWithTab("pink_campfire", () -> new BlockItemBase(ModBlocks.PINK_CAMPFIRE.get()));
    public static final RegistryObject<Item> CYAN_CAMPFIRE_ITEM = registerWithTab("cyan_campfire", () -> new BlockItemBase(ModBlocks.CYAN_CAMPFIRE.get()));
    public static final RegistryObject<Item> GRAY_CAMPFIRE_ITEM = registerWithTab("gray_campfire", () -> new BlockItemBase(ModBlocks.GRAY_CAMPFIRE.get()));
    public static final RegistryObject<Item> PURPLE_CAMPFIRE_ITEM = registerWithTab("purple_campfire", () -> new BlockItemBase(ModBlocks.PURPLE_CAMPFIRE.get()));

    public static RegistryObject<Item> registerWithTab(final String name, final Supplier<? extends Item> supplier) {
        RegistryObject<Item> item = ITEMS.register(name, supplier);
        CREATIVE_TAB_ITEMS.add(item);
        return item;
    }

    public static void loadClass() {
    }
}
