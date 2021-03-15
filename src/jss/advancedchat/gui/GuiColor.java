package jss.advancedchat.gui;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.cryptomorin.xseries.SkullUtils;
import com.cryptomorin.xseries.XMaterial;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.ColorFile;
import jss.advancedchat.utils.Utils;

public class GuiColor {

    private AdvancedChat plugin;

    public GuiColor(AdvancedChat plugin) {
        this.plugin = plugin;
    }

    public void openGuiColor(Player player, String player2) {
        ColorFile colorFile = plugin.getColorFile();
        FileConfiguration config = colorFile.getConfig();

        String title = config.getString("Title");
        title = title.replace("<player>", player2);
        Inventory inv = Bukkit.createInventory(null, 45, Utils.color(title));
        ItemStack item = null;
        SkullMeta skullMeta = null;
        ItemMeta meta = null;
        String materialglass = config.getString("Decoration.Glass-Color.Item");
        setDecoration(inv, item, meta, materialglass);

        item = XMaterial.PLAYER_HEAD.parseItem();
        skullMeta = (SkullMeta) item.getItemMeta();
        skullMeta.setDisplayName(Utils.color("&6&l&n" + player2));
        skullMeta = SkullUtils.applySkin(skullMeta, player2);
        item.setItemMeta(skullMeta);
        inv.setItem(36, item);

        for (String key : config.getConfigurationSection("Items").getKeys(false)) {
            String material = config.getString("Items." + key + ".Item");
            int slots = config.getInt("Items." + key + ".Slot");
            String name = config.getString("Items." + key + ".Name");
            String useSkull = config.getString("Items." + key + ".Use-Custom-Skull");
            String texture = config.getString("Items." + key + ".Texture");
            String id = config.getString("Items." + key + ".ID");
            int amont = config.getInt("Items." + key + ".Amount");
            List<String> lore = config.getStringList("Items." + key + ".Lore");

            item = XMaterial.valueOf(material).parseItem();

            if (useSkull.contains("true")) {
                item = Utils.setSkull(item, id, texture);
                skullMeta = (SkullMeta) item.getItemMeta();
                skullMeta.setDisplayName(Utils.color(name));
                for (int i = 0; i < lore.size(); i++) {
                    String text = (String) lore.get(i);
                    text = Utils.color(text);
                    text = Utils.getVar(player, text);
                    lore.add(text);
                }
                skullMeta.setLore(lore);
                item.setItemMeta(skullMeta);
            } else if (useSkull.contains("false")) {
                meta = item.getItemMeta();
                meta.setDisplayName(Utils.color(name));
                for (int i = 0; i < lore.size(); i++) {
                    String text = (String) lore.get(i);
                    text = Utils.color(text);
                    text = Utils.getVar(player, text);
                    lore.add(text);
                }
                meta.setLore(lore);
                item.setItemMeta(meta);
            }
            item.setAmount(amont);
            inv.setItem(slots, item);
        }

        String nameBack = config.getString("Decoration.Back.Name");
        String materialback = config.getString("Decoration.Back.Item");
        item = XMaterial.valueOf(materialback).parseItem();
        meta = item.getItemMeta();
        meta.setDisplayName(Utils.color(Utils.getVar(player, nameBack)));
        item.setItemMeta(meta);
        item.setAmount(1);
        inv.setItem(40, item);

        player.openInventory(inv);
        plugin.addInventoryPlayer(player, "color");
    }

    private void setDecoration(Inventory inv, ItemStack item, ItemMeta meta, String path) {

        for (int i = 0; i < 9; i++) {
            item = XMaterial.valueOf(path).parseItem();
            meta = item.getItemMeta();
            meta.setDisplayName(Utils.color(" "));
            item.setItemMeta(meta);
            item.setAmount(1);
            inv.setItem(i, item);

            if (i == 9) {
                break;
            }
        }

        for (int i = 36; i < 40; i++) {
            item = XMaterial.valueOf(path).parseItem();
            meta = item.getItemMeta();
            meta.setDisplayName(Utils.color(" "));
            item.setItemMeta(meta);
            item.setAmount(1);
            inv.setItem(i, item);

            if (i == 40) {
                break;
            }

        }
        item = XMaterial.valueOf(path).parseItem();
        meta = item.getItemMeta();
        meta.setDisplayName(Utils.color(" "));
        item.setItemMeta(meta);
        item.setAmount(1);
        inv.setItem(9, item);

        item = XMaterial.valueOf(path).parseItem();
        meta = item.getItemMeta();
        meta.setDisplayName(Utils.color(" "));
        item.setItemMeta(meta);
        item.setAmount(1);
        inv.setItem(18, item);

        item = XMaterial.valueOf(path).parseItem();
        inv.setItem(17, item);

        item = XMaterial.valueOf(path).parseItem();
        meta = item.getItemMeta();
        meta.setDisplayName(Utils.color(" "));
        item.setItemMeta(meta);
        item.setAmount(1);
        inv.setItem(26, item);

        item = XMaterial.valueOf(path).parseItem();
        meta = item.getItemMeta();
        meta.setDisplayName(Utils.color(" "));
        item.setItemMeta(meta);
        item.setAmount(1);
        inv.setItem(27, item);

        item = XMaterial.valueOf(path).parseItem();
        meta = item.getItemMeta();
        meta.setDisplayName(Utils.color(" "));
        item.setItemMeta(meta);
        item.setAmount(1);
        inv.setItem(35, item);

        for (int i = 28; i < 30; i++) {
            item = XMaterial.valueOf(path).parseItem();
            meta = item.getItemMeta();
            meta.setDisplayName(Utils.color(" "));
            item.setItemMeta(meta);
            item.setAmount(1);
            inv.setItem(i, item);
            
            if (i == 30) {
                break;
            }
        }
        for (int i = 33; i < 35; i++) {
            item = XMaterial.valueOf(path).parseItem();
            meta = item.getItemMeta();
            meta.setDisplayName(Utils.color(" "));
            item.setItemMeta(meta);
            item.setAmount(1);
            inv.setItem(i, item);

            if (i == 35) {
                break;
            }
        }

        for (int i = 41; i < 45; i++) {
            item = XMaterial.valueOf(path).parseItem();
            meta = item.getItemMeta();
            meta.setDisplayName(Utils.color(" "));
            item.setItemMeta(meta);
            item.setAmount(1);
            inv.setItem(i, item);

            if (i == 45) {
                break;
            }
        }
    }

}
