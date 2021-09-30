package jss.advancedchat.manager;

import java.util.Iterator;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Logger;

public class PlayerManager {

    private AdvancedChat plugin;
    private ColorManager colorManager;
    
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

    public void setMute(Player player, boolean mute) {
        FileConfiguration config = plugin.getPlayerDataFile().getConfig();
        if (config.contains(player.getName() + ".Mute")) {
            config.set(player.getName() + ".Mute", mute);
            plugin.getPlayerDataFile().saveConfig();
        }
    }
    
    public String getColor(Player player, String text) {
        FileConfiguration config = plugin.getPlayerDataFile().getConfig();
        
        Set<String> sections = config.getKeys(false);
        Iterator<String> section = sections.iterator();
        
        while(true) {
        	while(section.hasNext()) {
        		String key = (String) section.next();
        		if(key.contains(player.getName())) {
        			return colorManager.convertColor(player, config.getString(key + ".Color"), text);
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
    
    
    public void setGradient1(Player player, String hex) {
    	FileConfiguration config = AdvancedChat.getInstance().getPlayerDataFile().getConfig();
        if (config.contains(player.getName() + ".Gradient")) {
            config.set(player.getName() + ".Gradient.Color-1", hex);
            plugin.getPlayerDataFile().saveConfig();
        }
    }
    
    public void setGradient2(Player player, String hex) {
    	FileConfiguration config = AdvancedChat.getInstance().getPlayerDataFile().getConfig();
        if (config.contains(player.getName() + ".Gradient")) {
            config.set(player.getName() + ".Gradient.Color-2", hex);
            plugin.getPlayerDataFile().saveConfig();
        }
    }
    
    public String getGradient1(Player player) {
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
    
    public String getGradient2(Player player) {
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
    		config.set(player.getName() + ".UUID", player.getUniqueId().toString());
            config.set(player.getName() + ".Color", "WHITE");
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
