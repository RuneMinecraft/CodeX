package net.runemc.network;

import io.netty.util.AttributeKey;
import net.runemc.server.PlayerConnection;

public class AttributeKeys {
    public static final AttributeKey<PlayerConnection> PLAYER_CONNECTION = AttributeKey.valueOf("PLAYER_CONNECTION");
}