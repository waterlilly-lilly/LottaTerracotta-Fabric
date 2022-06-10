package org.villainy.lottaterracotta;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.DyeColor;
import org.villainy.lottaterracotta.block.GlazedTerracottaSlabBlocks;
import org.villainy.lottaterracotta.block.GlazedTerracottaTileBlocks;
import org.villainy.lottaterracotta.block.TerracottaSlabBlocks;

public class LottaTerracotta implements ModInitializer {
    @Override
    public void onInitialize() {
        boolean slabRegisterOk = TerracottaSlabBlocks.UNCOLORED_TERRACOTTA_SLAB_BLOCK != null;
        boolean glazedSlabRegisterOk = GlazedTerracottaSlabBlocks.GLAZED_TERRACOTTA_SLAB_BLOCKS.get(DyeColor.WHITE) != null;
        boolean glazedTileRegisterOk = GlazedTerracottaTileBlocks.GLAZED_TERRACOTTA_SLAB_BLOCKS.get(DyeColor.WHITE) != null;
    }
}
