package net.runemc

import net.runemc.api.Logger
import net.runemc.server.MinecraftServer

fun main() {
    val server = MinecraftServer(25566)
    try {
        server.start()
    }catch (e: Exception) {
        Logger.errorRaw(e.message)
        e.printStackTrace()
        server.stop()
    }finally {
        server.stop()
    }
}