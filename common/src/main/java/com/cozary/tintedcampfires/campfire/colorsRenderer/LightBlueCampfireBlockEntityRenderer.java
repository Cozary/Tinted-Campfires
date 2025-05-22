package com.cozary.tintedcampfires.campfire.colorsRenderer;

import com.cozary.tintedcampfires.campfire.AbstractTintedCampfireBlockEntityRenderer;
import com.cozary.tintedcampfires.campfire.colors.LightBlueCampfire;
import com.cozary.tintedcampfires.campfire.colorsBlockEntity.LightBlueCampfireBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public class LightBlueCampfireBlockEntityRenderer extends AbstractTintedCampfireBlockEntityRenderer<LightBlueCampfireBlockEntity> {

    public LightBlueCampfireBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Direction getFacingDirection(LightBlueCampfireBlockEntity entity) {
        return entity.getBlockState().getValue(LightBlueCampfire.FACING);
    }

    @Override
    protected NonNullList<ItemStack> getItems(LightBlueCampfireBlockEntity entity) {
        return entity.getItems();
    }
}

