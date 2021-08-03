package jss.advancedchat.config;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.config.files.ConfigFile;
import jss.advancedchat.utils.interfaces.FileLoader;

public class ConfigFileManager {
	
	private AdvancedChat plugin;
	
	public ConfigFileManager(AdvancedChat plugin) {
		this.plugin = plugin;
	}
	
	//test code
	public void load() {
		initFiles(new ConfigFile(plugin, "config.yml"));
	}
	
	private void initFiles(FileLoader... fileLoaders) {
		for(FileLoader loader : fileLoaders ) {
			loader.create();
		}
	}
	
}
