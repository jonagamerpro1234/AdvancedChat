package jss.advancedchat.test;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.json.manager.JsonPlayerStorage;
import jss.advancedchat.storage.json.model.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class JoinListenerTest implements Listener {

    private final AdvancedChat plugin = AdvancedChat.get();

    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent e){
        Player player = e.getPlayer();

        JsonPlayerStorage storage = new JsonPlayerStorage(plugin.getJsonPlayerFile());
        PlayerManager manager = new PlayerManager(storage);

        if (manager.loadPlayerData(player) == null){
            storage.savePlayerData(player.getName(), new PlayerData(player));
        }

    }

    @EventHandler
    public void onQuit(@NotNull PlayerQuitEvent e){
        Player player = e.getPlayer();

        JsonPlayerStorage storage = new JsonPlayerStorage(plugin.getJsonPlayerFile());
        PlayerManager manager = new PlayerManager(storage);

        PlayerData data = manager.loadPlayerData(player);

        if(manager.loadPlayerData(player) != null){
            manager.savePlayerData(player.getName(), data);
        }else {
            manager.savePlayerData(player.getName(), new PlayerData(player));
        }
    }

}
