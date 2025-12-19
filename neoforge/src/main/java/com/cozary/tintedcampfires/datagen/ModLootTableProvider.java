package com.cozary.tintedcampfires.datagen;

import com.cozary.tintedcampfires.init.ModBlocks;
import com.cozary.tintedcampfires.init.RegistryObject;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Set;

public class ModLootTableProvider extends BlockLootSubProvider {
    public ModLootTableProvider(HolderLookup.Provider lookupProvider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), lookupProvider);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries()
                .stream()
                .map(RegistryObject::get)
                .toList();
    }

    @Override
    protected void generate() {
        for (var blockHolder : ModBlocks.BLOCKS.getEntries()) {
            Block block = blockHolder.get();

            this.add(block, createSilkTouchDispatchTable(
                    block,
                    this.applyExplosionCondition(block,
                            LootItem.lootTableItem(Items.CHARCOAL)
                                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0f)))
                    )
            ));
        }
    }
}
