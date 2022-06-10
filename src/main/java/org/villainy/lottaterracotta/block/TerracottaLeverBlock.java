package org.villainy.lottaterracotta.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.LeverBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.util.DyeColor;

public class TerracottaLeverBlock extends LeverBlock {
    public TerracottaLeverBlock(DyeColor color) {
        super(FabricBlockSettings.of(Material.STONE).mapColor(color));
    }
    public TerracottaLeverBlock() {
        super(FabricBlockSettings.of(Material.STONE).mapColor(MapColor.ORANGE));
    }
}
