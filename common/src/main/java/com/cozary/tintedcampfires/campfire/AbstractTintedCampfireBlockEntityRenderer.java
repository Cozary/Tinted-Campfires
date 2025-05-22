package com.cozary.tintedcampfires.campfire;


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

public abstract class AbstractTintedCampfireBlockEntityRenderer<T extends AbstractTintedCampfireBlockEntity> implements BlockEntityRenderer<T> {
    private static final float SIZE = 0.375F;
    protected final ItemRenderer itemRenderer;

    public AbstractTintedCampfireBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(T campfireEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Direction direction = getFacingDirection(campfireEntity);
        NonNullList<ItemStack> items = getItems(campfireEntity);
        int seed = (int) campfireEntity.getBlockPos().asLong();

        for (int i = 0; i < items.size(); ++i) {
            ItemStack stack = items.get(i);
            if (!stack.isEmpty()) {
                poseStack.pushPose();
                poseStack.translate(0.5D, 0.44921875D, 0.5D);
                Direction itemDirection = Direction.from2DDataValue((i + direction.get2DDataValue()) % 4);
                float rotation = -itemDirection.toYRot();
                poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                poseStack.translate(-0.3125D, -0.3125D, 0.0D);
                poseStack.scale(SIZE, SIZE, SIZE);
                this.itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, packedLight, packedOverlay, poseStack, bufferSource, campfireEntity.getLevel(), seed + i);
                poseStack.popPose();
            }
        }
    }

    protected abstract Direction getFacingDirection(T campfireEntity);

    protected abstract NonNullList<ItemStack> getItems(T campfireEntity);
}

