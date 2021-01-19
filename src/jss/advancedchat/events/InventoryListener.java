package jss.advancedchat.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
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
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class InventoryListener implements Listener {

	private AdvancedChat plugin;
	private EventUtils eventUtils = new EventUtils(plugin);

	public InventoryListener(AdvancedChat plugin) {
		this.plugin = plugin;
		eventUtils.getEventManager().registerEvents(this, plugin);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryPlayer(InventoryClickEvent e) {
		PlayerManager manager = new PlayerManager(plugin);
		FileConfiguration config = plugin.getConfigFile().getConfig();
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
						if(p.isOp() || p.hasPermission("AdvancedChat.Gui.Player.Mute")) {
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
						}else {
							TextComponent msg = new TextComponent();
							msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
							p.spigot().sendMessage(msg);
							return;
						}
					}
					
					if(slot == 16 ) {
						if(p.isOp() || p.hasPermission("AdvancedChat.Gui.Player.Mute")) {
							e.setCancelled(true);
							GuiColor guiColor = new GuiColor(plugin);
							guiColor.openGuiColor(p, name);
						}else {
							TextComponent msg = new TextComponent();
							msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
							p.spigot().sendMessage(msg);
							return;
						}
					}
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryColor(InventoryClickEvent e) {
		FileConfiguration config = plugin.getConfigFile().getConfig();
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
						if(p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Dark_Red")) {
							e.setCancelled(true);
							playerManager.setColor(pp, "Dark_Red");
						}else {
							TextComponent msg = new TextComponent();
							msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
							p.spigot().sendMessage(msg);
							return;
						}
					}
					if (slot == 11) {
						if(p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Red")) {
							e.setCancelled(true);
							playerManager.setColor(pp, "Red");
						}else {
							TextComponent msg = new TextComponent();
							msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
							p.spigot().sendMessage(msg);
							return;
						}
					}
					if (slot == 12) {
						if(p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Dark_Blue")) {
							e.setCancelled(true);
							playerManager.setColor(pp, "Dark_Blue");
						}else {
							TextComponent msg = new TextComponent();
							msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
							p.spigot().sendMessage(msg);
							return;
						}
					}
					if (slot == 13) {
						if(p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Blue")) {
							e.setCancelled(true);
							playerManager.setColor(pp, "Blue");
						}else {
							TextComponent msg = new TextComponent();
							msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
							p.spigot().sendMessage(msg);
							return;
						}
					}
					if (slot == 14) {
						if(p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Dark_Green")) {
							e.setCancelled(true);
							playerManager.setColor(pp, "Dark_Green");
						}else {
							TextComponent msg = new TextComponent();
							msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
							p.spigot().sendMessage(msg);
							return;
						}
					}
					if (slot == 15) {
						if(p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Green")) {
							e.setCancelled(true);
							playerManager.setColor(pp, "Green");
						}else {
							TextComponent msg = new TextComponent();
							msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
							p.spigot().sendMessage(msg);
							return;
						}
					}
					if (slot == 16) {
						if(p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Yellow")) {
							e.setCancelled(true);
							playerManager.setColor(pp, "Yellow");
						}else {
							TextComponent msg = new TextComponent();
							msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
							p.spigot().sendMessage(msg);
							return;
						}
					}
					if (slot == 19) {
						if(p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Gold")) {
							e.setCancelled(true);
							playerManager.setColor(pp, "Gold");
						}else {
							TextComponent msg = new TextComponent();
							msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
							p.spigot().sendMessage(msg);
							return;
						}
					}
					if (slot == 20) {
						if(p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Dark_Aqua")) {
							e.setCancelled(true);
							playerManager.setColor(pp, "Dark_Aqua");
						}else {
							TextComponent msg = new TextComponent();
							msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
							p.spigot().sendMessage(msg);
							return;
						}
					}
					if (slot == 21) {
						if(p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Aqua")) {
							e.setCancelled(true);
							playerManager.setColor(pp, "Aqua");
						}else {
							TextComponent msg = new TextComponent();
							msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
							p.spigot().sendMessage(msg);
							return;
						}
					}
					if (slot == 22) {
						if(p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Light_Purple")) {
							e.setCancelled(true);
							playerManager.setColor(pp, "Light_Purple");
						}else {
							TextComponent msg = new TextComponent();
							msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
							p.spigot().sendMessage(msg);
							return;
						}
					}
					if (slot == 23) {
						if(p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Dark_Purple")) {
							e.setCancelled(true);
							playerManager.setColor(pp, "Dark_Purple");
						}else {
							TextComponent msg = new TextComponent();
							msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
							p.spigot().sendMessage(msg);
							return;
						}
					}
					if (slot == 24) {
						if(p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Gray")) {
							e.setCancelled(true);
							playerManager.setColor(pp, "Gray");
						}else {
							TextComponent msg = new TextComponent();
							msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
							p.spigot().sendMessage(msg);
							return;
						}
					}
					if (slot == 25) {
						if(p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Dark_Gray")) {
							e.setCancelled(true);
							playerManager.setColor(pp, "Dark_Gray");
						}else {
							TextComponent msg = new TextComponent();
							msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
							p.spigot().sendMessage(msg);
							return;
						}
					}
					if (slot == 30) {
						if(p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.White")) {
							e.setCancelled(true);
							playerManager.setColor(pp, "White");
						}else {
							TextComponent msg = new TextComponent();
							msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
							p.spigot().sendMessage(msg);
							return;
						}
					}
					if(slot == 31) {
						if(p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.RainBow")) {
							e.setCancelled(true);
							playerManager.setColor(pp, "RainBow");
						}else {
							TextComponent msg = new TextComponent();
							msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
							p.spigot().sendMessage(msg);
							return;
						}
					}
					if (slot == 32) {
						if(p.isOp() || p.hasPermission("AdvancedChat.Gui.Color.Black")) {
							e.setCancelled(true);
							playerManager.setColor(pp, "Black");
						}else {
							TextComponent msg = new TextComponent();
							msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
							p.spigot().sendMessage(msg);
							return;
						}
					}
					if(slot == 40){
						if(p.isOp() || p.hasPermission("AdvancedChat.Gui.Player")) {
							e.setCancelled(true);
							GuiPlayer guiPlayer = new GuiPlayer(plugin);
							guiPlayer.openPlayerGui(p, name);
						}else {
							TextComponent msg = new TextComponent();
							msg.setText(Utils.color(config.getString("AdvancedChat.No-Permission")));
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT , new ComponentBuilder(config.getString("AdvancedChat.No-Permission-Label")).color(ChatColor.YELLOW).create()));
							p.spigot().sendMessage(msg);
							return;
						}

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
