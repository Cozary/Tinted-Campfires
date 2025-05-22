package com.cozary.tintedcampfires.event;

import com.cozary.tintedcampfires.util.CampfireDyeHandler;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.function.Supplier;

@EventBusSubscriber(modid = "tintedcampfires")
public class EventHandler {


    @SubscribeEvent
    public static void replaceCampfire(PlayerInteractEvent.RightClickBlock event) {
        InteractionResult result = CampfireDyeHandler.tryReplaceCampfire(
                event.getEntity(), event.getLevel(), event.getHand(), event.getPos()
        );
        if (result == InteractionResult.SUCCESS) {
            event.setCanceled(true);
            event.setCancellationResult(result);
        }
    }

    public record CampfireData(Supplier<Block> blockSupplier, Supplier<ParticleOptions> particleSupplier) {
    }

}
