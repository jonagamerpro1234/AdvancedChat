package jss.advancedchat.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;

public class UnMuteCmd implements CommandExecutor {

	private AdvancedChat plugin;

	public UnMuteCmd(AdvancedChat plugin) {
		this.plugin = plugin;
		plugin.getCommand("UnMute").setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = Bukkit.getPlayer(args[0]);
			if (p == null) {

			}
			if (plugin.mute.contains(p.getName())) {
				plugin.mute.remove(p.getName());
			} else {

			}
		} else {

		}
		return false;
	}

}
