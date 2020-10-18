package jss.advancedchat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.PlayerData;
import jss.advancedchat.utils.EventsUtils;
import jss.advancedchat.utils.Utils;

public class UnMuteCmd extends EventsUtils implements CommandExecutor{

	private AdvancedChat plugin;
	private  CommandSender c = getConsoleSender();
	
	public UnMuteCmd(AdvancedChat plugin) {
		this.plugin = plugin;
		plugin.getCommand("UnMute").setExecutor(this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		FileConfiguration dconfig = plugin.getConfig();
		if(!(sender instanceof Player)) {
			Utils.sendColorMessage(c,  Utils.getPrefixConsole() +" "+ dconfig.getString("AdvancedChat.Error-Console"));
			return false;
		}
		Player j = (Player) sender;
		PlayerData playerdata = plugin.getPlayerData();
		FileConfiguration config = playerdata.getConfig();
		String path = "Players-Data";
		
		String name = args[0];	
		
		if(name == null) {
			return true;
		}

		config.set(path+"."+name+".Mute", false);
		plugin.mute.remove(name);
		playerdata.saveConfig();
		
		Utils.sendColorMessage(j, "has desmuteado a ["+name+"]");
		return true;
	}
	
}
