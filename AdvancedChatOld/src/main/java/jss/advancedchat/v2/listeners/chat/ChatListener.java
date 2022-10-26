package jss.advancedchat.listeners.chat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(@NotNull AsyncPlayerChatEvent e){
        String message = e.getMessage();
        Player p = e.getPlayer();


    }

}
