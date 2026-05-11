package com.cozary.tintedcampfires.client;

import com.cozary.tintedcampfires.TintedCampfires;
import com.cozary.tintedcampfires.campfire.TintedCampfireBlockEntityRenderer;
import com.cozary.tintedcampfires.init.ModBlockEntities;
import com.cozary.tintedcampfires.init.particles.ColorCampfireParticle;
import com.cozary.tintedcampfires.init.particles.ModParticles;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(value = Dist.CLIENT, modid = TintedCampfires.MOD_ID)
public class ClientRegister {

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
        event.registerBlockEntityRenderer(ModBlockEntities.TINTED_CAMPFIRE.get(), TintedCampfireBlockEntityRenderer::new);
    }
}
