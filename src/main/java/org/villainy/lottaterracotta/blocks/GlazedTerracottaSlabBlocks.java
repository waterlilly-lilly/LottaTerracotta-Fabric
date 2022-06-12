package org.villainy.lottaterracotta.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;
import org.villainy.lottaterracotta.block.GlazedTerracottaSlabBlock;

import java.util.HashMap;
import java.util.stream.Stream;

public class GlazedTerracottaSlabBlocks {
    public static final HashMap<DyeColor, GlazedTerracottaSlabBlock> GLAZED_TERRACOTTA_SLAB_BLOCKS = getGlazedTerracottaSlabBlocks();
    private static GlazedTerracottaSlabBlock register(@NotNull DyeColor color) {
        GlazedTerracottaSlabBlock block = Registry.register(Registry.BLOCK, new Identifier("lottaterracotta", color.getName() + "_glazed_terracotta_slab"), new GlazedTerracottaSlabBlock(color));
        Registry.register(Registry.ITEM, new Identifier("lottaterracotta", color.getName() + "_glazed_terracotta_slab"), new BlockItem(block, new FabricItemSettings().group(ItemGroup.SEARCH)));
        return block;
    }
    private static HashMap<DyeColor, GlazedTerracottaSlabBlock> getGlazedTerracottaSlabBlocks() {
        HashMap<DyeColor, GlazedTerracottaSlabBlock> map = new HashMap<>();
        Stream.of(DyeColor.values()).forEach(dyeColor -> map.put(dyeColor, register(dyeColor)));
        return map;
    }
}
