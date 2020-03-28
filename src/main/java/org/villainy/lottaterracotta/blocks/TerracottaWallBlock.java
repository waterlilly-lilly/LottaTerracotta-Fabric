package org.villainy.lottaterracotta.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.WallBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.villainy.lottaterracotta.config.LottaTerracottaConfig;
import org.villainy.lottaterracotta.objectholders.TerracottaWallBlocks;

import java.util.stream.Stream;

public class TerracottaWallBlock extends WallBlock {

    private boolean isEnabled() {
        return LottaTerracottaConfig.enableWalls;
    }

    public TerracottaWallBlock(DyeColor dyeColor) {
        super(Block.Properties.create(Material.ROCK, dyeColor).hardnessAndResistance(1.8F));
        setRegistryName(dyeColor.getName() + "_terracotta_wall");
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (group == ItemGroup.SEARCH || isEnabled())
            super.fillItemGroup(group, items);
    }

    public static Stream<Block> allBlocks() {
        return Stream.of(
                TerracottaWallBlocks.WHITE,
                TerracottaWallBlocks.ORANGE,
                TerracottaWallBlocks.MAGENTA,
                TerracottaWallBlocks.LIGHT_BLUE,
                TerracottaWallBlocks.YELLOW,
                TerracottaWallBlocks.LIME,
                TerracottaWallBlocks.PINK,
                TerracottaWallBlocks.GRAY,
                TerracottaWallBlocks.LIGHT_GRAY,
                TerracottaWallBlocks.CYAN,
                TerracottaWallBlocks.PURPLE,
                TerracottaWallBlocks.BLUE,
                TerracottaWallBlocks.BROWN,
                TerracottaWallBlocks.GREEN,
                TerracottaWallBlocks.RED,
                TerracottaWallBlocks.BLACK
        );
    }
}
