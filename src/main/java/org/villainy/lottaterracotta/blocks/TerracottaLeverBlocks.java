package org.villainy.lottaterracotta.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.villainy.lottaterracotta.block.TerracottaLeverBlock;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class TerracottaLeverBlocks {
    public static final TerracottaLeverBlock UNCOLORED_TERRACOTTA_LEVER_BLOCK = register(null);
    public static final HashMap<DyeColor, TerracottaLeverBlock> COLORED_TERRACOTTA_LEVER_BLOCKS = getColoredTerracottaLeverBlocks();

    private static TerracottaLeverBlock register(@Nullable DyeColor color) {
        if(color == null) {
            TerracottaLeverBlock block = Registry.register(Registry.BLOCK, new Identifier("lottaterracotta:terracotta_lever"), new TerracottaLeverBlock());
            Registry.register(Registry.ITEM, new Identifier("lottaterracotta:terracotta_lever"), new BlockItem(block, new FabricItemSettings().group(ItemGroup.SEARCH)));
            return block;
        } else {
            TerracottaLeverBlock block = Registry.register(Registry.BLOCK, new Identifier("lottaterracotta", color.getName() + "_terracotta_lever"), new TerracottaLeverBlock(color));
            Registry.register(Registry.ITEM, new Identifier("lottaterracotta", color.getName() + "_terracotta_lever"), new BlockItem(block, new FabricItemSettings().group(ItemGroup.SEARCH)));
            return block;
        }
    }
    private static HashMap<DyeColor, TerracottaLeverBlock> getColoredTerracottaLeverBlocks() {
        HashMap<DyeColor, TerracottaLeverBlock> map = new HashMap<>();
        Stream.of(DyeColor.values()).forEach(dyeColor -> map.put(dyeColor, register(dyeColor)));
        return map;
    }
}
