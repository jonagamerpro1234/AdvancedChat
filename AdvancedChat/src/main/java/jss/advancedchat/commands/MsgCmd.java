package jss.advancedchat.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Utils;

public class MsgCmd implements CommandExecutor, TabCompleter{
	
	private AdvancedChat plugin = AdvancedChat.get();
	
	public MsgCmd() {
		plugin.getCommand("Msg").setExecutor(this);
		plugin.getCommand("Msg").setTabCompleter(this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			if(args.length >= 1) {
				
				Player p = Bukkit.getPlayer(args[0]);
				
				if(p == null) {
					Utils.sendColorMessage(sender, Settings.message_No_Online_Player);
					return true;
				}
				
				if(args.length >= 2) {
					String message = args[1];
					if(message == null) {
						Utils.sendColorMessage(sender, Settings.message_msg_empty);
						return true;
					}
					
					Utils.sendColorMessage(sender,  "&d[&e"+p.getName()+"&d] &b> &7"+ message);
					Utils.sendColorMessage(p,  "&a[&bServer&a] &e> &7"+ message);
					return true;
				}
				Utils.sendColorMessage(sender, Utils.getPrefix(false) + Settings.message_msg_use);
				return true;
			}
			Utils.sendColorMessage(sender, Utils.getPrefix(false) + Settings.message_msg_use);
			return false;
		}
		
		Player j = (Player)sender;
		
		if(args.length >= 1) {
			
			Player p = Bukkit.getPlayer(args[0]);
			
			if(p == null) {
				Utils.sendColorMessage(j, Settings.message_No_Online_Player);
				return true;
			}
			
			if(args.length >= 2) {
				
				String message = args[1];
				
				if(message == null) {
					Utils.sendColorMessage(j, Settings.message_msg_empty);
					return true;
				}
				
				Utils.sendColorMessage(j,  "&d[&e"+p.getName()+"&d] &b> &7"+ message);
				Utils.sendColorMessage(p,  "&8* &a[&b"+j.getName()+"&a] &e> &7"+ message);
				return true;
			}
			Utils.sendColorMessage(j, Utils.getPrefix(false) + Settings.message_msg_use);
			return true;
		}
		Utils.sendColorMessage(j, Utils.getPrefix(false) + Settings.message_msg_use);
		return true;
	}

	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> list = new ArrayList<>();
		String lastArgs = args.length != 0 ? args[args.length - 1] : "";
		if (!(sender instanceof Player)) {
			switch (args.length) {
			case 0:
			case 1:
				for (Player p : Bukkit.getOnlinePlayers()) {
					list.add(p.getName());
				}
				break;
			}
			return Utils.setLimitTab(list, lastArgs);
		}

		switch (args.length) {
		case 0:
		case 1:
			Bukkit.getOnlinePlayers().forEach( (p) -> list.add(p.getName()));
			break;
		}
		return Utils.setLimitTab(list, lastArgs);
	}

}
