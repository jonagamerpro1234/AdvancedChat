package jss.advancedchat.inventory;

import java.util.ArrayList;
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
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.inventory.TSkullUtils;

public class GuiSettings {
	
	private AdvancedChat plugin = AdvancedChat.get();
	private Inventory inv;
	private ItemStack item;
	private ItemMeta meta;
	
	public void open(Player player, String target) {
		plugin.addInventoryView(player, "settingsGui");
		create();
		addItems(player, target);
		player.openInventory(inv);
	}
	
	private void create() {
		inv = Bukkit.createInventory(null, 54, Utils.color("&b&lMenu &8&l> &e&lSettings"));
	}

	private void addItems(Player player ,String target) {
		this.setDecoration();
		
		item = Utils.getPlayerHead(target);
		inv.setItem(4, item);
		
		PlayerManager playerManager = new PlayerManager(Bukkit.getPlayer(target));
		
		if(!playerManager.isLowMode()) {
			item  = Utils.createSkull(TSkullUtils.replace("[exit]"));
		}else {
			item  = XMaterial.RED_STAINED_GLASS_PANE.parseItem();
		}
		
		meta = item.getItemMeta();
		meta.setDisplayName(Utils.color("&c&lExit"));
		item.setItemMeta(meta);
		item.setAmount(1);
		inv.setItem(0, item);
		
		if(!playerManager.isLowMode()) {
			item  = Utils.createSkull(TSkullUtils.replace("[last]"));
		}else {
			item  = XMaterial.YELLOW_STAINED_GLASS_PANE.parseItem();
		}
		
		meta = item.getItemMeta();
		meta.setDisplayName(Utils.color("&e&lLast Menu"));
		item.setItemMeta(meta);
		item.setAmount(1);
		inv.setItem(3, item);
		
		isLowModeItem(playerManager, player);
		isChat(playerManager, player);
		isMention(playerManager, player);
		isMsg(playerManager, player);
		
		item = XMaterial.BEDROCK.parseItem();
		meta.setDisplayName(Utils.color("&e?"));
		item.setItemMeta(meta);
		item.setAmount(1);
		inv.setItem(37, item);
		
		item = XMaterial.BEDROCK.parseItem();
		meta.setDisplayName(Utils.color("&e?"));
		item.setItemMeta(meta);
		item.setAmount(1);
		inv.setItem(39, item);
		
		item = XMaterial.BEDROCK.parseItem();
		meta.setDisplayName(Utils.color("&e?"));
		item.setItemMeta(meta);
		item.setAmount(1);
		inv.setItem(41, item);
		
		item = XMaterial.BEDROCK.parseItem();
		meta.setDisplayName(Utils.color("&e?"));
		item.setItemMeta(meta);
		item.setAmount(1);
		inv.setItem(43, item);
		
		item = XMaterial.EMERALD.parseItem();
		meta.setDisplayName(Utils.color("&eUpdate Inventories"));
		item.setItemMeta(meta);
		inv.setItem(45, item);
	}
	
	
	public void isLowModeItem(PlayerManager playerManager, Player player) {
		ItemStack item = XMaterial.REPEATER.parseItem();
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Utils.color("&eLow Mode"));
		
		if(playerManager.isLowMode()) {
			meta.addEnchant(Enchantment.DURABILITY, 1, false);
		}
		
		List<String> lore = Arrays.asList(Settings.message_NoPermission);
		if(!player.isOp() || !player.hasPermission("AdvancedChat.Gui.Settings.LowMode")) {
			meta.setLore(coloredLore(lore));
		}
		
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		inv.setItem(19, item);
	}
	
	public void isChat(PlayerManager playerManager, Player player) {
		ItemStack item = XMaterial.REPEATER.parseItem();
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Utils.color("&eChat"));
		
		if(playerManager.isChat()) {
			meta.addEnchant(Enchantment.DURABILITY, 1, false);
		}
		
		List<String> lore = Arrays.asList(Settings.message_NoPermission);
		if(!player.isOp() || !player.hasPermission("AdvancedChat.Gui.Settings.Chat")) {
			meta.setLore(coloredLore(lore));
		}
		
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		inv.setItem(21, item);
	}
	
	public void isMention(PlayerManager playerManager, Player player) {
		ItemStack item = XMaterial.REPEATER.parseItem();
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Utils.color("&eMention"));
		
		if(playerManager.isMention()) {
			meta.addEnchant(Enchantment.DURABILITY, 1, false);
		}
		
		List<String> lore = Arrays.asList(Settings.message_NoPermission);
		if(!player.isOp() || !player.hasPermission("AdvancedChat.Gui.Settings.Mention")) {
			meta.setLore(coloredLore(lore));
		}
		
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		inv.setItem(23, item);
	}
	
	public void isMsg(PlayerManager playerManager, Player player) {
		ItemStack item = XMaterial.REPEATER.parseItem();
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Utils.color("&eMsg"));
		
		if(playerManager.isMention()) {
			meta.addEnchant(Enchantment.DURABILITY, 1, false);
		}
		
		List<String> lore = Arrays.asList(Settings.message_NoPermission);
		if(!player.isOp() || !player.hasPermission("AdvancedChat.Gui.Settings.Msg")) {
			meta.setLore(coloredLore(lore));
		}
		
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		inv.setItem(25, item);
	}
	
	public List<String> coloredLore(List<String> lore) {
		List<String> coloredlore = new ArrayList<>();
		lore.forEach((line) -> {
			String lineColored = Utils.color(line);
			coloredlore.add(lineColored);
		});
		return coloredlore;
	}

	public void setDecoration() {
		for (int i = 0; i < 54; i++) {
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
