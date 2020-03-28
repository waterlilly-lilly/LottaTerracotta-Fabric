package org.villainy.lottaterracotta.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.villainy.lottaterracotta.config.LottaTerracottaConfig;
import org.villainy.lottaterracotta.objectholders.TerracottaPressurePlateBlocks;

import java.util.stream.Stream;

public class TerracottaPressurePlateBlock extends PressurePlateBlock {

    private boolean isEnabled() {
        return LottaTerracottaConfig.enablePressurePlates;
    }

    public TerracottaPressurePlateBlock(DyeColor dyeColor) {
        super(Sensitivity.EVERYTHING,
                Block.Properties.create(Material.ROCK, dyeColor)
                        .doesNotBlockMovement()
                        .hardnessAndResistance(0.5F));
        setRegistryName(dyeColor.getName() + "_terracotta_pressure_plate");
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (group == ItemGroup.SEARCH || isEnabled())
            super.fillItemGroup(group, items);
    }

    public static Stream<Block> allBlocks() {
        return Stream.of(
                TerracottaPressurePlateBlocks.WHITE,
                TerracottaPressurePlateBlocks.ORANGE,
                TerracottaPressurePlateBlocks.MAGENTA,
                TerracottaPressurePlateBlocks.LIGHT_BLUE,
                TerracottaPressurePlateBlocks.YELLOW,
                TerracottaPressurePlateBlocks.LIME,
                TerracottaPressurePlateBlocks.PINK,
                TerracottaPressurePlateBlocks.GRAY,
                TerracottaPressurePlateBlocks.LIGHT_GRAY,
                TerracottaPressurePlateBlocks.CYAN,
                TerracottaPressurePlateBlocks.PURPLE,
                TerracottaPressurePlateBlocks.BLUE,
                TerracottaPressurePlateBlocks.BROWN,
                TerracottaPressurePlateBlocks.GREEN,
                TerracottaPressurePlateBlocks.RED,
                TerracottaPressurePlateBlocks.BLACK
        );
    }
}
