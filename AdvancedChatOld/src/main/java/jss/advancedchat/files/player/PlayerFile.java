package jss.advancedchat.files.player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.logger.Logger;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("all")
public class PlayerFile {

    private final AdvancedChat plugin;
    private String pathFile;
    private File file;
    private FileConfiguration config;

    public PlayerFile(AdvancedChat plugin, String pathFile) {
        this.plugin = plugin;
        this.pathFile = pathFile;
        Logger.debug("Initializing PlayerFile with path: " + pathFile);
    }

    public PlayerFile(AdvancedChat plugin) {
        this.plugin = plugin;
        Logger.debug("Initializing PlayerFile without specific path");
    }

    public void create() {
        if (pathFile == null || pathFile.isEmpty()) {
            Logger.debug("Invalid file path provided!");
            return;
        }

        file = new File(plugin.getDataFolder() + File.separator + "Players", pathFile + ".yml");
        Logger.debug("Creating file: " + file.getPath());

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    Logger.debug("Created new file: " + pathFile + ".yml");
                } else {
                    Logger.debug("Failed to create file: " + pathFile + ".yml");
                }
            } catch (IOException e) {
                Logger.debug("Error creating file: " + pathFile + ".yml - " + e.getMessage());
                return;
            }
        } else {
            Logger.debug("File already exists: " + pathFile + ".yml");
        }

        config = new YamlConfiguration();
        try {
            config.load(file);
            Logger.debug("File loaded successfully: " + pathFile + ".yml");
        } catch (IOException | InvalidConfigurationException e) {
            Logger.debug("The file could not be loaded: " + pathFile + ".yml - " + e);
        }
    }

    public FileConfiguration getConfig(String playerName) {
        File playerFile = new File(plugin.getDataFolder(), "Players/" + playerName + ".yml");
        Logger.debug("Fetching config for player: " + playerName);

        if (!playerFile.exists()) {
            try {
                playerFile.createNewFile();
                Logger.debug("Created new player config file: " + playerName + ".yml");
            } catch (IOException e) {
                Logger.debug("Failed to create config for player: " + playerName);
            }
        }
        return YamlConfiguration.loadConfiguration(playerFile);
    }

    public void save() {
        if (config == null) {
            Logger.debug("Cannot save config: Configuration is null!");
            return;
        }
        try {
            config.save(file);
            Logger.debug("Configuration saved successfully: " + file.getName());
        } catch (IOException e) {
            Logger.debug("Error saving file: " + file.getName() + " - " + e);
        }
    }

    public void reload() {
        if (pathFile == null || pathFile.isEmpty()) {
            Logger.debug("Invalid path file provided!");
            return;
        }

        file = new File(plugin.getDataFolder() + File.separator + "Players", pathFile + ".yml");
        Logger.debug("Reloading file: " + file.getPath());

        if (!file.exists()) {
            Logger.debug("File not found: " + pathFile + ".yml");
            return;
        }

        config = YamlConfiguration.loadConfiguration(file);
        Logger.debug("Configuration reloaded: " + pathFile + ".yml");
    }

    public String getPath() {
        Logger.debug("Fetching file path: " + pathFile);
        return pathFile;
    }

    public AdvancedChat getPlugin() {
        Logger.debug("Fetching plugin instance");
        return plugin;
    }
}
