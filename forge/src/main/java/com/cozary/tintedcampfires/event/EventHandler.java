package com.cozary.tintedcampfires.event;

import com.cozary.tintedcampfires.TintedCampfires;
import com.cozary.tintedcampfires.util.CampfireDyeHandler;
import net.minecraft.world.InteractionResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TintedCampfires.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler {


    @SubscribeEvent
    public void replaceCampfire(PlayerInteractEvent.RightClickBlock event) {
        InteractionResult result = CampfireDyeHandler.tryReplaceCampfire(
                event.getEntity(), event.getLevel(), event.getHand(), event.getPos()
        );
        if (result == InteractionResult.SUCCESS) {
            event.setCanceled(true);
            event.setCancellationResult(result);
        }
    }

}
