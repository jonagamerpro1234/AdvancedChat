package jss.advancedchat.manager;

import jss.advancedchat.files.PlayerFile;
import jss.advancedchat.files.utils.Settings;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerManager {

    private final PlayerFile playerFile = ConfigManager.getPlayerFile();
    private final FileConfiguration config;
    private final Player player;

    public PlayerManager(@NotNull Player player){
        this.config = playerFile.config(player.getName());
        this.player = player;
    }

    public String getName(){
        if(existsPlayer("Name")) return config.getString("Name");
        return "N/A";
    }

    public void setName(String name){
        if(existsPlayer("Name")) config.set("Name", name);
        save();
    }


    public String getGroup(){
        if(existsPlayer("Group")) return config.getString("Group");
        return "default";
    }

    public void setGroup(String group){
        if(existsPlayer("Group")) config.set("Group", group);
        save();
    }

    public String getChatType(){
        if(existsPlayer("ChatType")) return config.getString("ChatType");
        return "color";
    }

    public void setChatType(String channel){
        if(existsPlayer("Channel")) config.set("Channel", channel);
        save();
    }

    public String getColor(){
        if(existsPlayer("Color")) return config.getString("Color");
        return "white";
    }

    public void setColor(String color){
        if(existsPlayer("Color")) config.set("Color", color);
        save();
    }

    public String getChannel(){
        if(existsPlayer("Channel")) return config.getString("Channel");
        return "main";
    }

    public void setChannel(String channel){
        if(existsPlayer("Channel")) config.set("Channel", channel);
        save();
    }

    public int getRange(){
        if(existsPlayer("Range")) return config.getInt("Range");
        return 10;
    }

    public void setRange(int range){
        if(existsPlayer("Range")) config.set("Range", range);
        save();
    }

    public String getFirstGradient(){
        if(existsPlayer("Gradient.First")) return config.getString("Gradient.First");
        return "ffffff";
    }

    public void setFirstGradient(String gradient){
        if(existsPlayer("Gradient.First")) config.set("Gradient.First", gradient);
        save();
    }

    public String getSecondGradient(){
        if(existsPlayer("Gradient.Second")) return config.getString("Gradient.Second");
        return "ffffff";
    }

    public void setSecondGradient(String gradient){
        if(existsPlayer("Gradient.Second")) config.set("Gradient.Second", gradient);
        save();
    }

    public String getSpecialCodes(){
        if(existsPlayer("SpecialCodes")) return config.getString("SpecialCodes");
        return "none";
    }

    public void setSpecialCodes(String specialCodes){
        if(existsPlayer("SpecialCodes")) config.set("SpecialCodes", specialCodes);
        save();
    }

    public boolean isMute(){
        if(existsPlayer("Mute.Enabled")) return config.getBoolean("Mute.Enabled");
        return false;
    }

    public void setMute(boolean value){
        if(existsPlayer("Mute.Enabled")) config.set("Mute.Enabled", value);
        save();
    }

    public int getMutedTime(){
        if(existsPlayer("Mute.Time")) return config.getInt("Mute.Time");
        return -1;
    }

    public void setMutedTime(int time){
        if(existsPlayer("Mute.Time")) config.set("Mute.Time", time);
        save();
    }

    public boolean isLowMode(){
        if(existsPlayer("Inventory.LowMode")) return config.getBoolean("Inventory.LowMode");
        return false;
    }

    public void setLowMode(boolean value){
        if(existsPlayer("Inventory.LowMode")) config.set("Inventory.LowMode", value);
        save();
    }

    public boolean isChat(){
        if(existsPlayer("Settings.Chat")) return config.getBoolean("Settings.Chat");
        return false;
    }

    public void setChat(boolean value){
        if(existsPlayer("Settings.Chat")) config.set("Settings.Chat", value);
        save();
    }

    public boolean isPrivateMessage(){
        if(existsPlayer("Settings.PrivateMessage")) return config.getBoolean("Settings.PrivateMessage");
        return false;
    }

    public void setPrivateMessage(boolean value){
        if(existsPlayer("Settings.PrivateMessage")) config.set("Settings.PrivateMessage", value);
        save();
    }

    public void create(String group){
        config.set("Name",player.getName());
        config.set("Group",group);
        config.set("ChatType","color");
        config.set("Color", Settings.default_color);
        config.set("Channel","main");
        config.set("Range",10);
        config.set("Gradient.First","#AAFFAA");
        config.set("Gradient.Second","#AAAAAA");
        config.set("SpecialCodes","none");
        config.set("Mute.Enabled",false);
        config.set("Mute.Time",0);
        config.set("Inventory.LowMode",false);
        config.set("Settings.Chat",true);
        config.set("Settings.PrivateMessage",true);
        save();
    }

    public boolean existsPlayer(String section) {
        return config.contains(section);
    }

    public void save(){
        playerFile.save();
    }

}
