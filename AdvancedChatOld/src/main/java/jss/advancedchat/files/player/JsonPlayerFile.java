package jss.advancedchat.files.player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.logger.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonPlayerFile {

    private final AdvancedChat plugin;
    private final String directory = "Players"; // Directorio donde guardamos los archivos JSON

    public JsonPlayerFile(AdvancedChat plugin) {
        this.plugin = plugin;
    }

    // Método para obtener el archivo de un jugador
    public File getPlayerFile(String playerName) {
        return new File(plugin.getDataFolder(), directory + File.separator + playerName + ".json");
    }

    // Método para cargar un archivo JSON
    public JsonObject loadPlayerConfig(String playerName) {
        File playerFile = getPlayerFile(playerName);
        if (!playerFile.exists()) {
            Logger.debug("Player file does not exist, creating new one for " + playerName);
            return null;
        }

        try (FileReader reader = new FileReader(playerFile)) {
            JsonObject config = JsonParser.parseReader(reader).getAsJsonObject();
            Logger.debug("Loaded player config for " + playerName);
            return config;
        } catch (IOException e) {
            Logger.debug("Error loading player config: " + e.getMessage());
            return null;
        }
    }

    // Método para guardar los cambios en el archivo JSON
    public void savePlayerConfig(String playerName, JsonObject config) {
        File playerFile = getPlayerFile(playerName);
        try (FileWriter writer = new FileWriter(playerFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();//new Gson();
            gson.toJson(config, writer);
            Logger.debug("Player config saved for " + playerName);
        } catch (IOException e) {
            Logger.debug("Error saving player config: " + e.getMessage());
        }
    }

    //Método para crear un archivo JSON con los valores predeterminados
    private @NotNull JsonObject createDefaultConfig(String playerName) {
        JsonObject config = new JsonObject();
        config.addProperty("Name", playerName);
        config.addProperty("UUID", "default-uuid");  // Aquí deberías agregar la UUID del jugador
        config.addProperty("Chat-Message.Color", "#FFFFFF"); // Valor predeterminado para color
        config.addProperty("Chat-Message.Rainbow", "rainbow_1");
        config.addProperty("Chat-Message.Special-Color-Codes", "none");
        config.addProperty("Chat-Type", "color");
        config.addProperty("Is-Mute", false);
        config.addProperty("Group", "default-group");
        config.addProperty("Other-Settings.Mention", true);
        config.addProperty("Other-Settings.Low-Mode", false);
        config.addProperty("Other-Settings.Chat", true);
        config.addProperty("Other-Settings.Msg", true);

        savePlayerConfig(playerName, config);
        return config;
    }
}
