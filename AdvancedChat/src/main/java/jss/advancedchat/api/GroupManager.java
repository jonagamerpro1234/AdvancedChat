package jss.advancedchat.api;

import org.bukkit.configuration.file.FileConfiguration;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Utils;

public class GroupManager {
	
	public String getGroupFormat(String group) {
		FileConfiguration config = AdvancedChat.getInstance().getConfigFile().getConfig();
		
		if(config.isConfigurationSection("ChatFormat.Groups")) return Utils.getPrefix() + "!No exist groups path!"; 
		
		for(String key : config.getConfigurationSection("ChatFormat.Groups").getKeys(false)) {
			if(key.contains(group)) {
				return config.getString("ChatFormat.Groups." + key + ".Format");
			}
		}
		
		return null;
	}
	
	public String getGroupName(String name) {
		FileConfiguration config = AdvancedChat.getInstance().getConfigFile().getConfig();
		
		if(config.isConfigurationSection("ChatFormat.Groups")) return Utils.getPrefix() + "!No exist groups path!"; 
		
		for(String key : config.getConfigurationSection("ChatFormat.Groups").getKeys(false)) {
			if(key.contains(name)) {
				return config.getString("ChatFormat.Groups." + key);
			}
		}
		
		return null;
	}
}
