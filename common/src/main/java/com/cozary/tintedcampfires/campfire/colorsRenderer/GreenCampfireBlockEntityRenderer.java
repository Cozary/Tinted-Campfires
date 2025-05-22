package com.cozary.tintedcampfires.campfire.colorsRenderer;

import com.cozary.tintedcampfires.campfire.AbstractTintedCampfireBlockEntityRenderer;
import com.cozary.tintedcampfires.campfire.colors.GreenCampfire;
import com.cozary.tintedcampfires.campfire.colorsBlockEntity.GreenCampfireBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public class GreenCampfireBlockEntityRenderer extends AbstractTintedCampfireBlockEntityRenderer<GreenCampfireBlockEntity> {

    public GreenCampfireBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Direction getFacingDirection(GreenCampfireBlockEntity entity) {
        return entity.getBlockState().getValue(GreenCampfire.FACING);
    }

    @Override
    protected NonNullList<ItemStack> getItems(GreenCampfireBlockEntity entity) {
        return entity.getItems();
    }
}

