package jss.advancedchat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.EventsUtils;
import jss.advancedchat.utils.PlayerManager;
import jss.advancedchat.utils.Utils;

public class MuteCmd extends EventsUtils implements CommandExecutor{

	private AdvancedChat plugin;
	
	public MuteCmd(AdvancedChat plugin) {
		this.plugin = plugin;
		plugin.getCommand("Mute").setExecutor(this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		PlayerManager playerManager = new PlayerManager();
		Player j = (Player) sender;

		if(playerManager.isMute()){
			playerManager.setMute(false);
			playerManager.setConfigMute(plugin,j);
			Utils.sendColorMessage(j, "&cdesmuteado");
		}else {
			playerManager.setMute(true);
			playerManager.setConfigMute(plugin,j);
			Utils.sendColorMessage(j, "&emuteado");
		}
		
		return true;
	}
	
}
