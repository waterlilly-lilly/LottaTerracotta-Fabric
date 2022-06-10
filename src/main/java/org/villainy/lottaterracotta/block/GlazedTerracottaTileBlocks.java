package org.villainy.lottaterracotta.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.stream.Stream;

public class GlazedTerracottaTileBlocks {
    public static final HashMap<DyeColor, GlazedTerracottaTileBlock> GLAZED_TERRACOTTA_TILE_BLOCKS = getGlazedTerracottaTileBlocks();
    private static GlazedTerracottaTileBlock register(@NotNull DyeColor color) {
        GlazedTerracottaTileBlock block = Registry.register(Registry.BLOCK, new Identifier("lottaterracotta", color.getName() + "_glazed_terracotta_tile"), new GlazedTerracottaTileBlock(color));
        Registry.register(Registry.ITEM, new Identifier("lottaterracotta", color.getName() + "_glazed_terracotta_tile"), new BlockItem(block, new FabricItemSettings().group(ItemGroup.SEARCH)));
        return block;
    }
    private static HashMap<DyeColor, GlazedTerracottaTileBlock> getGlazedTerracottaTileBlocks() {
        HashMap<DyeColor, GlazedTerracottaTileBlock> map = new HashMap<>();
        Stream.of(DyeColor.values()).forEach(dyeColor -> {
            map.put(dyeColor, register(dyeColor));
        });
        return map;
    }
}
