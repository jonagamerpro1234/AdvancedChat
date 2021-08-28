package jss.advancedchat.manager;

import java.util.Iterator;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;

import jss.advancedchat.AdvancedChat;

public class ChatManager {
	
	private AdvancedChat plugin;

    
    public ChatManager(AdvancedChat plugin) {
        this.plugin = plugin;
    }
        
	public String getMessage() {
		FileConfiguration config = plugin.getChatDataFile().getConfig();
		
		Set<String> sections = config.getKeys(false);
		Iterator<String> section = sections.iterator();
		
		while(true) {
			while(section.hasNext()) {
				String key = (String) section.next();
				String message = config.getString(key + ".Log." +  key + ".Chat." + key );
				return message;
			}
		}
    }

    /*public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }*/

}
