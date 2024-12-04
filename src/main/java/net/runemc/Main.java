package net.runemc;

import net.runemc.server.MinecraftServer;

public class Main {
    private static final int DEFAULT_PORT = 25565;

    private static MinecraftServer server;
    public MinecraftServer server() {
        return server;
    }

    private static Main instance;
    public static Main instance() {
        return instance;
    }

    public static void main(String[] args) throws Exception {
        instance = new Main();

        server = new MinecraftServer(Main.DEFAULT_PORT);
        server.start();
    }
}