package net.runemc.network.packets;

import io.netty.buffer.ByteBuf;
import net.runemc.utils.IByteBufUtils;

public abstract class Packet implements IByteBufUtils {
    public abstract void encode(ByteBuf out);
    public abstract void decode(ByteBuf in);
}