package net.runemc.network.packets.serverbound;

import io.netty.buffer.ByteBuf;
import net.runemc.network.packets.Packet;

import java.nio.charset.StandardCharsets;

public class ChatPacket extends Packet {
    private String message;

    private ChatPacket() {}
    private ChatPacket(String message) {
        this.message = message;
    }

    public static ChatPacket create() {
        return new ChatPacket();
    }
    private static ChatPacket create(String message) {
        return new ChatPacket(message);
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void encode(ByteBuf out) {
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        out.writeInt(messageBytes.length);
        out.writeBytes(messageBytes);
    }

    @Override
    public void decode(ByteBuf in) {
        int length = in.readInt();
        byte[] messageBytes = new byte[length];
        in.readBytes(messageBytes);
        this.message = new String(messageBytes, StandardCharsets.UTF_8);
    }
}