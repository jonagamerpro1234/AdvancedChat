package jss.advancedchat.manager;

import java.util.Iterator;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.config.PlayerDataFileOld;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;

public class PlayerManager  {

	private final static PlayerDataFileOld playerDataFile = AdvancedChat.getInstance().getplayerdataoldFile();
	private final static FileConfiguration config = playerDataFile.getConfig();
    
    public static boolean isMute(Player player) {
    	
    	Set<String> sections = config.getKeys(false);
    	Iterator<String> section = sections.iterator();
    	while(true) {
    		while(section.hasNext()) {
    			String key = (String) section.next();
    			
    			if(key.contains(player.getName())) {
    				return config.getBoolean(key + ".Mute");
    			}
    		}
    	}
    }

    public static void setMute(Player player, boolean value) {        
        if (config.contains(player.getName() + ".Mute")) {
            config.set(player.getName() + ".Mute", value);
        }
    }
    
    public static String getColor(Player player) {
        Set<String> sections = config.getKeys(false);
        Iterator<String> section = sections.iterator();
        
        while(true) {
        	while(section.hasNext()) {
        		String key = (String) section.next();
        		if(key.contains(player.getName())) {
        			return config.getString(key + ".Color");
        		}
        	}
        }
    }

    public static void setColor(Player player, String color) {
        if (config.contains(player.getName() + ".Color")) {
            config.set(player.getName() + ".Color", color);
            save();
        }
    }
    
    public static String getChannel(Player player) {
        Set<String> sections = config.getKeys(false);
        Iterator<String> section = sections.iterator();
        
		while (true) {
			while (section.hasNext()) {
				String key = (String) section.next();
				if (key.contains(player.getName())) {
					String channel = config.getString(key + ".Chat.Channel");
					return channel.toLowerCase();
				}
			}
		}
	}
    
    public static void setChannel(Player player, int range) {
        if (config.contains(player.getName() + ".Chat.Channel")) {
            config.set(player.getName() + ".Chat.Channel", range);
            save();
        }
    }
    
    public static String getRange(Player player) {
        Set<String> sections = config.getKeys(false);
        Iterator<String> section = sections.iterator();
        
		while (true) {
			while (section.hasNext()) {
				String key = (String) section.next();
				if (key.contains(player.getName())) {
					String range = config.getString(key + ".Chat.Range");
					return range;
				}
			}
		}
    }
    
    public static void setRange(Player player, int range) {
        if (config.contains(player.getName() + ".Chat.Range")) {
            config.set(player.getName() + ".Chat.Range", range);
            save();
        }
    }
    
    public static void setUUID(Player player) {
        if (config.contains(player.getName() + ".UUID")) {
            config.set(player.getName() + ".UUID", player.getUniqueId().toString());
            save();
        }
    }
    
    public static void setFirstGradient(Player player, String hex) {
        if (config.contains(player.getName() + ".Gradient")) {
            config.set(player.getName() + ".FirstGradient", hex);
            save();
        }
    }
    
    public static void setSecondGradient(Player player, String hex) {
        if (config.contains(player.getName() + ".Gradient")) {
            config.set(player.getName() + ".SecondGradient", hex);
            save();
        }
    }
    
    public static String getFirstGradient(Player player) {
    	Set<String> sections = config.getKeys(false);
    	Iterator<String> section = sections.iterator();
    	
    	while(true) {
    		while(section.hasNext()) {
    			String key = (String) section.next();
    			if(existsPlayer(player)) {
    				return config.getString(key + ".FirstGradient");
    			}
    		}	
    	}
    }
    public static String getUUID(Player player) {  	
    	Set<String> sections = config.getKeys(false);
    	Iterator<String> section = sections.iterator();
    	
    	while(true) {
    		while(section.hasNext()) {
    			String key = (String) section.next();
    			if(existsPlayer(player)) {
    				return config.getString(key + ".UUID");
    			}
    		}	
    	}
    }
    
    
    public static String getSecondGradient(Player player) {
    	Set<String> sections = config.getKeys(false);
    	Iterator<String> section = sections.iterator();
    	
    	while(true) {
    		while(section.hasNext()) {
    			String key = (String) section.next();
    			if(existsPlayer(player)) {
    				return config.getString(key + ".SecondGradient");
    			}
    		}	
    	}
    }
        
    public static void create(Player player) {
    	if(!existsPlayer(player)) {
    		config.set(player.getName() + ".UUID", player.getUniqueId().toString());
            config.set(player.getName() + ".Color", Settings.default_color);
            config.set(player.getName() + ".FirstGradient", "FFFFFF");
            config.set(player.getName() + ".SecondGradient", "FFFFFF");
            config.set(player.getName() + ".Mute", false);
            config.set(player.getName() + ".Chat.Channel", "ALL");
            config.set(player.getName() + ".Chat.Range", 10);
            save();
            if(AdvancedChat.getInstance().isDebug()) {
            	Logger.debug("&9folder &7-> &e[Data] &7-> &efile &b[player.data] &7-> &aAdded " + player.getName());
            }
    	}else {
    		if(AdvancedChat.getInstance().isDebug()) {
    			Logger.debug("&9folder &7-> &e[Data] &7-> &efile &b[player.data] &7-> &eIt already existsPlayer " + player.getName());
    		}
    	}
    }
    
    public static boolean existsPlayer(Player player) {
		Set<String> sections = config.getKeys(false);
		Iterator<String> section = sections.iterator();
		while (true) {
			while (section.hasNext()) {
				String key = (String) section.next();

				if (key.contains(player.getName())) {
					return true;
				} else {
					return false;
				}
			}
		}
    }

    public static boolean removePlayerlist(Player player) {
        for (String key : config.getConfigurationSection("Players").getKeys(false)) {
            if (key.contains(player.getName())) {
                config.set(key, null);
                save();
                return true;
            }
        }
        return false;
    }
    
    private static void save() {
    	playerDataFile.saveConfig();
    }

}
