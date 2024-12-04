package net.runemc.network.packets;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class PacketHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof Packet packet) {
            System.out.println("Received packet: " + packet);
        } else {
            super.channelRead(ctx, msg);
        }
    }
}