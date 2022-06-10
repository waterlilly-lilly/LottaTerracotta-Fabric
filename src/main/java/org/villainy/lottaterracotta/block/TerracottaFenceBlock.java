package org.villainy.lottaterracotta.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.util.DyeColor;

public class TerracottaFenceBlock extends FenceBlock {
    public TerracottaFenceBlock(DyeColor color) {
        super(FabricBlockSettings.of(Material.STONE).mapColor(color).hardness(2.0f).resistance(6.0f));
    }
    public TerracottaFenceBlock() {
        super(FabricBlockSettings.of(Material.STONE).mapColor(MapColor.ORANGE).hardness(2.0f).resistance(6.0f));
    }
}
