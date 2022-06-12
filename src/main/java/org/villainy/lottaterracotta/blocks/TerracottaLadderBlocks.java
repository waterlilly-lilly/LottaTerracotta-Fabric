package org.villainy.lottaterracotta.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.villainy.lottaterracotta.block.TerracottaLadderBlock;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class TerracottaLadderBlocks {
    public static final TerracottaLadderBlock UNCOLORED_TERRACOTTA_LADDER_BLOCK = register(null);
    public static final HashMap<DyeColor, TerracottaLadderBlock> COLORED_TERRACOTTA_LADDER_BLOCKS = getColoredTerracottaLadderBlocks();

    private static TerracottaLadderBlock register(@Nullable DyeColor color) {
        if(color == null) {
            TerracottaLadderBlock block = Registry.register(Registry.BLOCK, new Identifier("lottaterracotta:terracotta_ladder"), new TerracottaLadderBlock());
            Registry.register(Registry.ITEM, new Identifier("lottaterracotta:terracotta_ladder"), new BlockItem(block, new FabricItemSettings().group(ItemGroup.SEARCH)));
            return block;
        } else {
            TerracottaLadderBlock block = Registry.register(Registry.BLOCK, new Identifier("lottaterracotta", color.getName() + "_terracotta_ladder"), new TerracottaLadderBlock(color));
            Registry.register(Registry.ITEM, new Identifier("lottaterracotta", color.getName() + "_terracotta_ladder"), new BlockItem(block, new FabricItemSettings().group(ItemGroup.SEARCH)));
            return block;
        }
    }
    private static HashMap<DyeColor, TerracottaLadderBlock> getColoredTerracottaLadderBlocks() {
        HashMap<DyeColor, TerracottaLadderBlock> map = new HashMap<>();
        Stream.of(DyeColor.values()).forEach(dyeColor -> map.put(dyeColor, register(dyeColor)));
        return map;
    }
}
