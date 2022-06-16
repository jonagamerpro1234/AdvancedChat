package jss.advancedchat.listeners.inventory;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.cryptomorin.xseries.XMaterial;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Util;
import jss.advancedchat.utils.inventory.InventoryActionHelper;
import jss.advancedchat.utils.inventory.InventoryActionHelper.InventoryType;
import jss.advancedchat.utils.inventory.InventoryUtils;
import jss.advancedchat.utils.inventory.InventoryView;

public class ColorInventoryListener implements Listener {
	
	private AdvancedChat plugin = AdvancedChat.get();
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player j = (Player) e.getWhoClicked();
		InventoryView inventoryView = plugin.getInventoryView(j);
		
		if(inventoryView == null) return;
		if(!inventoryView.getInventoryName().contains("colorGui")) return;
		if(e.getCurrentItem() == null || e.getCurrentItem().getType().name().contains("AIR")) {
			e.setCancelled(true);
			return;
		}
		if((e.getSlotType() == null)) {
			e.setCancelled(true);
			return;
		}else {
			
			if(!e.getClickedInventory().equals(j.getOpenInventory().getTopInventory())) return;
			
			int slot = e.getSlot();
			e.setCancelled(true);
			
			String playerName = Util.colorless(e.getClickedInventory().getItem(4).getItemMeta().getDisplayName());
			Player target = Bukkit.getPlayer(playerName);
			PlayerManager playerManager = new PlayerManager(target);
			InventoryActionHelper actionHelper = new InventoryActionHelper(j, target, playerManager, e);
			
			if(slot == Settings.color_inv_slot_red) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.Red", "red");
			}
			
			if(slot == Settings.color_inv_slot_darkred) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.DarkRed", "dark_red");
			}
			
			if(slot == Settings.color_inv_slot_blue) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.Blue", "blue");
			}
			
			if(slot == Settings.color_inv_slot_darkblue) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.DarkBlue", "dark_blue");
			}
			
			if(slot == Settings.color_inv_slot_green) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.Green", "green");
			}
			
			if(slot == Settings.color_inv_slot_darkgreen) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.DarkGreen", "dark_green");
			}
			
			if(slot == Settings.color_inv_slot_yellow) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.Yellow", "yellow");
			}
			
			if(slot == Settings.color_inv_slot_gold) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.Gold", "gold");
			}
			
			if(slot == Settings.color_inv_slot_lightpurple) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.Light_Purple", "light_purple");
			}
			
			if(slot == Settings.color_inv_slot_darkpurple) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.DarkPurple", "dark_purple");
			}
			
			if(slot == Settings.color_inv_slot_aqua) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.Aqua", "aqua");
			}
			
			if(slot == Settings.color_inv_slot_darkaqua) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.DarkAqua", "dark_aqua");
			}
			
			if(slot == Settings.color_inv_slot_gray) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.Gray", "gray");
			}
			
			if(slot == Settings.color_inv_slot_darkgray) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.DarkGray", "dark_gray");
			}
			
			if(slot == Settings.color_inv_slot_white) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.White", "white");
			}
			
			if(slot == Settings.color_inv_slot_black) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.Black", "black");
			}
			
			if(slot == Settings.color_inv_slot_rainbow) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.Random", "random");
			}
			
			if(slot == Settings.color_inv_slot_last) {
				plugin.removeInvetoryView(j);
				actionHelper.setOpenInventoryAction(playerName, InventoryType.Player);
			}
			
			if(slot == Settings.color_inv_slot_next) {
				plugin.removeInvetoryView(j);
				actionHelper.setOpenInventoryAction(playerName, InventoryType.Gradient);
			}
			
			if(slot == Settings.color_inv_slot_exit) {
				j.closeInventory();
			}
			
			if(slot == 45) {
				setCustomItem(playerManager, e.getInventory());
			}
		}
	}
	
	private void setCustomItem(PlayerManager playerManager, Inventory inv) {
		ItemStack item;
		ItemMeta meta;
		
		if(playerManager.isColor()) {
			item = XMaterial.GRAY_DYE.parseItem();
			meta = item.getItemMeta();
			meta.setDisplayName(Util.color("&cDisable"));
			List<String> lore = Arrays.asList("&7Click to &aenable");
			meta.setLore(InventoryUtils.coloredLore(lore));
			item.setItemMeta(meta);
			inv.setItem(45, item);
			playerManager.setColor(false);
		}else {
			item = XMaterial.LIME_DYE.parseItem();
			meta = item.getItemMeta();
			meta.setDisplayName(Util.color("&aEnable"));
			List<String> lore = Arrays.asList("&7Click to &cdisable");
			meta.setLore(InventoryUtils.coloredLore(lore));
			item.setItemMeta(meta);
			inv.setItem(45, item);
			playerManager.setColor(true);
		}
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e) {
		Player j = (Player) e.getPlayer();
		plugin.removeInvetoryView(j);
	}
	
}
