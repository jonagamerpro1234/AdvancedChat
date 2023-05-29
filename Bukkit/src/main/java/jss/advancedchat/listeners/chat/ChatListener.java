package jss.advancedchat.listeners.chat;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(@NotNull AsyncPlayerChatEvent e){

        Player player = e.getPlayer();
        String baseMessage = e.getMessage();

    }



    public List<Player> getNearbyPlayers(@NotNull Player target, double range){
        List<Player> nearbyPlayer = new ArrayList<>();
        List<Entity> entities = target.getNearbyEntities(range,range,range);
        entities.forEach( entity -> { if (entity instanceof Player) nearbyPlayer.add((Player) entity); });
        return nearbyPlayer;
    }

}
