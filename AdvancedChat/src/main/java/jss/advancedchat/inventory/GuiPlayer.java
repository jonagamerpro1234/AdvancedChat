package jss.advancedchat.inventory;

import java.util.Set;

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
    private ItemStack item;
    private ItemMeta meta;

    public GuiPlayer(AdvancedChat plugin) {
        this.plugin = plugin;
    }
    

    public void open(Player player, String player2) {
        FileConfiguration config = plugin.getPlayerGuiFile().getConfig();
        FileConfiguration invData = plugin.getInventoryDataFile().getConfig();
        
        String title = config.getString("Title");
        String colorglass = invData.getString("Color-Glass.Player");
        int amount = invData.getInt("Amount-Items");
        
        Inventory inv = Bukkit.createInventory(null, 54, Utils.color(title));

        setGlass(inv, colorglass);
        
        item = Utils.getPlayerHead(player2);
        inv.setItem(4, item);
        
       Set<String> section = config.getConfigurationSection("Items").getKeys(false);
       
       section.forEach( (key) -> {
    	   int slot = config.getInt("Items." + key + ".Slot");
        	boolean isSkull = config.getBoolean("Items." + key + ".Is-Skull");
        	String name = config.getString("Items." + key + ".Name");
        	
        	if(isSkull) {
            	String textures = config.getString("Items." + key + ".Texture");
            	textures = SkullUtils.replace(textures);
            	item = Utils.createSkull(textures);
        	}else {
        		String mat = config.getString("Items." + key + ".Item");
        		item = XMaterial.valueOf(mat.toUpperCase()).parseItem();
        	}
        	meta = item.getItemMeta();
        	meta.setDisplayName(Utils.color(name));
        	
        	item.setItemMeta(meta);
        	item.setAmount(amount);
        	
        	inv.setItem(slot, item);
        	
        	
        	
        });
       
        plugin.addInventoryView(player, "playerGui");
        player.openInventory(inv);
    }

    private void setGlass(Inventory inv, String path) {
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

    public ItemStack getMuteItem(Player player) {
        PlayerManager manager = new PlayerManager(plugin);
        if (manager.exists(player)) {
            if (manager.isMute(player)) {
            	return item = XMaterial.GREEN_DYE.parseItem();
            } else {
            	return item = XMaterial.GRAY_DYE.parseItem();
            }
        } else {
            return XMaterial.STONE.parseItem();
        }
    }



}
