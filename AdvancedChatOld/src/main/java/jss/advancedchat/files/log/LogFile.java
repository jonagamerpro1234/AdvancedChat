package jss.advancedchat.files.log;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Util;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LogFile {

    private final AdvancedChat plugin;
    private final String pathFile = Util.getDate();
    private File file;
    private FileConfiguration config;

    public LogFile(AdvancedChat plugin) {
        this.plugin = plugin;
    }

    public void create() {
        file = new File(plugin.getDataFolder() + File.separator + "Log", pathFile + ".yml");
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

    public FileConfiguration getConfig(String name) {
        file = new File(plugin.getDataFolder() + File.separator + "Log", name + ".yml");
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

    public void reload() {
        if (config == null) {
            file = new File(plugin.getDataFolder() + File.separator + "Log", pathFile);
        }
        config = YamlConfiguration.loadConfiguration(file);

        if (file != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(file);
            config.setDefaults(defConfig);
        }
    }

    public String getPath() {
        return pathFile;
    }

    public AdvancedChat getPlugin() {
        return plugin;
    }
}
