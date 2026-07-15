package com.cozary.tintedcampfires.client;

import com.cozary.tintedcampfires.TintedCampfires;
import com.cozary.tintedcampfires.init.ModBlockEntities;
import com.cozary.tintedcampfires.init.particles.ColorCampfireParticle;
import com.cozary.tintedcampfires.init.particles.ColorCampfireSmokeParticle;
import com.cozary.tintedcampfires.init.particles.ModParticles;
import com.cozary.tintedcampfires.campfire.TintedCampfireBlockEntityRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(value = Dist.CLIENT, modid = TintedCampfires.MOD_ID)
public class ClientRegister {

    @SubscribeEvent
    public static void registerFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.TINTED_LAVA.get(), ColorCampfireParticle.Factory::new);

        event.registerSpriteSet(ModParticles.TINTED_COSY_SMOKE.get(), ColorCampfireSmokeParticle.CosyProvider::new);
        event.registerSpriteSet(ModParticles.TINTED_SIGNAL_SMOKE.get(), ColorCampfireSmokeParticle.SignalProvider::new);
    }

    @SubscribeEvent
    public static void registerRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.TINTED_CAMPFIRE.get(), TintedCampfireBlockEntityRenderer::new);
    }
}
