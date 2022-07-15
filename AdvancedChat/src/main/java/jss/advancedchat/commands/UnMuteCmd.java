package jss.advancedchat.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Util;

public class UnMuteCmd implements CommandExecutor {
    
    public UnMuteCmd(AdvancedChat plugin) {
        plugin.getCommand("UnMute").setExecutor(this);
    }

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length >= 1) {
                Player target = Bukkit.getPlayer(args[0]);
                PlayerManager playerManager = new PlayerManager(target);
                if(Settings.mysql) {
                //	mySQL.setMute(target.getUniqueId().toString(), false);
                } else {
                	playerManager.setMute(false);
                }
                Util.sendColorMessage(sender, Util.getPrefix(false) + Util.getVar(target, Settings.message_UnMute_Player));
                return true;
            }
            Util.sendColorMessage(sender, Util.getPrefix(false) + Settings.message_Help_UnMute);
            return false;
        }
        Player j = (Player) sender;
        if (j.isOp() || j.hasPermission("AdvancedChat.UnMute")) {
            if (args.length >= 1) {
                Player target = Bukkit.getPlayer(args[0]);
                PlayerManager playerManager = new PlayerManager(target);

                if(Settings.mysql) {
                	//mySQL.setMute(target.getUniqueId().toString(), false);
                } else {
                	playerManager.setMute(false);
                }
                Util.sendColorMessage(j, Util.getPrefix(false) + Util.getVar(j, Settings.message_UnMute_Player));
                return true;
            }
        } else {
            Util.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
        	return true;
        }

        Util.sendColorMessage(j, Util.getPrefix(false) + Util.getVar(j, Settings.message_Help_UnMute));
        return true;
    }

}
