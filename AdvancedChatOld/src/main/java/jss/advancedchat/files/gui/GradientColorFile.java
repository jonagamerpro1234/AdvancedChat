package jss.advancedchat.files.gui;

import jss.advancedchat.AdvancedChat;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class GradientColorFile {

    private final AdvancedChat plugin;
    private final String path;
    private final String folderPath;
    private File file;
    private FileConfiguration config;

    public GradientColorFile(AdvancedChat plugin, String path, String folderPath) {
        this.plugin = plugin;
        this.file = null;
        this.config = null;
        this.path = path;
        this.folderPath = folderPath;
    }

    public String getFolderPath() {
        return this.folderPath;
    }

    public void create() {
        this.file = new File(plugin.getDataFolder() + File.separator + this.folderPath, this.path);
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
            throw new RuntimeException(e);
        }
    }

    public void reloadConfig() {
        if (this.config == null) {
            this.file = new File(plugin.getDataFolder() + File.separator + this.folderPath, this.path);
        }
        this.config = YamlConfiguration.loadConfiguration(this.file);
        Reader defaultConfigStream;
        try {
            defaultConfigStream = new InputStreamReader(Objects.requireNonNull(plugin.getResource(this.path)), StandardCharsets.UTF_8);
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(defaultConfigStream);
            config.setDefaults(defaultConfig);
        } catch (Exception e) {
            throw new RuntimeException(e);
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
            this.file = new File(plugin.getDataFolder() + File.separator + this.folderPath, this.path);
        }
        if (!this.file.exists()) {
            plugin.saveResource(this.path, false);
        }
    }

    public void resetConfig() {
        if (this.file == null) {
            this.file = new File(plugin.getDataFolder() + File.separator + this.folderPath, this.path);
        }
        if (!this.file.exists()) {
            plugin.saveResource(this.path, true);
        }
    }

    public boolean isFileExists() {
        this.file = new File(plugin.getDataFolder() + File.separator + this.folderPath, this.path);
        return this.file.exists();
    }

}
