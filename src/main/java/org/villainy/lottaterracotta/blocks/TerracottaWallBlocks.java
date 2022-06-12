package org.villainy.lottaterracotta.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.villainy.lottaterracotta.block.TerracottaWallBlock;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class TerracottaWallBlocks {
    public static final TerracottaWallBlock UNCOLORED_TERRACOTTA_WALL_BLOCK = register(null);
    public static final HashMap<DyeColor, TerracottaWallBlock> COLORED_TERRACOTTA_WALL_BLOCKS = getColoredTerracottaWallBlocks();

    private static TerracottaWallBlock register(@Nullable DyeColor color) {
        if(color == null) {
            TerracottaWallBlock block = Registry.register(Registry.BLOCK, new Identifier("lottaterracotta:terracotta_wall"), new TerracottaWallBlock());
            Registry.register(Registry.ITEM, new Identifier("lottaterracotta:terracotta_wall"), new BlockItem(block, new FabricItemSettings().group(ItemGroup.SEARCH)));
            return block;
        } else {
            TerracottaWallBlock block = Registry.register(Registry.BLOCK, new Identifier("lottaterracotta", color.getName() + "_terracotta_wall"), new TerracottaWallBlock(color));
            Registry.register(Registry.ITEM, new Identifier("lottaterracotta", color.getName() + "_terracotta_wall"), new BlockItem(block, new FabricItemSettings().group(ItemGroup.SEARCH)));
            return block;
        }
    }
    private static HashMap<DyeColor, TerracottaWallBlock> getColoredTerracottaWallBlocks() {
        HashMap<DyeColor, TerracottaWallBlock> map = new HashMap<>();
        Stream.of(DyeColor.values()).forEach(dyeColor -> map.put(dyeColor, register(dyeColor)));
        return map;
    }
}
