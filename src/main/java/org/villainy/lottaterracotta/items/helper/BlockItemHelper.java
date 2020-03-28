package org.villainy.lottaterracotta.items.helper;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import java.util.Objects;

public class BlockItemHelper {

    public static Item createBasicBlockItem(Block block, ItemGroup group) {
        return new BlockItem(block, new Item.Properties().group(group))
                .setRegistryName(Objects.requireNonNull(block.getRegistryName()));
    }

}
