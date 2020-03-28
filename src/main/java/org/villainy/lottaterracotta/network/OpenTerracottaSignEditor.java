package org.villainy.lottaterracotta.network;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;
import org.villainy.lottaterracotta.LottaTerracotta;

import java.util.function.Supplier;

public class OpenTerracottaSignEditor {
    public BlockPos position;

    public OpenTerracottaSignEditor(BlockPos position) {
        this.position = position;
    }

    public OpenTerracottaSignEditor(PacketBuffer buffer) {
        position = buffer.readBlockPos();
    }

    public void encode(PacketBuffer buffer) {
        buffer.writeBlockPos(position);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        LottaTerracotta.proxy.openSignButtonGui(position);
    }
}
