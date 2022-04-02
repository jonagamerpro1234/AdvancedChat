package jss.advancedchat.listeners;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;

public class EventLoader {

    private AdvancedChat plugin;
    private int taskId;
    private EventUtils eventsUtils = new EventUtils(plugin);

    public EventLoader(AdvancedChat plugin) {
        this.plugin = plugin;
    }
    
    public void runClearChat() {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        try {
            Long tick = (long) Settings.int_clearchat_tick;
            taskId = scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
                public void run() {
                    if (Settings.boolean_chatclear_autoclear) {
                        eventsUtils.getClearChatAction("server");
                        eventsUtils.getServerMessage();
                    } else {
                        scheduler.cancelTask(taskId);
                    }
                }
            }, 6000L, tick);

        } catch (Exception ex) {
        	Logger.error(ex);
            ex.printStackTrace();
        }
    }

}
