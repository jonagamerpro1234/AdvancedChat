package jss.advancedchat.storage.yaml;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.storage.PlayerData;
import jss.advancedchat.storage.utils.IStorage;
import jss.advancedchat.utils.Logger;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;


public class YamlStorage implements IStorage {

    private final AdvancedChat plugin;
    private File file;
    private FileConfiguration config;
    private String path;

    public YamlStorage(AdvancedChat plugin) {
        this.plugin = plugin;
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setDefault(){
        if(!config.contains("Name")){
            config.set("Name","jonagamerpro1234");
            config.set("Group","default");
            config.set("ChatType","color");
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
        }else{
            Logger.debug("is exist player data file in yaml file: true");
        }
    }

    public void savePlayerData(PlayerData playerData) {

    }

    public PlayerData loadPlayerData(String playerName) {
        return null;
    }
}
