package net.runemc.network.packets.serverbound;

import io.netty.buffer.ByteBuf;
import net.runemc.network.packets.Packet;

public class ServerboundLoginPacket extends Packet {
    private String name;

    @Override
    public void encode(ByteBuf out) {
        byte[] nameBytes = name.getBytes();
        out.writeInt(nameBytes.length);
        out.writeBytes(nameBytes);
    }

    @Override
    public void decode(ByteBuf in) {
        int length = in.readInt();
        byte[] nameBytes = new byte[length];
        in.readBytes(nameBytes);
        this.name = new String(nameBytes);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}