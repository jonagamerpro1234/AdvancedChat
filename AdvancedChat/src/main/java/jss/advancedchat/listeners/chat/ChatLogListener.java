package jss.advancedchat.listeners.chat;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import jss.advancedchat.AdvancedChat;

public class ChatLogListener implements Listener{
	
	private AdvancedChat plugin = AdvancedChat.get();
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onChat(AsyncPlayerChatEvent e) {
		String message = e.getMessage();
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onCommand(PlayerCommandPreprocessEvent e) {
		String message = e.getMessage();
		
		List<String> ignoreCommands;
		List<String> checkCommands;
		
		
		
		
	}
	
}
