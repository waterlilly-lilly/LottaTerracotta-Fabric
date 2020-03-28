package org.villainy.lottaterracotta.objectholders;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;
import org.villainy.lottaterracotta.tileEntities.TerracottaSignTileEntity;

@ObjectHolder("lottaterracotta")
public class TileEntityTypes {

    @ObjectHolder("terracotta_sign")
    public static final TileEntityType<TerracottaSignTileEntity> TERRACOTTA_SIGN = null;
}
