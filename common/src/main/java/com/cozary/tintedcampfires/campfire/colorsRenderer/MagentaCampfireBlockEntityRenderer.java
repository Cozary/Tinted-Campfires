package com.cozary.tintedcampfires.campfire.colorsRenderer;

import com.cozary.tintedcampfires.campfire.AbstractTintedCampfireBlockEntityRenderer;
import com.cozary.tintedcampfires.campfire.colors.MagentaCampfire;
import com.cozary.tintedcampfires.campfire.colorsBlockEntity.MagentaCampfireBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public class MagentaCampfireBlockEntityRenderer extends AbstractTintedCampfireBlockEntityRenderer<MagentaCampfireBlockEntity> {

    public MagentaCampfireBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Direction getFacingDirection(MagentaCampfireBlockEntity entity) {
        return entity.getBlockState().getValue(MagentaCampfire.FACING);
    }

    @Override
    protected NonNullList<ItemStack> getItems(MagentaCampfireBlockEntity entity) {
        return entity.getItems();
    }
}

