package com.cozary.tintedcampfires;

import com.cozary.tintedcampfires.event.RightClickTintedCampfireEvent;
import com.cozary.tintedcampfires.init.ModBlocks;
import com.cozary.tintedcampfires.init.ModItems;
import com.cozary.tintedcampfires.init.ModTabs;
import com.cozary.tintedcampfires.init.particles.ModParticles;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.CreativeModeTab;
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

public class TintedCampfiresFabric implements ModInitializer {

    @Override
    public void onInitialize() {

        TintedCampfires.init();

        ModTabs.loadClass();

        RightClickTintedCampfireEvent.loadEvent();
    }
}
