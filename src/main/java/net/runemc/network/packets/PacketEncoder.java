package net.runemc.network.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) throws Exception {
        int packetId = PacketRegistry.getPacketId(packet.getClass());
        if (packetId == -1) {
            throw new IllegalArgumentException("Unknown packet type: " + packet.getClass());
        }

        out.writeInt(packetId);
        packet.encode(out);
    }
}