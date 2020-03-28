package org.villainy.lottaterracotta.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.villainy.lottaterracotta.config.LottaTerracottaConfig;
import org.villainy.lottaterracotta.objectholders.TerracottaLadderBlocks;

import java.util.stream.Stream;

public class TerracottaLadderBlock extends LadderBlock {

    private boolean isEnabled() {
        return LottaTerracottaConfig.enableLadders;
    }

    public TerracottaLadderBlock(DyeColor dyeColor) {
        super(Block.Properties.create(Material.ROCK, dyeColor).hardnessAndResistance(0.4F).sound(SoundType.LADDER));
        setRegistryName(dyeColor.getName() + "_terracotta_ladder");
    }

    public TerracottaLadderBlock() {
        super(Block.Properties.create(Material.ROCK, MaterialColor.ADOBE).hardnessAndResistance(0.4F).sound(SoundType.LADDER));
        setRegistryName("terracotta_ladder");
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (group == ItemGroup.SEARCH || isEnabled())
            super.fillItemGroup(group, items);
    }

    public static Stream<Block> allBlocks() {
        return Stream.of(
                TerracottaLadderBlocks.UNCOLORED,
                TerracottaLadderBlocks.WHITE,
                TerracottaLadderBlocks.ORANGE,
                TerracottaLadderBlocks.MAGENTA,
                TerracottaLadderBlocks.LIGHT_BLUE,
                TerracottaLadderBlocks.YELLOW,
                TerracottaLadderBlocks.LIME,
                TerracottaLadderBlocks.PINK,
                TerracottaLadderBlocks.GRAY,
                TerracottaLadderBlocks.LIGHT_GRAY,
                TerracottaLadderBlocks.CYAN,
                TerracottaLadderBlocks.PURPLE,
                TerracottaLadderBlocks.BLUE,
                TerracottaLadderBlocks.BROWN,
                TerracottaLadderBlocks.GREEN,
                TerracottaLadderBlocks.RED,
                TerracottaLadderBlocks.BLACK
        );
    }
}
