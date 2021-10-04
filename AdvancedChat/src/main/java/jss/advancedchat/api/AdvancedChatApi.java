package jss.advancedchat.api;

import org.bukkit.configuration.file.FileConfiguration;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Utils;

public class AdvancedChatApi {
	 
	private static AdvancedChatApi instance;
	
	public AdvancedChatApi() {
		instance = this;
	}
	
	public static AdvancedChatApi getInstance() {
		return instance;
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
