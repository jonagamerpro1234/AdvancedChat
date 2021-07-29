package jss.advancedchat.inventory;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
        int amount = invData.getInt("Amount-Items");
        
        Inventory inv = Bukkit.createInventory(null, 54, Utils.color(title));

        ItemStack item = null;
        ItemMeta meta = null;

        //Player p = Bukkit.getPlayer(player2);

        setGlass(inv, item, meta, colorglass);
        item = Utils.getPlayerHead(player2);
        inv.setItem(4, item);
        
        for(String key : config.getConfigurationSection("Items").getKeys(false)) {
        	int slot = config.getInt("Items." + key + ".Slot");
        	String textures = config.getString("Items." + key + ".Texture");
        	textures = SkullUtils.replace(textures);
        	
        	item = Utils.createSkull(textures);
        	
        	item.setAmount(amount);
        	inv.setItem(slot, item);
        }
        
        plugin.addInventoryPlayer(player, "playerGui");
        player.openInventory(inv);
    }

    private void setGlass(Inventory inv, ItemStack item, ItemMeta meta, String path) {
        for(int i = 0 ; i < 54; i++) {
            item = XMaterial.valueOf(path).parseItem();
            meta = item.getItemMeta();
            meta.setDisplayName(" ");
            item.setItemMeta(meta);
            item.setAmount(1);
            inv.setItem(i, item);

            if (i == 54) {
                break;
            }
        }
    }

    public void setMuteItem(FileConfiguration config, Player player, Inventory inv, ItemStack item, ItemMeta meta) {
        PlayerManager manager = new PlayerManager(plugin);
        if (manager.check(player)) {
            if (manager.isMute(player) == true) {
            	
            } else {
            	
            }
        } else {
            return;
        }
    }



}
