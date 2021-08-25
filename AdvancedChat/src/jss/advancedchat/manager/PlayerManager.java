package jss.advancedchat.manager;

import java.util.Iterator;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;

public class PlayerManager {

    private AdvancedChat plugin;
    private int spam;
    private ColorManager colorManager;
    private FileConfiguration config = plugin.getPlayerDataFile().getConfig();
    
    public PlayerManager(AdvancedChat plugin) {
        this.plugin = plugin;
        this.spam = 0;
    }

    public int getSpam() {
        return spam;
    }

    public void setSpam(int spam) {
        this.spam = spam;
    }

    public boolean isMute(Player player) {
    	Set<String> sections = config.getKeys(false);
    	Iterator<String> section = sections.iterator();
    	while(true) {
    		while(section.hasNext()) {
    			String key = (String) section.next();
    			
    			if(key.contains(player.getName())) {
    				return config.getBoolean(key + ".Mute");
    			}else {
    				return false;
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
        if (config.contains("Players." + player.getName() + ".Mute")) {
            config.set("Players." + player.getName() + ".Mute", mute);
            plugin.getPlayerDataFile().saveConfig();
        }
    }

    public String getColor(Player player, String text) {
        FileConfiguration config = plugin.getPlayerDataFile().getConfig();

        for (String key : config.getConfigurationSection("Players").getKeys(false)) {
            if (key.contains(player.getName())) {
                String color = config.getString("Players." + key + ".Color");
                return colorManager.convertColor(color, text);
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
    
    public boolean check(Player player) {
        FileConfiguration config = plugin.getPlayerDataFile().getConfig();
        for (String key : config.getConfigurationSection("Players").getKeys(false)) {
            if (key.contains(player.getName())) {
                return true;
            }
        }
        return false;
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
