package org.villainy.lottaterracotta.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.LeverBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.villainy.lottaterracotta.config.LottaTerracottaConfig;
import org.villainy.lottaterracotta.objectholders.TerracottaLeverBlocks;

import java.util.stream.Stream;

public class TerracottaLeverBlock extends LeverBlock {
    private boolean isEnabled() {
        return LottaTerracottaConfig.enableLevers;
    }

    public TerracottaLeverBlock(DyeColor dyeColor) {
        super(Block.Properties.create(Material.ROCK, dyeColor));
        setRegistryName(dyeColor.getName() + "_terracotta_lever");
    }

    public TerracottaLeverBlock() {
        super(Block.Properties.create(Material.ROCK, MaterialColor.ADOBE));
        setRegistryName("terracotta_lever");
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if(group == ItemGroup.SEARCH || isEnabled())
            super.fillItemGroup(group, items);
    }

    public static Stream<Block> allBlocks() {
        return Stream.of(
                TerracottaLeverBlocks.UNCOLORED,
                TerracottaLeverBlocks.WHITE,
                TerracottaLeverBlocks.ORANGE,
                TerracottaLeverBlocks.MAGENTA,
                TerracottaLeverBlocks.LIGHT_BLUE,
                TerracottaLeverBlocks.YELLOW,
                TerracottaLeverBlocks.LIME,
                TerracottaLeverBlocks.PINK,
                TerracottaLeverBlocks.GRAY,
                TerracottaLeverBlocks.LIGHT_GRAY,
                TerracottaLeverBlocks.CYAN,
                TerracottaLeverBlocks.PURPLE,
                TerracottaLeverBlocks.BLUE,
                TerracottaLeverBlocks.BROWN,
                TerracottaLeverBlocks.GREEN,
                TerracottaLeverBlocks.RED,
                TerracottaLeverBlocks.BLACK
        );
    }
}
