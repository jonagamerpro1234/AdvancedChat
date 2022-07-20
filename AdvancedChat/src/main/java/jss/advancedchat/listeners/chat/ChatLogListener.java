package jss.advancedchat.listeners.chat;

import jss.advancedchat.AdvancedChat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChatLogListener implements Listener {

    private final AdvancedChat plugin = AdvancedChat.get();

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onChat(@NotNull AsyncPlayerChatEvent e) {
        String message = e.getMessage();
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onCommand(@NotNull PlayerCommandPreprocessEvent e) {
        String message = e.getMessage();

        List<String> ignoreCommands;
        List<String> checkCommands;


    }

}
