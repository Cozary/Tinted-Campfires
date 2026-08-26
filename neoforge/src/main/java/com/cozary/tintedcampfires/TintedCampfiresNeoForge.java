package com.cozary.tintedcampfires;

import com.cozary.tintedcampfires.dispenser.CampfireDispenseBehavior;
import com.cozary.tintedcampfires.dispenser.SetColorDispenseBehavior;
import com.cozary.tintedcampfires.init.ModTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DispenserBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(TintedCampfires.MOD_ID)
public class TintedCampfiresNeoForge {

    public TintedCampfiresNeoForge(IEventBus eventBus) {

        TintedCampfires.LOG.info("Hello NeoForge world!");
        TintedCampfires.init();
        eventBus.addListener(this::setup);

        ModTabs.init(eventBus);

    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            DispenserBlock.registerBehavior(Items.FLINT_AND_STEEL, new CampfireDispenseBehavior());
            Items.DYE.forEach(dye -> DispenserBlock.registerBehavior(dye, new SetColorDispenseBehavior()));
        });

    }
}