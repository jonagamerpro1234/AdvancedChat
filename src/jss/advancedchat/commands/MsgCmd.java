package jss.advancedchat.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.EventUtils;

public class MsgCmd implements CommandExecutor, TabCompleter{
	
	private AdvancedChat plugin;
	private EventUtils eventUtils = plugin.getEventUtils();
	
	public MsgCmd(AdvancedChat plugin) {
		this.plugin = plugin;
		plugin.getCommand("Msg").setExecutor(this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return false;
		}
		
		if(args.length >= 1) {
			
			
			return true;
		}
		
		return true;
	}

	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return null;
		}
		
		List<String> list ;
		
		return null;
	}
	
}
