package org.villainy.lottaterracotta;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.DyeColor;
import org.villainy.lottaterracotta.block.*;

public class LottaTerracotta implements ModInitializer {
    @Override
    public void onInitialize() {
        boolean slabRegisterOk = TerracottaSlabBlocks.UNCOLORED_TERRACOTTA_SLAB_BLOCK != null;
        boolean wallRegisterOk = TerracottaWallBlocks.UNCOLORED_TERRACOTTA_WALL_BLOCK != null;
        boolean fenceRegisterOk = TerracottaFenceBlocks.UNCOLORED_TERRACOTTA_FENCE_BLOCK != null;
        boolean glazedSlabRegisterOk = GlazedTerracottaSlabBlocks.GLAZED_TERRACOTTA_SLAB_BLOCKS.get(DyeColor.WHITE) != null;
        boolean glazedTileRegisterOk = GlazedTerracottaTileBlocks.GLAZED_TERRACOTTA_TILE_BLOCKS.get(DyeColor.WHITE) != null;
    }
}
