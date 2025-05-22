package com.cozary.tintedcampfires;

import com.cozary.tintedcampfires.campfire.colorsRenderer.*;
import com.cozary.tintedcampfires.dispenser.CampfireDispenseBehavior;
import com.cozary.tintedcampfires.dispenser.SetColorDispenseBehavior;
import com.cozary.tintedcampfires.event.EventHandler;
import com.cozary.tintedcampfires.init.ModBlockEntities;
import com.cozary.tintedcampfires.init.ModTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TintedCampfires.MOD_ID)
@Mod.EventBusSubscriber(modid = TintedCampfires.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TintedCampfiresForge {

    public TintedCampfiresForge() {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        TintedCampfires.init();
        ModTabs.CREATIVE_MODE_TABS.register(eventBus);

        eventBus.addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            DispenserBlock.registerBehavior(Items.FLINT_AND_STEEL, new CampfireDispenseBehavior());
            DispenserBlock.registerBehavior(Items.BLACK_DYE, new SetColorDispenseBehavior());
            DispenserBlock.registerBehavior(Items.RED_DYE, new SetColorDispenseBehavior());
            DispenserBlock.registerBehavior(Items.GREEN_DYE, new SetColorDispenseBehavior());
            DispenserBlock.registerBehavior(Items.BROWN_DYE, new SetColorDispenseBehavior());
            DispenserBlock.registerBehavior(Items.BLUE_DYE, new SetColorDispenseBehavior());
            DispenserBlock.registerBehavior(Items.PURPLE_DYE, new SetColorDispenseBehavior());
            DispenserBlock.registerBehavior(Items.CYAN_DYE, new SetColorDispenseBehavior());
            DispenserBlock.registerBehavior(Items.LIGHT_GRAY_DYE, new SetColorDispenseBehavior());
            DispenserBlock.registerBehavior(Items.GRAY_DYE, new SetColorDispenseBehavior());
            DispenserBlock.registerBehavior(Items.PINK_DYE, new SetColorDispenseBehavior());
            DispenserBlock.registerBehavior(Items.LIME_DYE, new SetColorDispenseBehavior());
            DispenserBlock.registerBehavior(Items.YELLOW_DYE, new SetColorDispenseBehavior());
            DispenserBlock.registerBehavior(Items.LIGHT_BLUE_DYE, new SetColorDispenseBehavior());
            DispenserBlock.registerBehavior(Items.MAGENTA_DYE, new SetColorDispenseBehavior());
            DispenserBlock.registerBehavior(Items.ORANGE_DYE, new SetColorDispenseBehavior());
            DispenserBlock.registerBehavior(Items.WHITE_DYE, new SetColorDispenseBehavior());
        });

    }
}