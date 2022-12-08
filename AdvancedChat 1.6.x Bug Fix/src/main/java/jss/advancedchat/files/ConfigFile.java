package jss.advancedchat.files;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.files.utils.FileManager;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.interfaces.FileHelper;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class ConfigFile extends FileManager implements FileHelper {
  private final AdvancedChat plugin;

  private File file;

  private FileConfiguration config;

  private final String path;

  public ConfigFile(AdvancedChat plugin, String path) {
    super(plugin);
    this.plugin = plugin;
    this.file = null;
    this.config = null;
    this.path = path;
  }

  public void create() {
    this.file = new File(getDataFolder(), this.path);
    if (!this.file.exists()) {
      getConfig().options().copyDefaults(true);
      saveConfig();
    } else if (getConfig().getInt("Settings.Config-Version") != 1) {
      Logger.warning("&cThe version of your config.yml is not compatible with the current version!");
      File configold = new File(getDataFolder(), "config_old.yml");
      this.file.renameTo(configold);
      Logger.info("&ereplacing with a new config.yml");
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
      this.file = new File(getDataFolder(), this.path);
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
      this.file = new File(getDataFolder(), this.path);
    if (!this.file.exists())
      saveResources(this.path, false);
  }

  public void resetConfig() {
    if (this.file == null)
      this.file = new File(getDataFolder(), this.path);
    if (!this.file.exists())
      saveResources(this.path, true);
  }
}
