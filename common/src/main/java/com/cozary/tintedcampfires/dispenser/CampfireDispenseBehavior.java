package com.cozary.tintedcampfires.dispenser;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

public class CampfireDispenseBehavior extends DefaultDispenseItemBehavior {
    private final DefaultDispenseItemBehavior defaultBehavior = new DefaultDispenseItemBehavior();

    @Override
    protected @NotNull ItemStack execute(BlockSource blockSource, @NotNull ItemStack itemStack) {
        ServerLevel level = blockSource.level();
        Direction direction = blockSource.state().getValue(DispenserBlock.FACING);
        BlockPos blockpos = blockSource.pos().relative(direction);
        BlockState blockstate = level.getBlockState(blockpos);

        if (blockstate.getBlock() instanceof CampfireBlock campfire && campfire.canLight(blockstate)) {

            if (!blockstate.hasProperty(BlockStateProperties.LIT))
                return this.defaultBehavior.dispense(blockSource, itemStack);

            level.setBlockAndUpdate(blockpos, blockstate.setValue(BlockStateProperties.LIT, true));
            level.gameEvent(null, GameEvent.BLOCK_CHANGE, blockpos);

            itemStack.hurtAndBreak(1, level, null, p_348117_ -> {
            });


        } else {
            return this.defaultBehavior.dispense(blockSource, itemStack);
        }

        return itemStack;
    }

}
