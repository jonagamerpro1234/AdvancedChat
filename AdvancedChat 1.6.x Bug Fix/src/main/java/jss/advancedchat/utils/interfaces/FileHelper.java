package jss.advancedchat.utils.interfaces;

import org.bukkit.configuration.file.FileConfiguration;

public interface FileHelper {
    void create();

    FileConfiguration getConfig();

    void saveConfig();

    void reloadConfig();

    String getPath();

    void saveDefaultConfig();

    void resetConfig();
}
