package jss.advancedchat.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.hooks.LuckPermsHook;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;

public class EventLoader {

	private AdvancedChat plugin = AdvancedChat.get();
	private int taskId;
	private EventUtils eventsUtils = new EventUtils(plugin);

	public void runClearChat() {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		try {
			Long tick = (long) Settings.int_clearchat_tick;
			taskId = scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
				public void run() {
					if (Settings.boolean_chatclear_autoclear) {
						eventsUtils.getClearChatAction();
						eventsUtils.getServerMessage();
					} else {
						scheduler.cancelTask(taskId);
					}
				}
			}, 6000L, tick);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	int taskGroupId;
	
	public void runGroupChecker() {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		try {
			for(Player p : Bukkit.getOnlinePlayers()) {
				PlayerManager playerManager = new PlayerManager(p);
				taskGroupId = scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
					public void run() {
						if(Settings.hook_luckperms_autoupdate_group) {
							if(!playerManager.getGroup().equalsIgnoreCase(LuckPermsHook.getApi().getUserManager().getUser(p.getName()).getPrimaryGroup())) {
								playerManager.setGroup(LuckPermsHook.getApi().getUserManager().getUser(p.getName()).getPrimaryGroup());
							}else {
								Logger.debug("&eThe player already has the same group!");
							}
						}else {
							scheduler.cancelTask(taskGroupId);
						}
					}
				}, 0L, 600L);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
