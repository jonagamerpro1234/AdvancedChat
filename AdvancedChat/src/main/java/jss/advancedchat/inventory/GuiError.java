package jss.advancedchat.inventory;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.cryptomorin.xseries.XMaterial;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.inventory.InventoryUtils;

public class GuiError {
	
	private AdvancedChat plugin = AdvancedChat.get();
	private Inventory inv;
	private ItemStack item;
	private ItemMeta meta;
	
	public void open(Player player) {
		plugin.addInventoryView(player, "errorGui");
		create();
		player.openInventory(inv);
	}
	
	private void create() {
		inv = Bukkit.createInventory(null, 9, Utils.color("&b&lMenu &8&l> &c&lError"));
		setDecoration();
		
		item = XMaterial.OAK_SIGN.parseItem();
		meta = item.getItemMeta();
		meta.setDisplayName(Utils.color("&cError loading inventory"));
		meta.addEnchant(Enchantment.DURABILITY, 1, false);
		List<String> lore = Arrays.asList("&eIf this error persists please report it","&eon discord or github");
		meta.setLore(InventoryUtils.coloredLore(lore));
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		inv.setItem(4, item);
	}
	
	
	private void setDecoration() {
		for (int i = 0; i < 9; i++) {
			item = XMaterial.BLACK_STAINED_GLASS_PANE.parseItem();
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
}
