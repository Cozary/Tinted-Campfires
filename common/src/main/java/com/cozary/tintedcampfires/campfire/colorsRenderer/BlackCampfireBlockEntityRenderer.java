package com.cozary.tintedcampfires.campfire.colorsRenderer;

import com.cozary.tintedcampfires.campfire.AbstractTintedCampfireBlockEntityRenderer;
import com.cozary.tintedcampfires.campfire.colors.BlackCampfire;
import com.cozary.tintedcampfires.campfire.colorsBlockEntity.BlackCampfireBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public class BlackCampfireBlockEntityRenderer extends AbstractTintedCampfireBlockEntityRenderer<BlackCampfireBlockEntity> {

    public BlackCampfireBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Direction getFacingDirection(BlackCampfireBlockEntity entity) {
        return entity.getBlockState().getValue(BlackCampfire.FACING);
    }

    @Override
    protected NonNullList<ItemStack> getItems(BlackCampfireBlockEntity entity) {
        return entity.getItems();
    }
}

