package com.cozary.tintedcampfires.block;

import com.cozary.tintedcampfires.TintedCampfires;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class BlockItemBase extends BlockItem {

    public BlockItemBase(Block block, String name) {
        super(block, new Properties()
                .setId(ResourceKey.create(
                        Registries.ITEM,
                        Identifier.fromNamespaceAndPath(TintedCampfires.MOD_ID, name)
                )));
    }
}
