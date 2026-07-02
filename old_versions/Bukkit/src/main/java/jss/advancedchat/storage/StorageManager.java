package jss.advancedchat.storage;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.storage.json.JsonStorage;
import jss.advancedchat.storage.mysql.MySqlStorage;
import jss.advancedchat.storage.utils.IStorage;
import jss.advancedchat.storage.utils.StorageType;
import jss.advancedchat.storage.yaml.YamlStorage;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class StorageManager {

    private final AdvancedChat plugin;
    private IStorage storage;
    private final Map<String, PlayerData> playerDataCache;
    private StorageType storageType;

    public StorageManager(AdvancedChat plugin) {
        this.plugin = plugin;
        this.storageType = StorageType.YAML;
        this.playerDataCache = new HashMap<>();
        switchStorage(storageType);
    }

    public void switchStorage(@NotNull StorageType storageType){
        this.storageType = storageType;
        switch (storageType){
            case YAML:
                storage = new YamlStorage(plugin);
                break;
            case JSON:
                storage = new JsonStorage(plugin);
                break;
            case MYSQL:
                storage = new MySqlStorage();
            default:
                throw new IllegalArgumentException("Invalid storage type: " + storageType);
        }
    }

    public PlayerData getPlayerData(String playerName) {
        if (playerDataCache.containsKey(playerName)) {
            return playerDataCache.get(playerName);
        }

        PlayerData playerData = storage.loadPlayerData(playerName);
        playerDataCache.put(playerName, playerData);
        return playerData;
    }

    public void savePlayerData(PlayerData playerData) {
        storage.savePlayerData(playerData);
        playerDataCache.put(playerData.getName(), playerData);
    }

    public void reloadPlayerData(String playerName) {
        PlayerData playerData = storage.loadPlayerData(playerName);
        playerDataCache.put(playerName, playerData);
    }

}
