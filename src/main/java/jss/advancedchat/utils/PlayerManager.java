package jss.advancedchat.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.PlayerDataFile;

public class PlayerManager extends EventsUtils{
	
	private CommandSender c = getConsoleSender();
	
	private boolean mute = false;
	private Player player;
	public List<String> playerlist = new ArrayList<String>(); 
	
	public Player getPlayer() {
		return player;
	}

	public boolean isMute() {
		return mute;
	}

	public void setMute(boolean mute) {
		this.mute = mute;
	}
	
	public void setConfigMute(AdvancedChat plugin ,Player player) {
		PlayerDataFile playerData = plugin.getPlayerDataFile();
		FileConfiguration config = playerData.getConfig();
		if(config.contains("Players")) {
			config.set("Players."+player.getName()+".IsMute", isMute());
			playerData.saveConfig();
		}else {
			config.createSection("Players");
			Utils.sendColorMessage(c, "No existe el path Players, preparando para crear...");
		}

	}
	
	public boolean IsPlayerList(String name) {
		for(int i = 0; i < playerlist.size(); i++) {
			if(playerlist.contains(name)) {
				return true;
			}
		}
		return false;
	}
	
	public void setPlayerList(String name) {
		this.playerlist.add(name);
	}
	public boolean removePlayerList(String name) {
		for(int i = 0; i < playerlist.size(); i++) {
			if(playerlist.contains(name)) {
				this.playerlist.remove(name);
				return true;
			}
		}
		return false;
	}
	
}
