package org.villainy.lottaterracotta.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class TerracottaSlabBlocks {
    public static final TerracottaSlabBlock UNCOLORED_TERRACOTTA_SLAB_BLOCK = register(null);
    public static final HashMap<DyeColor, TerracottaSlabBlock> COLORED_TERRACOTTA_SLAB_BLOCKS = getColoredTerracottaSlabBlocks();

    private static TerracottaSlabBlock register(DyeColor color) {
        if(color == null) {
            TerracottaSlabBlock block = Registry.register(Registry.BLOCK, new Identifier("lottaterracotta:terracotta_slab"), new TerracottaSlabBlock());
            Registry.register(Registry.ITEM, new Identifier("lottaterracotta:terracotta_slab"), new BlockItem(block, new FabricItemSettings().group(ItemGroup.SEARCH)));
            return block;
        } else {
            TerracottaSlabBlock block = Registry.register(Registry.BLOCK, new Identifier("lottaterracotta", color.getName() + "_terracotta_slab"), new TerracottaSlabBlock(color));
            Registry.register(Registry.ITEM, new Identifier("lottaterracotta", color.getName() + "_terracotta_slab"), new BlockItem(block, new FabricItemSettings().group(ItemGroup.SEARCH)));
            return block;
        }
    }
    private static HashMap<DyeColor, TerracottaSlabBlock> getColoredTerracottaSlabBlocks() {
        HashMap<DyeColor, TerracottaSlabBlock> map = new HashMap<>();
        Stream.of(DyeColor.values()).forEach(dyeColor -> {
            map.put(dyeColor, register(dyeColor));
        });
        return map;
    }
}
