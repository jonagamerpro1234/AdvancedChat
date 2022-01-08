package jss.advancedchat.chat;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;

public class ClickEventManager {
	
	public static ClickEvent getRunCommandEvent(String command) {
		return new ClickEvent(Action.RUN_COMMAND, command);
	}
	
	public static ClickEvent getUrlEvent(String url) {
		return new ClickEvent(Action.OPEN_URL, url);
	}
	
	public static ClickEvent getSuggestCommand(String suggest) {
		return new ClickEvent(Action.SUGGEST_COMMAND, suggest);
	}
}
