package com.cozary.tintedcampfires.campfire.colorsRenderer;

import com.cozary.tintedcampfires.campfire.AbstractTintedCampfireBlockEntityRenderer;
import com.cozary.tintedcampfires.campfire.colors.OrangeCampfire;
import com.cozary.tintedcampfires.campfire.colorsBlockEntity.OrangeCampfireBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public class OrangeCampfireBlockEntityRenderer extends AbstractTintedCampfireBlockEntityRenderer<OrangeCampfireBlockEntity> {

    public OrangeCampfireBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Direction getFacingDirection(OrangeCampfireBlockEntity entity) {
        return entity.getBlockState().getValue(OrangeCampfire.FACING);
    }

    @Override
    protected NonNullList<ItemStack> getItems(OrangeCampfireBlockEntity entity) {
        return entity.getItems();
    }
}

