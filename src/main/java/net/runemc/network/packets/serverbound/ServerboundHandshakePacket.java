package net.runemc.network.packets.serverbound;

import io.netty.buffer.ByteBuf;
import net.runemc.network.packets.Packet;

public class ServerboundHandshakePacket extends Packet {
    private int protocolVersion;
    private String serverAddress;
    private int serverPort;
    private int nextState;

    @Override
    public void decode(ByteBuf in) {
        this.protocolVersion = readVarInt(in);        // Protocol version
        this.serverAddress = readString(in);          // Server address (e.g., "0.0.0.0")
        this.serverPort = in.readUnsignedShort();     // Server port (e.g., 25565)
        this.nextState = readVarInt(in);              // Next state (1 = Status, 2 = Login)
    }

    @Override
    public void encode(ByteBuf out) {
        writeVarInt(out, protocolVersion);
        writeString(out, serverAddress);
        out.writeShort(serverPort);
        writeVarInt(out, nextState);
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }
    public String getServerAddress() {
        return serverAddress;
    }
    public int getServerPort() {
        return serverPort;
    }
    public int getNextState() {
        return nextState;
    }
}