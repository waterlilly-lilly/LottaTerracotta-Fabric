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
public class TerracottaFenceGateBlocks {
    public static final TerracottaFenceGateBlock UNCOLORED_TERRACOTTA_FENCE_GATE_BLOCK = register(null);
    public static final HashMap<DyeColor, TerracottaFenceGateBlock> COLORED_TERRACOTTA_FENCE_GATE_BLOCKS = getColoredTerracottaFenceGateBlocks();

    private static TerracottaFenceGateBlock register(@Nullable DyeColor color) {
        if(color == null) {
            TerracottaFenceGateBlock block = Registry.register(Registry.BLOCK, new Identifier("lottaterracotta:terracotta_fence_gate"), new TerracottaFenceGateBlock());
            Registry.register(Registry.ITEM, new Identifier("lottaterracotta:terracotta_fence_gate"), new BlockItem(block, new FabricItemSettings().group(ItemGroup.SEARCH)));
            return block;
        } else {
            TerracottaFenceGateBlock block = Registry.register(Registry.BLOCK, new Identifier("lottaterracotta", color.getName() + "_terracotta_fence_gate"), new TerracottaFenceGateBlock(color));
            Registry.register(Registry.ITEM, new Identifier("lottaterracotta", color.getName() + "_terracotta_fence_gate"), new BlockItem(block, new FabricItemSettings().group(ItemGroup.SEARCH)));
            return block;
        }
    }
    private static HashMap<DyeColor, TerracottaFenceGateBlock> getColoredTerracottaFenceGateBlocks() {
        HashMap<DyeColor, TerracottaFenceGateBlock> map = new HashMap<>();
        Stream.of(DyeColor.values()).forEach(dyeColor -> {
            map.put(dyeColor, register(dyeColor));
        });
        return map;
    }
}
