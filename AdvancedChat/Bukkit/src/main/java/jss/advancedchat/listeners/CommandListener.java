package jss.advancedchat.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Utils;

public class CommandListener implements Listener {

	private AdvancedChat plugin;

	public CommandListener(AdvancedChat plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onCommandChat(PlayerCommandPreprocessEvent e) {
		Player j = e.getPlayer();

		if ((j.isOp()) || (j.hasPermission("AdvancedChat.CommandBlocker.Bypass")))
			return;

		if (Settings.boolean_command_blocker) {
			if (Settings.boolean_command_blocker_disable_command) {
				for (String a : Settings.list_command_blocker_no_use) {
					if (e.getMessage().toLowerCase().contains(a)) {
						e.setCancelled(true);
						Utils.sendColorMessage(j, Settings.message_No_Use_Command.replace("<cmd>", a));
						break;
					}
				}
			}
			if (Settings.boolean_command_blocker_disable_command_mute) {
				if (PlayerManager.isMute(j)) {
					for (String a : Settings.list_command_blocker_no_use_mute) {
						if (e.getMessage().toLowerCase().contains(a)) {
							e.setCancelled(true);
							Utils.sendColorMessage(j, Settings.message_No_Use_Command_Mute.replace("<cmd>", a));
							break;
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onCommandDataLog(PlayerCommandPreprocessEvent e) {
		FileConfiguration config = plugin.getChatDataFile().getConfig();
		Player j = e.getPlayer();

		String date = Utils.getDate(System.currentTimeMillis());
		String time = Utils.getTime(System.currentTimeMillis());

		config.set(j.getName() + ".Log." + date + ".Command." + time, Utils.colorless(e.getMessage()));
		plugin.getChatDataFile().saveConfig();
	}

	@EventHandler
	public void onCommandLog(PlayerCommandPreprocessEvent e) {
		FileConfiguration config = plugin.getCommandLogFile().getConfig();
		Player j = e.getPlayer();

		String date = Utils.getDate(System.currentTimeMillis());
		String time = Utils.getTime(System.currentTimeMillis());

		config.set("Players." + j.getName() + ".Command." + date + "." + time, Utils.colorless(e.getMessage()));
		plugin.getCommandLogFile().saveConfig();
	}

}
