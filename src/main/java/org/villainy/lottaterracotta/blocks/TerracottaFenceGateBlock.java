package org.villainy.lottaterracotta.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.villainy.lottaterracotta.config.LottaTerracottaConfig;
import org.villainy.lottaterracotta.objectholders.TerracottaFenceGateBlocks;

import java.util.stream.Stream;

public class TerracottaFenceGateBlock extends FenceGateBlock {

    private boolean isEnabled() {
        return LottaTerracottaConfig.enableFences;
    }

    public TerracottaFenceGateBlock(DyeColor dyeColor) {
        super(Block.Properties.create(Material.ROCK, dyeColor)
                .hardnessAndResistance(2.0F, 6.0F)
                .sound(SoundType.STONE));
        setRegistryName(dyeColor.getName() + "_terracotta_fence_gate");
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (group == ItemGroup.SEARCH || isEnabled())
            super.fillItemGroup(group, items);
    }

    public static Stream<Block> allBlocks() {
        return Stream.of(
                TerracottaFenceGateBlocks.WHITE,
                TerracottaFenceGateBlocks.ORANGE,
                TerracottaFenceGateBlocks.MAGENTA,
                TerracottaFenceGateBlocks.LIGHT_BLUE,
                TerracottaFenceGateBlocks.YELLOW,
                TerracottaFenceGateBlocks.LIME,
                TerracottaFenceGateBlocks.PINK,
                TerracottaFenceGateBlocks.GRAY,
                TerracottaFenceGateBlocks.LIGHT_GRAY,
                TerracottaFenceGateBlocks.CYAN,
                TerracottaFenceGateBlocks.PURPLE,
                TerracottaFenceGateBlocks.BLUE,
                TerracottaFenceGateBlocks.BROWN,
                TerracottaFenceGateBlocks.GREEN,
                TerracottaFenceGateBlocks.RED,
                TerracottaFenceGateBlocks.BLACK
        );
    }
}
