package com.cozary.tintedcampfires.event;

import com.cozary.tintedcampfires.util.CampfireDyeHandler;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;

public class RightClickTintedCampfireEvent {

    public static void loadEvent() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> CampfireDyeHandler.tryReplaceCampfire(player, world, hand, hitResult.getBlockPos()));

    }

}
