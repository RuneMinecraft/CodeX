package net.runemc.api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class Logger {

    public static void log(String ... s) {
        new ArrayList<>(List.of(s)).forEach((message) -> System.out.println(Color.WHITE+"["+Logger.getTime()+" INFO] [Rune | Codex] "+message));
    }
    public static void logRaw(String ... s) {
        new ArrayList<>(List.of(s)).forEach((message) -> System.out.println(Color.WHITE+"["+Logger.getTime()+" INFO] "+message));
    }
    public static void warn(String ... s) {
        new ArrayList<>(List.of(s)).forEach((message) -> System.out.println(Color.YELLOW+"["+Logger.getTime()+" WARN] [Rune | Codex] "+message));
    }
    public static void warnRaw(String ... s) {
        new ArrayList<>(List.of(s)).forEach((message) -> System.out.println(Color.YELLOW+"["+Logger.getTime()+" WARN] "+message));
    }
    public static void error(String ... s) {
        new ArrayList<>(List.of(s)).forEach((message) -> System.out.println(Color.RED+"["+Logger.getTime()+" ERROR] [Rune | Codex] "+message));
    }
    public static void errorRaw(String ... s) {
        new ArrayList<>(List.of(s)).forEach((message) -> System.out.println(Color.RED+"["+Logger.getTime()+" ERROR] "+message));
    }

    public static class Color {
        public static final String RESET = "\u001B[0m";
        public static final String WHITE = "\u001B[37m";
        public static final String YELLOW = "\u001B[33m";
        public static final String RED = "\u001B[31m";
    }

    private static String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
}
