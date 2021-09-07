package jss.advancedchat.chat;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AdvancedChatEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled = false;
	private AsyncPlayerChatEvent a;
	private Player player;
	private String prefixFormat;
	private String messageFormat;
	private List<String> hover;
		
	public AdvancedChatEvent(Player player, String prefixFormat, String messageFormat, List<String> hover) {
		this.player = player;
		this.prefixFormat = prefixFormat;
		this.messageFormat = messageFormat;
		this.hover = hover;
	}

	public Player getPlayer() {
		return player;
	}

	public String getPrefixFormat() {
		return prefixFormat;
	}

	public String getMessageFormat() {
		return messageFormat;
	}
	
	public void setHover(List<String> hover) {
		this.hover = hover;
	}

	public List<String> getHover() {
		return hover;
	}
	
	public void setFormat(String prefix, String message) {
		Json json = new Json(this.player, prefix, message.replace("<message>", this.getMessage()));
		json.setHover(this.hover).sendDoubleToAll();
	}

	public String getMessage() {
		return a.getMessage();
	}

	public boolean isCancelled() {
		return this.cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
