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

public class GuiColor {
	
	private AdvancedChat plugin;
	
	public GuiColor(AdvancedChat plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	public void openGuiColor(Player player) {
		ColorFile colorFile = plugin.getColorFile();
		FileConfiguration config = colorFile.getConfig();
		
		String title = config.getString("Title"); 
		
		Inventory inv = Bukkit.createInventory(null, 45, Utils.color(title));
		ItemStack item = null;
		SkullMeta skullMeta = null;
		ItemMeta meta = null;
		
		for(int i = 0; i < 9; i++) {
			String materialglass = config.getString("Decoration.Glass-Color.Item");
			String materialglasslegacy = config.getString("Decoration.Glass-Color.Legacy.Item");
			int datavalue = config.getInt("Decoration.Glass-Color.Legacy.Data-Value");
			if((Bukkit.getVersion().contains("1.13")) || (Bukkit.getVersion().contains("1.14")) || (Bukkit.getVersion().contains("1.15")) || (Bukkit.getVersion().contains("1.16"))) {
				item = new ItemStack(Material.valueOf(materialglass));
			}else {
				item = new ItemStack(Material.valueOf(materialglasslegacy));
				item.setDurability((short)datavalue);
			}
			meta = item.getItemMeta();
			meta.setDisplayName(Utils.color(" "));
			item.setItemMeta(meta);
			item.setAmount(1);
			inv.setItem(i, item);
		}
		
		for(String key : config.getConfigurationSection("Items").getKeys(false)) {	
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
			if((Bukkit.getVersion().contains("1.13")) || (Bukkit.getVersion().contains("1.14")) || (Bukkit.getVersion().contains("1.15")) || (Bukkit.getVersion().contains("1.16"))) {
				item = new ItemStack(Material.valueOf(material));
			}else {
				item = new ItemStack(Material.valueOf(materiallegacy));
				item.setDurability((short) datavalue);
			}

			if(useSkull.equals("true")) {
				item = Utils.setSkull(item, id, texture);
				skullMeta = (SkullMeta) item.getItemMeta();
				skullMeta.setDisplayName(Utils.color(name));
				for(int i = 0; i < lore.size(); i++) {
					String text = (String) lore.get(i);
					text = Utils.color(text);
					text = Utils.getVar(text, player);
					lore.add(text);
				}
				skullMeta.setLore(lore);
				item.setItemMeta(skullMeta);
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
	
		for(int i = 36; i < 40; i++) {
			String materialglass = config.getString("Decoration.Glass-Color.Item");
			String materialglasslegacy = config.getString("Decoration.Glass-Color.Legacy.Item");
			int datavalue = config.getInt("Decoration.Glass-Color.Legacy.Data-Value");
			if((Bukkit.getVersion().contains("1.13")) || (Bukkit.getVersion().contains("1.14")) || (Bukkit.getVersion().contains("1.15")) || (Bukkit.getVersion().contains("1.16"))) {
				item = new ItemStack(Material.valueOf(materialglass));
			}else {
				item = new ItemStack(Material.valueOf(materialglasslegacy));
				item.setDurability((short)datavalue);
			}
			meta = item.getItemMeta();
			meta.setDisplayName(Utils.color(" "));
			item.setItemMeta(meta);
			item.setAmount(1);
			inv.setItem(i, item);
		}
		
		String materialglass0 = config.getString("Decoration.Glass-Color.Item");
		String materialglasslegacy0 = config.getString("Decoration.Glass-Color.Legacy.Item");
		int datavalue0 = config.getInt("Decoration.Glass-Color.Legacy.Data-Value");
		if((Bukkit.getVersion().contains("1.13")) || (Bukkit.getVersion().contains("1.14")) || (Bukkit.getVersion().contains("1.15")) || (Bukkit.getVersion().contains("1.16"))) {
			item = new ItemStack(Material.valueOf(materialglass0));
		}else {
			item = new ItemStack(Material.valueOf(materialglasslegacy0));
			item.setDurability((short)datavalue0);
		}
		meta = item.getItemMeta();
		meta.setDisplayName(Utils.color(" "));
		item.setItemMeta(meta);
		item.setAmount(1);
		inv.setItem(9, item);
		
		String materialglass1 = config.getString("Decoration.Glass-Color.Item");
		String materialglasslegacy1 = config.getString("Decoration.Glass-Color.Legacy.Item");
		int datavalue1 = config.getInt("Decoration.Glass-Color.Legacy.Data-Value");
		if((Bukkit.getVersion().contains("1.13")) || (Bukkit.getVersion().contains("1.14")) || (Bukkit.getVersion().contains("1.15")) || (Bukkit.getVersion().contains("1.16"))) {
			item = new ItemStack(Material.valueOf(materialglass1));
		}else {
			item = new ItemStack(Material.valueOf(materialglasslegacy1));
			item.setDurability((short)datavalue1);
		}
		meta = item.getItemMeta();
		meta.setDisplayName(Utils.color(" "));
		item.setItemMeta(meta);
		item.setAmount(1);
		inv.setItem(18, item);
		
		String materialglass11 = config.getString("Decoration.Glass-Color.Item");
		String materialglasslegacy11 = config.getString("Decoration.Glass-Color.Legacy.Item");
		int datavalue11 = config.getInt("Decoration.Glass-Color.Legacy.Data-Value");
		if((Bukkit.getVersion().contains("1.13")) || (Bukkit.getVersion().contains("1.14")) || (Bukkit.getVersion().contains("1.15")) || (Bukkit.getVersion().contains("1.16"))) {
			item = new ItemStack(Material.valueOf(materialglass11));
		}else {
			item = new ItemStack(Material.valueOf(materialglasslegacy11));
			item.setDurability((short)datavalue11);
		}
		meta = item.getItemMeta();
		meta.setDisplayName(Utils.color(" "));
		item.setItemMeta(meta);
		item.setAmount(1);
		inv.setItem(17, item);
		
		String materialglass111 = config.getString("Decoration.Glass-Color.Item");
		String materialglasslegacy111 = config.getString("Decoration.Glass-Color.Legacy.Item");
		int datavalue111 = config.getInt("Decoration.Glass-Color.Legacy.Data-Value");
		if((Bukkit.getVersion().contains("1.13")) || (Bukkit.getVersion().contains("1.14")) || (Bukkit.getVersion().contains("1.15")) || (Bukkit.getVersion().contains("1.16"))) {
			item = new ItemStack(Material.valueOf(materialglass111));
		}else {
			item = new ItemStack(Material.valueOf(materialglasslegacy111));
			item.setDurability((short)datavalue111);
		}
		meta = item.getItemMeta();
		meta.setDisplayName(Utils.color(" "));
		item.setItemMeta(meta);
		item.setAmount(1);
		inv.setItem(26, item);
		
		String materialglass1111 = config.getString("Decoration.Glass-Color.Item");
		String materialglasslegacy1111 = config.getString("Decoration.Glass-Color.Legacy.Item");
		int datavalue1111 = config.getInt("Decoration.Glass-Color.Legacy.Data-Value");
		if((Bukkit.getVersion().equals("1.13")) || (Bukkit.getVersion().equals("1.14")) || (Bukkit.getVersion().equals("1.15")) || (Bukkit.getVersion().equals("1.16"))) {
			item = new ItemStack(Material.valueOf(materialglass1111));
		}else {
			item = new ItemStack(Material.valueOf(materialglasslegacy1111));
			item.setDurability((short)datavalue1111);
		}
		meta = item.getItemMeta();
		meta.setDisplayName(Utils.color(" "));
		item.setItemMeta(meta);
		item.setAmount(1);
		inv.setItem(27, item);
		
		String materialglass11111 = config.getString("Decoration.Glass-Color.Item");
		String materialglasslegacy11111 = config.getString("Decoration.Glass-Color.Legacy.Item");
		int datavalue11111 = config.getInt("Decoration.Glass-Color.Legacy.Data-Value");
		if((Bukkit.getVersion().contains("1.13")) || (Bukkit.getVersion().contains("1.14")) || (Bukkit.getVersion().contains("1.15")) || (Bukkit.getVersion().contains("1.16"))) {
			item = new ItemStack(Material.valueOf(materialglass11111));
		}else {
			item = new ItemStack(Material.valueOf(materialglasslegacy11111));
			item.setDurability((short)datavalue11111);
		}
		meta = item.getItemMeta();
		meta.setDisplayName(Utils.color(" "));
		item.setItemMeta(meta);
		item.setAmount(1);
		inv.setItem(35, item);
		
		meta = item.getItemMeta();
		meta.setDisplayName(Utils.color(" "));
		item.setItemMeta(meta);
		item.setAmount(1);
		inv.setItem(27, item);
		
		String materialglass111111 = config.getString("Decoration.Glass-Color.Item");
		String materialglasslegacy111111 = config.getString("Decoration.Glass-Color.Legacy.Item");
		int datavalue111111 = config.getInt("Decoration.Glass-Color.Legacy.Data-Value");
		if((Bukkit.getVersion().contains("1.13")) || (Bukkit.getVersion().contains("1.14")) || (Bukkit.getVersion().contains("1.15")) || (Bukkit.getVersion().contains("1.16"))) {
			item = new ItemStack(Material.valueOf(materialglass111111));
		}else {
			item = new ItemStack(Material.valueOf(materialglasslegacy111111));
			item.setDurability((short)datavalue111111);
		}
		meta = item.getItemMeta();
		meta.setDisplayName(Utils.color(" "));
		item.setItemMeta(meta);
		item.setAmount(1);
		inv.setItem(35, item);
		
		meta = item.getItemMeta();
		meta.setDisplayName(Utils.color(" "));
		item.setItemMeta(meta);
		item.setAmount(1);
		inv.setItem(31, item);
		
		for(int i = 28 ; i < 30; i++) {
			String materialglass = config.getString("Decoration.Glass-Color.Item");
			String materialglasslegacy = config.getString("Decoration.Glass-Color.Legacy.Item");
			int datavalue = config.getInt("Decoration.Glass-Color.Legacy.Data-Value");
			if((Bukkit.getVersion().contains("1.13")) || (Bukkit.getVersion().contains("1.14")) || (Bukkit.getVersion().contains("1.15")) || (Bukkit.getVersion().contains("1.16"))) {
				item = new ItemStack(Material.valueOf(materialglass));
			}else {
				item = new ItemStack(Material.valueOf(materialglasslegacy));
				item.setDurability((short)datavalue);
			}
			meta = item.getItemMeta();
			meta.setDisplayName(Utils.color(" "));
			item.setItemMeta(meta);
			item.setAmount(1);
			inv.setItem(i, item);
		}
		
		for(int i = 33 ; i < 35; i++) {
			String materialglass = config.getString("Decoration.Glass-Color.Item");
			String materialglasslegacy = config.getString("Decoration.Glass-Color.Legacy.Item");
			int datavalue = config.getInt("Decoration.Glass-Color.Legacy.Data-Value");
			if((Bukkit.getVersion().contains("1.13")) || (Bukkit.getVersion().contains("1.14")) || (Bukkit.getVersion().contains("1.15")) || (Bukkit.getVersion().contains("1.16"))) {
				item = new ItemStack(Material.valueOf(materialglass));
			}else {
				item = new ItemStack(Material.valueOf(materialglasslegacy));
				item.setDurability((short)datavalue);
			}
			meta = item.getItemMeta();
			meta.setDisplayName(Utils.color(" "));
			item.setItemMeta(meta);
			item.setAmount(1);
			inv.setItem(i, item);
		}
		
		String nameBack = config.getString("Decoration.Back.Name");
		String materialback = config.getString("Decoration.Back.Item");
		String materialbacklegacy = config.getString("Decoration.Back.Legacy.Item");
		int backdatavalue = config.getInt("Decoration.Back.Legacy.Data-Value");
		if((Bukkit.getVersion().equals("1.13")) || (Bukkit.getVersion().equals("1.14")) || (Bukkit.getVersion().equals("1.15")) || (Bukkit.getVersion().equals("1.16"))) {
			item = new ItemStack(Material.valueOf(materialback));
		}else {
			item = new ItemStack(Material.valueOf(materialbacklegacy));
			item.setDurability((short)backdatavalue);
		}
		meta = item.getItemMeta();
		meta.setDisplayName(Utils.color(nameBack));
		item.setItemMeta(meta);
		item.setAmount(1);
		inv.setItem(40, item);
		
		
		for(int i = 41; i < 45; i++) {
			String materialglass = config.getString("Decoration.Glass-Color.Item");
			String materialglasslegacy = config.getString("Decoration.Glass-Color.Legacy.Item");
			int datavalue = config.getInt("Decoration.Glass-Color.Legacy.Data-Value");
			if((Bukkit.getVersion().contains("1.13")) || (Bukkit.getVersion().contains("1.14")) || (Bukkit.getVersion().contains("1.15")) || (Bukkit.getVersion().contains("1.16"))) {
				item = new ItemStack(Material.valueOf(materialglass));
			}else {
				item = new ItemStack(Material.valueOf(materialglasslegacy));
				item.setDurability((short)datavalue);
			}
			meta = item.getItemMeta();
			meta.setDisplayName(Utils.color(" "));
			item.setItemMeta(meta);
			item.setAmount(1);
			inv.setItem(i, item);
		}
		
		player.openInventory(inv);
		plugin.addInventoryPlayer(player, "color");
	}
	
}
