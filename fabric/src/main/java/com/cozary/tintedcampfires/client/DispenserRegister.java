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
        DispenserBlock.registerBehavior(Items.BLACK_DYE, new SetColorDispenseBehavior());
        DispenserBlock.registerBehavior(Items.RED_DYE, new SetColorDispenseBehavior());
        DispenserBlock.registerBehavior(Items.GREEN_DYE, new SetColorDispenseBehavior());
        DispenserBlock.registerBehavior(Items.BROWN_DYE, new SetColorDispenseBehavior());
        DispenserBlock.registerBehavior(Items.BLUE_DYE, new SetColorDispenseBehavior());
        DispenserBlock.registerBehavior(Items.PURPLE_DYE, new SetColorDispenseBehavior());
        DispenserBlock.registerBehavior(Items.CYAN_DYE, new SetColorDispenseBehavior());
        DispenserBlock.registerBehavior(Items.LIGHT_GRAY_DYE, new SetColorDispenseBehavior());
        DispenserBlock.registerBehavior(Items.GRAY_DYE, new SetColorDispenseBehavior());
        DispenserBlock.registerBehavior(Items.PINK_DYE, new SetColorDispenseBehavior());
        DispenserBlock.registerBehavior(Items.LIME_DYE, new SetColorDispenseBehavior());
        DispenserBlock.registerBehavior(Items.YELLOW_DYE, new SetColorDispenseBehavior());
        DispenserBlock.registerBehavior(Items.LIGHT_BLUE_DYE, new SetColorDispenseBehavior());
        DispenserBlock.registerBehavior(Items.MAGENTA_DYE, new SetColorDispenseBehavior());
        DispenserBlock.registerBehavior(Items.ORANGE_DYE, new SetColorDispenseBehavior());
        DispenserBlock.registerBehavior(Items.WHITE_DYE, new SetColorDispenseBehavior());
    }
}
