package com.cozary.tintedcampfires.init.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class ColorCampfireSmokeParticle extends SingleQuadParticle {

    protected ColorCampfireSmokeParticle(ClientLevel level, double x, double y, double z, double xa, double ya, double za, boolean isSignalFire, TextureAtlasSprite sprite) {
        super(level, x, y, z, sprite);
        this.scale(3.0F);
        this.setSize(0.25F, 0.25F);
        if (isSignalFire) {
            this.lifetime = this.random.nextInt(50) + 280;
        } else {
            this.lifetime = this.random.nextInt(50) + 80;
        }

        this.gravity = 3.0E-6F;

        this.setColor((float) xa, (float) ya, (float) za);

        this.xd = 0.0D;
        this.yd = 0.07D + (double) (this.random.nextFloat() / 500.0F);
        this.zd = 0.0D;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ < this.lifetime && !(this.alpha <= 0.0F)) {
            this.xd += (double) (this.random.nextFloat() / 5000.0F * (float) (this.random.nextBoolean() ? 1 : -1));
            this.zd += (double) (this.random.nextFloat() / 5000.0F * (float) (this.random.nextBoolean() ? 1 : -1));
            this.yd -= (double) this.gravity;
            this.move(this.xd, this.yd, this.zd);
            if (this.age >= this.lifetime - 60 && this.alpha > 0.01F) {
                this.alpha -= 0.015F;
            }
        } else {
            this.remove();
        }
    }

    @Override
    public Layer getLayer() {
        return Layer.TRANSLUCENT;
    }

    public static class CosyProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public CosyProvider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, RandomSource random) {
            ColorCampfireSmokeParticle particle = new ColorCampfireSmokeParticle(level, x, y, z, xAux, yAux, zAux,
                    false, this.sprites.get(random));
            particle.setAlpha(0.9F);
            return particle;
        }
    }

    public static class SignalProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public SignalProvider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, RandomSource random) {
            ColorCampfireSmokeParticle particle = new ColorCampfireSmokeParticle(level, x, y, z, xAux, yAux, zAux, true,
                    this.sprites.get(random));
            particle.setAlpha(0.95F);
            return particle;
        }
    }
}
