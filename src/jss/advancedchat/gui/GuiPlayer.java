package jss.advancedchat.gui;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.cryptomorin.xseries.XMaterial;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Utils;

public class GuiPlayer {

	private AdvancedChat plugin;
	
	public GuiPlayer(AdvancedChat plugin) {
		this.plugin = plugin;
	}

	public void openPlayerGui(Player player) {
		FileConfiguration config = plugin.getPlayerGuiFile().getConfig();
		
		String title = config.getString("Title");
		String pathcolor = config.getString("Decoration.Glass-Color.Item");
		
		title = title.replace("<player>", player.getName());
		Inventory inv = Bukkit.createInventory(null, 27, Utils.color(title));
		
		ItemStack item = null;
		ItemMeta meta = null;
		
		setDecorationPlayer(inv, item, meta, pathcolor);
		
		player.openInventory(inv);
	}
	
	public void openPlayerListGui(Player player) {
		Inventory inv = Bukkit.createInventory(null, 56, "Player List");
	}
	
	
	private void setDecorationPlayer(Inventory inv, ItemStack item, ItemMeta meta, String path) {
		
		for(int i = 1; i < 9; i++) {
			item = XMaterial.valueOf(path).parseItem();
			item.setAmount(1);
			meta = item.getItemMeta();
			meta.setDisplayName(" ");
			item.setItemMeta(meta);
			inv.setItem(i, item);
			if(i == 9) {
				break;
			}
		}
		
		for(int i = 18; i < 27; i++) {
			item = XMaterial.valueOf(path).parseItem();
			item.setAmount(1);
			meta = item.getItemMeta();
			meta.setDisplayName(" ");
			item.setItemMeta(meta);
			inv.setItem(i, item);
			if(i == 9) {
				break;
			}
		}
	}
	
	private void setDecorationPlayerList(Inventory inv, ItemStack item, ItemMeta meta) {
		
		for(int i = 0; i < 9; i++) {
			item = XMaterial.BLACK_STAINED_GLASS_PANE.parseItem();
			item.setAmount(1);
			meta = item.getItemMeta();
			meta.setDisplayName(" ");
			item.setItemMeta(meta);
			inv.setItem(i, item);
			
			if(i == 9) {
				break;
			}
		}
		
		/*for(int i = 46; i < 53; i++) {
			item = XMaterial.BLACK_STAINED_GLASS_PANE.parseItem();
			item.setAmount(1);
			meta = item.getItemMeta();
			meta.setDisplayName(" ");
			item.setItemMeta(meta);
			inv.setItem(i, item);
			if(i == 9) {
				break;
			}
		}*/
	}
	
}
