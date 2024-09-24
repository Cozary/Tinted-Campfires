package com.cozary.tintedcampfires.client;

import com.cozary.tintedcampfires.TintedCampfires;
import com.cozary.tintedcampfires.campfire.colorsRenderer.*;
import com.cozary.tintedcampfires.init.ModBlockEntities;
import com.cozary.tintedcampfires.init.particles.ColorCampfireParticle;
import com.cozary.tintedcampfires.init.particles.ModParticles;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(modid = TintedCampfires.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientRegister {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.BLACK_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.BLUE_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.BROWN_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.CYAN_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.GRAY_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.GREEN_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.LIGHT_BLUE_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.LIGHT_GRAY_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.LIME_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.MAGENTA_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.ORANGE_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.PINK_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.PURPLE_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.RED_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.WHITE_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.YELLOW_LAVA.get(), ColorCampfireParticle.Factory::new);
    }

    @SubscribeEvent
    public static void registerRenders(EntityRenderersEvent.RegisterRenderers event) {
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
}
