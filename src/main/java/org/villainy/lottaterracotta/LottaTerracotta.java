package org.villainy.lottaterracotta;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.DyeColor;
import org.villainy.lottaterracotta.block.*;

public class LottaTerracotta implements ModInitializer {
    @Override
    public void onInitialize() {
        if(!(TerracottaSlabBlocks.UNCOLORED_TERRACOTTA_SLAB_BLOCK != null &&
                TerracottaVerticalSlabBlocks.UNCOLORED_TERRACOTTA_VERTICAL_SLAB_BLOCK != null &&
                TerracottaStairsBlocks.UNCOLORED_TERRACOTTA_STAIRS_BLOCK != null &&
                TerracottaWallBlocks.UNCOLORED_TERRACOTTA_WALL_BLOCK != null &&
                TerracottaFenceBlocks.UNCOLORED_TERRACOTTA_FENCE_BLOCK != null &&
                TerracottaFenceGateBlocks.UNCOLORED_TERRACOTTA_FENCE_GATE_BLOCK != null &&
                TerracottaLadderBlocks.UNCOLORED_TERRACOTTA_LADDER_BLOCK != null &&
                TerracottaPressurePlateBlocks.UNCOLORED_TERRACOTTA_PRESSURE_PLATE_BLOCK != null &&
                TerracottaLeverBlocks.UNCOLORED_TERRACOTTA_LEVER_BLOCK != null &&
                TerracottaButtonBlocks.UNCOLORED_TERRACOTTA_BUTTON_BLOCK != null &&
                GlazedTerracottaSlabBlocks.GLAZED_TERRACOTTA_SLAB_BLOCKS.get(DyeColor.WHITE) != null &&
                GlazedTerracottaTileBlocks.GLAZED_TERRACOTTA_TILE_BLOCKS.get(DyeColor.WHITE) != null))
        {
            System.err.println("Lotta Terracotta failed to load!");
        }
    }
}
