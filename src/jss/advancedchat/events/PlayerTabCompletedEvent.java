package jss.advancedchat.events;

import java.util.Collection;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerTabCompletedEvent extends PlayerEvent implements Cancellable{

	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled = false;
	private String message;
	private String lastToken;
	private Collection<String> completions;

	public PlayerTabCompletedEvent(Player who, String message, String lastToken,
			Collection<String> completions) {
		super(who);
		this.message = message;
		this.lastToken = lastToken;
		this.completions = completions;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLastToken() {
		return lastToken;
	}

	public void setLastToken(String lastToken) {
		this.lastToken = lastToken;
	}

	public Collection<String> getCompletions() {
		return completions;
	}

	public void setCompletions(Collection<String> completions) {
		this.completions = completions;
	}

	public boolean isCancelled() {
		return cancelled;
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
