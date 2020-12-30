package jss.advancedchat.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.cryptomorin.xseries.XMaterial;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.ColorFile;
import jss.advancedchat.test.PlayerManager;
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
		PlayerManager manager = new PlayerManager(plugin);

		String title = config.getString("Title");

		Inventory inv = Bukkit.createInventory(null, 45, Utils.color(title));
		ItemStack item = null;
		SkullMeta skullMeta = null;
		ItemMeta meta = null;
		String version = Bukkit.getServer().getClass().getPackage().getName();
		String materialglass = config.getString("Decoration.Glass-Color.Item");
		setDecoration(inv, item, meta, materialglass);
		// skulls

		/*item = XMaterial.PLAYER_HEAD.parseItem();
		SkullMeta skullmeta = (SkullMeta) item.getItemMeta();
		skullmeta.setDisplayName(Utils.color("&6&l"+player.getName()));
		skullmeta.setOwner(player.getName());
		List<String> lores = new ArrayList<String>();
		lores.add(Utils.color("&bColor: "+manager.getColor(player)+"=========="));
		skullmeta.setLore(lores);
		item.setItemMeta(skullmeta);
		item.setAmount(1);
		inv.setItem(9, item);*/
		
		for (String key : config.getConfigurationSection("Items").getKeys(false)) {
			String material = config.getString("Items." + key + ".Item");
			String materiallegacy = config.getString("Items." + key + ".Legacy.Item");
			int datavalue = config.getInt("Items." + key + ".Legacy.Data-Value");
			int slots = config.getInt("Items." + key + ".Slot");
			String name = config.getString("Items." + key + ".Name");
			String useSkull = config.getString("Items." + key + ".Use-Custom-Skull");
			String texture = config.getString("Items." + key + ".Texture");
			String id = config.getString("Items." + key + ".ID");
			int amont = config.getInt("Items." + key + ".Amount");
			List<String> lore = config.getStringList("Items." + key + ".Lore");

			if ((version.contains("1.13")) || (version.contains("1.14")) || (version.contains("1.15"))
					|| (version.contains("1.16"))) {
				item = new ItemStack(Material.valueOf(material));
			} else {
				item = new ItemStack(Material.valueOf(materiallegacy));
				item.setDurability((short) datavalue);
			}

			if (useSkull.contains("true")) {
				item = Utils.setSkull(item, id, texture);
				skullMeta = (SkullMeta) item.getItemMeta();
				skullMeta.setDisplayName(Utils.color(name));
				for (int i = 0; i < lore.size(); i++) {
					String text = (String) lore.get(i);
					text = Utils.color(text);
					text = Utils.getVar(text, player);
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
					text = Utils.getVar(text, player);
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
		meta.setDisplayName(Utils.color(nameBack));
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
		}
		
		for (int i = 36; i < 40; i++) {
			item = XMaterial.valueOf(path).parseItem();
			meta = item.getItemMeta();
			meta.setDisplayName(Utils.color(" "));
			item.setItemMeta(meta);
			item.setAmount(1);
			inv.setItem(i, item);
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
		}
		for (int i = 33; i < 35; i++) {
			item = XMaterial.valueOf(path).parseItem();
			meta = item.getItemMeta();
			meta.setDisplayName(Utils.color(" "));
			item.setItemMeta(meta);
			item.setAmount(1);
			inv.setItem(i, item);
		}

		for (int i = 41; i < 45; i++) {
			item = XMaterial.valueOf(path).parseItem();
			meta = item.getItemMeta();
			meta.setDisplayName(Utils.color(" "));
			item.setItemMeta(meta);
			item.setAmount(1);
			inv.setItem(i, item);
		}
	}

}
