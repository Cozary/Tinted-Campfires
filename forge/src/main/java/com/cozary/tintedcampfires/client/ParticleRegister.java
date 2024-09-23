package com.cozary.tintedcampfires.client;

import com.cozary.tintedcampfires.TintedCampfires;
import com.cozary.tintedcampfires.init.particles.ColorCampfireParticle;
import com.cozary.tintedcampfires.init.particles.ModParticles;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TintedCampfires.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticleRegister {

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
}
