package com.cozary.tintedcampfires.campfire.colorsRenderer;

import com.cozary.tintedcampfires.campfire.AbstractTintedCampfireBlockEntityRenderer;
import com.cozary.tintedcampfires.campfire.colors.YellowCampfire;
import com.cozary.tintedcampfires.campfire.colorsBlockEntity.YellowCampfireBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public class YellowCampfireBlockEntityRenderer extends AbstractTintedCampfireBlockEntityRenderer<YellowCampfireBlockEntity> {

    public YellowCampfireBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Direction getFacingDirection(YellowCampfireBlockEntity entity) {
        return entity.getBlockState().getValue(YellowCampfire.FACING);
    }

    @Override
    protected NonNullList<ItemStack> getItems(YellowCampfireBlockEntity entity) {
        return entity.getItems();
    }
}

