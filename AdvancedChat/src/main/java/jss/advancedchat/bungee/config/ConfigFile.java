package jss.advancedchat.bungee.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;

import jss.advancedchat.bungee.AdvancedChatBungee;
import jss.advancedchat.bungee.utils.file.FileManagerBunge;
import net.md_5.bungee.config.Configuration;

public class ConfigFile extends FileManagerBunge{
	
	private AdvancedChatBungee plugin;
	private Configuration config;
	private File file;
	
	public ConfigFile(AdvancedChatBungee plugin) {
		super(plugin);
		this.plugin = plugin;
		this.file = null;
		this.config = null;
	}
	
	public void create() {
		this.file = new File(getDataFolder(), "bungee-config.yml");
		if(!this.file.exists()) {
			try {
				Files.copy(plugin.getResourceAsStream("bungee-config.yml"), file.toPath(), new CopyOption[0]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		reloadConfig();
	}
	
	public void reloadConfig() {
		if(this.config == null) {
			this.file = new File(getDataFolder(), "bungee-config.yml");
		}
		try {
			this.config = getConfigProvider().load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Configuration getConfig() {
		if(this.config == null) {
			reloadConfig();
		}
		return this.config;
	}
	
	public void saveConfig() {
		if(this.config == null) {
			reloadConfig();
		}
		try {
			getConfigProvider().save(this.config, this.file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
