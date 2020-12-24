package jss.advancedchat.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.test.PlayerManager;
import jss.advancedchat.utils.Utils;

public class UnMuteCmd implements CommandExecutor {

	private AdvancedChat plugin;

	public UnMuteCmd(AdvancedChat plugin) {
		this.plugin = plugin;
		plugin.getCommand("UnMute").setExecutor(this);
	}

	//mensages faltantes
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		FileConfiguration config = plugin.getConfigfile().getConfig();
		PlayerManager manager = new PlayerManager(plugin);
		
		String text = config.getString("AdvancedChat.Help-UnMute");
		if (!(sender instanceof Player)) {
			
			if(args.length >= 1) {
				Player p = Bukkit.getPlayer(args[0]);
				manager.setMute(p, false);
				return true;
			}
			Utils.sendColorMessage(sender, Utils.getPrefix() + " " + text);
			return false;
		}
		Player j = (Player) sender;
		if(args.length >= 1) {
			text = Utils.getVar(text, j);
			Player p = Bukkit.getPlayer(args[0]);
			manager.setMute(p, false);
			return true;
		}
		Utils.sendColorMessage(j, Utils.getPrefix() + " " + text);
		return true;
	}

}
