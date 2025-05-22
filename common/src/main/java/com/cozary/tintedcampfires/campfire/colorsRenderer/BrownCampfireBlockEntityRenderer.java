package com.cozary.tintedcampfires.campfire.colorsRenderer;

import com.cozary.tintedcampfires.campfire.AbstractTintedCampfireBlockEntityRenderer;
import com.cozary.tintedcampfires.campfire.colors.BrownCampfire;
import com.cozary.tintedcampfires.campfire.colorsBlockEntity.BrownCampfireBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public class BrownCampfireBlockEntityRenderer extends AbstractTintedCampfireBlockEntityRenderer<BrownCampfireBlockEntity> {

    public BrownCampfireBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Direction getFacingDirection(BrownCampfireBlockEntity entity) {
        return entity.getBlockState().getValue(BrownCampfire.FACING);
    }

    @Override
    protected NonNullList<ItemStack> getItems(BrownCampfireBlockEntity entity) {
        return entity.getItems();
    }
}

