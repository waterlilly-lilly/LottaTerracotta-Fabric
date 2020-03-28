package org.villainy.lottaterracotta.tileEntities;

import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class TerracottaSignTileEntity extends SignTileEntity {
    @ObjectHolder("lottaterracotta:terracotta_sign")
    public static TileEntityType<TerracottaSignTileEntity> TYPE;

    @Override
    public TileEntityType<?> getType() {
        return TYPE;
    }
}
