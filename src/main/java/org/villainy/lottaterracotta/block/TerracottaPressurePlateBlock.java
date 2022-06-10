package org.villainy.lottaterracotta.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.util.DyeColor;

public class TerracottaPressurePlateBlock extends PressurePlateBlock {
    public TerracottaPressurePlateBlock(DyeColor color) {
        super(ActivationRule.EVERYTHING, FabricBlockSettings.of(Material.STONE).mapColor(color).hardness(0.5f));
    }
    public TerracottaPressurePlateBlock() {
        super(ActivationRule.EVERYTHING, FabricBlockSettings.of(Material.STONE).mapColor(MapColor.ORANGE).hardness(0.5f));
    }
}
