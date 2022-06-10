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
public class TerracottaSignBlocks {
    public static final TerracottaSignBlock UNCOLORED_TERRACOTTA_SIGN_BLOCK = register(null);
    public static final HashMap<DyeColor, TerracottaSignBlock> COLORED_TERRACOTTA_SIGN_BLOCKS = getColoredTerracottaSignBlocks();
    private static TerracottaSignBlock register(@Nullable DyeColor color) {
        if(color == null) {
            TerracottaSignBlock block = Registry.register(Registry.BLOCK, new Identifier("lottaterracotta:terracotta_sign"), new TerracottaSignBlock());
            Registry.register(Registry.ITEM, new Identifier("lottaterracotta:terracotta_sign"), new BlockItem(block, new FabricItemSettings().group(ItemGroup.SEARCH)));
            return block;
        } else {
            TerracottaSignBlock block = Registry.register(Registry.BLOCK, new Identifier("lottaterracotta", color.getName() + "_terracotta_sign"), new TerracottaSignBlock(color));
            Registry.register(Registry.ITEM, new Identifier("lottaterracotta", color.getName() + "_terracotta_sign"), new BlockItem(block, new FabricItemSettings().group(ItemGroup.SEARCH)));
            return block;
        }
    }
    private static HashMap<DyeColor, TerracottaSignBlock> getColoredTerracottaSignBlocks() {
        HashMap<DyeColor, TerracottaSignBlock> map = new HashMap<>();
        Stream.of(DyeColor.values()).forEach(dyeColor -> {
            map.put(dyeColor, register(dyeColor));
        });
        return map;
    }
}
