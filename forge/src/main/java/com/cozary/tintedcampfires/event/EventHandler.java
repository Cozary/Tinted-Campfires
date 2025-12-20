package com.cozary.tintedcampfires.event;

import com.cozary.tintedcampfires.TintedCampfires;
import com.cozary.tintedcampfires.util.CampfireDyeHandler;
import net.minecraft.world.InteractionResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TintedCampfires.MOD_ID)
public class EventHandler {

    @SubscribeEvent
    public static void replaceCampfire(PlayerInteractEvent.RightClickBlock event) {
        InteractionResult result = CampfireDyeHandler.tryReplaceCampfire(
                event.getEntity(), event.getLevel(), event.getHand(), event.getPos()
        );
        if (result == InteractionResult.SUCCESS) {
            event.setCancellationResult(result);
        }
    }

}
