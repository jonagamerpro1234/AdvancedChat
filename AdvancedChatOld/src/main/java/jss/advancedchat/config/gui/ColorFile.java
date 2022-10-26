package jss.advancedchat.config.gui;

import jss.advancedchat.AdvancedChat;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class ColorFile {

    private final AdvancedChat plugin;
    private final String path;
    private final String folderpath;
    private File file;
    private FileConfiguration config;

    public ColorFile(AdvancedChat plugin, String path, String folderpath) {
        this.plugin = plugin;
        this.file = null;
        this.config = null;
        this.path = path;
        this.folderpath = folderpath;
    }

    public String getFolderPath() {
        return this.folderpath;
    }

    public void create() {
        this.file = new File(plugin.getDataFolder() + File.separator + this.folderpath, this.path);
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void reloadConfig() {
        if (this.config == null) {
            this.file = new File(plugin.getDataFolder() + File.separator + this.folderpath, this.path);
        }
        this.config = YamlConfiguration.loadConfiguration(this.file);
        Reader defaultConfigStream;
        try {
            defaultConfigStream = new InputStreamReader(plugin.getResource(this.path), "UTF8");
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(defaultConfigStream);
            config.setDefaults(defaultConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getPath() {
        return this.path;
    }

    public AdvancedChat getPlugin() {
        return plugin;
    }

    public void saveDefaultConfig() {
        if (this.file == null) {
            this.file = new File(plugin.getDataFolder() + File.separator + this.folderpath, this.path);
        }
        if (!this.file.exists()) {
            plugin.saveResource(this.path, false);
        }
    }

    public void resetConfig() {
        if (this.file == null) {
            this.file = new File(plugin.getDataFolder() + File.separator + this.folderpath, this.path);
        }
        if (!this.file.exists()) {
            plugin.saveResource(this.path, true);
        }
    }

    public boolean isFileExists() {
        this.file = new File(plugin.getDataFolder() + File.separator + this.folderpath, this.path);
        return this.file.exists();
    }

}
