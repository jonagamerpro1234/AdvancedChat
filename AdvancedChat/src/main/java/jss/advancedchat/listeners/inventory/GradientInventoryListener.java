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
import jss.advancedchat.utils.inventory.InventoryView;
import jss.advancedchat.utils.inventory.InventoryActionHelper.InventoryType;

public class GradientInventoryListener implements Listener{
	
	private AdvancedChat plugin;

	public GradientInventoryListener(AdvancedChat plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler 
	public void onClick(InventoryClickEvent e) {
		Player j = (Player) e.getWhoClicked();
		InventoryView inventoryView = plugin.getInventoryView(j);
		
		if(inventoryView == null) return;
		if(!inventoryView.getInventoryName().contains("gradientGui")) return;
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
			InventoryActionHelper actionHelper = new InventoryActionHelper(j, target, playerManager, e);
			
			if(slot == Settings.gradient_inv_slot_red) {
				actionHelper.setDoubleActionColor("AdvancedChat.Gui.Gradient.Red", "FF5555", "FF5555");
			}
			
			if(slot == Settings.gradient_inv_slot_darkred) {
				actionHelper.setDoubleActionColor("AdvancedChat.Gui.Gradient.Dark_Red", "AA0000", "AA0000");
			}
			
			if(slot == Settings.gradient_inv_slot_blue) {
				actionHelper.setDoubleActionColor("AdvancedChat.Gui.Gradient.Blue", "5555FF", "5555FF");
			}
			
			if(slot == Settings.gradient_inv_slot_darkblue) {
				actionHelper.setDoubleActionColor("AdvancedChat.Gui.Gradient.Dark_Blue", "0000AA", "0000AA");
			}
			
			if(slot == Settings.gradient_inv_slot_green) {
				actionHelper.setDoubleActionColor("AdvancedChat.Gui.Gradient.Green", "55FF55", "55FF55");
			}
			
			if(slot == Settings.gradient_inv_slot_darkgreen) {
				actionHelper.setDoubleActionColor("AdvancedChat.Gui.Gradient.Dark_Green", "00AA00", "00AA00");
			}
			
			if(slot == Settings.gradient_inv_slot_yellow) {
				actionHelper.setDoubleActionColor("AdvancedChat.Gui.Gradient.Yellow", "FFFF55", "FFFF55");
			}
			
			if(slot == Settings.gradient_inv_slot_gold) {
				actionHelper.setDoubleActionColor("AdvancedChat.Gui.Gradient.Gold", "FFAA00", "FFAA00");
			}
			
			if(slot == Settings.gradient_inv_slot_aqua) {
				actionHelper.setDoubleActionColor("AdvancedChat.Gui.Gradient.Aqua", "55FFFF", "55FFFF");
			}
			
			if(slot == Settings.gradient_inv_slot_darkaqua) {
				actionHelper.setDoubleActionColor("AdvancedChat.Gui.Gradient.Dark_Aqua", "00AAAA", "00AAAA");
			}
			
			if(slot == Settings.gradient_inv_slot_lightpurple) {
				actionHelper.setDoubleActionColor("AdvancedChat.Gui.Gradient.Light_Purple", "FF55FF", "FF55FF");
			}
			
			if(slot == Settings.gradient_inv_slot_darkpurple) {
				actionHelper.setDoubleActionColor("AdvancedChat.Gui.Gradient.Dark_Purple", "AA00AA", "AA00AA");
			}
			
			if(slot == Settings.gradient_inv_slot_gray) {
				actionHelper.setDoubleActionColor("AdvancedChat.Gui.Gradient.Gray", "AAAAAA", "AAAAAA");
			}
			
			if(slot == Settings.gradient_inv_slot_darkgray) {
				actionHelper.setDoubleActionColor("AdvancedChat.Gui.Gradient.Dark_Gray", "555555", "555555");
			}
			
			if(slot == Settings.gradient_inv_slot_white) {
				actionHelper.setDoubleActionColor("AdvancedChat.Gui.Gradient.White", "FFFFFF", "FFFFFF");
			}
			
			if(slot == Settings.gradient_inv_slot_black) {
				actionHelper.setDoubleActionColor("AdvancedChat.Gui.Gradient.Black", "000000", "000000");
			}
			
			if(slot == Settings.gradient_inv_slot_last) {
				plugin.removeInvetoryView(j);
				actionHelper.setOpenInventoryAction(playerName, InventoryType.Color);
			}
			
			if(slot == Settings.gradient_inv_slot_exit) {
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
