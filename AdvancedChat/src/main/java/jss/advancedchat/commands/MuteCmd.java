package jss.advancedchat.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.MySQL;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Utils;

public class MuteCmd implements CommandExecutor {

    private AdvancedChat plugin;

    public MuteCmd(AdvancedChat plugin) {
        this.plugin = plugin;
        plugin.getCommand("Mute").setExecutor(this);
    }

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String prefix = "";
        String prefixserver = "";

        if (Settings.boolean_use_default_prefix) {
            prefix = Utils.getPrefixPlayer();
            prefixserver = Utils.getPrefix();
        } else {
        	prefix = Settings.message_prefix_custom;
        	prefixserver = Settings.message_prefix_custom;
        }
        
        if (!(sender instanceof Player)) {

            if (args.length >= 1) {
                Player target = Bukkit.getPlayer(args[0]);
                PlayerManager playerManager = new PlayerManager(target);
                if(target == null) {
                	Utils.sendColorMessage(sender, Settings.message_No_Online_Player);
                	return true;
                }else {
                    if(target.isOp() || target.hasPermission("AdvancedChat.Mute.Bypass")) {
                    	Utils.sendColorMessage(sender, Settings.message_mute_bypass);
                        return true;
                    }
                    
                    if(Settings.mysql_use) {
                    	MySQL.setMute(plugin, target.getUniqueId().toString(), true);
                    } else {
                    playerManager.setMute(target, true);
                    }
                }
                
                Utils.sendColorMessage(sender, prefixserver + " " + Utils.getVar(target, Settings.message_Mute_Player));
                return true;
            }
            Utils.sendColorMessage(sender, Utils.getPrefix() + Settings.message_Help_Mute);
            return false;
        }
        Player j = (Player) sender;
        
        if (j.isOp() || j.hasPermission("AdvancedChat.Mute")) {
            if (args.length >= 1) {
                Player target = Bukkit.getPlayer(args[0]);
                PlayerManager playerManager = new PlayerManager(target);
                if(target == null) {
                	Utils.sendColorMessage(j, Settings.message_No_Online_Player);
                	return true;
                }else {
                    if(target.isOp() || target.hasPermission("AdvancedChat.Mute.Bypass")) {
                    	Utils.sendColorMessage(j, Settings.message_mute_bypass);
                        return true;
                    }
                    
                    if(Settings.mysql_use) {
                    	MySQL.setMute(plugin, target.getUniqueId().toString(), true);
                    } else {
                    	playerManager.setMute(target, true);
                    }
                }
                
                Utils.sendColorMessage(j, prefix + Utils.getVar(target, Settings.message_Mute_Player));
                return true;
            }
        } else {
        	Utils.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
        	return true;
        }
        Utils.sendColorMessage(j, Utils.getPrefix() + Utils.getVar(j, Settings.message_Help_Mute));
        return true;
    }
}
