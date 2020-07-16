package org.villainy.lottaterracotta.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.villainy.lottaterracotta.config.LottaTerracottaConfig;
import org.villainy.lottaterracotta.objectholders.TerracottaStairsBlocks;

import java.util.stream.Stream;

public class TerracottaStairsBlock extends StairsBlock {

    private boolean isEnabled() {
        return LottaTerracottaConfig.enableStairs;
    }

    public TerracottaStairsBlock(DyeColor dyeColor, BlockState blockState) {
        super(blockState, Block.Properties.create(Material.ROCK, dyeColor).hardnessAndResistance(1.8F));
        setRegistryName(dyeColor.getTranslationKey() + "_terracotta_stairs");
    }

    public TerracottaStairsBlock(BlockState blockState) {
        super(blockState, Block.Properties.create(Material.ROCK, MaterialColor.ADOBE).hardnessAndResistance(1.8F));
        setRegistryName("terracotta_stairs");
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (group == ItemGroup.SEARCH || isEnabled())
            super.fillItemGroup(group, items);
    }

    public static Stream<Block> allBlocks() {
        return Stream.of(
                TerracottaStairsBlocks.UNCOLORED,
                TerracottaStairsBlocks.WHITE,
                TerracottaStairsBlocks.ORANGE,
                TerracottaStairsBlocks.MAGENTA,
                TerracottaStairsBlocks.LIGHT_BLUE,
                TerracottaStairsBlocks.YELLOW,
                TerracottaStairsBlocks.LIME,
                TerracottaStairsBlocks.PINK,
                TerracottaStairsBlocks.GRAY,
                TerracottaStairsBlocks.LIGHT_GRAY,
                TerracottaStairsBlocks.CYAN,
                TerracottaStairsBlocks.PURPLE,
                TerracottaStairsBlocks.BLUE,
                TerracottaStairsBlocks.BROWN,
                TerracottaStairsBlocks.GREEN,
                TerracottaStairsBlocks.RED,
                TerracottaStairsBlocks.BLACK
        );
    }
}
