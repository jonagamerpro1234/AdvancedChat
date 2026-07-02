package jss.advancedchat.listeners;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.ConfigManager;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.listeners.utils.EventUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitScheduler;

public class EventLoader {
    private AdvancedChat plugin;

    private int taskId;

    private final EventUtils eventsUtils = new EventUtils(this.plugin);

    public EventLoader(AdvancedChat plugin) {
        this.plugin = plugin;
    }

    public void runClearChat() {
        final FileConfiguration config = ConfigManager.getConfig();
        final BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        try {
            this.taskId = scheduler.scheduleSyncRepeatingTask(this.plugin, () -> {
                if (Settings.boolean_chatclear_autoclear) {
                    EventLoader.this.eventsUtils.getClearChatAction("server");
                    EventLoader.this.eventsUtils.getServerMessage(config);
                } else {
                    scheduler.cancelTask(EventLoader.this.taskId);
                }
            }, Settings.long_clearchat_start_tick, Settings.long_clearchat_tick);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }
}
