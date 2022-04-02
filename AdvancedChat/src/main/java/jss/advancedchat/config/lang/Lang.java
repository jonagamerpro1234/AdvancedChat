package jss.advancedchat.config.lang;

import java.io.File;
import java.util.Locale;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Logger;

public class Lang {
	
	private AdvancedChat plugin = AdvancedChat.get();
	private FileConfiguration config;
	private File file;
	private Locale locale;
	private String localeName;
	private int index;
	
	public Lang(String localeName, int index) {
		this.localeName = localeName;
		this.index = index;
		getLang(localeName);
		load();
		locale = new Locale(localeName.substring(9, 11), localeName.substring(12, 14));
	}
	
	public FileConfiguration getLang(String langname) {
		if(this.config == null) {
			reloadLang(langname);
		}
		return config;
	}
	
	public void reloadLang(String langname) {
		File dir = new File(plugin.getDataFolder() + File.separator + "lang");
		
		if(!dir.exists()) {
			dir.mkdir();
		}
		
		if(file == null) {
			file = new File(dir.getPath(), "messages_" + langname + ".yml");
		}
		
		if(!file.exists()) {
			config = YamlConfiguration.loadConfiguration(file);
		}else {
			if(plugin.getResource("lang" + File.separator +  "messages_" + langname + ".yml") == null) {
				plugin.saveResource("lang" + File.separator +  "messages_" + langname + ".yml", true);
				file = new File(plugin.getDataFolder() + File.separator + "lang", "messages_" + langname + ".yml");
				config = YamlConfiguration.loadConfiguration(file);
			}else {
				Logger.error("");
			}
		}
	}
	
	public void load() {
		
	}
	
	public String getLocaleName() {
		return this.localeName;
	}

	public String getLanguageName() {
		if (locale == null) {
			return "unknown";
		}
		return locale.getDisplayLanguage(locale);
	}

	public String getCountryName() {
		if (locale == null) {
			return "unknown";
		}
		return locale.getDisplayCountry(locale);
	}

	public int getIndex() {
		return index;
	}
}
