package org.villainy.lottaterracotta.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.util.DyeColor;

public class TerracottaSlabBlock extends SlabBlock {
    public TerracottaSlabBlock(DyeColor color) {
        super(FabricBlockSettings.of(Material.STONE).mapColor(color).hardness(1.8f));
    }
    public TerracottaSlabBlock() {
        super(FabricBlockSettings.of(Material.STONE).mapColor(MapColor.ORANGE).hardness(1.8f));
    }
}
