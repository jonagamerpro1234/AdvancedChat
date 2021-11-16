package jss.advancedchat.common.interfaces;

import org.bukkit.configuration.file.FileConfiguration;

public interface IFileHelper {

    public FileConfiguration getConfig();

    public void saveConfig();

    public void reloadConfig();

    public String getPath();

    public void saveDefaultConfig();

    public void resetConfig();

    public boolean isFileExists();
}
