package jss.advancedchat.listeners.chat;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Util;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.jetbrains.annotations.NotNull;

public class ChatLogListener implements Listener {

    private final AdvancedChat plugin = AdvancedChat.get();

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onChat(@NotNull AsyncPlayerChatEvent e) {
        FileConfiguration config = plugin.getLogFile().getConfig(Util.getDate());
        Player p = e.getPlayer();
        String message = e.getMessage();

        if(Settings.chatlogs){
            if (Settings.chatlogs_log_chat){
                config.set(Util.getTime(), "[CHAT] - " + p.getName() + ": " + message);
                plugin.getLogFile().save();
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onCommand(@NotNull PlayerCommandPreprocessEvent e) {
        FileConfiguration config = plugin.getLogFile().getConfig(Util.getDate());
        Player p = e.getPlayer();
        String message = e.getMessage();

        if(Settings.chatlogs){
            if (Settings.chatlogs_log_command){
                for (String ignore : Settings.list_chatlogs_no_register_commands){
                    if (message.equalsIgnoreCase(ignore)){
                        return;
                    }
                }
                config.set(Util.getTime(), "[COMMAND] - " + p.getName() + ": " + message);
                plugin.getLogFile().save();
            }
        }

    }

}
