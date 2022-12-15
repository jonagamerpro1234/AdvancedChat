package jss.advancedchat.files;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Logger;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PlayerFile {

    private final AdvancedChat plugin;
    private String path;
    private File file;
    private FileConfiguration config;

    public PlayerFile(AdvancedChat plugin, String path) {
        this.plugin = plugin;
        this.path = path;
    }

    public PlayerFile(AdvancedChat plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void create(){
        file = new File(plugin.getDataFolder() + File.separator + "Players", path + ".yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = new YamlConfiguration();
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            Logger.error("The file could not be loaded");
            e.printStackTrace();
        }
    }

    public FileConfiguration config(String name) {
        file = new File(plugin.getDataFolder() + File.separator + "Players", name + ".yml");
        config = YamlConfiguration.loadConfiguration(file);

        return config;
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPath() {
        return path;
    }
}
