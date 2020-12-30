package jss.advancedchat.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.test.PlayerManager;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.InventoryPlayer;

public class InventoryListener implements Listener {

	private AdvancedChat plugin;
	private EventUtils eventUtils = new EventUtils(plugin);

	public InventoryListener(AdvancedChat plugin) {
		this.plugin = plugin;
		eventUtils.getEventManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onInventoryColor(InventoryClickEvent e) {
		PlayerManager playerManager = new PlayerManager(plugin);
		Player p = (Player) e.getWhoClicked();
		InventoryPlayer inventoryPlayer = plugin.getInventoryPlayer(p);
		if (inventoryPlayer != null) {
			if (inventoryPlayer.getInventory().equals("color")) {
				if (e.getCurrentItem() == null) {
					return;
				}
				if (e.getClickedInventory() != null && e.getClickedInventory().equals(p.getOpenInventory().getTopInventory())) {
					if ((e.getCurrentItem().getType() == Material.AIR) || (e.getSlotType() == null)) {
						return;
					}
					e.setCancelled(true);
					int slot = e.getSlot();
					if (slot == 10) {
						e.setCancelled(true);
						playerManager.setColor(p, "Dark_Red");
					}
					if (slot == 11) {
						e.setCancelled(true);
						playerManager.setColor(p, "Red");
					}
					if (slot == 12) {
						e.setCancelled(true);
						playerManager.setColor(p, "Dark_Blue");
					}
					if (slot == 13) {
						e.setCancelled(true);
						playerManager.setColor(p, "Blue");
					}
					if (slot == 14) {
						e.setCancelled(true);
						playerManager.setColor(p, "Dark_Green");
					}
					if (slot == 15) {
						e.setCancelled(true);
						playerManager.setColor(p, "Green");
					}
					if (slot == 16) {
						e.setCancelled(true);
						playerManager.setColor(p, "Yellow");
					}
					if (slot == 19) {
						e.setCancelled(true);
						playerManager.setColor(p, "Gold");
					}
					if (slot == 20) {
						e.setCancelled(true);
						playerManager.setColor(p, "Dark_Aqua");
					}
					if (slot == 21) {
						e.setCancelled(true);
						playerManager.setColor(p, "Aqua");
					}
					if (slot == 22) {
						e.setCancelled(true);
						playerManager.setColor(p, "Light_Purple");
					}
					if (slot == 23) {
						e.setCancelled(true);
						playerManager.setColor(p, "Dark_Purple");
					}
					if (slot == 24) {
						e.setCancelled(true);
						playerManager.setColor(p, "Gray");
					}
					if (slot == 25) {
						e.setCancelled(true);
						playerManager.setColor(p, "Dark_Gray");
					}
					if (slot == 30) {
						e.setCancelled(true);
						playerManager.setColor(p, "White");
					}
					if(slot == 31) {
						e.setCancelled(true);
						playerManager.setColor(p, "RainBow");
					}
					if (slot == 32) {
						e.setCancelled(true);
						playerManager.setColor(p, "Black");
					}
				}
			}
		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		plugin.removeInvetoryPlayer(p);
	}
	
	@EventHandler
	public void onInventoryCloseColor(InventoryCloseEvent e) {
		Player p = (Player) e.getPlayer();
		plugin.removeInvetoryPlayer(p);
	}


}
