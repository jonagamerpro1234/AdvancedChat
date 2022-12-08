package jss.advancedchat.files;

import jss.advancedchat.AdvancedChat;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class MessageFile {

    private final AdvancedChat plugin;
    private final String path;
    private File file;
    private FileConfiguration config;

    public MessageFile(AdvancedChat plugin, String path) {
        this.plugin = plugin;
        this.path = path;
        this.file = null;
        this.config = null;
    }

    public void createFile() {
        file = new File(plugin.getDataFolder(), path);
        if (!file.exists()) {
            get().options().copyDefaults(true);
            save();
        }
    }

    public FileConfiguration get() {
        if (config == null) {
            this.reload();
        }
        return config;
    }

    public void reload() {
        if (config == null) {
            file = new File(plugin.getDataFolder(), path);
        }
        config = YamlConfiguration.loadConfiguration(file);
        Reader reader;
        try {
            reader = new InputStreamReader(plugin.getResource(path), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(bufferedReader);
            config.setDefaults(configuration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveDefault() {
        if (this.file == null) {
            this.file = new File(plugin.getDataFolder(), this.path);
        }
        if (!this.file.exists()) {
            plugin.saveResource(this.path, false);
        }
    }
}
