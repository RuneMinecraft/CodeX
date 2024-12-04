package net.runemc.network.packets;

import net.runemc.network.packets.clientbound.ClientboundJoinGamePacket;
import net.runemc.network.packets.clientbound.ClientboundLoginSuccessPacket;
import net.runemc.network.packets.serverbound.ChatPacket;
import net.runemc.network.packets.serverbound.ServerboundHandshakePacket;
import net.runemc.network.packets.serverbound.ServerboundLoginPacket;

import java.util.HashMap;
import java.util.Map;

public class PacketRegistry {
    private static final Map<Integer, Class<? extends Packet>> idToPacket = new HashMap<>();
    private static final Map<Class<? extends Packet>, Integer> packetToId = new HashMap<>();

    static {
        registerPacket(0x0E, ClientboundJoinGamePacket.class);
        registerPacket(0x02, ClientboundLoginSuccessPacket.class);
        registerPacket(0x00, ServerboundHandshakePacket.class);
        registerPacket(0x00, ServerboundLoginPacket.class);
    }

    public static void registerPacket(int id, Class<? extends Packet> packetClass) {
        idToPacket.put(id, packetClass);
        packetToId.put(packetClass, id);
    }

    public static Packet createPacket(int id) {
        Class<? extends Packet> packetClass = idToPacket.get(id);
        if (packetClass == null) {
            return null;
        }

        try {
            return packetClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getPacketId(Class<? extends Packet> packetClass) {
        return packetToId.getOrDefault(packetClass, -1);
    }
}