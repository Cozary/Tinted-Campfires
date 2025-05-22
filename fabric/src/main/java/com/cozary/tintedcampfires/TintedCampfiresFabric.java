package com.cozary.tintedcampfires;

import com.cozary.tintedcampfires.event.RightClickTintedCampfireEvent;
import com.cozary.tintedcampfires.init.ModTabs;
import net.fabricmc.api.ModInitializer;

public class TintedCampfiresFabric implements ModInitializer {

    @Override
    public void onInitialize() {

        TintedCampfires.init();

        ModTabs.loadClass();

        RightClickTintedCampfireEvent.loadEvent();
    }
}
