package jss.advancedchat.utils.inventory;

import java.util.List;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.cryptomorin.xseries.XMaterial;

import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Utils;

public class ItemBuilder {
	
	private ItemStack item;
	private ItemMeta meta;
	
	private String texture;
	private int slot;
	
	public ItemBuilder name(String name) {
		meta.setDisplayName(Utils.color(name));
		return this;
	}
	
	public ItemBuilder slot(int slot) {
		this.slot = slot;
		return this;
	}
	
	public ItemBuilder lore(List<String> lore) {
		meta.setLore(lore);
		return this;
	}
	
	public ItemBuilder amount(int amount) {
		item.setAmount(amount);
		return this;
	}
	
	public ItemBuilder material(String material) {
		if(material.isEmpty()) {
			Logger.error("Could not find " + material + ", please check item name");
		}
		
		item = XMaterial.valueOf(material.toUpperCase()).parseItem();
		return this;
	}
	
	public ItemBuilder skull() {
		item = Utils.createSkull(texture);
		return this;
	}
	
	public ItemBuilder enchantment(Enchantment ench, int level, boolean ignore) {
		meta.addEnchant(ench, level, ignore);
		return this;
	}
	
	public ItemBuilder enchantment(Enchantment ench, int level) {
		enchantment(ench, level, false);
		return this;
	}
	
	public ItemBuilder enchantment(Enchantment ench) {
		enchantment(ench, 1, false);
		return this;
	}
	
	public ItemBuilder itemflag(ItemFlag... flags) {
		meta.addItemFlags(flags);
		return this;
	}
	
	public ItemBuilder closeItemMeta() {
		item.setItemMeta(meta);
		return this;
	}
	
	public ItemBuilder texture(String texture) {
		this.texture = texture;
		return this;
	}
	
	public void createItem(Inventory inv) {
		inv.setItem(slot, item);
	}
	
	public void createRowItem(Inventory inv, int init, int end) {
		for(int i = init; i < end; i++) {
			createItem(inv);
			if(i == end) break;
		}
	}
	
	
}
