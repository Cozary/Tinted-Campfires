package com.cozary.tintedcampfires.client;

import com.cozary.tintedcampfires.campfire.TintedCampfireBlockEntityRenderer;
import com.cozary.tintedcampfires.init.ModBlockEntities;
import com.cozary.tintedcampfires.init.particles.ColorCampfireParticle;
import com.cozary.tintedcampfires.init.particles.ColorCampfireSmokeParticle;
import com.cozary.tintedcampfires.init.particles.ModParticles;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;

public class ClientRegister {

    public static void registerFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.TINTED_LAVA.get(), ColorCampfireParticle.Factory::new);

        event.registerSpriteSet(ModParticles.TINTED_COSY_SMOKE.get(), ColorCampfireSmokeParticle.CosyProvider::new);
        event.registerSpriteSet(ModParticles.TINTED_SIGNAL_SMOKE.get(), ColorCampfireSmokeParticle.SignalProvider::new);
    }

    public static void registerRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.TINTED_CAMPFIRE.get(), TintedCampfireBlockEntityRenderer::new);
    }
}
