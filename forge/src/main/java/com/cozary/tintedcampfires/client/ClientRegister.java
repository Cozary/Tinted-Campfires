package com.cozary.tintedcampfires.client;

import com.cozary.tintedcampfires.TintedCampfires;
import com.cozary.tintedcampfires.campfire.TintedCampfireBlockEntityRenderer;
import com.cozary.tintedcampfires.init.ModBlockEntities;
import com.cozary.tintedcampfires.init.ModBlocks;
import com.cozary.tintedcampfires.init.particles.ColorCampfireParticle;
import com.cozary.tintedcampfires.init.particles.ModParticles;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = TintedCampfires.MOD_ID, value = Dist.CLIENT)
public class ClientRegister {

    @SubscribeEvent
    public static void registerFactories(RegisterParticleProvidersEvent event) {

        event.registerSpriteSet(ModParticles.BLACK_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.BLUE_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.BROWN_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.CYAN_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.GRAY_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.GREEN_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.LIGHT_BLUE_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.LIGHT_GRAY_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.LIME_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.MAGENTA_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.ORANGE_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.PINK_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.PURPLE_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.RED_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.WHITE_LAVA.get(), ColorCampfireParticle.Factory::new);
        event.registerSpriteSet(ModParticles.YELLOW_LAVA.get(), ColorCampfireParticle.Factory::new);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLACK_CAMPFIRE.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLUE_CAMPFIRE.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BROWN_CAMPFIRE.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CYAN_CAMPFIRE.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GRAY_CAMPFIRE.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GREEN_CAMPFIRE.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LIGHT_BLUE_CAMPFIRE.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LIGHT_GRAY_CAMPFIRE.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LIME_CAMPFIRE.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAGENTA_CAMPFIRE.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ORANGE_CAMPFIRE.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PINK_CAMPFIRE.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PURPLE_CAMPFIRE.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RED_CAMPFIRE.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WHITE_CAMPFIRE.get(), ChunkSectionLayer.CUTOUT);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.YELLOW_CAMPFIRE.get(), ChunkSectionLayer.CUTOUT);
    }

    @SubscribeEvent
    public static void registerRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.TINTED_CAMPFIRE.get(), TintedCampfireBlockEntityRenderer::new);
    }
}
