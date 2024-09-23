package com.cozary.tintedcampfires.campfire.colorsRenderer;

import com.cozary.tintedcampfires.TintedCampfires;
import com.cozary.tintedcampfires.campfire.colors.BlackCampfire;
import com.cozary.tintedcampfires.campfire.colorsBlockEntity.BrownCampfireBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class BrownCampfireBlockEntityRenderer implements BlockEntityRenderer<BrownCampfireBlockEntity> {
    private static final float SIZE = 0.375F;
    private final ItemRenderer itemRenderer;

    public BrownCampfireBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(BrownCampfireBlockEntity colorCampfireTileEntity, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {
        Direction direction = colorCampfireTileEntity.getBlockState().getValue(BlackCampfire.FACING);
        NonNullList<ItemStack> nonnulllist = colorCampfireTileEntity.getItems();
        TintedCampfires.LOG.debug(String.valueOf(nonnulllist));
        int i = (int) colorCampfireTileEntity.getBlockPos().asLong();

        for (int j = 0; j < nonnulllist.size(); ++j) {
            ItemStack itemstack = nonnulllist.get(j);
            if (itemstack != ItemStack.EMPTY) {
                poseStack.pushPose();
                poseStack.translate(0.5D, 0.44921875D, 0.5D);
                Direction direction1 = Direction.from2DDataValue((j + direction.get2DDataValue()) % 4);
                float f = -direction1.toYRot();
                poseStack.mulPose(Axis.YP.rotationDegrees(f));
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                poseStack.translate(-0.3125D, -0.3125D, 0.0D);
                poseStack.scale(0.375F, 0.375F, 0.375F);
                this.itemRenderer.renderStatic(itemstack, ItemDisplayContext.FIXED, packedLight, packedOverlay, poseStack, multiBufferSource, colorCampfireTileEntity.getLevel(), i + j);
                poseStack.popPose();
            }
        }

    }

}