package jss.advancedchat.config.log;

import java.io.File;

import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Util;

public class LogFile {

	private AdvancedChat plugin;
	private String pathFile = Util.getDate();
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

		if (config == null) {
			reload();
		}
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
