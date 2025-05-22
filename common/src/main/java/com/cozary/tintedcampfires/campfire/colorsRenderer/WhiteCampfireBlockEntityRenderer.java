package com.cozary.tintedcampfires.campfire.colorsRenderer;

import com.cozary.tintedcampfires.campfire.AbstractTintedCampfireBlockEntityRenderer;
import com.cozary.tintedcampfires.campfire.colors.WhiteCampfire;
import com.cozary.tintedcampfires.campfire.colorsBlockEntity.WhiteCampfireBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public class WhiteCampfireBlockEntityRenderer extends AbstractTintedCampfireBlockEntityRenderer<WhiteCampfireBlockEntity> {

    public WhiteCampfireBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Direction getFacingDirection(WhiteCampfireBlockEntity entity) {
        return entity.getBlockState().getValue(WhiteCampfire.FACING);
    }

    @Override
    protected NonNullList<ItemStack> getItems(WhiteCampfireBlockEntity entity) {
        return entity.getItems();
    }
}

