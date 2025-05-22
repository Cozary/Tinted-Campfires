package com.cozary.tintedcampfires.campfire.colorsRenderer;

import com.cozary.tintedcampfires.campfire.AbstractTintedCampfireBlockEntityRenderer;
import com.cozary.tintedcampfires.campfire.colors.RedCampfire;
import com.cozary.tintedcampfires.campfire.colorsBlockEntity.RedCampfireBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public class RedCampfireBlockEntityRenderer extends AbstractTintedCampfireBlockEntityRenderer<RedCampfireBlockEntity> {

    public RedCampfireBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Direction getFacingDirection(RedCampfireBlockEntity entity) {
        return entity.getBlockState().getValue(RedCampfire.FACING);
    }

    @Override
    protected NonNullList<ItemStack> getItems(RedCampfireBlockEntity entity) {
        return entity.getItems();
    }
}

