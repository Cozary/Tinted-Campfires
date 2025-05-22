package com.cozary.tintedcampfires.campfire.colorsRenderer;


import com.cozary.tintedcampfires.campfire.AbstractTintedCampfireBlockEntityRenderer;
import com.cozary.tintedcampfires.campfire.colors.BlueCampfire;
import com.cozary.tintedcampfires.campfire.colorsBlockEntity.BlueCampfireBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public class BlueCampfireBlockEntityRenderer extends AbstractTintedCampfireBlockEntityRenderer<BlueCampfireBlockEntity> {

    public BlueCampfireBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Direction getFacingDirection(BlueCampfireBlockEntity entity) {
        return entity.getBlockState().getValue(BlueCampfire.FACING);
    }

    @Override
    protected NonNullList<ItemStack> getItems(BlueCampfireBlockEntity entity) {
        return entity.getItems();
    }
}

