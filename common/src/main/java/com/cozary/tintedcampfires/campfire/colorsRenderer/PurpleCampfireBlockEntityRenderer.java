package com.cozary.tintedcampfires.campfire.colorsRenderer;

import com.cozary.tintedcampfires.campfire.AbstractTintedCampfireBlockEntityRenderer;
import com.cozary.tintedcampfires.campfire.colors.PurpleCampfire;
import com.cozary.tintedcampfires.campfire.colorsBlockEntity.PurpleCampfireBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public class PurpleCampfireBlockEntityRenderer extends AbstractTintedCampfireBlockEntityRenderer<PurpleCampfireBlockEntity> {

    public PurpleCampfireBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Direction getFacingDirection(PurpleCampfireBlockEntity entity) {
        return entity.getBlockState().getValue(PurpleCampfire.FACING);
    }

    @Override
    protected NonNullList<ItemStack> getItems(PurpleCampfireBlockEntity entity) {
        return entity.getItems();
    }
}

