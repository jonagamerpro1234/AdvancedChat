package jss.advancedchat.utils;

import de.tr7zw.changeme.nbtapi.NBTItem;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.lib.iridium.IridiumColorAPI;
import jss.advancedchat.update.UpdateSettings;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class Utils {

    public static @NotNull String colorized(String text){
        return IridiumColorAPI.process(text);
    }

    @Contract(pure = true)
    public static @NotNull String unColorized(String text){
        return IridiumColorAPI.stripColorFormatting(text);
    }

    public static void sendColorMessage(@NotNull CommandSender sender, String text){
        sender.sendMessage(colorized(text));
    }

    public static void sendColorMessage(@NotNull Player player, String text){
        player.sendMessage(colorized(onPlaceholderAPI(player, text)));
    }

    public static String onPlaceholderAPI(Player player, String text){
        return Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI") ? PlaceholderAPI.setPlaceholders(player, text) : text;
    }

    public static void setStringItemNbt(ItemStack item, String key, String value) {
        if (item != null && !item.getType().equals(Material.AIR)) {
            NBTItem nbtItem = new NBTItem(item);
            nbtItem.setString(key, value);
            nbtItem.applyNBT(item);
        }
    }

    public static String getStringItemNbt(ItemStack item, String key) {
        if (item != null && !item.getType().equals(Material.AIR)) {
            NBTItem nbtItem = new NBTItem(item);
            if (nbtItem.hasKey(key)) {
                return nbtItem.getString(key);
            }
        }
        return "N/A";
    }

    public static @NotNull List<String> coloredLore(@NotNull List<String> lore) {
        List<String> coloredLore = new ArrayList<>();
        lore.forEach((line) -> {
            String lineColored = jss.advancedchat.utils.Util.color(line);
            coloredLore.add(lineColored);
        });
        return coloredLore;
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
        MessageUtils.sendColorMessage(sender, " ");
        MessageUtils.sendColorMessage(sender, "&5-=-=-=-=-=[&b" + name + "&5]=-=-=-=-=-=-");
        MessageUtils.sendColorMessage(sender, " ");
        MessageUtils.sendColorMessage(sender, "&5 > &3Name: &b" + name);
        MessageUtils.sendColorMessage(sender, "&5 > &3Author: &6jonagamerpro1234");
        MessageUtils.sendColorMessage(sender, "&5 > &3Version: &6" + version);
        MessageUtils.sendColorMessage(sender, "&5 > &3Update: &a" + latestVersion);
        MessageUtils.sendColorMessage(sender, "&5 > &6Spigot: &a" + UpdateSettings.URL_PLUGIN[0]);
        MessageUtils.sendColorMessage(sender, "&5 > &dSongoda: &a" + UpdateSettings.URL_PLUGIN[1]);
        MessageUtils.sendColorMessage(sender, " ");
        MessageUtils.sendColorMessage(sender, "&5-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        MessageUtils.sendColorMessage(sender, " ");
    }

    public static @NotNull String getPrefix(boolean ignoreCustomPrefix) {
        String prefixTemp;
        if(ignoreCustomPrefix){
            prefixTemp = "&e[&dAdvancedChat&e]&7 ";
        }else{
            prefixTemp = Settings.lang_prefix + " ";
        }
        return prefixTemp;
    }

}
