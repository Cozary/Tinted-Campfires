package com.cozary.tintedcampfires.client;

import com.cozary.tintedcampfires.init.particles.ColorCampfireParticle;
import com.cozary.tintedcampfires.init.particles.ColorCampfireSmokeParticle;
import com.cozary.tintedcampfires.init.particles.ModParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;

public class ParticleRegister implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ParticleProviderRegistry.getInstance().register(ModParticles.TINTED_LAVA.get(), ColorCampfireParticle.Factory::new);

        ParticleProviderRegistry.getInstance().register(ModParticles.TINTED_COSY_SMOKE.get(), ColorCampfireSmokeParticle.CosyProvider::new);
        ParticleProviderRegistry.getInstance().register(ModParticles.TINTED_SIGNAL_SMOKE.get(), ColorCampfireSmokeParticle.SignalProvider::new);
    }
}
