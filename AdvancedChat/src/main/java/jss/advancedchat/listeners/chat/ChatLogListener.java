package jss.advancedchat.listeners.chat;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.config.ChatDataFile;
import jss.advancedchat.config.ChatLogFile;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Util;

public class ChatLogListener implements Listener{
	
	private AdvancedChat plugin = AdvancedChat.get();

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void chatDataLog(AsyncPlayerChatEvent e) {
		ChatDataFile chatDataFile = plugin.getChatDataFile();
		FileConfiguration config = chatDataFile.getConfig();
		Player j = e.getPlayer();

		String date = Util.getDate(System.currentTimeMillis());
		String time = Util.getTime(System.currentTimeMillis());

		config.set("Players." + j.getName() + ".Log." + date + ".Chat." + time, Util.colorless(e.getMessage()));
		chatDataFile.saveConfig();
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void chatLog(AsyncPlayerChatEvent e) {
		ChatLogFile chatLogFile = plugin.getChatLogFile();
		FileConfiguration config = chatLogFile.getConfig();
		Player j = e.getPlayer();

		String date = Util.getDate(System.currentTimeMillis());
		String time = Util.getTime(System.currentTimeMillis());
		
		if(Settings.chatlogs_log_chat) {
			config.set("Players." + j.getName() + ".Chat." + date + "." + time, Util.colorless(e.getMessage()));
			chatLogFile.saveConfig();
		}
	}
	
}
