package jss.advancedchat.inventory;

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

import com.cryptomorin.xseries.XEnchantment;
import com.cryptomorin.xseries.XMaterial;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.SkullUtils;
import jss.advancedchat.utils.Utils;

public class GuiPlayer {

    private AdvancedChat plugin;

    public GuiPlayer(AdvancedChat plugin) {
        this.plugin = plugin;
    }

    public void openPlayerGui(Player player, String player2) {
        FileConfiguration config = plugin.getPlayerGuiFile().getConfig();
        FileConfiguration invData = plugin.getInventoryDataFile().getConfig();
        
        String title = config.getString("Title");
        String colorglass = invData.getString("Color-Glass.Color");

        Inventory inv = Bukkit.createInventory(null, 54, Utils.color(title));

        ItemStack item = null;
        ItemMeta meta = null;
        SkullMeta skullmeta = null;

        Player p = Bukkit.getPlayer(player2);

        setDecoration(inv, item, meta, colorglass);
        item = Utils.getPlayerHead(player2);
        inv.setItem(4, item);
        
        for(String key : config.getConfigurationSection("Items-Scroll").getKeys(false)) {
        	int slot = config.getInt("Items-Scroll." + key + ".Slot");
        	String textures = config.getString("Items-Scroll." + key + ".Texture");
        	textures = SkullUtils.replace(textures);
        	
        	item = Utils.createSkull(textures);
        	
        	inv.setItem(slot, item);
        }
        
        
        

       /* String material = config.getString("Player-Gui.Items.Color.Item");
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
        inv.setItem(16, item);*/

        player.openInventory(inv);
        //plugin.addInventoryPlayer(player, "player");
    }

    private void setDecoration(Inventory inv, ItemStack item, ItemMeta meta, String path) {
        for(int i = 0 ; i < 54; i++) {
            item = XMaterial.valueOf(path).parseItem();
            meta = item.getItemMeta();
            meta.setDisplayName(Utils.color(" "));
            item.setItemMeta(meta);
            item.setAmount(1);
            inv.setItem(i, item);

            if (i == 54) {
                break;
            }
        }
    }

    private void setMuteItem(FileConfiguration config, Player player, Inventory inv, ItemStack item, ItemMeta meta) {

        PlayerManager manager = new PlayerManager(plugin);
        if (manager.check(player)) {
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
            if (i == 9) break;
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
            if (i == 27) break;            
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
