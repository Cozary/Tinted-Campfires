package com.cozary.tintedcampfires.campfire.colors;

import com.cozary.tintedcampfires.campfire.AbstractTintedCampfire;
import com.cozary.tintedcampfires.campfire.colorsBlockEntity.LightGrayCampfireBlockEntity;
import com.cozary.tintedcampfires.init.ModBlockEntities;
import com.cozary.tintedcampfires.init.particles.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class LightGrayCampfire extends AbstractTintedCampfire {

    public LightGrayCampfire(boolean spawnParticles, int fireDamage, BlockBehaviour.Properties props) {
        super(spawnParticles, fireDamage, props);
    }

    @Override
    protected ParticleOptions getCustomParticle() {
        return ModParticles.LIGHT_GRAY_LAVA.get();
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LightGrayCampfireBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide
                ? state.getValue(LIT) ? createTickerHelper(type, ModBlockEntities.LIGHT_GRAY_CAMPFIRE_TILE.get(), LightGrayCampfireBlockEntity::particleTick) : null
                : state.getValue(LIT) ? createTickerHelper(type, ModBlockEntities.LIGHT_GRAY_CAMPFIRE_TILE.get(), LightGrayCampfireBlockEntity::cookTick)
                : createTickerHelper(type, ModBlockEntities.LIGHT_GRAY_CAMPFIRE_TILE.get(), LightGrayCampfireBlockEntity::cooldownTick);
    }
}