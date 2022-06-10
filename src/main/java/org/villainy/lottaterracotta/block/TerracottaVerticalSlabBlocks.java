package org.villainy.lottaterracotta.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class TerracottaVerticalSlabBlocks {
    public static final TerracottaVerticalSlabBlock UNCOLORED_TERRACOTTA_VERTICAL_SLAB_BLOCK = register(null);
    public static final HashMap<DyeColor, TerracottaVerticalSlabBlock> COLORED_TERRACOTTA_VERTICAL_SLAB_BLOCKS = getColoredTerracottaSlabBlocks();

    private static TerracottaVerticalSlabBlock register(@Nullable DyeColor color) {
        if(color == null) {
            TerracottaVerticalSlabBlock block = Registry.register(Registry.BLOCK, new Identifier("lottaterracotta:terracotta_vertical_slab"), new TerracottaVerticalSlabBlock());
            Registry.register(Registry.ITEM, new Identifier("lottaterracotta:terracotta_vertical_slab"), new BlockItem(block, new FabricItemSettings().group(ItemGroup.SEARCH)));
            return block;
        } else {
            TerracottaVerticalSlabBlock block = Registry.register(Registry.BLOCK, new Identifier("lottaterracotta", color.getName() + "_terracotta_vertical_slab"), new TerracottaVerticalSlabBlock(color));
            Registry.register(Registry.ITEM, new Identifier("lottaterracotta", color.getName() + "_terracotta_vertical_slab"), new BlockItem(block, new FabricItemSettings().group(ItemGroup.SEARCH)));
            return block;
        }
    }
    private static HashMap<DyeColor, TerracottaVerticalSlabBlock> getColoredTerracottaSlabBlocks() {
        HashMap<DyeColor, TerracottaVerticalSlabBlock> map = new HashMap<>();
        Stream.of(DyeColor.values()).forEach(dyeColor -> {
            map.put(dyeColor, register(dyeColor));
        });
        return map;
    }
}
