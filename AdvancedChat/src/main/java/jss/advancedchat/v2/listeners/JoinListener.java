package jss.advancedchat.listeners;

import jss.advancedchat.AdvancedChat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class JoinListener implements Listener {

    private AdvancedChat plugin = AdvancedChat.get();

    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent e){

        Player player = e.getPlayer();


    }

}
