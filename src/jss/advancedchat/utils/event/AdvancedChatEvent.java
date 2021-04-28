package jss.advancedchat.utils.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class AdvancedChatEvent extends PlayerEvent implements Cancellable{

	private boolean cancelled;
	private static final HandlerList handlers = new HandlerList();
	private String message;
	private String format;
	private String hovermessage;
	private String clickmessage;
	
	public AdvancedChatEvent(Player who, String message, String format, String hovermessage, String clickmessage) {
		super(who);
		this.cancelled = false;
		this.message = message;
		this.format = format;
		this.hovermessage = hovermessage;
		this.clickmessage = clickmessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getHovermessage() {
		return hovermessage;
	}

	public void setHovermessage(String hovermessage) {
		this.hovermessage = hovermessage;
	}

	public String getClickmessage() {
		return clickmessage;
	}

	public void setClickmessage(String clickmessage) {
		this.clickmessage = clickmessage;
	}

	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	@Override
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}
	
}
