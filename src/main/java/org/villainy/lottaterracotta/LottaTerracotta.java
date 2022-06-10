package org.villainy.lottaterracotta;

import net.fabricmc.api.ModInitializer;
import org.villainy.lottaterracotta.block.TerracottaSlabBlocks;

import java.util.stream.Stream;

public class LottaTerracotta implements ModInitializer {
    @Override
    public void onInitialize() {
        boolean slabRegisterOk = TerracottaSlabBlocks.UNCOLORED_TERRACOTTA_SLAB_BLOCK != null;
    }
}
