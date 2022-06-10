package org.villainy.lottaterracotta;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.DyeColor;
import org.villainy.lottaterracotta.block.*;

public class LottaTerracotta implements ModInitializer {
    @Override
    public void onInitialize() {
        assert TerracottaSlabBlocks.UNCOLORED_TERRACOTTA_SLAB_BLOCK != null;
        assert TerracottaStairsBlocks.UNCOLORED_TERRACOTTA_STAIRS_BLOCK != null;
        assert TerracottaWallBlocks.UNCOLORED_TERRACOTTA_WALL_BLOCK != null;
        assert TerracottaFenceBlocks.UNCOLORED_TERRACOTTA_FENCE_BLOCK != null;
        assert TerracottaFenceGateBlocks.UNCOLORED_TERRACOTTA_FENCE_GATE_BLOCK != null;
        assert TerracottaPressurePlateBlocks.UNCOLORED_TERRACOTTA_PRESSURE_PLATE_BLOCK != null;
        assert GlazedTerracottaSlabBlocks.GLAZED_TERRACOTTA_SLAB_BLOCKS.get(DyeColor.WHITE) != null;
        assert GlazedTerracottaTileBlocks.GLAZED_TERRACOTTA_TILE_BLOCKS.get(DyeColor.WHITE) != null;
    }
}
