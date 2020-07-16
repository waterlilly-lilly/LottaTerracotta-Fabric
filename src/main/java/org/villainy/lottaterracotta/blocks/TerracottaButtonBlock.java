package org.villainy.lottaterracotta.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.StoneButtonBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.villainy.lottaterracotta.config.LottaTerracottaConfig;
import org.villainy.lottaterracotta.objectholders.TerracottaButtonBlocks;

import java.util.stream.Stream;

public class TerracottaButtonBlock extends StoneButtonBlock {

    private boolean isEnabled() {
        return LottaTerracottaConfig.enableButtons;
    }

    public TerracottaButtonBlock(DyeColor dyeColor) {
        super(Block.Properties.create(Material.ROCK, dyeColor));
        setRegistryName(dyeColor.getTranslationKey() + "_terracotta_button");
    }

    public TerracottaButtonBlock() {
        super(Block.Properties.create(Material.ROCK, MaterialColor.ADOBE));
        setRegistryName("terracotta_button");
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if(group == ItemGroup.SEARCH || isEnabled())
            super.fillItemGroup(group, items);
    }

    public static Stream<Block> allBlocks() {
        return Stream.of(
                TerracottaButtonBlocks.UNCOLORED,
                TerracottaButtonBlocks.WHITE,
                TerracottaButtonBlocks.ORANGE,
                TerracottaButtonBlocks.MAGENTA,
                TerracottaButtonBlocks.LIGHT_BLUE,
                TerracottaButtonBlocks.YELLOW,
                TerracottaButtonBlocks.LIME,
                TerracottaButtonBlocks.PINK,
                TerracottaButtonBlocks.GRAY,
                TerracottaButtonBlocks.LIGHT_GRAY,
                TerracottaButtonBlocks.CYAN,
                TerracottaButtonBlocks.PURPLE,
                TerracottaButtonBlocks.BLUE,
                TerracottaButtonBlocks.BROWN,
                TerracottaButtonBlocks.GREEN,
                TerracottaButtonBlocks.RED,
                TerracottaButtonBlocks.BLACK
        );
    }
}
