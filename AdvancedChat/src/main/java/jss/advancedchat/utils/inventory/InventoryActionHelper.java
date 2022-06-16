package jss.advancedchat.utils.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.inventory.GuiChannel;
import jss.advancedchat.inventory.GuiColor;
import jss.advancedchat.inventory.GuiError;
import jss.advancedchat.inventory.GuiGradient;
import jss.advancedchat.inventory.GuiPlayer;
import jss.advancedchat.inventory.GuiRainbow;
import jss.advancedchat.inventory.GuiSettings;
import jss.advancedchat.inventory.GuiSpecialColorCodes;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Util;

public class InventoryActionHelper {
	
	private AdvancedChat plugin = AdvancedChat.get();
	private PlayerManager playerManager;
	private Player player;
	private Player target;
	private InventoryClickEvent inventoryClickEvent;
	
	public enum InventoryType{
		Player,Settings,Color,Gradient,Channel,Rainbow,SpecialCodes;
	}
	
	public InventoryActionHelper(Player player, Player target, PlayerManager playerManager, InventoryClickEvent inventoryClickEvent) {
		this.player = player;
		this.playerManager = playerManager;
		this.target = target;
		this.inventoryClickEvent = inventoryClickEvent;
	}
	
	public void setDoubleActionColor(String permission, String left, String right) {
		if(player.isOp() || player.hasPermission(permission)) {
			
			if(inventoryClickEvent.getClick().isLeftClick()) {
				
				if(Settings.mysql) {
				//	plugin.getMySQL().setGradientFirst(target, left);
				}else {
					playerManager.setFirstGradient(left);
				}
			}else if(inventoryClickEvent.getClick().isRightClick()) {
				if(Settings.mysql) {
				//	plugin.getMySQL().setGradientSecond(target, right);
				}else {
					playerManager.setSecondGradient(right);
				}
				
			}
		}else {
			Util.sendHoverEvent(player, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
		}
	}
	
	public void setActionColor(String permission, String action) {
		if(player.isOp() || player.hasPermission(permission)) {
			if(Settings.mysql) {
			//	plugin.getMySQL().setColor(target, action);
			}else {
				playerManager.setColor(action);
			}
		}else {
			Util.sendHoverEvent(player, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
		}
	}
	
	public void setOpenInventoryAction(String playerName, InventoryType inventoryType) {
		player.closeInventory();
		switch (inventoryType) {
		case Player:
			GuiPlayer guiPlayer = new GuiPlayer();
			guiPlayer.open(player, playerName);
			break;
		case Channel:
			GuiChannel guiChannel = new GuiChannel(plugin);
			guiChannel.open(player, playerName);
			break;
		case Color:
			GuiColor guiColor = new GuiColor();
			guiColor.open(player, playerName);
			break;
		case Gradient:
			GuiGradient guiGradient = new GuiGradient();
			guiGradient.open(player, playerName);
			break;
		case Settings:
			GuiSettings guiSettings = new GuiSettings();
			guiSettings.open(player, playerName);
			break;
		case Rainbow:
			GuiRainbow guiRainbow = new GuiRainbow();
			guiRainbow.open(player, playerName);
			break;
		case SpecialCodes:
			GuiSpecialColorCodes guiSpecialColorCodes = new GuiSpecialColorCodes();
			guiSpecialColorCodes.open(player, playerName);
			break;
		default:
			GuiError guiError = new GuiError();
			guiError.open(player);
			break;
		}
	}
	
}
