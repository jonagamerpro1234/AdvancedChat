package jss.advancedchat.utils.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.inventory.GuiChannel;
import jss.advancedchat.inventory.GuiColor;
import jss.advancedchat.inventory.GuiGradient;
import jss.advancedchat.inventory.GuiPlayer;
import jss.advancedchat.inventory.GuiSettings;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.MySQL;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Utils;

public class InventoryActionHelper {
	
	private AdvancedChat plugin;
	private PlayerManager playerManager;
	private Player player;
	private Player target;
	private InventoryClickEvent inventoryClickEvent;
	
	public enum InventoryType{
		Player,Settings,Color,Gradient,Channel;
	}
	
	public InventoryActionHelper(AdvancedChat plugin, Player player, Player target, PlayerManager playerManager, InventoryClickEvent inventoryClickEvent) {
		this.plugin = plugin;
		this.player = player;
		this.playerManager = playerManager;
		this.target = target;
		this.inventoryClickEvent = inventoryClickEvent;
	}
	
	public void setDoubleActionColor(String permission, String left, String right) {
		if(player.isOp() || player.hasPermission(permission)) {
			
			if(inventoryClickEvent.getClick().isLeftClick()) {
				
				if(Settings.mysql_use) {
					MySQL.setGradientFirst(plugin, target, left);
				}else {
					playerManager.setFirstGradient(target, left);
				}
			}else if(inventoryClickEvent.getClick().isRightClick()) {
				if(Settings.mysql_use) {
					MySQL.setGradientSecond(plugin, target, right);
				}else {
					playerManager.setSecondGradient(target, right);
				}
				
			}
		}else {
			Utils.sendHoverEvent(player, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
		}
	}
	
	public void setActionColor(String permission, String action) {
		if(player.isOp() || player.hasPermission(permission)) {
			
			if(Settings.mysql_use) {
				MySQL.setColor(plugin, target, action);
			}else {
				playerManager.setColor(target, action);
			}
		}else {
			Utils.sendHoverEvent(player, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
		}
	}
	
	public void setOpenInventoryAction(String playerName, InventoryType inventoryType) {
		switch (inventoryType) {
		case Player:
			GuiPlayer guiPlayer = new GuiPlayer(plugin);
			guiPlayer.open(player, playerName);
			break;
		case Channel:
			GuiChannel guiChannel = new GuiChannel(plugin);
			guiChannel.open(player, playerName);
			break;
		case Color:
			GuiColor guiColor = new GuiColor(plugin);
			guiColor.open(player, playerName);
			break;
		case Gradient:
			GuiGradient guiGradient = new GuiGradient(plugin);
			guiGradient.open(player, playerName);
			break;
		case Settings:
			GuiSettings guiSettings = new GuiSettings();
			guiSettings.open(player);
			break;
		}
	}
	
}
