package jss.advancedchat.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


import com.cryptomorin.xseries.XEnchantment;
import com.cryptomorin.xseries.XMaterial;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.gui.GuiColor;
import jss.advancedchat.gui.GuiPlayer;
import jss.advancedchat.test.PlayerManager;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.InventoryPlayer;
import jss.advancedchat.utils.Utils;

public class InventoryListener implements Listener {

	private AdvancedChat plugin;
	private EventUtils eventUtils = new EventUtils(plugin);

	public InventoryListener(AdvancedChat plugin) {
		this.plugin = plugin;
		eventUtils.getEventManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onInventoryPlayer(InventoryClickEvent e) {
		PlayerManager manager = new PlayerManager(plugin);
		Player p = (Player) e.getWhoClicked();
		InventoryPlayer inventoryPlayer = plugin.getInventoryPlayer(p);
		if(inventoryPlayer != null) {
			if(inventoryPlayer.getInventory().equals("player")) {
				if(e.getCurrentItem() == null) { 
					return;
				}
				if(e.getClickedInventory() != null && e.getClickedInventory().equals(p.getOpenInventory().getTopInventory())) {
					if ((e.getCurrentItem().getType() == Material.AIR) || (e.getSlotType() == null)) {
						return;
					}
					e.setCancelled(true);
					int slot = e.getSlot();
					
					String namecolor = e.getClickedInventory().getItem(10).getItemMeta().getDisplayName();
					String name = Utils.colorless(namecolor);
					
					Player pp = Bukkit.getPlayer(name);
					
					if(slot == 22 ) {
						e.setCancelled(true);
						
						if(manager.isMute(pp) == false) {
							manager.setMute(pp, true);
							ItemStack item = XMaterial.GREEN_DYE.parseItem();
							ItemMeta meta = item.getItemMeta();
							item.setAmount(1);
							meta.setDisplayName(Utils.color("&a&lTrue"));
							item.setItemMeta(meta);
							e.getInventory().setItem(22, item);
							
							item = XMaterial.PAPER.parseItem();
							meta = item.getItemMeta();
							item.setAmount(1);
							meta.addEnchant(XEnchantment.DURABILITY.parseEnchantment(), 1, false);
							meta.setDisplayName(Utils.color("&6&lMute Player"));
							meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
							item.setItemMeta(meta);
							e.getInventory().setItem(13, item);
							
						}else if (manager.isMute(pp) == true) {
							manager.setMute(pp.getPlayer(), false);
							ItemStack item = XMaterial.RED_DYE.parseItem();
							ItemMeta meta = item.getItemMeta();
							item.setAmount(1);
							meta.setDisplayName(Utils.color("&c&lFalse"));
							item.setItemMeta(meta);
							e.getInventory().setItem(22, item);
							
							item = XMaterial.PAPER.parseItem();
							meta = item.getItemMeta();
							item.setAmount(1);
							meta.setDisplayName(Utils.color("&6&lMute Player"));
							item.setItemMeta(meta);
							e.getInventory().setItem(13, item);
						}
						
					}
					
					if(slot == 16 ) {
						e.setCancelled(true);
						GuiColor guiColor = new GuiColor(plugin);
						guiColor.openGuiColor(p, name);
					}
					
				}
			}
		}
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
					String namecolor = e.getClickedInventory().getItem(36).getItemMeta().getDisplayName();
					String name = Utils.colorless(namecolor);
					
					Player pp = Bukkit.getPlayer(name);
					
					
					if (slot == 10) {
						e.setCancelled(true);
						playerManager.setColor(pp, "Dark_Red");
					}
					if (slot == 11) {
						e.setCancelled(true);
						playerManager.setColor(pp, "Red");
					}
					if (slot == 12) {
						e.setCancelled(true);
						playerManager.setColor(pp, "Dark_Blue");
					}
					if (slot == 13) {
						e.setCancelled(true);
						playerManager.setColor(pp, "Blue");
					}
					if (slot == 14) {
						e.setCancelled(true);
						playerManager.setColor(pp, "Dark_Green");
					}
					if (slot == 15) {
						e.setCancelled(true);
						playerManager.setColor(pp, "Green");
					}
					if (slot == 16) {
						e.setCancelled(true);
						playerManager.setColor(pp, "Yellow");
					}
					if (slot == 19) {
						e.setCancelled(true);
						playerManager.setColor(pp, "Gold");
					}
					if (slot == 20) {
						e.setCancelled(true);
						playerManager.setColor(pp, "Dark_Aqua");
					}
					if (slot == 21) {
						e.setCancelled(true);
						playerManager.setColor(pp, "Aqua");
					}
					if (slot == 22) {
						e.setCancelled(true);
						playerManager.setColor(pp, "Light_Purple");
					}
					if (slot == 23) {
						e.setCancelled(true);
						playerManager.setColor(pp, "Dark_Purple");
					}
					if (slot == 24) {
						e.setCancelled(true);
						playerManager.setColor(pp, "Gray");
					}
					if (slot == 25) {
						e.setCancelled(true);
						playerManager.setColor(pp, "Dark_Gray");
					}
					if (slot == 30) {
						e.setCancelled(true);
						playerManager.setColor(pp, "White");
					}
					if(slot == 31) {
						e.setCancelled(true);
						playerManager.setColor(pp, "RainBow");
					}
					if (slot == 32) {
						e.setCancelled(true);
						playerManager.setColor(pp, "Black");
					}
					
					if(slot == 40){
						e.setCancelled(true);
						GuiPlayer guiPlayer = new GuiPlayer(plugin);
						guiPlayer.openPlayerGui(p, name);
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
