package jss.advancedchat.files;

import jss.advancedchat.AdvancedChat;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class BadWordFile {

    private final AdvancedChat plugin;
    private final String path;
    private File file;
    private FileConfiguration config;

    public BadWordFile(AdvancedChat plugin, String path) {
        this.plugin = plugin;
        this.file = null;
        this.config = null;
        this.path = path;
    }

    public void create() {
        this.file = new File(plugin.getDataFolder(), this.path);
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void reloadConfig() {
        if (this.config == null) {
            this.file = new File(plugin.getDataFolder(), this.path);
        }
        this.config = YamlConfiguration.loadConfiguration(this.file);
        Reader defaultConfigStream;
        try {
            defaultConfigStream = new InputStreamReader(Objects.requireNonNull(plugin.getResource(this.path)), StandardCharsets.UTF_8);
            BufferedReader in = new BufferedReader(defaultConfigStream);
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(in);
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
            this.file = new File(plugin.getDataFolder(), this.path);
        }
        if (!this.file.exists()) {
            plugin.saveResource(this.path, false);
        }
    }

    public boolean isFileExists() {
        this.file = new File(plugin.getDataFolder(), this.path);
        return this.file.exists();
    }

}
