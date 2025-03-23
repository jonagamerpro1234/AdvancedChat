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
            Logger.error("Invalid file path provided!");
            return;
        }

        file = new File(plugin.getDataFolder() + File.separator + "Players", pathFile + ".yml");
        Logger.info("Creating file: " + file.getPath());

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    Logger.info("Created new file: " + pathFile + ".yml");
                } else {
                    Logger.warning("Failed to create file: " + pathFile + ".yml");
                }
            } catch (IOException e) {
                Logger.error("Error creating file: " + pathFile + ".yml - " + e.getMessage());
                return;
            }
        } else {
            Logger.info("File already exists: " + pathFile + ".yml");
        }

        config = new YamlConfiguration();
        try {
            config.load(file);
            Logger.info("File loaded successfully: " + pathFile + ".yml");
        } catch (IOException | InvalidConfigurationException e) {
            Logger.error("The file could not be loaded: " + pathFile + ".yml - " + e);
        }
    }

    public FileConfiguration getConfig(String playerName) {
        File playerFile = new File(plugin.getDataFolder(), "Players/" + playerName + ".yml");
        Logger.info("Fetching config for player: " + playerName);

        if (!playerFile.exists()) {
            try {
                playerFile.createNewFile();
                Logger.info("Created new player config file: " + playerName + ".yml");
            } catch (IOException e) {
                Logger.error("Failed to create config for player: " + playerName);
            }
        }
        return YamlConfiguration.loadConfiguration(playerFile);
    }

    public void save() {
        if (config == null) {
            Logger.error("Cannot save config: Configuration is null!");
            return;
        }
        try {
            config.save(file);
            Logger.info("Configuration saved successfully: " + file.getName());
        } catch (IOException e) {
            Logger.error("Error saving file: " + file.getName() + " - " + e);
        }
    }

    public void reload() {
        if (pathFile == null || pathFile.isEmpty()) {
            Logger.error("Invalid path file provided!");
            return;
        }

        file = new File(plugin.getDataFolder() + File.separator + "Players", pathFile + ".yml");
        Logger.info("Reloading file: " + file.getPath());

        if (!file.exists()) {
            Logger.error("File not found: " + pathFile + ".yml");
            return;
        }

        config = YamlConfiguration.loadConfiguration(file);
        Logger.info("Configuration reloaded: " + pathFile + ".yml");
    }

    public String getPath() {
        Logger.info("Fetching file path: " + pathFile);
        return pathFile;
    }

    public AdvancedChat getPlugin() {
        Logger.info("Fetching plugin instance");
        return plugin;
    }
}