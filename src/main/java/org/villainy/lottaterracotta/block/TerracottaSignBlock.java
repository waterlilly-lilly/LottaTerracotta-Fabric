package org.villainy.lottaterracotta.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.villainy.lottaterracotta.block.sign.TerracottaSignType;

public class TerracottaSignBlock extends SignBlock {
    public TerracottaSignBlock() {
        super(FabricBlockSettings.of(Material.STONE).resistance(0.5f).collidable(false), new TerracottaSignType(new Identifier("lottaterracotta:terracotta")));
    }
    public TerracottaSignBlock(DyeColor color) {
        super(FabricBlockSettings.of(Material.STONE).resistance(0.5f).collidable(false), new TerracottaSignType(new Identifier("lottaterracotta", color.getName() + "_terracotta")));
    }
}
