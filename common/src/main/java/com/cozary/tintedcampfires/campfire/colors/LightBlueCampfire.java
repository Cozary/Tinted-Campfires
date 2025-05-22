package com.cozary.tintedcampfires.campfire.colors;

import com.cozary.tintedcampfires.campfire.AbstractTintedCampfire;
import com.cozary.tintedcampfires.campfire.colorsBlockEntity.LightBlueCampfireBlockEntity;
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

public class LightBlueCampfire extends AbstractTintedCampfire {

    public LightBlueCampfire(boolean spawnParticles, int fireDamage, BlockBehaviour.Properties props) {
        super(spawnParticles, fireDamage, props);
    }

    @Override
    protected ParticleOptions getCustomParticle() {
        return ModParticles.LIGHT_BLUE_LAVA.get();
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LightBlueCampfireBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide
                ? state.getValue(LIT) ? createTickerHelper(type, ModBlockEntities.LIGHT_BLUE_CAMPFIRE_TILE.get(), LightBlueCampfireBlockEntity::particleTick) : null
                : state.getValue(LIT) ? createTickerHelper(type, ModBlockEntities.LIGHT_BLUE_CAMPFIRE_TILE.get(), LightBlueCampfireBlockEntity::cookTick)
                : createTickerHelper(type, ModBlockEntities.LIGHT_BLUE_CAMPFIRE_TILE.get(), LightBlueCampfireBlockEntity::cooldownTick);
    }
}
