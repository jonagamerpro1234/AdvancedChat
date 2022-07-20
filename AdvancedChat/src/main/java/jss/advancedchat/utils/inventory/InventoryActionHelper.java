package jss.advancedchat.utils.inventory;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.inventory.*;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryActionHelper {

    private final AdvancedChat plugin = AdvancedChat.get();
    private final PlayerManager playerManager;
    private final Player player;
    private final InventoryClickEvent inventoryClickEvent;

    public InventoryActionHelper(Player player, Player target, PlayerManager playerManager, InventoryClickEvent inventoryClickEvent) {
        this.player = player;
        this.playerManager = playerManager;
        this.inventoryClickEvent = inventoryClickEvent;
    }

    public void setDoubleActionColor(String permission, String left, String right) {
        if (player.isOp() || player.hasPermission(permission)) {

            if (inventoryClickEvent.getClick().isLeftClick()) {
                playerManager.setFirstGradient(left);
            } else if (inventoryClickEvent.getClick().isRightClick()) {
                playerManager.setSecondGradient(right);
            }
        } else {
            Util.sendHoverEvent(player, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
        }
    }

    public void setActionColor(String permission, String action) {
        if (player.isOp() || player.hasPermission(permission)) {
            playerManager.setColor(action);
        } else {
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
                Logger.error("&cThe inventory could not be loaded, if the error persists, report it to:");
                Logger.warning("&eIssue Tracker: https://github.com/jonagamerpro1234/AdvancedChat/issues");
                Logger.error("&9Discord: https://discord.gg/c5GhQDQCK5");
                break;
        }
    }

    public enum InventoryType {
        Player, Settings, Color, Gradient, Channel, Rainbow, SpecialCodes
    }

}
