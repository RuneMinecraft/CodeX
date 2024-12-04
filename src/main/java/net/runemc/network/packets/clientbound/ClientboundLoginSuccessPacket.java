package net.runemc.network.packets.clientbound;

import io.netty.buffer.ByteBuf;
import net.runemc.network.packets.Packet;

import java.util.UUID;

public class ClientboundLoginSuccessPacket extends Packet {
    private String uuid;
    private String username;

    public ClientboundLoginSuccessPacket(String username) {
        this.uuid = String.valueOf(UUID.randomUUID());
        this.username = username;
    }

    @Override
    public void encode(ByteBuf out) {
        writeString(out, uuid);
        writeString(out, username);
    }

    @Override
    public void decode(ByteBuf in) {
        this.uuid = readString(in);
        this.username = readString(in);
    }
}