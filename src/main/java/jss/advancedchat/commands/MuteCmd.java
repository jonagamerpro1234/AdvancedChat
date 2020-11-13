package jss.advancedchat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
public class MuteCmd implements CommandExecutor{

	private AdvancedChat plugin;
	
	public MuteCmd(AdvancedChat plugin) {
		this.plugin = plugin;
		plugin.getCommand("Mute").setExecutor(this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		Player j = (Player) sender;

		
		return true;
	}
	
}
