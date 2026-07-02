package jss.advancedchat.storage.json.manager;

import com.google.gson.JsonObject;
import jss.advancedchat.files.player.JsonPlayerFile;
import jss.advancedchat.storage.json.model.PlayerData;
import jss.advancedchat.storage.json.serializer.JsonSerialize;
import jss.advancedchat.utils.logger.Logger;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class JsonPlayerStorage {

    private final JsonPlayerFile jsonPlayerFile;

    public JsonPlayerStorage(JsonPlayerFile jsonPlayerFile) {
        this.jsonPlayerFile = jsonPlayerFile;
        Logger.debug("JsonPlayerStorage initialized with JsonPlayerFile instance.");
    }

    // Cargar los datos del jugador
    public PlayerData loadPlayerData(@NotNull Player player) {
        Logger.debug("Loading player data for player: " + player.getName());
        JsonObject json = jsonPlayerFile.loadPlayerConfig(player.getName());
        if (json != null) {
            Logger.debug("Player JSON data found, deserializing for player: " + player.getName());
            return JsonSerialize.fromJsonObject(json);
        }
        Logger.debug("No player JSON data found, returning empty PlayerData for player: " + player.getName());
        return new PlayerData(player); // Devuelve un PlayerData vacío si no se encuentra el archivo
    }

    // Guardar los datos del jugador
    public void savePlayerData(String playerName, PlayerData playerData) {
        Logger.debug("Saving player data for player: " + playerName);
        JsonObject jsonObject = JsonSerialize.toJsonObject(playerData);
        jsonPlayerFile.savePlayerConfig(playerName, jsonObject);
        Logger.debug("Player data saved for player: " + playerName);
    }
}
