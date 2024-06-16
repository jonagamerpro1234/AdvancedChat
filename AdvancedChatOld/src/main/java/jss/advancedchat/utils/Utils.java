package jss.advancedchat.utils;

import jss.advancedchat.lib.iridium.IridiumColorAPI;
import jss.advancedchat.update.UpdateSettings;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static @NotNull String colorized(String text){
        return IridiumColorAPI.process(text);
    }

    @Contract(pure = true)
    public static @NotNull String unColorized(String text){
        return IridiumColorAPI.stripColorFormatting(text);
    }

    public static  void sendColorMessage(@NotNull CommandSender sender, String text){
        sender.sendMessage(colorized(text));
    }

    public static void sendColorMessage(@NotNull Player player, String text){
        player.sendMessage(colorized(onPlaceholderAPI(player, text)));
    }

    public static String onPlaceholderAPI(Player player, String text){
        return Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI") ? PlaceholderAPI.setPlaceholders(player, text) : text;
    }

    public static @NotNull List<String> setLimitTab(@NotNull List<String> options, String lastArgs){
        List<String> returned = new ArrayList<>();

        options.forEach( s -> {
            if(s != null && s.toLowerCase().startsWith(lastArgs.toLowerCase())){
                returned.add(s);
            }
        });
        return returned;
    }

    public static void getInfoPlugin(CommandSender sender, String name, String version, String latestVersion) {
        MessagesUtils.sendColorMessage(sender, " ");
        MessagesUtils.sendColorMessage(sender, "&5-=-=-=-=-=[&b" + name + "&5]=-=-=-=-=-=-");
        MessagesUtils.sendColorMessage(sender, " ");
        MessagesUtils.sendColorMessage(sender, "&5 > &3Name: &b" + name);
        MessagesUtils.sendColorMessage(sender, "&5 > &3Author: &6jonagamerpro1234");
        MessagesUtils.sendColorMessage(sender, "&5 > &3Version: &6" + version);
        MessagesUtils.sendColorMessage(sender, "&5 > &3Update: &a" + latestVersion);
        MessagesUtils.sendColorMessage(sender, "&5 > &6Spigot: &a" + UpdateSettings.URL_PLUGIN[0]);
        MessagesUtils.sendColorMessage(sender, "&5 > &dSongoda: &a" + UpdateSettings.URL_PLUGIN[1]);
        MessagesUtils.sendColorMessage(sender, " ");
        MessagesUtils.sendColorMessage(sender, "&5-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        MessagesUtils.sendColorMessage(sender, " ");
    }

}
