package jss.advancedchat.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.database.SQLGetter;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Utils;

public class MuteCmd implements CommandExecutor {

    private AdvancedChat plugin;

    public MuteCmd(AdvancedChat plugin) {
        this.plugin = plugin;
        plugin.getCommand("Mute").setExecutor(this);
    }

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        FileConfiguration config = plugin.getConfigFile().getConfig();
        PlayerManager manager = new PlayerManager(plugin);
        SQLGetter sqlGetter = plugin.getSQLGetter();
        
        String text = config.getString("AdvancedChat.Help-Mute");
        String prefix = "";
        String prefixserver = "";
        
        if (config.getString("Settings.Use-Default-Prefix").equals("false")) {
            prefixserver = config.getString("Settings.Prefix");
        } else if (config.getString("Settings.Use-Default-Prefix").equals("true")) {
            prefixserver = Utils.getPrefix();
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
                	sqlGetter.setMute(plugin.getMySQL(), p.getName(), p.getUniqueId().toString(), true);
                }else {
                	manager.setMute(p, true);
                }
                
                Utils.sendColorMessage(sender, prefixserver + config.getString("AdvancedChat.Mute-Player").replace("<name>", p.getName()));
                return true;
            }
            Utils.sendColorMessage(sender, Utils.getPrefix() + " " + text);
            return false;
        }
        Player j = (Player) sender;
        if (j.isOp() || j.hasPermission("AdvancedChat.Mute")) {
            if (args.length >= 1) {
                text = Utils.getVar(j, text);
                Player p = Bukkit.getPlayer(args[0]);
                if(Settings.mysql_use) {
                	sqlGetter.setMute(plugin.getMySQL(), p.getName(), p.getUniqueId().toString(), true);
                }else {
                	manager.setMute(p, true);
                }
                Utils.sendColorMessage(j, prefix + config.getString("AdvancedChat.Mute-Player").replace("<name>", p.getName()));
                return true;
            }
        } else {
        	Utils.sendHoverEventText(j, config.getString("AdvancedChat.No-Permission"),config.getString("AdvancedChat.No-Permission-Hover"));
            return true;
        }

        Utils.sendColorMessage(j, Utils.getPrefix() + " " + text);
        return true;
    }

}
