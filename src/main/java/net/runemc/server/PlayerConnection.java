package net.runemc.server;

import io.netty.channel.Channel;

public class PlayerConnection {
    private final Channel channel;
    private String name;

    public PlayerConnection(Channel channel) {
        this.channel = channel;
    }

    public void sendPacket(Object packet) {
        channel.writeAndFlush(packet);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}