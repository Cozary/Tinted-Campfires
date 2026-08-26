package com.cozary.tintedcampfires;

import com.cozary.tintedcampfires.client.ClientRegister;
import com.cozary.tintedcampfires.dispenser.CampfireDispenseBehavior;
import com.cozary.tintedcampfires.dispenser.SetColorDispenseBehavior;
import com.cozary.tintedcampfires.init.ModTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TintedCampfires.MOD_ID)
public class TintedCampfiresForge {

    public TintedCampfiresForge(FMLJavaModLoadingContext context) {
        var modBusGroup = context.getModBusGroup();

        TintedCampfires.LOG.info("Hello Forge world!");
        TintedCampfires.init();
        ModTabs.CREATIVE_MODE_TAB.register(modBusGroup);

        FMLCommonSetupEvent.getBus(modBusGroup).addListener(this::setup);

        RegisterParticleProvidersEvent.BUS.addListener(ClientRegister::registerFactories);
        EntityRenderersEvent.RegisterRenderers.BUS.addListener(ClientRegister::registerRenders);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            DispenserBlock.registerBehavior(Items.FLINT_AND_STEEL, new CampfireDispenseBehavior());
            Items.DYE.forEach(dye -> DispenserBlock.registerBehavior(dye, new SetColorDispenseBehavior()));
        });
    }
}
