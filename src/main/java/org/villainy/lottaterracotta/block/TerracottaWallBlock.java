package org.villainy.lottaterracotta.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.util.DyeColor;

public class TerracottaWallBlock extends WallBlock {
    public TerracottaWallBlock(DyeColor color) {
        super(FabricBlockSettings.of(Material.STONE).mapColor(color).hardness(1.8f));
    }
    public TerracottaWallBlock() {
        super(FabricBlockSettings.of(Material.STONE).mapColor(MapColor.ORANGE).hardness(1.8f));
    }
}
