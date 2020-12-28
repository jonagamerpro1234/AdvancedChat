package jss.advancedchat.test;

import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.PlayerDataFile;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Utils;

public class PlayerManager {
	
	private AdvancedChat plugin;
	private EventUtils eventUtils = new EventUtils(plugin);
	public UUID uuid;
	public String name;
	public float range;
	
	public PlayerManager(AdvancedChat plugin) {
		super();
		this.plugin = plugin;
		this.uuid = null;
		this.name = null;
		this.range = 0;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMute(Player player) {
		PlayerDataFile playerDataFile = plugin.getPlayerDataFile();
		FileConfiguration config = playerDataFile.getConfig();
		for(String key: config.getConfigurationSection("Players").getKeys(false)) {
			if(key.contains(player.getName())) {
				return config.getBoolean("Players."+key+".Mute");
			}
		}
		return false;
	}

	public void setMute(Player player, boolean mute) {
		PlayerDataFile playerDataFile = plugin.getPlayerDataFile();
		FileConfiguration config = playerDataFile.getConfig();
		if(player == null) {
			Utils.sendColorMessage(eventUtils.getConsoleSender(), "No se pudo encontrar el jugador");
			return;
		}
		if(config.contains("Players."+player.getName()+".Mute")) {
			config.set("Players."+player.getName()+".Mute", mute);
			playerDataFile.saveConfig();
		}
	}

	public String getColor(Player player) {
		PlayerDataFile playerDataFile = plugin.getPlayerDataFile();
		FileConfiguration config = playerDataFile.getConfig();
		for(String key : config.getConfigurationSection("Players").getKeys(false)) {
			if(key.contains(player.getName())) {
				String color = config.getString("Players."+key+"Color");
				return convertColor(color);
			}
		}
		return null;
	}

	public void setColor(Player player, String color) {
		PlayerDataFile playerDataFile = plugin.getPlayerDataFile();
		FileConfiguration config = playerDataFile.getConfig();
		if(config.contains("Players."+player.getName()+".Color")) {
			config.set("Players."+player.getName()+".Color", color);
			playerDataFile.saveConfig();
		}else {
			Utils.sendColorMessage(eventUtils.getConsoleSender(), "No se pudo encontrar el jugador");
		}
	}	
	
	public boolean checkPlayerList(Player player) {
		PlayerDataFile playerDataFile = plugin.getPlayerDataFile();
		FileConfiguration config = playerDataFile.getConfig();
		for(String key : config.getConfigurationSection("Players").getKeys(false)) {
			if(key.contains(player.getName())) {
				Utils.sendColorMessage(player, "&aExiste " + player.getName());
				return true;
			}
		}
		Utils.sendColorMessage(player, "&cNo Existe " + player.getName());
		return false;
	}
	
	public boolean removePlayerlist(Player player) {
		PlayerDataFile playerDataFile = plugin.getPlayerDataFile();
		FileConfiguration config = playerDataFile.getConfig();

		for(String key : config.getConfigurationSection("Players").getKeys(false)) {
			if(key.contains(player.getName())) {
				config.set("Players."+key, null);
				playerDataFile.saveConfig();
				Utils.sendColorMessage(player, "&a"+player.getName() + " Ha sido removido de la lista!");
				return true;
			}
		}
		Utils.sendColorMessage(player, "&cNo se pudo encontrar a " + player.getName());
		return false;
	}
	
	
	private String convertColor(String color) {
		String temp = color;
		if(temp.equalsIgnoreCase("Dark_Red")) {
			return "&4";
		}else if(temp.equalsIgnoreCase("Red")) {
			return "&c";
		}else if(temp.equalsIgnoreCase("Dark_Blue")) {
			return "&1";
		}else if(temp.equalsIgnoreCase("Blue")) {
			return "&9";
		}else if(temp.equalsIgnoreCase("Dark_Green")) {
			return "&2";
		}else if(temp.equalsIgnoreCase("Green")) {
			return "&a";
		}else if(temp.equalsIgnoreCase("Yellow")) {
			return "&e";
		}else if(temp.equalsIgnoreCase("Gold")) {
			return "&6";
		}else if(temp.equalsIgnoreCase("Dark_Aqua")) {
			return "&3";
		}else if(temp.equalsIgnoreCase("Aqua")) {
			return "&b";
		}else if(temp.equalsIgnoreCase("Light_Purple")) {
			return "&d";
		}else if(temp.equalsIgnoreCase("Dark_Purple")) {
			return "&5";
		}else if(temp.equalsIgnoreCase("Gray")) {
			return "&7";
		}else if(temp.equalsIgnoreCase("Dark_Gray")) {
			return "&8";
		}else if(temp.equalsIgnoreCase("White")) {
			return "&f";
		}else if(temp.equalsIgnoreCase("Black")) {
			return "&0";
		}
		
		return null;
	}
}
