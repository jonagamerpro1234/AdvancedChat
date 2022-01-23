package jss.advancedchat.listeners.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.inventory.InventoryActionHelper;
import jss.advancedchat.utils.inventory.InventoryActionHelper.InventoryType;
import jss.advancedchat.utils.inventory.InventoryView;

public class ColorInventoryListener implements Listener {
	
	private AdvancedChat plugin;

	public ColorInventoryListener(AdvancedChat plugin) {
		this.plugin = plugin;
		
	}
	
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
			
			String playerName = Utils.colorless(e.getClickedInventory().getItem(4).getItemMeta().getDisplayName());
			Player target = Bukkit.getPlayer(playerName);
			PlayerManager playerManager = new PlayerManager(target);
			InventoryActionHelper actionHelper = new InventoryActionHelper(plugin, j, target, playerManager, e);
			
			if(slot == Settings.color_inv_slot_red) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.Red", "red");
			}
			
			if(slot == Settings.color_inv_slot_darkred) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.Dark_Red", "dark_red");
			}
			
			if(slot == Settings.color_inv_slot_blue) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.blue", "blue");
			}
			
			if(slot == Settings.color_inv_slot_darkblue) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.Dark_Blue", "dark_blue");
			}
			
			if(slot == Settings.color_inv_slot_green) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.Green", "green");
			}
			
			if(slot == Settings.color_inv_slot_darkgreen) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.Dark_Green", "dark_green");
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
				actionHelper.setActionColor("AdvancedChat.Gui.Color.Dark_Purple", "dark_purple");
			}
			
			if(slot == Settings.color_inv_slot_aqua) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.Aqua", "aqua");
			}
			
			if(slot == Settings.color_inv_slot_darkaqua) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.Dark_Aqua", "dark_aqua");
			}
			
			if(slot == Settings.color_inv_slot_gray) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.Gray", "gray");
			}
			
			if(slot == Settings.color_inv_slot_darkgray) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.Dark_Gray", "dark_gray");
			}
			
			if(slot == Settings.color_inv_slot_white) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.White", "white");
			}
			
			if(slot == Settings.color_inv_slot_black) {
				actionHelper.setActionColor("AdvancedChat.Gui.Color.Black", "black");
			}
			
			if(slot == Settings.color_inv_slot_last) {
				actionHelper.setOpenInventoryAction(playerName, InventoryType.Gradient);
			}
			
			if(slot == Settings.color_inv_slot_next) {
				plugin.removeInvetoryView(target);
				actionHelper.setOpenInventoryAction(playerName, InventoryType.Player);
			}
			
			if(slot == Settings.color_inv_slot_exit) {
				j.closeInventory();
			}
			
		}
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e) {
		Player j = (Player) e.getPlayer();
		plugin.removeInvetoryView(j);
	}
	
}
