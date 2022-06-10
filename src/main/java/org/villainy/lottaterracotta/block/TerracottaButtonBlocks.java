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
public class TerracottaButtonBlocks {
    public static final TerracottaButtonBlock UNCOLORED_TERRACOTTA_BUTTON_BLOCK = register(null);
    public static final HashMap<DyeColor, TerracottaButtonBlock> COLORED_TERRACOTTA_BUTTON_BLOCKS = getColoredTerracottaButtonBlocks();

    private static TerracottaButtonBlock register(@Nullable DyeColor color) {
        if(color == null) {
            TerracottaButtonBlock block = Registry.register(Registry.BLOCK, new Identifier("lottaterracotta:terracotta_button"), new TerracottaButtonBlock());
            Registry.register(Registry.ITEM, new Identifier("lottaterracotta:terracotta_button"), new BlockItem(block, new FabricItemSettings().group(ItemGroup.SEARCH)));
            return block;
        } else {
            TerracottaButtonBlock block = Registry.register(Registry.BLOCK, new Identifier("lottaterracotta", color.getName() + "_terracotta_button"), new TerracottaButtonBlock(color));
            Registry.register(Registry.ITEM, new Identifier("lottaterracotta", color.getName() + "_terracotta_button"), new BlockItem(block, new FabricItemSettings().group(ItemGroup.SEARCH)));
            return block;
        }
    }
    private static HashMap<DyeColor, TerracottaButtonBlock> getColoredTerracottaButtonBlocks() {
        HashMap<DyeColor, TerracottaButtonBlock> map = new HashMap<>();
        Stream.of(DyeColor.values()).forEach(dyeColor -> {
            map.put(dyeColor, register(dyeColor));
        });
        return map;
    }
}
