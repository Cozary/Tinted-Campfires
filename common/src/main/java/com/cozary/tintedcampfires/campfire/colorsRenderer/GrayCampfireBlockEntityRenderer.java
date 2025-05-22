package com.cozary.tintedcampfires.campfire.colorsRenderer;

import com.cozary.tintedcampfires.campfire.AbstractTintedCampfireBlockEntityRenderer;
import com.cozary.tintedcampfires.campfire.colors.GrayCampfire;
import com.cozary.tintedcampfires.campfire.colorsBlockEntity.GrayCampfireBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public class GrayCampfireBlockEntityRenderer extends AbstractTintedCampfireBlockEntityRenderer<GrayCampfireBlockEntity> {

    public GrayCampfireBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Direction getFacingDirection(GrayCampfireBlockEntity entity) {
        return entity.getBlockState().getValue(GrayCampfire.FACING);
    }

    @Override
    protected NonNullList<ItemStack> getItems(GrayCampfireBlockEntity entity) {
        return entity.getItems();
    }
}

