package jss.advancedchat.v2;

import de.tr7zw.changeme.nbtapi.NBTItem;
import jss.advancedchat.AdvancedChat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Util {

    private static final AdvancedChat plugin = AdvancedChat.get();

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

}
