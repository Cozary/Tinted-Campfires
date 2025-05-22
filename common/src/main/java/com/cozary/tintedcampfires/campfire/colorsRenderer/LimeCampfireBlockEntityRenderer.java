package com.cozary.tintedcampfires.campfire.colorsRenderer;

import com.cozary.tintedcampfires.campfire.AbstractTintedCampfireBlockEntityRenderer;
import com.cozary.tintedcampfires.campfire.colors.LimeCampfire;
import com.cozary.tintedcampfires.campfire.colorsBlockEntity.LimeCampfireBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public class LimeCampfireBlockEntityRenderer extends AbstractTintedCampfireBlockEntityRenderer<LimeCampfireBlockEntity> {

    public LimeCampfireBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Direction getFacingDirection(LimeCampfireBlockEntity entity) {
        return entity.getBlockState().getValue(LimeCampfire.FACING);
    }

    @Override
    protected NonNullList<ItemStack> getItems(LimeCampfireBlockEntity entity) {
        return entity.getItems();
    }
}

