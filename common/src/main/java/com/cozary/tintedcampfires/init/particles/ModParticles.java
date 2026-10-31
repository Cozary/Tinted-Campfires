package com.cozary.tintedcampfires.init.particles;

import com.cozary.tintedcampfires.TintedCampfires;
import com.cozary.tintedcampfires.init.RegistrationProvider;
import com.cozary.tintedcampfires.init.RegistryObject;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public class ModParticles {

    public static final RegistrationProvider<ParticleType<?>> PARTICLES = RegistrationProvider.get(Registries.PARTICLE_TYPE, TintedCampfires.MOD_ID);

    public static final RegistryObject<ParticleType<ColorParticleOption>> TINTED_LAVA = PARTICLES.register("tinted_lava",
            () -> new ParticleType<ColorParticleOption>(false) {
                @Override
                public MapCodec<ColorParticleOption> codec() {
                    return ColorParticleOption.codec(this);
                }

                @Override
                public StreamCodec<? super RegistryFriendlyByteBuf, ColorParticleOption> streamCodec() {
                    return ColorParticleOption.streamCodec(this);
                }
            });

    public static final RegistryObject<SimpleParticleType> TINTED_COSY_SMOKE = PARTICLES.register("tinted_cosy_smoke", () -> new SimpleParticleType(true) {});
    public static final RegistryObject<SimpleParticleType> TINTED_SIGNAL_SMOKE = PARTICLES.register("tinted_signal_smoke", () -> new SimpleParticleType(true) {});

    public static void loadClass() {
    }
}