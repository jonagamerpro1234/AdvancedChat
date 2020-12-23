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
		if(config.contains("Players."+player.getName()+".Mute")) {
			config.set("Players."+player.getName()+".Mute", mute);
		}else {
			Utils.sendColorMessage(eventUtils.getConsoleSender(), "No se pudo encontrar el jugador");
		}
	}

	public String getColor(Player player) {
		PlayerDataFile playerDataFile = plugin.getPlayerDataFile();
		FileConfiguration config = playerDataFile.getConfig();
		
		for(String key : config.getConfigurationSection("Players").getKeys(false)) {
			if(key.contains(player.getName())) {
				String color = config.getString("Players."+key+"Color");
				return color;
			}
		}
		return null;
	}

	public void setColor(Player player, String color) {
		PlayerDataFile playerDataFile = plugin.getPlayerDataFile();
		FileConfiguration config = playerDataFile.getConfig();
		if(config.contains("Players."+player.getName()+".Color")) {
			config.set("Players."+player.getName()+".Color", color);
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
	
}
