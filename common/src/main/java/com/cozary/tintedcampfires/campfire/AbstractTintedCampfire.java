package com.cozary.tintedcampfires.campfire;


import com.cozary.tintedcampfires.TintedCampfires;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipePropertySet;
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
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.function.ToIntFunction;

public abstract class AbstractTintedCampfire extends CampfireBlock {

    protected final boolean spawnParticles;
    protected final int fireDamage;

    public AbstractTintedCampfire(boolean spawnParticles, int fireDamage, BlockBehaviour.Properties properties, String name) {
        super(true, 1, properties.of().mapColor(MapColor.PODZOL).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).lightLevel(litBlockEmission(15)).noOcclusion().ignitedByLava().setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(TintedCampfires.MOD_ID, name))));
        this.spawnParticles = spawnParticles;
        this.fireDamage = fireDamage;
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(LIT, true)
                .setValue(SIGNAL_FIRE, false)
                .setValue(WATERLOGGED, false)
                .setValue(FACING, Direction.NORTH));
    }

    public static ToIntFunction<BlockState> litBlockEmission(int p_50760_) {
        return (p_50763_) -> {
            return p_50763_.getValue(BlockStateProperties.LIT) ? p_50760_ : 0;
        };
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        BlockEntity entity = level.getBlockEntity(pos);
        if (entity instanceof AbstractTintedCampfireBlockEntity campfireEntity) {
            ItemStack itemstack = player.getItemInHand(hand);
            if (level.recipeAccess().propertySet(RecipePropertySet.CAMPFIRE_INPUT).test(itemstack)) {
                if (level instanceof ServerLevel) {
                    ServerLevel serverlevel = (ServerLevel) level;
                    if (campfireEntity.placeFood(serverlevel, player, itemstack)) {
                        player.awardStat(Stats.INTERACT_WITH_CAMPFIRE);
                        return InteractionResult.SUCCESS_SERVER;
                    }
                }

                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.TRY_WITH_EMPTY_HAND;
    }

/*    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof AbstractTintedCampfireBlockEntity campfireEntity) {
                Containers.dropContents(level, pos, campfireEntity.getItems());
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }*/

    protected abstract ParticleOptions getCustomParticle();

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
    public abstract BlockEntity newBlockEntity(BlockPos pos, BlockState state);

    @Nullable
    @Override
    public abstract <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type);
}
