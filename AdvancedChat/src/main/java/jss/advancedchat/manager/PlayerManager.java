package jss.advancedchat.manager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.config.player.PlayerFile;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;
import org.jetbrains.annotations.NotNull;

public class PlayerManager  {
	
	private final PlayerFile playerFile = AdvancedChat.get().getPlayerFile();
	private final FileConfiguration config;
	
	public PlayerManager(@NotNull Player player) {
		config = playerFile.getConfig(player.getName());
	}
	
	public String getName() {
		if(existsPlayer("Name")) return config.getString("Name"); return null;
	}
	
    public boolean isColor() {
    	if(existsPlayer("Chat-Format.Color")) return config.getBoolean("Chat-Format.Color"); return false;
    }
    
    public boolean isRainbow() {
    	if(existsPlayer("Chat-Format.Rainbow")) return config.getBoolean("Chat-Format.Rainbow"); return false;
    }
    
    public boolean isRandom() {
    	if(existsPlayer("Chat-Format.Random")) return config.getBoolean("Chat-Format.Random"); return false;
    }
    
    public boolean isGradient() {
    	if(existsPlayer("Chat-Format.Gradient")) return config.getBoolean("Chat-Format.Gradient"); return false;
    }
    
    public boolean isSpecialCodes() {
    	if(existsPlayer("Chat-Format.Special-Color-Codes")) return config.getBoolean("Chat-Format.Special-Color-Codes"); return false;
    }
    
    public boolean isLowMode() {
    	if(existsPlayer("Other-Settings.Low-Mode")) return config.getBoolean("Other-Settings.Low-Mode"); return false;
    }
    
    public boolean isMention() {
    	if(existsPlayer("Other-Settings.Mention")) return config.getBoolean("Other-Settings.Mention"); return false;
    }
	
    public boolean isMsg() {
    	if(existsPlayer("Other-Settings.Msg")) return config.getBoolean("Other-Settings.Msg"); return false;
    }
    
    public boolean isChat() {
    	if(existsPlayer("Other-Settings.Chat")) return config.getBoolean("Other-Settings.Chat"); return false;
    }
    
    public boolean isMute() {
    	if(existsPlayer("Is-Mute")) return config.getBoolean("Is-Mute"); return false;
    }
    
    public String getColor() {
    	if(existsPlayer("Chat-Message.Color")) return config.getString("Chat-Message.Color"); return null;
    }
    
    public String getSpecialColor() {
    	if(existsPlayer("Chat-Message.Special-Color-Codes")) return config.getString("Chat-Message.Special-Color-Codes"); return null;
    }

    public String getChannel() {
    	if(existsPlayer("Channel")) return config.getString("Channel"); return null;
	}
    
    public String getRange() {
    	if(existsPlayer("Chat-Range")) return config.getString("Chat-Range"); return null;
    }
    
    public String getFirstGradient() {
    	if(existsPlayer("Chat-Message.First-Gradient")) return config.getString("Chat-Message.First-Gradient"); return null;
    }
    
    public String getSecondGradient() {
    	if(existsPlayer("Chat-Message.Second-Gradient")) return config.getString("Chat-Message.Second-Gradient"); return null;
    }
    
    public String getUUID() {  	
    	if(existsPlayer("UUID")) return config.getString("UUID"); return null;
    }
    
    public String getGroup() {  	
    	if(existsPlayer("Group")) return config.getString("Group"); return null;
    }
    
    public String getRainbow() {
    	if(existsPlayer("Chat-Message.Rainbow")) return config.getString("Chat-Message.Rainbow"); return null;
    }
    
    public void setMention(boolean value) {
    	if(existsPlayer("Other-Settings.Mention")) config.set("Other-Settings.Mention", value); save();
    }
	
    public void setMsg(boolean value) {
    	if(existsPlayer("Other-Settings.Msg")) config.set("Other-Settings.Msg", value); save();
    }
    
    public void setChat(boolean value) {
    	if(existsPlayer("Other-Settings.Chat")) config.set("Other-Settings.Chat", value); save();
    }
    
    public void setRainbow(String rainbow) {
    	if(existsPlayer("Chat-Message.Rainbow")) config.set("Chat-Message.Rainbow", rainbow); save();
    }
    
    public void setColor(String color) {
        if(existsPlayer("Chat-Message.Color")) config.set("Chat-Message.Color", color); save();
    }
    
    public void setSpecialColor(String color) {
        if(existsPlayer("Chat-Message.Special-Color")) config.set("Chat-Message.Special-Color-Coders", color); save();
    }
    
    public void setChannel(String channel) {
        if(existsPlayer("Channel")) config.set("Channel", channel); save();
    }
    
    public void setLowMode(boolean value) {        
        if(config.contains("Other-Settings.Low-Mode")) config.set("Other-Settings.Low-Mode", value); save();
    }
    
    public void setMute(boolean value) {        
        if(config.contains("Is-Mute")) config.set("Is-Mute", value); save();
    }
    
    public void setRange(int range) {
        if(existsPlayer("Chat-Range")) config.set("Chat-Range", range); save();
    }
    
    public void setUUID(Player player) {
        if(existsPlayer("UUID")) config.set("UUID", player.getUniqueId().toString()); save();
    }
    
    public void setFirstGradient(String hex) {
        if(existsPlayer("Chat-Message.First-Gradient")) config.set("Chat-Message.First-Gradient", hex); save();
    }
    
    public void setSecondGradient(String hex) {
        if(existsPlayer("Chat-Message.Second-Gradient")) config.set("Chat-Message.Second-Gradient", hex); save();
    }
    
    public void setGroup(String group) {
        if(existsPlayer("Group")) config.set("Group", group); save();
    }
    
    public void setColor(boolean value) {
    	if(existsPlayer("Chat-Format.Color")) config.set("Chat-Format.Color", value); save();
    }
    
    public void setRainbow(boolean value) {
    	if(existsPlayer("Chat-Format.Rainbow"))  config.set("Chat-Format.Rainbow", value); save();
    }
    
    public void setRandom(boolean value) {
    	if(existsPlayer("Chat-Format.Random")) config.set("Chat-Format.Random", value); save();
    }
    
    public void setGradient(boolean value) {
    	if(existsPlayer("Chat-Format.Gradient")) config.set("Chat-Format.Gradient", value); save();
    }
    
    public void setSpecialCodes(boolean value) {
    	if(existsPlayer("Chat-Format.Special-Color-Codes")) config.set("Chat-Format.Special-Color-Codes", value); save();
    }
    
    public void create(Player player, String group) {
    	if(!existsPlayer("Name")) {
			config.set("Name", player.getName());
			config.set("UUID", player.getUniqueId().toString());
			config.set("Chat.Hover", "[]");
			config.set("Chat-Message.Color", Settings.default_color);
			config.set("Chat-Message.Rainbow", "rainbow_1");
			config.set("Chat-Message.Special-Color-Codes", "none");
			config.set("Chat-Message.First-Gradient", "FFFFFF");
			config.set("Chat-Message.Second-Gradient", "FFFFFF");
			config.set("Chat-Format.Color", true);
			config.set("Chat-Format.Rainbow", false);
			config.set("Chat-Format.Gradient", false);
			config.set("Chat-Format.Random", false);
			config.set("Is-Mute", false);
			config.set("Group", group);
			config.set("Channel", "all");
			config.set("Range-Chat", 10);
			config.set("Other-Settings.Low-Mode", false);
			config.set("Other-Settings.Chat", true);
			config.set("Other-Settings.Msg", true);
			config.set("Other-Settings.Mention", true);
            save();
            if(AdvancedChat.get().isDebug())
            	Logger.debug("&9folder &7-> &e[Data] &7-> &d[Players] &7-> &efile &b[" + player.getName() + ".yml] &7-> &aAdded " + player.getName());
    	}else {
    		if(AdvancedChat.get().isDebug())
    			Logger.debug("&9folder &7-> &e[Data] &7-> &d[Players] &7-> &efile &b[" + player.getName() + ".yml] &7-> &eIt already existsPlayer " + player.getName());
    	}
    }
    
    public boolean existsPlayer(String section) {
        return config.contains(section);
    }
    
    private void save() {
    	playerFile.save();
    }

}
