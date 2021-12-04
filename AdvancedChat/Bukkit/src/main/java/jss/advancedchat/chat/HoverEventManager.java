package jss.advancedchat.chat;

import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;

public class HoverEventManager {
	
	@SuppressWarnings("deprecation")
	public static HoverEvent getLegacyShowText(BaseComponent[] text) {
		return new HoverEvent(Action.SHOW_TEXT, text);
	}
	
	public static HoverEvent getShowText(String text) {
		return new HoverEvent(Action.SHOW_TEXT, new Text(text));
	}
}
