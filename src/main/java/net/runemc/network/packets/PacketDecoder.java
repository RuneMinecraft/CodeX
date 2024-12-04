package net.runemc.network.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import net.runemc.utils.IByteBufUtils;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder implements IByteBufUtils {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        try {
            if (in.readableBytes() < 1) {
                return;
            }

            int packetId = in.readByte();
            Packet packet = PacketRegistry.createPacket(packetId);

            if (packet != null) {
                packet.decode(in);
                out.add(packet);
            } else {
                System.out.println("Unknown packet ID: " + packetId);
                in.skipBytes(in.readableBytes());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String toHexString(ByteBuf buf) {
        int length = buf.readableBytes();
        byte[] bytes = new byte[length];
        buf.getBytes(buf.readerIndex(), bytes);
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            hex.append(String.format("%02X ", b));
        }
        return hex.toString();
    }
}