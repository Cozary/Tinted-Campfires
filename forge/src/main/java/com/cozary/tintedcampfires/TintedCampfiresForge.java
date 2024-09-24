package com.cozary.tintedcampfires;

import com.cozary.tintedcampfires.campfire.colorsRenderer.*;
import com.cozary.tintedcampfires.dispenser.CampfireDispenseBehavior;
import com.cozary.tintedcampfires.dispenser.SetColorDispenseBehavior;
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

        eventBus.addListener(this::registerRenders);
        eventBus.addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(new EventHandler());
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.BLACK_CAMPFIRE_TILE.get(), BlackCampfireBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BLUE_CAMPFIRE_TILE.get(), BlueCampfireBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BROWN_CAMPFIRE_TILE.get(), BrownCampfireBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.CYAN_CAMPFIRE_TILE.get(), CyanCampfireBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.GRAY_CAMPFIRE_TILE.get(), GrayCampfireBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.GREEN_CAMPFIRE_TILE.get(), GreenCampfireBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.LIGHT_BLUE_CAMPFIRE_TILE.get(), LightBlueCampfireBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.LIGHT_GRAY_CAMPFIRE_TILE.get(), LightGrayCampfireBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.LIME_CAMPFIRE_TILE.get(), LimeCampfireBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.MAGENTA_CAMPFIRE_TILE.get(), MagentaCampfireBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.ORANGE_CAMPFIRE_TILE.get(), OrangeCampfireBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.PINK_CAMPFIRE_TILE.get(), PinkCampfireBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.PURPLE_CAMPFIRE_TILE.get(), PurpleCampfireBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.RED_CAMPFIRE_TILE.get(), RedCampfireBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.WHITE_CAMPFIRE_TILE.get(), WhiteCampfireBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.YELLOW_CAMPFIRE_TILE.get(), YellowCampfireBlockEntityRenderer::new);
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