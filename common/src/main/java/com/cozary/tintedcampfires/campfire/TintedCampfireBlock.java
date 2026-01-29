package com.cozary.tintedcampfires.campfire;

import com.cozary.tintedcampfires.TintedCampfires;
import com.cozary.tintedcampfires.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class TintedCampfireBlock extends CampfireBlock {

    protected final boolean spawnParticles;
    private final Supplier<ParticleOptions> particleSupplier;

    public TintedCampfireBlock(boolean spawnParticles, int fireDamage, BlockBehaviour.Properties properties, String name, Supplier<ParticleOptions> particleSupplier) {
        super(spawnParticles, fireDamage, properties.mapColor(MapColor.PODZOL).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).lightLevel(litBlockEmission(15)).noOcclusion().ignitedByLava().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(TintedCampfires.MOD_ID, name))));
        this.spawnParticles = spawnParticles;
        this.particleSupplier = particleSupplier;
    }

    public static ToIntFunction<BlockState> litBlockEmission(int p_50760_) {
        return (p_50763_) -> {
            return p_50763_.getValue(BlockStateProperties.LIT) ? p_50760_ : 0;
        };
    }

    protected ParticleOptions getCustomParticle() {
        return particleSupplier.get();
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        if (state.getValue(LIT)) {
            if (rand.nextInt(10) == 0) {
                level.playLocalSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.CAMPFIRE_CRACKLE, SoundSource.BLOCKS, 0.5F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.6F, false);
            }
            if (this.spawnParticles && rand.nextInt(5) == 0) {
                level.addParticle(getCustomParticle(), pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, rand.nextFloat() / 2.0F, 5.0E-5D, rand.nextFloat() / 2.0F);
            }
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TintedCampfireBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if (level instanceof ServerLevel serverlevel) {
            if ((Boolean) state.getValue(LIT)) {
                RecipeManager.CachedCheck<SingleRecipeInput, CampfireCookingRecipe> cachedcheck = RecipeManager.createCheck(RecipeType.CAMPFIRE_COOKING);
                return createTickerHelper(blockEntityType, ModBlockEntities.TINTED_CAMPFIRE.get(), (p_379259_, p_379260_, p_379261_, p_379262_) -> {
                    TintedCampfireBlockEntity.cookTick(serverlevel, p_379260_, p_379261_, p_379262_, cachedcheck);
                });
            } else {
                return createTickerHelper(blockEntityType, ModBlockEntities.TINTED_CAMPFIRE.get(), TintedCampfireBlockEntity::cooldownTick);
            }
        } else {
            return (Boolean) state.getValue(LIT) ? createTickerHelper(blockEntityType, ModBlockEntities.TINTED_CAMPFIRE.get(), TintedCampfireBlockEntity::particleTick) : null;
        }
    }
}
