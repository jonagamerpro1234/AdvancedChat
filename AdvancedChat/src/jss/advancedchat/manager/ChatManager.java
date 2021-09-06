package jss.advancedchat.manager;

import java.util.Iterator;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;

public class ChatManager {
	
	private AdvancedChat plugin;

    
    public ChatManager(AdvancedChat plugin) {
        this.plugin = plugin;
    }
        
	public String getMessage(Player player) {
		FileConfiguration config = plugin.getChatDataFile().getConfig();
		
		Set<String> sections = config.getKeys(false);
		Iterator<String> section = sections.iterator();
		
		while(true) {
			while(section.hasNext()) {
				String key = (String) section.next();
				
				if(this.exists(player)) {
					return config.getString(key + ".Log." + key + ".Chat." + key);
				}
			}
		}
    }

	public String getCommand(Player player) {
		FileConfiguration config = plugin.getChatDataFile().getConfig();
		
		Set<String> sections = config.getKeys(false);
		Iterator<String> section = sections.iterator();
		
		while(true) {
			while(section.hasNext()) {
				String key = (String) section.next();
				
				if(this.exists(player)) {
					return config.getString(key + ".Log." + key + ".Command." + key);
				}
			}
		}
	}
	
	public boolean exists(Player player) {
		FileConfiguration config = plugin.getChatDataFile().getConfig();
		
		for(String key : config.getKeys(false)) {
			if(key.contains(player.getName())) {
				return true;
			}
		}
		return false;
	}
}
