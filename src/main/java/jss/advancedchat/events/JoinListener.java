package jss.advancedchat.events;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.EventsUtils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener  implements Listener {
	
	private AdvancedChat plugin;
	private EventsUtils eventsUtils = new EventsUtils(plugin);
	
	public JoinListener(AdvancedChat plugin) {
		this.plugin = plugin;
		eventsUtils.addEventList(this);
	}
	
	@EventHandler
	public void sendUpdatePlayer(PlayerJoinEvent e) {
		Player j = e.getPlayer();
		if ((!j.hasPermission("AdvancedChat.Update.Notify")) || (!j.isOp())) {
			return;
		}
	}
}
