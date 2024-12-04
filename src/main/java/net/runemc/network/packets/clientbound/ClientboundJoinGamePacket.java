package net.runemc.network.packets.clientbound;

import io.netty.buffer.ByteBuf;
import net.runemc.network.packets.Packet;

public class ClientboundJoinGamePacket extends Packet {
    private int entityId;
    private int gamemode;
    private int dimension;
    private String levelType;

    public ClientboundJoinGamePacket() {
        this.entityId = 1;
        this.gamemode = 1;
        this.dimension = 0;
        this.levelType = "default";
    }

    @Override
    public void encode(ByteBuf out) {
        out.writeInt(entityId);
        out.writeByte(gamemode);
        out.writeInt(dimension);
        out.writeByte(0);
        out.writeByte(1);

        writeStringVI(out, levelType);
    }

    @Override
    public void decode(ByteBuf in) {
        this.entityId = in.readInt();
        this.gamemode = in.readByte();
        this.dimension = in.readInt();
        in.readByte();
        in.readByte();
        this.levelType = readStringVI(in);
    }
}