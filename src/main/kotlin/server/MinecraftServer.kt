package net.runemc.server

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.*
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import net.runemc.api.Logger

class MinecraftServer(private val port: Int) {
    private var serverChannel: Channel? = null
    private var bossGroup: EventLoopGroup? = null
    private var workerGroup: EventLoopGroup? = null
    
    fun start() {
        Logger.logRaw("Starting server on port $port...")
        bossGroup = NioEventLoopGroup(1)
        workerGroup = NioEventLoopGroup()

        try {
            val bootstrap = ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel::class.java)
                .childHandler(object : ChannelInitializer<Channel>() {
                    override fun initChannel(ch: Channel) {
                        val pipeline = ch.pipeline()
                        pipeline.addLast("handler", ServerHandler())
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)

            serverChannel = bootstrap.bind(port).sync().channel()
            Logger.logRaw("Server started on port $port!")
        } catch (e: Exception) {
            e.printStackTrace()
            stop()
        }
    }
    
    fun stop() {
        Logger.logRaw("Stopping server...")
        try {
            serverChannel?.close()?.sync()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } finally {
            bossGroup?.shutdownGracefully()
            workerGroup?.shutdownGracefully()
            Logger.logRaw("Minecraft server stopped.")
        }
    }
    
    fun restart() {
        Logger.logRaw("Restarting...")
        stop()
        start()
    }
}