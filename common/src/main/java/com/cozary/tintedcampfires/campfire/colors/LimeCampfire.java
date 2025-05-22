package com.cozary.tintedcampfires.campfire.colors;

import com.cozary.tintedcampfires.campfire.AbstractTintedCampfire;
import com.cozary.tintedcampfires.campfire.AbstractTintedCampfireBlockEntity;
import com.cozary.tintedcampfires.campfire.colorsBlockEntity.LimeCampfireBlockEntity;
import com.cozary.tintedcampfires.init.ModBlockEntities;
import com.cozary.tintedcampfires.init.particles.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class LimeCampfire extends AbstractTintedCampfire {

    public LimeCampfire(boolean spawnParticles, int fireDamage, BlockBehaviour.Properties props, String name) {
        super(spawnParticles, fireDamage, props, name);
    }

    @Override
    protected ParticleOptions getCustomParticle() {
        return ModParticles.LIME_LAVA.get();
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LimeCampfireBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if (level instanceof ServerLevel serverlevel) {
            if ((Boolean) state.getValue(LIT)) {
                RecipeManager.CachedCheck<SingleRecipeInput, CampfireCookingRecipe> cachedcheck = RecipeManager.createCheck(RecipeType.CAMPFIRE_COOKING);
                return createTickerHelper(blockEntityType, ModBlockEntities.LIME_CAMPFIRE_TILE.get(), (p_379259_, p_379260_, p_379261_, p_379262_) -> {
                    AbstractTintedCampfireBlockEntity.cookTick(serverlevel, p_379260_, p_379261_, p_379262_, cachedcheck);
                });
            } else {
                return createTickerHelper(blockEntityType, ModBlockEntities.LIME_CAMPFIRE_TILE.get(), AbstractTintedCampfireBlockEntity::cooldownTick);
            }
        } else {
            return (Boolean) state.getValue(LIT) ? createTickerHelper(blockEntityType, ModBlockEntities.LIME_CAMPFIRE_TILE.get(), AbstractTintedCampfireBlockEntity::particleTick) : null;
        }
    }
}