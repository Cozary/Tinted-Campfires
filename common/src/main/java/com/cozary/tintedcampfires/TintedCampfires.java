package com.cozary.tintedcampfires;

import com.cozary.tintedcampfires.init.ModBlockEntities;
import com.cozary.tintedcampfires.init.ModBlocks;
import com.cozary.tintedcampfires.init.ModItems;
import com.cozary.tintedcampfires.init.particles.ModParticles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TintedCampfires {

    public static final String MOD_ID = "tintedcampfires";
    public static final String MOD_NAME = "Tinted Campfires";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {
        ModBlocks.loadClass();
        ModItems.loadClass();
        ModBlockEntities.loadClass();
        ModParticles.loadClass();

    }
}
