package jss.advancedchat.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.MySQL;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Utils;

public class UnMuteCmd implements CommandExecutor {

    private AdvancedChat plugin;

    public UnMuteCmd(AdvancedChat plugin) {
        this.plugin = plugin;
        plugin.getCommand("UnMute").setExecutor(this);
    }

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        FileConfiguration config = plugin.getConfigFile().getConfig();
        PlayerManager manager = new PlayerManager(plugin);
        
        String text = Settings.message_Help_UnMute;
        String prefix = "";
        String prefixserver = "";

        if (config.getString("Settings.Use-Default-Prefix").equals("false")) {
            prefixserver = config.getString("Settings.Prefix");
        } else if (config.getString("Settings.Use-Default-Prefix").equals("true")) {
            prefixserver = Utils.getPrefixPlayer();
        }

        if (config.getString("Settings.Use-Default-Prefix").equals("false")) {
            prefix = config.getString("Settings.Prefix");
        } else if (config.getString("Settings.Use-Default-Prefix").equals("true")) {
            prefix = Utils.getPrefixPlayer();
        }

        if (!(sender instanceof Player)) {
            if (args.length >= 1) {
                Player p = Bukkit.getPlayer(args[0]);
                if(Settings.mysql_use) {
                	MySQL.setMute(plugin, p.getUniqueId().toString(), false);
                } else {
                	manager.setMute(p, false);
                }
                Utils.sendColorMessage(sender, prefixserver + Utils.getVar(p, config.getString("AdvancedChat.UnMute-Player")));
                return true;
            }
            Utils.sendColorMessage(sender, Utils.getPrefix() + " " + text);
            return false;
        }
        Player j = (Player) sender;
        if (j.isOp() || j.hasPermission("AdvancedChat.UnMute")) {
            if (args.length >= 1) {
                text = Utils.getVar(j, text);
                Player p = Bukkit.getPlayer(args[0]);
                
                if(p == null) {
                	Utils.sendColorMessage(j, Settings.message_No_Online_Player);
                	return true;
                }
                
                if(Settings.mysql_use) {
                	MySQL.setMute(plugin, p.getUniqueId().toString(), false);
                } else {
                	manager.setMute(p, false);
                }
                Utils.sendColorMessage(j, prefix + Utils.getVar(j, config.getString("AdvancedChat.UnMute-Player")));
                return true;
            }
        } else {
            Utils.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
        	return true;
        }

        Utils.sendColorMessage(j, Utils.getPrefix() + " " + text);
        return true;
    }

}
