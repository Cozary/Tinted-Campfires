package com.cozary.tintedcampfires.init;


import com.cozary.tintedcampfires.TintedCampfires;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TintedCampfires.MOD_ID);

    public static RegistryObject<CreativeModeTab> TINTED_CAMPFIRES_TAB = CREATIVE_MODE_TABS.register("tintedcampfires", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.PINK_CAMPFIRE_ITEM.get()))
            .title(Component.translatable("itemGroup.tintedcampfires"))
            .displayItems((parameters, output) -> ModItems.CREATIVE_TAB_ITEMS.forEach((item) -> output.accept(item.get())))
            .build());
}


