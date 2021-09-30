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
    
    public String getStateMute(Player player) {
        FileConfiguration config = plugin.getPlayerDataFile().getConfig();
        String stateMute = "";
        for (String key : config.getConfigurationSection("Players").getKeys(false)) {
            if (key.contains(player.getName())) {
            	boolean is = config.getString("Players." + key + ".Mute").equals("true");
            	if(is) {
            		stateMute = "true";
            	}else{
            		stateMute ="false";
            	}
            }
        }
		return stateMute;
	}

    public String getStateMuteLore(Player player) {
        FileConfiguration config = plugin.getPlayerDataFile().getConfig();
        String stateMute = "";
        for (String key : config.getConfigurationSection("Players").getKeys(false)) {
            if (key.contains(player.getName())) {
            	boolean is = config.getString("Players." + key + ".Mute").equals("true");
            	if(is) {
            		stateMute = "false";
            	}else{
            		stateMute = "true";
            	}
            }
        }
		return stateMute;
	}
    
    @Deprecated
    public boolean isMuteOld(Player player) {
        FileConfiguration config = plugin.getPlayerDataFile().getConfig();
        for (String key : config.getConfigurationSection("Players").getKeys(false)) {
            if (key.contains(player.getName())) {
                return config.getBoolean("Players." + key + ".Mute");
            }
        }
        return false;
    }

    public void setMute(Player player, boolean mute) {
        FileConfiguration config = plugin.getPlayerDataFile().getConfig();
        if (player == null) {
            return;
        }
        if (config.contains(player.getName() + ".Mute")) {
            config.set(player.getName() + ".Mute", mute);
            plugin.getPlayerDataFile().saveConfig();
        }
    }
    
    @Deprecated
    public void setMuteOld(Player player, boolean mute) {
        FileConfiguration config = plugin.getPlayerDataFile().getConfig();
        if (player == null) {
            return;
        }
        if (config.contains(player.getName() + ".Mute")) {
            config.set("Players." + player.getName() + ".Mute", mute);
            plugin.getPlayerDataFile().saveConfig();
        }
    }

    public String getColor(Player player, String text) {
        FileConfiguration config = plugin.getPlayerDataFile().getConfig();
        
        
        
        for (String key : config.getConfigurationSection("Players").getKeys(false)) {
            if (key.contains(player.getName())) {
                String color = config.getString("Players." + key + ".Color");
                return colorManager.convertColor(player, color, text);
            }
        }
        return null;
    }

    public void setColor(Player player, String color) {
        FileConfiguration config = plugin.getPlayerDataFile().getConfig();
        if (config.contains("Players." + player.getName() + ".Color")) {
            config.set("Players." + player.getName() + ".Color", color);
            plugin.getPlayerDataFile().saveConfig();
        }
    }
    
    public String getChannel(Player player) {
    	FileConfiguration config = plugin.getPlayerDataFile().getConfig();
    	for(String key : config.getConfigurationSection("Players").getKeys(false)) {
    		if(key.contains(player.getName())) {
    			String channel = config.getString("Players." + key + ".Chat.Channel");
    			return channel.toLowerCase();
    		}
    	}
    	return null;
    }
    
    public void setChannel(Player player, int range) {
    	FileConfiguration config = plugin.getPlayerDataFile().getConfig();
        if (config.contains("Players." + player.getName() + ".Chat.Channel")) {
            config.set("Players." + player.getName() + ".Chat.Channel", range);
            plugin.getPlayerDataFile().saveConfig();
        }
    }
    
    public String getRange(Player player) {
    	FileConfiguration config = plugin.getPlayerDataFile().getConfig();
    	for(String key : config.getConfigurationSection("Players").getKeys(false)) {
    		if(key.contains(player.getName())) {
    			String channel = config.getString("Players." + key + ".Chat.Range");
    			return channel.toLowerCase();
    		}
    	}
    	return null;
    }
    
    public void setRange(Player player, int range) {
    	FileConfiguration config = plugin.getPlayerDataFile().getConfig();
        if (config.contains("Players." + player.getName() + ".Chat.Range")) {
            config.set("Players." + player.getName() + ".Chat.Range", range);
            plugin.getPlayerDataFile().saveConfig();
        }
    }
    
    
    public void setGradient1(Player player, String hex) {
    	FileConfiguration config = AdvancedChat.getInstance().getPlayerDataFile().getConfig();
    	
    	Set<String> sections = config.getKeys(false);
    	
    	sections.forEach( key -> {
			if(key.contains(player.getName())) {
				config.set(key + ".Gradient.Color-1", hex);
			}
    	});
    }
    
    public void setGradient2(Player player, String hex) {
    	FileConfiguration config = AdvancedChat.getInstance().getPlayerDataFile().getConfig();
    	
    	Set<String> sections = config.getKeys(false);
    	
    	sections.forEach( key -> {
			if(key.contains(player.getName())) {
				config.set(key + ".Gradient.Color-2", hex);
			}
    	});
    }
    
    public String getGradient1(Player player) {
    	FileConfiguration config = AdvancedChat.getInstance().getPlayerDataFile().getConfig();
    	
    	Set<String> sections = config.getKeys(false);
    	String color = "";	
    	for(String key : sections) {
			if(key.contains(player.getName())) {
				color = config.getString(key + ".Gradient.Color-1");
			}
    	}
    	return color;
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
