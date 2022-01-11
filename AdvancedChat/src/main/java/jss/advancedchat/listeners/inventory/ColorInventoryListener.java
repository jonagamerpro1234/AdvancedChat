package jss.advancedchat.listeners.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.inventory.InventoryActionHelper;
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
			
			
			if(slot == 1) {
				Utils.sendColorMessage(j, "Tested event slot 1");
			}
			
		}
	}
	
}
