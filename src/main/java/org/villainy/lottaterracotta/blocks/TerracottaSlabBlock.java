package org.villainy.lottaterracotta.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.villainy.lottaterracotta.config.LottaTerracottaConfig;
import org.villainy.lottaterracotta.objectholders.TerracottaSlabBlocks;

import java.util.stream.Stream;

public class TerracottaSlabBlock extends SlabBlock {

    private boolean isEnabled() {
        return LottaTerracottaConfig.enableSlabs;
    }

    public TerracottaSlabBlock(DyeColor dyeColor) {
        super(Block.Properties.create(Material.ROCK, dyeColor).hardnessAndResistance(1.8F));
        setRegistryName(dyeColor.getName() + "_terracotta_slab");
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (group == ItemGroup.SEARCH || isEnabled())
            super.fillItemGroup(group, items);
    }

    static public Stream<Block> allBlocks() {
        return Stream.of(
                TerracottaSlabBlocks.WHITE,
                TerracottaSlabBlocks.ORANGE,
                TerracottaSlabBlocks.MAGENTA,
                TerracottaSlabBlocks.LIGHT_BLUE,
                TerracottaSlabBlocks.YELLOW,
                TerracottaSlabBlocks.LIME,
                TerracottaSlabBlocks.PINK,
                TerracottaSlabBlocks.GRAY,
                TerracottaSlabBlocks.LIGHT_GRAY,
                TerracottaSlabBlocks.CYAN,
                TerracottaSlabBlocks.PURPLE,
                TerracottaSlabBlocks.BLUE,
                TerracottaSlabBlocks.BROWN,
                TerracottaSlabBlocks.GREEN,
                TerracottaSlabBlocks.RED,
                TerracottaSlabBlocks.BLACK
        );
    }
}
