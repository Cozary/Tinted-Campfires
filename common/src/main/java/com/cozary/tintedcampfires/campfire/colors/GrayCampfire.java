package com.cozary.tintedcampfires.campfire.colors;

import com.cozary.tintedcampfires.campfire.colorsBlockEntity.GrayCampfireBlockEntity;
import com.cozary.tintedcampfires.init.ModBlockEntities;
import com.cozary.tintedcampfires.init.particles.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class GrayCampfire extends CampfireBlock {

    public static final BooleanProperty LIT;
    public static final BooleanProperty SIGNAL_FIRE;
    public static final BooleanProperty WATERLOGGED;
    public static final DirectionProperty FACING;
    protected static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 7.0, 16.0);
    private static final VoxelShape VIRTUAL_FENCE_POST;
    private static final int SMOKE_DISTANCE = 5;

    static {
        LIT = BlockStateProperties.LIT;
        SIGNAL_FIRE = BlockStateProperties.SIGNAL_FIRE;
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
        FACING = BlockStateProperties.HORIZONTAL_FACING;
        VIRTUAL_FENCE_POST = Block.box(6.0, 0.0, 6.0, 10.0, 16.0, 10.0);
    }

    private final boolean spawnParticles;
    private final int fireDamage;


    public GrayCampfire(boolean $$0, int $$1, BlockBehaviour.Properties $$2) {
        super($$0, $$1, $$2);
        this.spawnParticles = $$0;
        this.fireDamage = $$1;
        this.registerDefaultState((BlockState) ((BlockState) ((BlockState) ((BlockState) ((BlockState) this.stateDefinition.any()).setValue(LIT, true)).setValue(SIGNAL_FIRE, false)).setValue(WATERLOGGED, false)).setValue(FACING, Direction.NORTH));
    }

    public static void dowse(@Nullable Entity $$0, LevelAccessor $$1, BlockPos $$2, BlockState $$3) {
        if ($$1.isClientSide()) {
            for (int $$4 = 0; $$4 < 20; ++$$4) {
                makeParticles((Level) $$1, $$2, (Boolean) $$3.getValue(SIGNAL_FIRE), true);
            }
        }

        BlockEntity $$5 = $$1.getBlockEntity($$2);
        if ($$5 instanceof GrayCampfireBlockEntity) {
            ((GrayCampfireBlockEntity) $$5).dowse();
        }

        $$1.gameEvent($$0, GameEvent.BLOCK_CHANGE, $$2);
    }

    public static void makeParticles(Level $$0, BlockPos $$1, boolean $$2, boolean $$3) {
        RandomSource $$4 = $$0.getRandom();
        SimpleParticleType $$5 = $$2 ? ParticleTypes.CAMPFIRE_SIGNAL_SMOKE : ParticleTypes.CAMPFIRE_COSY_SMOKE;
        $$0.addAlwaysVisibleParticle($$5, true, (double) $$1.getX() + 0.5 + $$4.nextDouble() / 3.0 * (double) ($$4.nextBoolean() ? 1 : -1), (double) $$1.getY() + $$4.nextDouble() + $$4.nextDouble(), (double) $$1.getZ() + 0.5 + $$4.nextDouble() / 3.0 * (double) ($$4.nextBoolean() ? 1 : -1), 0.0, 0.07, 0.0);
        if ($$3) {
            $$0.addParticle(ParticleTypes.SMOKE, (double) $$1.getX() + 0.5 + $$4.nextDouble() / 4.0 * (double) ($$4.nextBoolean() ? 1 : -1), (double) $$1.getY() + 0.4, (double) $$1.getZ() + 0.5 + $$4.nextDouble() / 4.0 * (double) ($$4.nextBoolean() ? 1 : -1), 0.0, 0.005, 0.0);
        }

    }

    public static boolean isSmokeyPos(Level $$0, BlockPos $$1) {
        for (int $$2 = 1; $$2 <= 5; ++$$2) {
            BlockPos $$3 = $$1.below($$2);
            BlockState $$4 = $$0.getBlockState($$3);
            if (isLitCampfire($$4)) {
                return true;
            }

            boolean $$5 = Shapes.joinIsNotEmpty(VIRTUAL_FENCE_POST, $$4.getCollisionShape($$0, $$1, CollisionContext.empty()), BooleanOp.AND);
            if ($$5) {
                BlockState $$6 = $$0.getBlockState($$3.below());
                return isLitCampfire($$6);
            }
        }

        return false;
    }

    public static boolean isLitCampfire(BlockState $$0) {
        return $$0.hasProperty(LIT) && $$0.is(BlockTags.CAMPFIRES) && (Boolean) $$0.getValue(LIT);
    }

    public static boolean canLight(BlockState $$0) {
        return $$0.is(BlockTags.CAMPFIRES, ($$0x) -> {
            return $$0x.hasProperty(WATERLOGGED) && $$0x.hasProperty(LIT);
        }) && !(Boolean) $$0.getValue(WATERLOGGED) && !(Boolean) $$0.getValue(LIT);
    }

    protected ItemInteractionResult useItemOn(ItemStack $$0, BlockState $$1, Level $$2, BlockPos $$3, Player $$4, InteractionHand $$5, BlockHitResult $$6) {
        if ($$2.getBlockEntity($$3) instanceof GrayCampfireBlockEntity $$8) {
            ItemStack $$9 = $$4.getItemInHand($$5);
            Optional<RecipeHolder<CampfireCookingRecipe>> $$10 = $$8.getCookableRecipe($$9);
            if ($$10.isPresent()) {
                if (!$$2.isClientSide && $$8.placeFood($$4, $$4.hasInfiniteMaterials() ? $$9.copy() : $$9, $$10.get().value().getCookingTime())) {
                    $$4.awardStat(Stats.INTERACT_WITH_CAMPFIRE);
                    return ItemInteractionResult.SUCCESS;
                }

                return ItemInteractionResult.CONSUME;
            }
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    protected void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if ((Boolean)pState.getValue(LIT) && pEntity instanceof LivingEntity) {
            pEntity.hurt(pLevel.damageSources().campfire(), (float)this.fireDamage);
        }

        super.entityInside(pState, pLevel, pPos, pEntity);
    }

    public void onRemove(BlockState $$0, Level $$1, BlockPos $$2, BlockState $$3, boolean $$4) {
        if (!$$0.is($$3.getBlock())) {
            BlockEntity $$5 = $$1.getBlockEntity($$2);
            if ($$5 instanceof GrayCampfireBlockEntity) {
                Containers.dropContents($$1, $$2, ((GrayCampfireBlockEntity) $$5).getItems());
            }

            super.onRemove($$0, $$1, $$2, $$3, $$4);
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext $$0) {
        LevelAccessor $$1 = $$0.getLevel();
        BlockPos $$2 = $$0.getClickedPos();
        boolean $$3 = $$1.getFluidState($$2).getType() == Fluids.WATER;
        return (BlockState) ((BlockState) ((BlockState) ((BlockState) this.defaultBlockState().setValue(WATERLOGGED, $$3)).setValue(SIGNAL_FIRE, this.isSmokeSource($$1.getBlockState($$2.below())))).setValue(LIT, !$$3)).setValue(FACING, $$0.getHorizontalDirection());
    }

    public BlockState updateShape(BlockState $$0, Direction $$1, BlockState $$2, LevelAccessor $$3, BlockPos $$4, BlockPos $$5) {
        if ((Boolean) $$0.getValue(WATERLOGGED)) {
            $$3.scheduleTick($$4, Fluids.WATER, Fluids.WATER.getTickDelay($$3));
        }

        return $$1 == Direction.DOWN ? (BlockState) $$0.setValue(SIGNAL_FIRE, this.isSmokeSource($$2)) : super.updateShape($$0, $$1, $$2, $$3, $$4, $$5);
    }

    private boolean isSmokeSource(BlockState $$0) {
        return $$0.is(Blocks.HAY_BLOCK);
    }

    public VoxelShape getShape(BlockState $$0, BlockGetter $$1, BlockPos $$2, CollisionContext $$3) {
        return SHAPE;
    }

    public RenderShape getRenderShape(BlockState $$0) {
        return RenderShape.MODEL;
    }

    @Override
    public void animateTick(BlockState p_51287_, Level p_51288_, BlockPos p_51289_, RandomSource p_51290_) {
        if (p_51287_.getValue(LIT)) {
            if (p_51290_.nextInt(10) == 0) {
                p_51288_.playLocalSound((double) p_51289_.getX() + 0.5D, (double) p_51289_.getY() + 0.5D, (double) p_51289_.getZ() + 0.5D, SoundEvents.CAMPFIRE_CRACKLE, SoundSource.BLOCKS, 0.5F + p_51290_.nextFloat(), p_51290_.nextFloat() * 0.7F + 0.6F, false);
            }

            if (this.spawnParticles && p_51290_.nextInt(5) == 0) {
                for (int i = 0; i < p_51290_.nextInt(1) + 1; ++i) {
                    p_51288_.addParticle(ModParticles.GRAY_LAVA.get(), (double) p_51289_.getX() + 0.5D, (double) p_51289_.getY() + 0.5D, (double) p_51289_.getZ() + 0.5D, p_51290_.nextFloat() / 2.0F, 5.0E-5D, p_51290_.nextFloat() / 2.0F);
                }
            }

        }
    }

    public boolean placeLiquid(LevelAccessor $$0, BlockPos $$1, BlockState $$2, FluidState $$3) {
        if (!(Boolean) $$2.getValue(BlockStateProperties.WATERLOGGED) && $$3.getType() == Fluids.WATER) {
            boolean $$4 = (Boolean) $$2.getValue(LIT);
            if ($$4) {
                if (!$$0.isClientSide()) {
                    $$0.playSound((Player) null, $$1, SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.BLOCKS, 1.0F, 1.0F);
                }

                dowse((Entity) null, $$0, $$1, $$2);
            }

            $$0.setBlock($$1, (BlockState) ((BlockState) $$2.setValue(WATERLOGGED, true)).setValue(LIT, false), 3);
            $$0.scheduleTick($$1, $$3.getType(), $$3.getType().getTickDelay($$0));
            return true;
        } else {
            return false;
        }
    }

    public void onProjectileHit(Level $$0, BlockState $$1, BlockHitResult $$2, Projectile $$3) {
        BlockPos $$4 = $$2.getBlockPos();
        if (!$$0.isClientSide && $$3.isOnFire() && $$3.mayInteract($$0, $$4) && !(Boolean) $$1.getValue(LIT) && !(Boolean) $$1.getValue(WATERLOGGED)) {
            $$0.setBlock($$4, (BlockState) $$1.setValue(BlockStateProperties.LIT, true), 11);
        }

    }

    public FluidState getFluidState(BlockState $$0) {
        return (Boolean) $$0.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState($$0);
    }

    public BlockState rotate(BlockState $$0, Rotation $$1) {
        return (BlockState) $$0.setValue(FACING, $$1.rotate((Direction) $$0.getValue(FACING)));
    }

    public BlockState mirror(BlockState $$0, Mirror $$1) {
        return $$0.rotate($$1.getRotation((Direction) $$0.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> $$0) {
        $$0.add(new Property[]{LIT, SIGNAL_FIRE, WATERLOGGED, FACING});
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new GrayCampfireBlockEntity(blockPos, blockState);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level $$0, BlockState $$1, BlockEntityType<T> $$2) {
        if ($$0.isClientSide) {
            return (Boolean) $$1.getValue(LIT) ? createTickerHelper($$2, ModBlockEntities.GRAY_CAMPFIRE_TILE.get(), GrayCampfireBlockEntity::particleTick) : null;
        } else {
            return (Boolean) $$1.getValue(LIT) ? createTickerHelper($$2, ModBlockEntities.GRAY_CAMPFIRE_TILE.get(), GrayCampfireBlockEntity::cookTick) : createTickerHelper($$2, ModBlockEntities.GRAY_CAMPFIRE_TILE.get(), GrayCampfireBlockEntity::cooldownTick);
        }
    }

}
