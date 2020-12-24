package jss.advancedchat.gui;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.ColorFile;
import jss.advancedchat.utils.Utils;

public class InventoryColor {
	
	private AdvancedChat plugin;
	
	public InventoryColor(AdvancedChat plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	public void openGuiColor(Player player) {
		ColorFile colorFile = plugin.getColorFile();
		FileConfiguration config = colorFile.getConfig();
		
		String title = config.getString("Title"); 
		
		Inventory inv = Bukkit.createInventory(null, 27, Utils.color(title));
		
		for(String key : config.getConfigurationSection("Items").getKeys(false)) {
			
			ItemStack item = null;
			SkullMeta skullMeta = null;
			ItemMeta meta = null;
			String material = config.getString("Items."+key+".Item");
			String materiallegacy = config.getString("Items."+key+".Legacy.Item");
			int datavalue = config.getInt("Items."+key+".Legacy.Data-Value");
			int slots = config.getInt("Items."+key+".Slot");
			String name = config.getString("Items."+key+".Name");
			String useSkull = config.getString("Items."+key+".Use-Custom-Skull");
			String texture = config.getString("Items."+key+".Texture");
			String id = config.getString("Items."+key+".ID");
			int amont = config.getInt("Items."+key+".Amount");
			List<String> lore = config.getStringList("Items."+key+".Lore");
			if((Bukkit.getVersion().equals("1.13")) || (Bukkit.getVersion().equals("1.14")) || (Bukkit.getVersion().equals("1.15")) || (Bukkit.getVersion().equals("1.16"))) {
				item = new ItemStack(Material.valueOf(material));
			}else {
				item = new ItemStack(Material.valueOf(materiallegacy));
			}

			if(useSkull.equals("true")) {
				skullMeta = (SkullMeta) item.getItemMeta();
				skullMeta.setDisplayName(Utils.color(name));
				for(int i = 0; i < lore.size(); i++) {
					String text = (String) lore.get(i);
					text = Utils.color(text);
					text = Utils.getVar(text, player);
					lore.add(text);
				}
				skullMeta.setLore(lore);
				item.setDurability((short) datavalue);
				item.setItemMeta(skullMeta);
				item = Utils.setSkull(item, id, texture);
			}else if(useSkull.equals("false")) {
				meta = item.getItemMeta();
				meta.setDisplayName(Utils.color(name));
				for(int i = 0; i < lore.size(); i++) {
					String text = (String) lore.get(i);
					text = Utils.color(text);
					text = Utils.getVar(text, player);
					lore.add(text);
				}
				meta.setLore(lore);

				item.setItemMeta(meta);
			}
			item.setAmount(amont);
			inv.setItem(slots, item);
			
		}
		
		player.openInventory(inv);
	}
	
}
