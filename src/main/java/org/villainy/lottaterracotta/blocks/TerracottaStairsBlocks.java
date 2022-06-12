package org.villainy.lottaterracotta.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.villainy.lottaterracotta.block.TerracottaStairsBlock;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.stream.Stream;

import static org.villainy.lottaterracotta.blocks.TerracottaSlabBlocks.COLORED_TERRACOTTA_SLAB_BLOCKS;
import static org.villainy.lottaterracotta.blocks.TerracottaSlabBlocks.UNCOLORED_TERRACOTTA_SLAB_BLOCK;

@SuppressWarnings("unused")
public class TerracottaStairsBlocks {
    public static final TerracottaStairsBlock UNCOLORED_TERRACOTTA_STAIRS_BLOCK = register(null);
    public static final HashMap<DyeColor, TerracottaStairsBlock> COLORED_TERRACOTTA_WALL_BLOCKS = getColoredTerracottaWallBlocks();

    private static TerracottaStairsBlock register(@Nullable DyeColor color) {
        if(color == null) {
            TerracottaStairsBlock block = Registry.register(Registry.BLOCK, new Identifier("lottaterracotta:terracotta_stairs"), new TerracottaStairsBlock(UNCOLORED_TERRACOTTA_SLAB_BLOCK.getDefaultState()));
            Registry.register(Registry.ITEM, new Identifier("lottaterracotta:terracotta_stairs"), new BlockItem(block, new FabricItemSettings().group(ItemGroup.SEARCH)));
            return block;
        } else {
            TerracottaStairsBlock block = Registry.register(Registry.BLOCK, new Identifier("lottaterracotta", color.getName() + "_terracotta_stairs"), new TerracottaStairsBlock(color, COLORED_TERRACOTTA_SLAB_BLOCKS.get(color).getDefaultState()));
            Registry.register(Registry.ITEM, new Identifier("lottaterracotta", color.getName() + "_terracotta_stairs"), new BlockItem(block, new FabricItemSettings().group(ItemGroup.SEARCH)));
            return block;
        }
    }
    private static HashMap<DyeColor, TerracottaStairsBlock> getColoredTerracottaWallBlocks() {
        HashMap<DyeColor, TerracottaStairsBlock> map = new HashMap<>();
        Stream.of(DyeColor.values()).forEach(dyeColor -> map.put(dyeColor, register(dyeColor)));
        return map;
    }
}
