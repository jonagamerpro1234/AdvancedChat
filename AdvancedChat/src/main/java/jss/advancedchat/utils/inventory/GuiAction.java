package jss.advancedchat.utils.inventory;

import org.bukkit.event.Event;

public interface GuiAction<T extends Event> {

	public void execute(final T event);
	
}
