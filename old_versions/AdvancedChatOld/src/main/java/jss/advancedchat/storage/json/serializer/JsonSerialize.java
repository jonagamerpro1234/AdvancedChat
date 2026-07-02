package jss.advancedchat.storage.json.serializer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jss.advancedchat.storage.json.model.PlayerData;
import jss.advancedchat.utils.logger.Logger;

public class JsonSerialize {

    private static final Gson gson = new Gson();

    // Método para convertir PlayerData a JSON
    public static String serialize(PlayerData playerData) {
        Logger.debug("Serializing PlayerData object to JSON string.");
        String json = gson.toJson(playerData);
        Logger.debug("Serialization complete.");
        return json;
    }

    // Método para convertir JSON a PlayerData
    public static PlayerData deserialize(String json) {
        Logger.debug("Deserializing JSON string to PlayerData object.");
        PlayerData playerData = gson.fromJson(json, PlayerData.class);
        Logger.debug("Deserialization complete.");
        return playerData;
    }

    // Método para convertir un JsonObject a PlayerData
    public static PlayerData fromJsonObject(JsonObject jsonObject) {
        Logger.debug("Deserializing JsonObject to PlayerData object.");
        PlayerData playerData = gson.fromJson(jsonObject, PlayerData.class);
        Logger.debug("Deserialization from JsonObject complete.");
        return playerData;
    }

    // Método para convertir PlayerData a JsonObject
    public static JsonObject toJsonObject(PlayerData playerData) {
        Logger.debug("Serializing PlayerData object to JsonObject.");
        JsonObject jsonObject = gson.toJsonTree(playerData).getAsJsonObject();
        Logger.debug("Serialization to JsonObject complete.");
        return jsonObject;
    }
}
