package org.villainy.lottaterracotta.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.villainy.lottaterracotta.block.TerracottaPressurePlateBlock;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class TerracottaPressurePlateBlocks {
    public static final TerracottaPressurePlateBlock UNCOLORED_TERRACOTTA_PRESSURE_PLATE_BLOCK = register(null);
    public static final HashMap<DyeColor, TerracottaPressurePlateBlock> COLORED_TERRACOTTA_PRESSURE_PLATE_BLOCKS = getColoredTerracottaPressurePlateBlocks();

    private static TerracottaPressurePlateBlock register(@Nullable DyeColor color) {
        if(color == null) {
            TerracottaPressurePlateBlock block = Registry.register(Registry.BLOCK, new Identifier("lottaterracotta:terracotta_pressure_plate"), new TerracottaPressurePlateBlock());
            Registry.register(Registry.ITEM, new Identifier("lottaterracotta:terracotta_pressure_plate"), new BlockItem(block, new FabricItemSettings().group(ItemGroup.SEARCH)));
            return block;
        } else {
            TerracottaPressurePlateBlock block = Registry.register(Registry.BLOCK, new Identifier("lottaterracotta", color.getName() + "_terracotta_pressure_plate"), new TerracottaPressurePlateBlock(color));
            Registry.register(Registry.ITEM, new Identifier("lottaterracotta", color.getName() + "_terracotta_pressure_plate"), new BlockItem(block, new FabricItemSettings().group(ItemGroup.SEARCH)));
            return block;
        }
    }
    private static HashMap<DyeColor, TerracottaPressurePlateBlock> getColoredTerracottaPressurePlateBlocks() {
        HashMap<DyeColor, TerracottaPressurePlateBlock> map = new HashMap<>();
        Stream.of(DyeColor.values()).forEach(dyeColor -> map.put(dyeColor, register(dyeColor)));
        return map;
    }
}
