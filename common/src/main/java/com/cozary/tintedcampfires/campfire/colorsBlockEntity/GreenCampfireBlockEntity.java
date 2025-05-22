package com.cozary.tintedcampfires.campfire.colorsBlockEntity;

import com.cozary.tintedcampfires.campfire.AbstractTintedCampfireBlockEntity;
import com.cozary.tintedcampfires.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class GreenCampfireBlockEntity extends AbstractTintedCampfireBlockEntity {
    public GreenCampfireBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GREEN_CAMPFIRE_TILE.get(), pos, state);
    }
}
