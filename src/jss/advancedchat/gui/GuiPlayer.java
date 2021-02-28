package jss.advancedchat.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.cryptomorin.xseries.SkullUtils;
import com.cryptomorin.xseries.XEnchantment;
import com.cryptomorin.xseries.XMaterial;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Utils;

public class GuiPlayer {

    private AdvancedChat plugin;

    public GuiPlayer(AdvancedChat plugin) {
        this.plugin = plugin;
    }

    public void openPlayerGui(Player player, String player2) {

        FileConfiguration config = plugin.getPlayerGuiFile().getConfig();

        String title = config.getString("Player-Gui.Title");
        String pathcolor = config.getString("Player-Gui.Decoration.Glass-Color.Item");

        title = title.replace("<player>", player2);
        Inventory inv = Bukkit.createInventory(null, 36, Utils.color(title));

        ItemStack item = null;
        ItemMeta meta = null;
        SkullMeta skullmeta = null;

        Player p = Bukkit.getPlayer(player2);

        setDecorationPlayer(inv, item, meta, pathcolor);
        //player head
        item = XMaterial.PLAYER_HEAD.parseItem();
        skullmeta = (SkullMeta) item.getItemMeta();
        skullmeta.setDisplayName(Utils.color("&6&l&n" + player2));
        skullmeta = SkullUtils.applySkin(skullmeta, player2);
        item.setItemMeta(skullmeta);
        inv.setItem(10, item);
        //mute
        setMuteItem(config,p, inv, item, meta);

        //color

        String material = config.getString("Player-Gui.Items.Color.Item");
        String useSkull = config.getString("Player-Gui.Items.Color.Use-Custom-Skull");
        String name = config.getString("Player-Gui.Items.Color.Name");
        String texture = config.getString("Player-Gui.Items.Color.Texture");
        String id = config.getString("Player-Gui.Items.Color.ID");
        item = XMaterial.valueOf(material).parseItem();

        if (useSkull.contains("true")) {
            item = Utils.setSkull(item, id, texture);
            skullmeta = (SkullMeta) item.getItemMeta();
            skullmeta.setDisplayName(Utils.color(name));
            item.setItemMeta(skullmeta);
        } else if (useSkull.contains("false")) {
            meta = item.getItemMeta();
            meta.setDisplayName(Utils.color(name));
            item.setItemMeta(meta);
        }
        inv.setItem(16, item);

        player.openInventory(inv);
        plugin.addInventoryPlayer(player, "player");
    }


    private void setMuteItem(FileConfiguration config, Player player, Inventory inv, ItemStack item, ItemMeta meta) {

        PlayerManager manager = new PlayerManager(plugin);
        if (manager.checkPlayerList(player)) {
            if (manager.isMute(player) == true) {

                item = XMaterial.PAPER.parseItem();
                meta = item.getItemMeta();
                item.setAmount(1);
                meta.addEnchant(XEnchantment.DURABILITY.parseEnchantment(), 1, false);
                meta.setDisplayName(Utils.color(config.getString("Player-Gui.Items.Mute.Name.Enable")));
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                item.setItemMeta(meta);
                inv.setItem(13, item);

                item = XMaterial.GREEN_DYE.parseItem();
                meta = item.getItemMeta();
                item.setAmount(1);
                meta.setDisplayName(Utils.color("&a&lTrue"));
                String a = config.getString("Player-Gui.Items.Mute.Lore.Enable");
                List<String> lore = new ArrayList<>();
                lore.add(a);
                meta.setLore(lore);
                item.setItemMeta(meta);
                inv.setItem(22, item);
            } else if (manager.isMute(player) == false) {
                item = XMaterial.PAPER.parseItem();
                meta = item.getItemMeta();
                item.setAmount(1);
                meta.setDisplayName(Utils.color(config.getString("Player-Gui.Items.Mute.Name.Disable")));
                item.setItemMeta(meta);
                inv.setItem(13, item);

                item = XMaterial.RED_DYE.parseItem();
                meta = item.getItemMeta();
                item.setAmount(1);
                meta.setDisplayName(Utils.color("&c&lFalse"));
                String a = config.getString("Player-Gui.Items.Mute.Lore.Enable");
                List<String> lore = new ArrayList<>();
                lore.add(a);
                meta.setLore(lore);
                item.setItemMeta(meta);
                inv.setItem(22, item);
            }
        } else {
            return;
        }
    }

    private void setDecorationPlayer(Inventory inv, ItemStack item, ItemMeta meta, String path) {

        for (int i = 0; i < 9; i++) {
            item = XMaterial.valueOf(path).parseItem();
            item.setAmount(1);
            meta = item.getItemMeta();
            meta.setDisplayName(" ");
            item.setItemMeta(meta);
            inv.setItem(i, item);
            if (i == 9) {
                break;
            }
        }

        item = XMaterial.valueOf(path).parseItem();
        item.setAmount(1);
        meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        inv.setItem(14, item);

        item = XMaterial.valueOf(path).parseItem();
        item.setAmount(1);
        meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        inv.setItem(15, item);

        for (int i = 18; i < 27; i++) {
            item = XMaterial.valueOf(path).parseItem();
            item.setAmount(1);
            meta = item.getItemMeta();
            meta.setDisplayName(" ");
            item.setItemMeta(meta);
            inv.setItem(i, item);
            if (i == 27) {
                break;
            }
        }

        for (int i = 27; i < 36; i++) {
            item = XMaterial.valueOf(path).parseItem();
            item.setAmount(1);
            meta = item.getItemMeta();
            meta.setDisplayName(" ");
            item.setItemMeta(meta);
            inv.setItem(i, item);
            if (i == 36) {
                break;
            }
        }

        item = XMaterial.valueOf(path).parseItem();
        item.setAmount(1);
        meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        inv.setItem(9, item);

        item = XMaterial.valueOf(path).parseItem();
        item.setAmount(1);
        meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        inv.setItem(17, item);

        item = XMaterial.valueOf(path).parseItem();
        item.setAmount(1);
        meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        inv.setItem(12, item);

        item = XMaterial.valueOf(path).parseItem();
        item.setAmount(1);
        meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        inv.setItem(11, item);


    }


}
