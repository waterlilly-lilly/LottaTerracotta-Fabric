package org.villainy.lottaterracotta.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.villainy.lottaterracotta.config.LottaTerracottaConfig;
import org.villainy.lottaterracotta.objectholders.TerracottaVerticalSlabBlocks;

import java.util.stream.Stream;

public class TerracottaVerticalSlabBlock extends VerticalSlabBlock {
    private boolean isEnabled() {
        return LottaTerracottaConfig.enableVerticalSlabs;
    }

    public TerracottaVerticalSlabBlock(DyeColor dyeColor) {
        super(Block.Properties.create(Material.ROCK, dyeColor).hardnessAndResistance(1.8F));
        setRegistryName(dyeColor.getTranslationKey() + "_terracotta_vertical_slab");
    }

    public TerracottaVerticalSlabBlock() {
        super(Block.Properties.create(Material.ROCK, MaterialColor.ADOBE).hardnessAndResistance(1.8F));
        setRegistryName("terracotta_vertical_slab");
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (group == ItemGroup.SEARCH || isEnabled())
            super.fillItemGroup(group, items);
    }

    static public Stream<Block> allBlocks() {
        return Stream.of(
                TerracottaVerticalSlabBlocks.UNCOLORED,
                TerracottaVerticalSlabBlocks.WHITE,
                TerracottaVerticalSlabBlocks.ORANGE,
                TerracottaVerticalSlabBlocks.MAGENTA,
                TerracottaVerticalSlabBlocks.LIGHT_BLUE,
                TerracottaVerticalSlabBlocks.YELLOW,
                TerracottaVerticalSlabBlocks.LIME,
                TerracottaVerticalSlabBlocks.PINK,
                TerracottaVerticalSlabBlocks.GRAY,
                TerracottaVerticalSlabBlocks.LIGHT_GRAY,
                TerracottaVerticalSlabBlocks.CYAN,
                TerracottaVerticalSlabBlocks.PURPLE,
                TerracottaVerticalSlabBlocks.BLUE,
                TerracottaVerticalSlabBlocks.BROWN,
                TerracottaVerticalSlabBlocks.GREEN,
                TerracottaVerticalSlabBlocks.RED,
                TerracottaVerticalSlabBlocks.BLACK
        );
    }
}

