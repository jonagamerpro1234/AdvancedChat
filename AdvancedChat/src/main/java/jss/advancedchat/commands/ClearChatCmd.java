package jss.advancedchat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Utils;

public class ClearChatCmd implements CommandExecutor {

    private AdvancedChat plugin;
    private EventUtils eventUtils = new EventUtils(plugin);

    public ClearChatCmd(AdvancedChat plugin) {
        this.plugin = plugin;
        plugin.getCommand("ClearChat").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            eventUtils.getClearChatAction();
            eventUtils.getServerMessage();
            return false;
        }
        
        Player j = (Player) sender;
        
        if (j.isOp() || j.hasPermission("AdvancedChat.ClearChat")) {
        	if(Settings.boolean_clearchat_bypass) {
            	if((j.isOp()) || (j.hasPermission("AdvancedChat.ClearChat.Bypass"))) {
            		Utils.sendColorMessage(j, Utils.getPrefix(false) + Utils.getVar(j, Settings.message_ClearChat_Staff));
            		return true;
            	}else {
            		eventUtils.getClearChatAction();
            	}
        	}else {
        		eventUtils.getClearChatAction();
        	}
            eventUtils.getPlayerMessage(j);
        } else {
        	Utils.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
        }
        return true;
    }

}
