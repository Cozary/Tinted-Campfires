package com.cozary.tintedcampfires.init.particles;

import com.cozary.tintedcampfires.campfire.TintedCampfireBlock;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ColorCampfireParticle extends SingleQuadParticle {

    public ColorCampfireParticle(ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, TextureAtlasSprite sprite) {
        super(world, x, y, z, xSpeed, ySpeed, zSpeed, sprite);

        this.xd *= 0.8F;
        this.yd *= 0.8F;
        this.zd *= 0.8F;
        this.yd = this.random.nextFloat() * 0.4F + 0.05F;
        this.quadSize *= this.random.nextFloat() * 2.0F + 0.2F;
        this.lifetime = (int) (16.0D / (Math.random() * 0.8D + 0.2D));

        BlockPos blockPos = BlockPos.containing(x, y, z);
        BlockState blockState = world.getBlockState(blockPos);
        if (!(blockState.getBlock() instanceof TintedCampfireBlock)) {
            blockState = world.getBlockState(blockPos.below());
        }

        if (blockState.getBlock() instanceof TintedCampfireBlock tintedBlock) {
            DyeColor dyeColor = tintedBlock.getDyeColor();
            int colorVal = dyeColor.getTextureDiffuseColor();
            float r = ((colorVal >> 16) & 0xFF) / 255.0F;
            float g = ((colorVal >> 8) & 0xFF) / 255.0F;
            float b = (colorVal & 0xFF) / 255.0F;
            this.setColor(r, g, b);
        } else {
            this.setColor(1.0F, 1.0F, 1.0F);
        }
    }

    @Override
    public float getQuadSize(float scaleFactor) {
        float f = ((float) this.age + scaleFactor) / (float) this.lifetime;
        return this.quadSize * (1.0F - f * f);
    }

    @Override
    protected Layer getLayer() {
        return Layer.OPAQUE;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        float f = (float) this.age / (float) this.lifetime;
        if (this.random.nextFloat() > f) {
            this.level.addParticle(ParticleTypes.SMOKE, this.x, this.y, this.z, this.xd, this.yd, this.zd);
        }

        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.yd -= 0.03D;
            this.move(this.xd, this.yd, this.zd);
            this.xd *= 0.999F;
            this.yd *= 0.999F;
            this.zd *= 0.999F;
            if (this.onGround) {
                this.xd *= 0.7F;
                this.zd *= 0.7F;
            }
        }
    }

    @Override
    protected int getLightCoords(float a) {
        int i = super.getLightCoords(a);
        int k = i >> 16 & 255;
        return 240 | k << 16;
    }

    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Factory(SpriteSet sprite) {
            this.spriteSet = sprite;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
            TextureAtlasSprite sprite = this.spriteSet.get(random);
            ColorCampfireParticle particle = new ColorCampfireParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, sprite);
            return particle;
        }
    }
}
