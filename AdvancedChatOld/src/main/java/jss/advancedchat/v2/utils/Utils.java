package jss.advancedchat.v2.utils;

import jss.advancedchat.lib.iridium.IridiumColorAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    @Contract("_ -> new")
    public static @NotNull String colorized(String text){
        return IridiumColorAPI.process(text);
    }

    public static @NotNull List<String> colorizedList(List<String> list){
        return IridiumColorAPI.process(list);
    }

    public static @NotNull String uncolorized(String text){
        return IridiumColorAPI.stripColorFormatting(text);
    }

    public static String placeholderReplace(Player player, String text) {
        return Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI") ? PlaceholderAPI.setPlaceholders(player, text) : text;
    }

    public static void sendMessage(@NotNull CommandSender sender, String text){
        sender.sendMessage(colorized(text));
    }

    public static void sendMessage(Player player, String text){
        sendMessage(player, placeholderReplace(player,text));
    }

    public static @NotNull List<String> setLimitTab(@NotNull List<String> list, String init) {
        List<String> returned = new ArrayList<>();
        list.forEach((s) -> {
            if (s != null && s.toLowerCase().startsWith(init.toLowerCase())) {
                returned.add(s);
            }
        });
        return returned;
    }

    @Contract(pure = true)
    public static @NotNull String getPrefix(){
        return "&e[&dAdvancedChat&e]&7 ";
    }

}
