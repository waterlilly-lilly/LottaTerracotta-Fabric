package org.villainy.lottaterracotta.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.villainy.lottaterracotta.config.LottaTerracottaConfig;
import org.villainy.lottaterracotta.objectholders.TerracottaFenceBlocks;

import java.util.stream.Stream;

public class TerracottaFenceBlock extends FenceBlock {

    private boolean isEnabled() {
        return LottaTerracottaConfig.enableFences;
    }

    public TerracottaFenceBlock(DyeColor dyeColor) {
        super(Block.Properties.create(Material.ROCK, dyeColor).hardnessAndResistance(2.0F, 6.0F));
        setRegistryName(dyeColor.getName() + "_terracotta_fence");
    }

    public TerracottaFenceBlock() {
        super(Block.Properties.create(Material.ROCK, MaterialColor.ADOBE).hardnessAndResistance(2.0F, 6.0F));
        setRegistryName("terracotta_fence");
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (group == ItemGroup.SEARCH || isEnabled())
            super.fillItemGroup(group, items);
    }

    public static Stream<Block> allBlocks() {
        return Stream.of(
                TerracottaFenceBlocks.UNCOLORED,
                TerracottaFenceBlocks.WHITE,
                TerracottaFenceBlocks.ORANGE,
                TerracottaFenceBlocks.MAGENTA,
                TerracottaFenceBlocks.LIGHT_BLUE,
                TerracottaFenceBlocks.YELLOW,
                TerracottaFenceBlocks.LIME,
                TerracottaFenceBlocks.PINK,
                TerracottaFenceBlocks.GRAY,
                TerracottaFenceBlocks.LIGHT_GRAY,
                TerracottaFenceBlocks.CYAN,
                TerracottaFenceBlocks.PURPLE,
                TerracottaFenceBlocks.BLUE,
                TerracottaFenceBlocks.BROWN,
                TerracottaFenceBlocks.GREEN,
                TerracottaFenceBlocks.RED,
                TerracottaFenceBlocks.BLACK
        );
    }
}
