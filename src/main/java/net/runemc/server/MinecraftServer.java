package net.runemc.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.HashSet;
import java.util.Set;

public class MinecraftServer {
    private final int port;
    private final Set<PlayerConnection> players;

    public MinecraftServer(int port) {
        this.port = port;
        this.players = new HashSet<>();
    }

    public void start() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MinecraftChannelInitializer())
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = bootstrap.bind(port).sync();
            System.out.println("Minecraft server started on port: " + port);
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public void addPlayer(PlayerConnection player) {
        players.add(player);
        System.out.println("Player joined: " + player.getName());
    }

    public void removePlayer(PlayerConnection player) {
        players.remove(player);
        System.out.println("Player left: " + player.getName());
    }

    public Set<PlayerConnection> getPlayers() {
        return players;
    }
}