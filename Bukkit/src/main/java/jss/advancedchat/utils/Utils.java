package jss.advancedchat.utils;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.files.utils.Settings;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Utils {

    private static final AdvancedChat plugin = AdvancedChat.get();
    private static final String prefix = getPrefix(true);

    public static String colorized(String text){
        return text;
    }

    public static String unColorized(String text) {
        return text;
    }

    public static void sendColorMessage(@NotNull CommandSender sender, String text) {
        sender.sendMessage(colorized(text));
    }

    public static void sendColorMessage(@NotNull Player player, String text) {
        player.sendMessage(colorized(text));
    }

    public static @NotNull String getDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(calendar.getTime());
    }

    public static @NotNull String getTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(calendar.getTime());
    }

    public static String getPrefix(boolean ignore){
        String finalPrefix = "";

        if(ignore){
            finalPrefix = "&e[&d" + plugin.name + "&e] &7";
        }else{
            finalPrefix = Settings.messages_prefix + " &7";
        }
        return colorized(finalPrefix);
    }

    public static boolean setPerm(@NotNull Player player, String permName){
        return player.hasPermission("advancedchat." + permName);
    }

    public static @NotNull List<String> setLimitTab(@NotNull List<String> options, String lastArgs){
        List<String> returned = new ArrayList<>();

        options.forEach( s -> {
            if (s != null && s.toLowerCase().startsWith(lastArgs.toLowerCase())){
                returned.add(s);
            }
        });
        return returned;
    }

    private static void sendMessage(String text){
        CommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage(colorized(text));
    }

    public static void sendLoad() {
        sendMessage("&d    ___   ______");
        sendMessage("&d   /   | / ____/ &a");
        sendMessage("&d  / /| |/ /      &bBy jonagamerpro1234");
        sendMessage("&d / ___ / /___    &bVersion&7: &3" + plugin.version);
        sendMessage("&d/_/  |_\\____/   &eThanks for using AdvancedChat &c<3");
        sendMessage("  ");
    }

    public static void sendEnable() {
        sendMessage(prefix + "&5<||============================================----");
        sendMessage(prefix + "&5<|| &c* &bThe plugin is &d[&aSuccessfully activated&d]");
        sendMessage(prefix + "&5<|| &c* &bVersion: &e[&a" + plugin.version + "&e]");
        sendMessage(prefix + "&5<|| &c* &bBy: &e[&bjonagamerpro1234&e]");
        sendMessage(prefix + "&5<|| &c* &bTested Versions &3|&a1.16.x &3- &a1.19.x&3| &eComing Soon -> &c1.20");
        sendMessage(prefix + "&5<|| &c* &bSupported Versions &3|&a1.8.x - 1.15.x (Legacy) &3- &a1.19.x&3|");
        sendMessage(prefix + "&5<|| &a* &eThanks for using &bAdvancedChat &c<3");
        sendMessage(prefix + "&5<||============================================----");
    }

    public static void sendDisable() {
        sendMessage(prefix + "&5<||============================================----");
        sendMessage(prefix + "&5<|| &c* &bThe plugin is &d[&aSuccessfully activated&d]");
        sendMessage(prefix + "&5<|| &c* &bVersion: &e[&a" + plugin.version + "&e]");
        sendMessage(prefix + "&5<|| &c* &bBy: &e[&bjonagamerpro1234&e]");
        sendMessage(prefix + "&5<|| &c* &bTested Versions &3|&a1.16.x &3- &a1.19.x&3| &eComing Soon -> &c1.20");
        sendMessage(prefix + "&5<|| &a* &eThanks for using &bAdvancedChat &c<3");
        sendMessage(prefix + "&5<||============================================----");
    }

}
