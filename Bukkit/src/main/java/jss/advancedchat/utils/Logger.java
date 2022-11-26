package jss.advancedchat.utils;

import org.bukkit.Bukkit;

public class Logger {

    public static void info(String text){
        Utils.sendColorMessage(Bukkit.getConsoleSender(),Utils.getPrefix(true) + "- [INFO]: &7" + text);
    }

    public static void outline(String text){
        Utils.sendColorMessage(Bukkit.getConsoleSender(),Utils.getPrefix(true) + "- [OUTLINE]: &7" + text);
    }

    public static void success(String text){
        Utils.sendColorMessage(Bukkit.getConsoleSender(),Utils.getPrefix(true) + "- [SUCCESS]: &7" + text);
    }

    public static void warning(String text){
        Utils.sendColorMessage(Bukkit.getConsoleSender(),Utils.getPrefix(true) + "- [WARNING]: &7" + text);
    }

    public static void error(String text){
        Utils.sendColorMessage(Bukkit.getConsoleSender(),Utils.getPrefix(true) + "- [ERROR]: &7" + text);
    }

    public static void debug(String text){
        Utils.sendColorMessage(Bukkit.getConsoleSender(),Utils.getPrefix(true) + "- [DEBUG]: &7" + text);
    }

    public static void chat(String text){
        Utils.sendColorMessage(Bukkit.getConsoleSender(),Utils.getPrefix(true) + "- [CHAT]: &7" + text);
    }

}
