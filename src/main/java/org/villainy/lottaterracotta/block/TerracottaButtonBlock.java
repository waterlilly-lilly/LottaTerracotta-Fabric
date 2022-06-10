package org.villainy.lottaterracotta.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.StoneButtonBlock;
import net.minecraft.util.DyeColor;

public class TerracottaButtonBlock extends StoneButtonBlock {
    public TerracottaButtonBlock(DyeColor color) {
        super(FabricBlockSettings.of(Material.STONE).mapColor(color));
    }
    public TerracottaButtonBlock() {
        super(FabricBlockSettings.of(Material.STONE).mapColor(MapColor.ORANGE));
    }
}
