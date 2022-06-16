package jss.advancedchat.common;

import java.sql.Connection;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.bungee.AdvancedChatBungee;

public class PluginHelper {
	
	@SuppressWarnings("unused")
	private AdvancedChat pluginSpigot = AdvancedChat.get(); 
	private AdvancedChatBungee pluginBungee = AdvancedChatBungee.get();
	private Connection connection;
	
	public void checkPlatform(String platform) {
		if(platform.equalsIgnoreCase("Spigot")) {
			//connection = pluginSpigot.getConnection();
		}else if(platform.equalsIgnoreCase("BungeeCord")) {
			connection = pluginBungee.getConnection();
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
}
