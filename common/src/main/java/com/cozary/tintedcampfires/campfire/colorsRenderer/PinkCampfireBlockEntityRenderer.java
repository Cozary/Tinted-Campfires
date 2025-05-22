package com.cozary.tintedcampfires.campfire.colorsRenderer;

import com.cozary.tintedcampfires.campfire.AbstractTintedCampfireBlockEntityRenderer;
import com.cozary.tintedcampfires.campfire.colors.PinkCampfire;
import com.cozary.tintedcampfires.campfire.colorsBlockEntity.PinkCampfireBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public class PinkCampfireBlockEntityRenderer extends AbstractTintedCampfireBlockEntityRenderer<PinkCampfireBlockEntity> {

    public PinkCampfireBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Direction getFacingDirection(PinkCampfireBlockEntity entity) {
        return entity.getBlockState().getValue(PinkCampfire.FACING);
    }

    @Override
    protected NonNullList<ItemStack> getItems(PinkCampfireBlockEntity entity) {
        return entity.getItems();
    }
}

