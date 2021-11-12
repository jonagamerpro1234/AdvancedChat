package jss.advancedchat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.config.ConfigFile;
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
        ConfigFile configFile = plugin.getConfigFile();
        FileConfiguration config = configFile.getConfig();
        if (!(sender instanceof Player)) {
            eventUtils.getClearChatAction("server");
            eventUtils.getServerMessage(config);
            return false;
        }
        Player j = (Player) sender;
        if (j.isOp() || j.hasPermission("AdvancedChat.ClearChat")) {
        	if(Settings.boolean_clearchat_bypass) {
            	if((j.isOp()) || (j.hasPermission("AdvancedChat.ClearChat.Bypass"))) {
            		if(Settings.boolean_use_default_prefix) {
            			Utils.sendColorMessage(j, Utils.getPrefixPlayer() + Utils.getVar(j, Settings.message_ClearChat_Staff));
            		}else {
            			Utils.sendColorMessage(j, Settings.message_prefix_custom + " " + Utils.getVar(j, Settings.message_ClearChat_Staff));
            		}
            		return true;
            	}else {
            		eventUtils.getClearChatAction("player");
            	}
        	}else {
        		eventUtils.getClearChatAction("player");
        	}
            eventUtils.getPlayerMessage(j, config);
        } else {
        	Utils.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
        }
        return true;
    }

}
