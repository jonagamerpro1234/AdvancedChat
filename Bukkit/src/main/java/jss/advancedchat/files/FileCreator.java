package jss.advancedchat.files;

import jss.advancedchat.AdvancedChat;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FileCreator {

    private final AdvancedChat plugin;
    private File file;
    private YamlConfiguration config;

    public FileCreator(AdvancedChat plugin) {
        this.plugin = plugin;
    }

    public void create(String folder,String name, boolean inFolder) {
        String dir;
        if(inFolder){
            dir = plugin.getDataFolder() + File.separator + folder;
        }else{
            dir = plugin.getDataFolder().getName();
        }


        this.file = new File(dir, name + ".yml");
        if (!this.file.exists()) {
            getConfig(folder,name,inFolder).options().copyDefaults(true);
            saveConfig(folder,name,inFolder);
        }
    }

    public FileConfiguration getConfig(String folder, String name, boolean inFolder) {
        if (this.config == null) {
            reloadConfig(name);
        }
        file = getFile(folder,name,inFolder);
        config = YamlConfiguration.loadConfiguration(file);
        return this.config;
    }

    public FileConfiguration getConfig(String name) {
        return getConfig("",name,false);
    }


    public void saveConfig(String folder, String name, boolean inFolder) {
        try {
            file = getFile(folder,name,inFolder);
            this.config.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveConfig(String name) {
        saveConfig("",name,false);
    }

    public void reloadConfig(String name) {
        if (this.config == null) {
            this.file = new File(plugin.getDataFolder(), name + ".yml");
        }
        this.config = YamlConfiguration.loadConfiguration(this.file);
        Reader defaultConfigStream;
        try {
            //noinspection ConstantConditions
            defaultConfigStream = new InputStreamReader(plugin.getResource(name + ".yml"), StandardCharsets.UTF_8);
            BufferedReader in = new BufferedReader(defaultConfigStream);
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(in);
            config.setDefaults(defaultConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveDefaultConfig(String folder, String name, boolean inFolder) {
        if (this.file == null) {
            this.file = getFile(folder, name, inFolder);
        }
        if (!this.file.exists()) {
            plugin.saveResource(name + ".yml", false);
        }
    }

    public void saveDefaultConfig(String name) {
        saveDefaultConfig("",name,false);
    }

    public File getFile(String folder, String name, boolean inFolder) {
        String dir;
        if(inFolder){
            dir = plugin.getDataFolder() + File.separator + folder;
        }else{
            dir = plugin.getDataFolder().getName();
        }
        this.file = new File(this.plugin.getDataFolder(), name + ".yml");
        return this.file;
    }

}
