package jss.advancedchat.config;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.FileManager;
import jss.advancedchat.utils.interfaces.FileHelper;
import jss.advancedchat.utils.interfaces.FolderHelper;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ChatDataFile extends FileManager implements FileHelper, FolderHelper {
  private final AdvancedChat plugin;

  private File file;

  private FileConfiguration config;

  private final String path;

  private final String folderpath;

  public ChatDataFile(AdvancedChat plugin, String path, String folderpath) {
    super(plugin);
    this.plugin = plugin;
    this.path = path;
    this.folderpath = folderpath;
    this.config = null;
    this.file = null;
  }

  public String getFolderPath() {
    return this.folderpath;
  }

  public void create() {
    this.file = new File(getDataFolder() + File.separator + this.folderpath, this.path);
    if (!this.file.exists()) {
      getConfig().options().copyDefaults(true);
      saveConfig();
    }
  }

  public FileConfiguration getConfig() {
    if (this.config == null)
      reloadConfig();
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
    if (this.config == null)
      this.file = new File(getDataFolder() + File.separator + this.folderpath, this.path);
    this.config = YamlConfiguration.loadConfiguration(this.file);
    try {
      Reader defaultConfigStream = new InputStreamReader(getResources(this.path), StandardCharsets.UTF_8);
      if (defaultConfigStream != null) {
        YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(defaultConfigStream);
        this.config.setDefaults(defaultConfig);
      }
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
  }

  public String getPath() {
    return this.path;
  }

  public AdvancedChat getPlugin() {
    return this.plugin;
  }

  public void saveDefaultConfig() {
    if (this.file == null)
      this.file = new File(getDataFolder() + File.separator + this.folderpath, this.path);
    if (!this.file.exists())
      saveResources(this.path, false);
  }

  public void resetConfig() {
    if (this.file == null)
      this.file = new File(getDataFolder() + File.separator + this.folderpath, this.path);
    if (!this.file.exists())
      saveResources(this.path, true);
  }
}
