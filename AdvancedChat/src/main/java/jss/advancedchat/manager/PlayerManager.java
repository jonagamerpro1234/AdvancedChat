package jss.advancedchat.manager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.common.api.ApiPlayerData;
import jss.advancedchat.config.player.PlayerFile;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;

public class PlayerManager  {
	
	@SuppressWarnings("unused")
	private static ApiPlayerData api = ApiPlayerData.api;
	private final PlayerFile playerFile = AdvancedChat.get().getPlayerFile();
	private FileConfiguration config = null;
	
	public PlayerManager(Player player) {
		config = playerFile.getConfig(player.getName());
	}
	
    public boolean isMute() {
    	if(existsPlayer("Is-Mute")) return config.getBoolean("Is-Mute"); return false;
    }
    
    public String getColor() {
    	if(existsPlayer("Message-Color")) return config.getString("Message-Color"); return null;
    }

    public String getChannel() {
    	if(existsPlayer("Channel")) return config.getString("Channel"); return null;
	}
    
    public String getRange() {
    	if(existsPlayer("Chat-Range")) return config.getString("Chat-Range"); return null;
    }
    
    public String getFirstGradient() {
    	if(existsPlayer("First-Gradient")) return config.getString("First-Gradient"); return null;
    }
    
    public String getSecondGradient() {
    	if(existsPlayer("Second-Gradient")) return config.getString("Second-Gradient"); return null;
    }
    
    public String getUUID() {  	
    	if(existsPlayer("UUID")) return config.getString("UUID"); return null;
    }
    
    public void setColor(Player player, String color) {
        if(existsPlayer("Message-Color")) config.set("Message-Color", color); save();
    }
    
    public void setChannel(Player player, String channel) {
        if(existsPlayer("Channel")) config.set("Channel", channel); save();
    }
    
    public void setMute(Player player, boolean value) {        
        if(config.contains("Is-Mute")) config.set("Is-Mute", value); save();
    }
    
    public void setRange(Player player, int range) {
        if(existsPlayer("Chat-Range")) config.set("Chat-Range", range); save();
    }
    
    public void setUUID(Player player) {
        if(existsPlayer("UUID")) config.set("UUID", player.getUniqueId().toString()); save();
    }
    
    public void setFirstGradient(Player player, String hex) {
        if(existsPlayer("First-Gradient")) config.set("First-Gradient", hex); save();
    }
    
    public void setSecondGradient(Player player, String hex) {
        if(existsPlayer("Second-Gradient")) config.set("Second-Gradient", hex); save();
    }
        
    public void create(Player player, String group) {
    	if(!existsPlayer("Name")) {
			config.set("Name", player.getName());
			config.set("UUID", player.getUniqueId().toString());

			if (GroupManager.get().isGroup()) {
				if (group == null) {
					config.set("Message-Color", Settings.default_color);
				}else {
					config.set("Message-Color", GroupManager.get().getGroupColor(group));
				}
			} else {
				config.set("Message-Color", Settings.default_color);
			}

			config.set("First-Gradient", "FFFFFF");
			config.set("Second-Gradient", "FFFFFF");
			config.set("Is-Mute", false);
			config.set("Channel", "all");
			config.set("Range-Chat", 10);
            save();
            if(AdvancedChat.get().isDebug())
            	Logger.debug("&9folder &7-> &e[Data] &7-> &d[Players] &7-> &efile &b[" + player.getName() + ".yml] &7-> &aAdded " + player.getName());
    	}else {
    		if(AdvancedChat.get().isDebug())
    			Logger.debug("&9folder &7-> &e[Data] &7-> &d[Players] &7-> &efile &b[" + player.getName() + ".yml] &7-> &eIt already existsPlayer " + player.getName());
    	}
    }
    
    public boolean existsPlayer(String section) {
    	if(config.contains(section)) return true; return false;
    }
    
    private void save() {
    	playerFile.save();
    }

}
