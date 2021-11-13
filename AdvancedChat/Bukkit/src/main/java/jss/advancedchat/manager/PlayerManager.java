package jss.advancedchat.manager;

import java.util.Iterator;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.api.IPlayerData;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;

public class PlayerManager implements IPlayerData {

    private AdvancedChat plugin;
    
    public PlayerManager(AdvancedChat plugin) {
        this.plugin = plugin;
    }
    
    
    public boolean isMute(Player player) {
    	FileConfiguration config = plugin.getPlayerDataFile().getConfig();
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

    public void setMute(Player player, boolean value) {
        FileConfiguration config = plugin.getPlayerDataFile().getConfig();
        if (config.contains(player.getName() + ".Mute")) {
            config.set(player.getName() + ".Mute", value);
            plugin.getPlayerDataFile().saveConfig();
        }
    }
    
    public String getColor(Player player) {
        FileConfiguration config = plugin.getPlayerDataFile().getConfig();
        
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

    public void setColor(Player player, String color) {
        FileConfiguration config = plugin.getPlayerDataFile().getConfig();
        if (config.contains(player.getName() + ".Color")) {
            config.set(player.getName() + ".Color", color);
            plugin.getPlayerDataFile().saveConfig();
        }
    }
    
    public String getChannel(Player player) {
    	FileConfiguration config = plugin.getPlayerDataFile().getConfig();
    	
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
    
    public void setChannel(Player player, int range) {
    	FileConfiguration config = plugin.getPlayerDataFile().getConfig();
        if (config.contains(player.getName() + ".Chat.Channel")) {
            config.set(player.getName() + ".Chat.Channel", range);
            plugin.getPlayerDataFile().saveConfig();
        }
    }
    
    public String getRange(Player player) {
    	FileConfiguration config = plugin.getPlayerDataFile().getConfig();
    	
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
    
    public void setRange(Player player, int range) {
    	FileConfiguration config = plugin.getPlayerDataFile().getConfig();
        if (config.contains(player.getName() + ".Chat.Range")) {
            config.set(player.getName() + ".Chat.Range", range);
            plugin.getPlayerDataFile().saveConfig();
        }
    }
    
    public void setUUID(Player player) {
    	FileConfiguration config = plugin.getPlayerDataFile().getConfig();
        if (config.contains(player.getName() + ".UUID")) {
            config.set(player.getName() + ".UUID", player.getUniqueId().toString());
            plugin.getPlayerDataFile().saveConfig();
        }
    }
    
    public void setFirstGradient(Player player, String hex) {
    	FileConfiguration config = AdvancedChat.getInstance().getPlayerDataFile().getConfig();
        if (config.contains(player.getName() + ".Gradient")) {
            config.set(player.getName() + ".Gradient.Color-1", hex);
            plugin.getPlayerDataFile().saveConfig();
        }
    }
    
    public void setSecondGradient(Player player, String hex) {
    	FileConfiguration config = AdvancedChat.getInstance().getPlayerDataFile().getConfig();
        if (config.contains(player.getName() + ".Gradient")) {
            config.set(player.getName() + ".Gradient.Color-2", hex);
            plugin.getPlayerDataFile().saveConfig();
        }
    }
    
    public String getFirstGradient(Player player) {
    	FileConfiguration config = AdvancedChat.getInstance().getPlayerDataFile().getConfig();
    	
    	Set<String> sections = config.getKeys(false);
    	Iterator<String> section = sections.iterator();
    	
    	while(true) {
    		while(section.hasNext()) {
    			String key = (String) section.next();
    			if(this.exists(player)) {
    				return config.getString(key + ".Gradient.Color-1");
    			}
    		}	
    	}
    }
    public String getUUID(Player player) {
    	FileConfiguration config = AdvancedChat.getInstance().getPlayerDataFile().getConfig();
    	
    	Set<String> sections = config.getKeys(false);
    	Iterator<String> section = sections.iterator();
    	
    	while(true) {
    		while(section.hasNext()) {
    			String key = (String) section.next();
    			if(this.exists(player)) {
    				return config.getString(key + ".UUID");
    			}
    		}	
    	}
    }
    
    
    public String getSecondGradient(Player player) {
    	FileConfiguration config = AdvancedChat.getInstance().getPlayerDataFile().getConfig();
    	
    	Set<String> sections = config.getKeys(false);
    	Iterator<String> section = sections.iterator();
    	
    	while(true) {
    		while(section.hasNext()) {
    			String key = (String) section.next();
    			if(this.exists(player)) {
    				return config.getString(key + ".Gradient.Color-2");
    			}
    		}	
    	}
    }
        
    public void create(Player player) {
    	FileConfiguration config = plugin.getPlayerDataFile().getConfig();
    	if(!this.exists(player)) {
    		this.setUUID(player);
            config.set(player.getName() + ".Color", Settings.default_color);
            config.set(player.getName() + ".Gradient.Color-1", "FFFFFF");
            config.set(player.getName() + ".Gradient.Color-2", "FFFFFF");
            config.set(player.getName() + ".Mute", false);
            config.set(player.getName() + ".Chat.Channel", "ALL");
            config.set(player.getName() + ".Chat.Range", 10);
            plugin.getPlayerDataFile().saveConfig();
            if(plugin.isDebug()) {
            	Logger.debug("&9folder &7-> &e[Data] &7-> &efile &b[player.data] &7-> &aAdded " + player.getName());
            }
    	}else {
    		if(plugin.isDebug()) {
    			Logger.debug("&9folder &7-> &e[Data] &7-> &efile &b[player.data] &7-> &eIt already exists " + player.getName());
    		}
    	}
    }
    
    public boolean exists(Player player) {
		FileConfiguration config = plugin.getPlayerDataFile().getConfig();
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

    public boolean removePlayerlist(Player player) {
        FileConfiguration config = plugin.getPlayerDataFile().getConfig();
        for (String key : config.getConfigurationSection("Players").getKeys(false)) {
            if (key.contains(player.getName())) {
                config.set("Players." + key, null);
                plugin.getPlayerDataFile().saveConfig();
                return true;
            }
        }
        return false;
    }
    
    public void save() {
    	plugin.getPlayerDataFile().saveConfig();
    }

}
