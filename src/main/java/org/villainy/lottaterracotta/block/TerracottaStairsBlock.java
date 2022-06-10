package org.villainy.lottaterracotta.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.util.DyeColor;

public class TerracottaStairsBlock extends StairsBlock {
    public TerracottaStairsBlock(DyeColor color, BlockState state) {
        super(state, FabricBlockSettings.of(Material.STONE).mapColor(color).hardness(1.8f));
    }
    public TerracottaStairsBlock(BlockState state) {
        super(state, FabricBlockSettings.of(Material.STONE).mapColor(MapColor.ORANGE).hardness(1.8f));
    }
}
