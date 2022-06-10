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
public class TerracottaFenceBlocks {
    public static final TerracottaFenceBlock UNCOLORED_TERRACOTTA_FENCE_BLOCK = register(null);
    public static final HashMap<DyeColor, TerracottaFenceBlock> COLORED_TERRACOTTA_FENCE_BLOCKS = getColoredTerracottaFenceBlocks();

    private static TerracottaFenceBlock register(@Nullable DyeColor color) {
        if(color == null) {
            TerracottaFenceBlock block = Registry.register(Registry.BLOCK, new Identifier("lottaterracotta:terracotta_fence"), new TerracottaFenceBlock());
            Registry.register(Registry.ITEM, new Identifier("lottaterracotta:terracotta_fence"), new BlockItem(block, new FabricItemSettings().group(ItemGroup.SEARCH)));
            return block;
        } else {
            TerracottaFenceBlock block = Registry.register(Registry.BLOCK, new Identifier("lottaterracotta", color.getName() + "_terracotta_fence"), new TerracottaFenceBlock(color));
            Registry.register(Registry.ITEM, new Identifier("lottaterracotta", color.getName() + "_terracotta_fence"), new BlockItem(block, new FabricItemSettings().group(ItemGroup.SEARCH)));
            return block;
        }
    }
    private static HashMap<DyeColor, TerracottaFenceBlock> getColoredTerracottaFenceBlocks() {
        HashMap<DyeColor, TerracottaFenceBlock> map = new HashMap<>();
        Stream.of(DyeColor.values()).forEach(dyeColor -> {
            map.put(dyeColor, register(dyeColor));
        });
        return map;
    }
}
