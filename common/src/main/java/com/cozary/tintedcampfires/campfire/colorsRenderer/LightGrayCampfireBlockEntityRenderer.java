package com.cozary.tintedcampfires.campfire.colorsRenderer;

import com.cozary.tintedcampfires.campfire.AbstractTintedCampfireBlockEntityRenderer;
import com.cozary.tintedcampfires.campfire.colors.LightGrayCampfire;
import com.cozary.tintedcampfires.campfire.colorsBlockEntity.LightGrayCampfireBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public class LightGrayCampfireBlockEntityRenderer extends AbstractTintedCampfireBlockEntityRenderer<LightGrayCampfireBlockEntity> {

    public LightGrayCampfireBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Direction getFacingDirection(LightGrayCampfireBlockEntity entity) {
        return entity.getBlockState().getValue(LightGrayCampfire.FACING);
    }

    @Override
    protected NonNullList<ItemStack> getItems(LightGrayCampfireBlockEntity entity) {
        return entity.getItems();
    }
}

