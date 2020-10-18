package jss.advancedchat.events;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.PlayerData;
import jss.advancedchat.utils.UpdateChecker;
import jss.advancedchat.utils.EventsUtils;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener extends EventsUtils implements Listener {
	
	private AdvancedChat plugin;

	private CommandSender c = getConsoleSender();
	
	public JoinListener(AdvancedChat plugin) {
		this.plugin = plugin;
		EventsUtils.getManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void tes(PlayerJoinEvent e) {
		Player j = e.getPlayer();
		PlayerData playerdata = plugin.getPlayerData();
		FileConfiguration config = playerdata.getConfig();
		
		String path = "Players-Data.";
		
		config.createSection("Players-Data");
		config.set(path+j.getName()+".UUID", j.getUniqueId().toString());
		config.set(path+j.getName()+".Color-Chat", "NONE");
		config.set(path+j.getName()+".Mute", false);
		config.set(path+j.getName()+".Mute-Reason", "none");
		config.set(path+j.getName()+".Mute-Time", "none");
		plugin.addPlayerList(j, j.getUniqueId(), false, "none");
		playerdata.saveConfig();
		
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
