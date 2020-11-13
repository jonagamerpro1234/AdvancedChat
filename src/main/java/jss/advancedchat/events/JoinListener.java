package jss.advancedchat.events;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.UpdateChecker;
import jss.advancedchat.utils.EventsUtils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
@SuppressWarnings("unused")
public class JoinListener extends EventsUtils implements Listener {
	
	private AdvancedChat plugin;
	private CommandSender c = getConsoleSender();
	
	public JoinListener(AdvancedChat plugin) {
		this.plugin = plugin;
		EventsUtils.getManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void sendUpdatePlayer(PlayerJoinEvent e) {
		Player j = e.getPlayer();
		UpdateChecker update = new UpdateChecker(this.plugin);
		if ((!j.hasPermission("AdvancedChat.Update.Notify")) || (!j.isOp())) {
			return;
		}
		update.Update(j);
	}
}
