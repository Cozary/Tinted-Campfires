package com.cozary.tintedcampfires.campfire.colorsRenderer;

import com.cozary.tintedcampfires.campfire.AbstractTintedCampfireBlockEntityRenderer;
import com.cozary.tintedcampfires.campfire.colors.CyanCampfire;
import com.cozary.tintedcampfires.campfire.colorsBlockEntity.CyanCampfireBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public class CyanCampfireBlockEntityRenderer extends AbstractTintedCampfireBlockEntityRenderer<CyanCampfireBlockEntity> {

    public CyanCampfireBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Direction getFacingDirection(CyanCampfireBlockEntity entity) {
        return entity.getBlockState().getValue(CyanCampfire.FACING);
    }

    @Override
    protected NonNullList<ItemStack> getItems(CyanCampfireBlockEntity entity) {
        return entity.getItems();
    }
}

