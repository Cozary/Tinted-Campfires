package com.cozary.tintedcampfires.client;

import com.cozary.tintedcampfires.init.particles.ColorCampfireParticle;
import com.cozary.tintedcampfires.init.particles.ModParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public class ParticleRegister implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(ModParticles.BLACK_LAVA.get(), ColorCampfireParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.BLUE_LAVA.get(), ColorCampfireParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.BROWN_LAVA.get(), ColorCampfireParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.CYAN_LAVA.get(), ColorCampfireParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.GRAY_LAVA.get(), ColorCampfireParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.GREEN_LAVA.get(), ColorCampfireParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.LIGHT_BLUE_LAVA.get(), ColorCampfireParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.LIGHT_GRAY_LAVA.get(), ColorCampfireParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.LIME_LAVA.get(), ColorCampfireParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.MAGENTA_LAVA.get(), ColorCampfireParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.ORANGE_LAVA.get(), ColorCampfireParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.PINK_LAVA.get(), ColorCampfireParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.PURPLE_LAVA.get(), ColorCampfireParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.RED_LAVA.get(), ColorCampfireParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.WHITE_LAVA.get(), ColorCampfireParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.YELLOW_LAVA.get(), ColorCampfireParticle.Factory::new);
    }
}
