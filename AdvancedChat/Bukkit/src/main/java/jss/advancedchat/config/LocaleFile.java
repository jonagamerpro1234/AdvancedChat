package jss.advancedchat.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.file.FileManager;

public class LocaleFile extends FileManager {
	
	private File file;
	private FileConfiguration config;
	private String folderPath;
	
	public LocaleFile(AdvancedChat plugin, String folder) {
		super(plugin);
		this.file = null;
		this.config = null;
		this.folderPath = folder;
	}
	
	public void setupLocale() {
		this.file = new File(getDataFolder() + File.separator + folderPath, "message_" + Settings.locale + ".yml");
		if(!this.file.exists()) {
			
		}
	}
	
	public FileConfiguration get() {
		if(this.config == null) {
			this.reload();
		}
		return this.config;
	}
	
	public void reload() {
		if(this.config == null) {
			this.file = new File(getDataFolder() + File.separator + folderPath, "message_" + Settings.locale + ".yml");
		}
		this.config = YamlConfiguration.loadConfiguration(this.file);
		Reader dcs;
		try {
			dcs = new InputStreamReader(getResources(folderPath + File.separator + "message_" + Settings.locale + ".yml"));
			if(dcs != null) {
				YamlConfiguration dc = YamlConfiguration.loadConfiguration(dcs);
				this.config.setDefaults(dc);
			}
		}catch(Exception e){
			Logger.error(e.getCause());
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			this.config.save(this.folderPath + File.separator + "message_" + Settings.locale + ".yml");
		} catch (IOException e) {
			Logger.error(e.getCause());
			e.printStackTrace();
		}
	}
	
	public void saveDefault() {
		if(this.file == null) {
			this.file = new File(getDataFolder() + File.separator + folderPath, "message_" + Settings.locale + ".yml");
		}
		if(!this.file.exists()) {
			saveResources(folderPath + File.separator + "message_" + Settings.locale + ".yml", false);
		}
	}
}
