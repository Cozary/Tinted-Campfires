package com.cozary.tintedcampfires.init.particles;

import com.cozary.tintedcampfires.TintedCampfires;
import com.cozary.tintedcampfires.init.RegistrationProvider;
import com.cozary.tintedcampfires.init.RegistryObject;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;

public class ModParticles {

    public static final RegistrationProvider<ParticleType<?>> PARTICLES = RegistrationProvider.get(Registries.PARTICLE_TYPE, TintedCampfires.MOD_ID);

    public static final RegistryObject<SimpleParticleType> BLACK_LAVA = PARTICLES.register("black_lava", () -> new SimpleParticleType(true) {
    });
    public static final RegistryObject<SimpleParticleType> BLUE_LAVA = PARTICLES.register("blue_lava", () -> new SimpleParticleType(true) {
    });
    public static final RegistryObject<SimpleParticleType> BROWN_LAVA = PARTICLES.register("brown_lava", () -> new SimpleParticleType(true) {
    });
    public static final RegistryObject<SimpleParticleType> CYAN_LAVA = PARTICLES.register("cyan_lava", () -> new SimpleParticleType(true) {
    });
    public static final RegistryObject<SimpleParticleType> GRAY_LAVA = PARTICLES.register("gray_lava", () -> new SimpleParticleType(true) {
    });
    public static final RegistryObject<SimpleParticleType> GREEN_LAVA = PARTICLES.register("green_lava", () -> new SimpleParticleType(true) {
    });
    public static final RegistryObject<SimpleParticleType> LIGHT_BLUE_LAVA = PARTICLES.register("light_blue_lava", () -> new SimpleParticleType(true) {
    });
    public static final RegistryObject<SimpleParticleType> LIGHT_GRAY_LAVA = PARTICLES.register("light_gray_lava", () -> new SimpleParticleType(true) {
    });
    public static final RegistryObject<SimpleParticleType> LIME_LAVA = PARTICLES.register("lime_lava", () -> new SimpleParticleType(true) {
    });
    public static final RegistryObject<SimpleParticleType> MAGENTA_LAVA = PARTICLES.register("magenta_lava", () -> new SimpleParticleType(true) {
    });
    public static final RegistryObject<SimpleParticleType> ORANGE_LAVA = PARTICLES.register("orange_lava", () -> new SimpleParticleType(true) {
    });
    public static final RegistryObject<SimpleParticleType> PINK_LAVA = PARTICLES.register("pink_lava", () -> new SimpleParticleType(true) {
    });
    public static final RegistryObject<SimpleParticleType> PURPLE_LAVA = PARTICLES.register("purple_lava", () -> new SimpleParticleType(true) {
    });
    public static final RegistryObject<SimpleParticleType> RED_LAVA = PARTICLES.register("red_lava", () -> new SimpleParticleType(true) {
    });
    public static final RegistryObject<SimpleParticleType> WHITE_LAVA = PARTICLES.register("white_lava", () -> new SimpleParticleType(true) {
    });
    public static final RegistryObject<SimpleParticleType> YELLOW_LAVA = PARTICLES.register("yellow_lava", () -> new SimpleParticleType(true) {
    });

    public static void loadClass() {
    }


}
