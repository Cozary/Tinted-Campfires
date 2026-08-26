package com.cozary.tintedcampfires.client;

import com.cozary.tintedcampfires.dispenser.CampfireDispenseBehavior;
import com.cozary.tintedcampfires.dispenser.SetColorDispenseBehavior;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DispenserBlock;

public class DispenserRegister implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        DispenserBlock.registerBehavior(Items.FLINT_AND_STEEL, new CampfireDispenseBehavior());
        Items.DYE.forEach(dye -> DispenserBlock.registerBehavior(dye, new SetColorDispenseBehavior()));
    }
}
