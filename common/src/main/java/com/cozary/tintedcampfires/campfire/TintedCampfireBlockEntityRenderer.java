package com.cozary.tintedcampfires.campfire;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.CampfireRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class TintedCampfireBlockEntityRenderer implements BlockEntityRenderer<TintedCampfireBlockEntity, CampfireRenderState> {
    private static final float SIZE = 0.375F;
    protected final ItemModelResolver itemRenderer;

    public TintedCampfireBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.itemModelResolver();
    }

    public CampfireRenderState createRenderState() {
        return new CampfireRenderState();
    }

    public void extractRenderState(TintedCampfireBlockEntity blockEntity, CampfireRenderState renderState, float partialTick, Vec3 cameraPosition, ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        renderState.facing = (Direction) blockEntity.getBlockState().getValue(CampfireBlock.FACING);
        int i = (int) blockEntity.getBlockPos().asLong();
        renderState.items = new ArrayList();

        for (int j = 0; j < blockEntity.getItems().size(); ++j) {
            ItemStackRenderState itemstackrenderstate = new ItemStackRenderState();
            this.itemRenderer.updateForTopItem(itemstackrenderstate, (ItemStack) blockEntity.getItems().get(j), ItemDisplayContext.FIXED, blockEntity.getLevel(), (ItemOwner) null, i + j);
            renderState.items.add(itemstackrenderstate);
        }

    }

    public void submit(CampfireRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState) {
        Direction direction = renderState.facing;
        List<ItemStackRenderState> list = renderState.items;

        for (int i = 0; i < list.size(); ++i) {
            ItemStackRenderState itemstackrenderstate = (ItemStackRenderState) list.get(i);
            if (!itemstackrenderstate.isEmpty()) {
                poseStack.pushPose();
                poseStack.translate(0.5F, 0.44921875F, 0.5F);
                Direction direction1 = Direction.from2DDataValue((i + direction.get2DDataValue()) % 4);
                float f = -direction1.toYRot();
                poseStack.mulPose(Axis.YP.rotationDegrees(f));
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                poseStack.translate(-0.3125F, -0.3125F, 0.0F);
                poseStack.scale(0.375F, 0.375F, 0.375F);
                itemstackrenderstate.submit(poseStack, nodeCollector, renderState.lightCoords, OverlayTexture.NO_OVERLAY, 0);
                poseStack.popPose();
            }
        }

    }
}
