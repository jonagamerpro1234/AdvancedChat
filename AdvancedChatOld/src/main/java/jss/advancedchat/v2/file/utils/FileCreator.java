package jss.advancedchat.v2.file.utils;

import jss.advancedchat.AdvancedChat;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FileCreator {
    
    private final AdvancedChat plugin = AdvancedChat.get();
    private File file;
    private FileConfiguration config;
    private final String path;
    private final File dir;

    public FileCreator(File dir, String filename){
        this.dir = dir;
        this.path = filename;
        saveDefaultConfig();
        this.file = new File(dir, this.path);
        if (!this.file.exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }

    public FileConfiguration getConfig() {
        if (this.config == null) {
            reloadConfig();
        }
        return this.config;
    }

    public void saveConfig() {
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadConfig() {
        if (this.config == null) {
            this.file = new File(dir, this.path);
        }
        this.config = YamlConfiguration.loadConfiguration(this.file);
        Reader defaultConfigStream;
        try {
            defaultConfigStream = new InputStreamReader(Objects.requireNonNull(plugin.getResource(this.path)), StandardCharsets.UTF_8);
            BufferedReader in = new BufferedReader(defaultConfigStream);
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(in);
            config.setDefaults(defaultConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveDefaultConfig() {
        if (this.file == null) {
            this.file = new File(dir, this.path);
        }
        if (!this.file.exists()) {
            plugin.saveResource(this.path, false);
        }
    }
}
