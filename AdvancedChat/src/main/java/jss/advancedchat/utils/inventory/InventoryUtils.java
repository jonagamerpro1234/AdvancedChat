package jss.advancedchat.utils.inventory;

import com.cryptomorin.xseries.XMaterial;
import jss.advancedchat.utils.Util;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventoryUtils {

    public static List<String> coloredLore(List<String> lore) {
        List<String> coloredlore = new ArrayList<>();
        lore.forEach((line) -> {
            String lineColored = Util.color(line);
            coloredlore.add(lineColored);
        });
        return coloredlore;
    }

    public static void setItemChecker(Inventory inv, int slot, boolean value) {
        if (value) {
            ItemStack item = XMaterial.LIME_DYE.parseItem();
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(Util.color("&aEnable"));
            List<String> lore = Arrays.asList("&7Click to &cdisable");
            meta.setLore(coloredLore(lore));
            item.setItemMeta(meta);
            inv.setItem(slot, item);
        } else {
            ItemStack item = XMaterial.GRAY_DYE.parseItem();
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(Util.color("&cDisable"));
            List<String> lore = Arrays.asList("&7Click to &aenable");
            meta.setLore(coloredLore(lore));
            item.setItemMeta(meta);
            inv.setItem(slot, item);
        }
    }

    public static void setRowGlass(Inventory inventory, ItemStack item, int row) {
        int finalrow = row;
        if (!(row >= 1 && row <= 6))
            finalrow = 1;
        row = finalrow;
        int invSize = row * 9;

        for (int i = 0; i < invSize; i++) {
            item = XMaterial.BLACK_STAINED_GLASS_PANE.parseItem();
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(" ");
            item.setItemMeta(meta);
            item.setAmount(1);
            inventory.setItem(i, item);

        }
    }

}
