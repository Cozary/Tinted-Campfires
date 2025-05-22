package com.cozary.tintedcampfires.event;

import com.cozary.tintedcampfires.TintedCampfiresFabric;
import com.cozary.tintedcampfires.init.ModBlocks;
import com.cozary.tintedcampfires.init.particles.ModParticles;
import com.cozary.tintedcampfires.util.CampfireDyeHandler;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static net.minecraft.world.level.block.CampfireBlock.FACING;
import static net.minecraft.world.level.block.CampfireBlock.LIT;

public class RightClickTintedCampfireEvent {

    public static void loadEvent(){
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> CampfireDyeHandler.tryReplaceCampfire(player, world, hand, hitResult.getBlockPos()));

    }

}
