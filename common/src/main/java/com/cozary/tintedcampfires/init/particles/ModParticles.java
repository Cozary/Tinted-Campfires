package com.cozary.tintedcampfires.init.particles;

import com.cozary.tintedcampfires.TintedCampfires;
import com.cozary.tintedcampfires.init.RegistrationProvider;
import com.cozary.tintedcampfires.init.RegistryObject;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;

public class ModParticles {

    public static final RegistrationProvider<ParticleType<?>> PARTICLES = RegistrationProvider.get(Registries.PARTICLE_TYPE, TintedCampfires.MOD_ID);

    public static final RegistryObject<SimpleParticleType> TINTED_LAVA = PARTICLES.register("tinted_lava", () -> new SimpleParticleType(true) {});

    public static final RegistryObject<SimpleParticleType> TINTED_COSY_SMOKE = PARTICLES.register("tinted_cosy_smoke", () -> new SimpleParticleType(true) {});
    public static final RegistryObject<SimpleParticleType> TINTED_SIGNAL_SMOKE = PARTICLES.register("tinted_signal_smoke", () -> new SimpleParticleType(true) {});

    public static void loadClass() {
    }
}