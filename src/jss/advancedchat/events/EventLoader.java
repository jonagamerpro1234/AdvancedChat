package jss.advancedchat.events;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitScheduler;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.EventUtils;

public class EventLoader {

    private AdvancedChat plugin;
    private int taskId;
    private EventUtils eventsUtils = new EventUtils(plugin);

    public EventLoader(AdvancedChat plugin) {
        this.plugin = plugin;
    }

    public void runClearChat() {
        FileConfiguration config = plugin.getConfig();
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        try {
            Long tick = (long) config.getInt("Settings.ClearChat.Tick");
            String path = "Settings.ClearChat.AutoClear";
            taskId = scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
                public void run() {
                    if (config.getString(path).equals("true")) {
                        eventsUtils.getClearChatAction("server");
                        eventsUtils.getServerMessage(config);
                    } else {
                        scheduler.cancelTask(taskId);
                    }
                }
            }, 6000L, tick);

        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

}
