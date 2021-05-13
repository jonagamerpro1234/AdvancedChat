package jss.advancedchat.config;

public class Files {
	
	//file names list
	
	public static String getName(FileName name) {
		switch (name) {
		case CONFIG:
			return "config.yml";
		case BUNGEECONFIG:
			return "bungee-config.yml";
		case CHATLOG:
			return "chat.yml";			
		case CHATLOGDATA:
			return "chat-log.data";
		case COMMANDLOG:
			return "command.yml";
		case COMMANDLOGDATA:
			return "command-log.data";
		case COLORGUI:
			return "color-gui.yml";
		case PLAYERGUI:
			return "player-gui.yml";
		case PLAYERDATA:
			return "players.data";
		}
		return null;
	}
	
	
	public enum FileName {
		CONFIG,
		BUNGEECONFIG,
		CHATLOG,
		CHATLOGDATA,
		COMMANDLOG,
		COMMANDLOGDATA,
		COLORGUI,
		PLAYERGUI,
		PLAYERDATA;
	}
	
	
}
