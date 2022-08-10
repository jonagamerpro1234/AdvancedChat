package jss.advancedchat.listeners.chat;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

public class ChatLogListener implements Listener {



    public void onLogChat(@NotNull AsyncPlayerChatEvent e){
        Player player = e.getPlayer();

    }

    public void onLogCommand(){

    }

}
