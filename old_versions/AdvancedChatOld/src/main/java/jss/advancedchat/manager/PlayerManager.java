package jss.advancedchat.manager;

import jss.advancedchat.storage.json.manager.JsonPlayerStorage;
import jss.advancedchat.storage.json.model.PlayerData;
import org.bukkit.entity.Player;

public class PlayerManager {

    private final JsonPlayerStorage jsonPlayerStorage;

    public PlayerManager(JsonPlayerStorage jsonPlayerStorage) {
        this.jsonPlayerStorage = jsonPlayerStorage;
    }

    public PlayerData loadPlayerData(Player player) {
        return jsonPlayerStorage.loadPlayerData(player);
    }

    public void savePlayerData(String playerName, PlayerData playerData) {
        jsonPlayerStorage.savePlayerData(playerName, playerData);
    }
}
