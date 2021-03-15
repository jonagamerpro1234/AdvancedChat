package jss.advancedchat.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.function.Consumer;

import org.bukkit.Bukkit;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Logger.Level;
import jss.advancedchat.utils.interfaces.UpdateHelper;


public class UpdateChecker implements UpdateHelper {

    private AdvancedChat plugin;
    private Logger logger = new Logger(plugin);
    private int ID;

    public UpdateChecker(AdvancedChat plugin, int ID) {
        this.plugin = plugin;
        this.ID = ID;
    }

    public void getUpdateVersion(Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try (InputStream inputStream = new URL(UpdateSettings.SPIGOT_UPDATE_API + this.ID).openStream(); Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException e) {
                logger.Log(Level.INFO, "Could not check for updates:&c" + e.getMessage());
            }
        });
    }
    
    public void getUpdateVersion() {
        String version = getJson("https://songoda.com/api/v2/products/advancedchat-chat-related/");
        if (version.trim() != null && !version.trim().equalsIgnoreCase(plugin.version)) {
           
        }
    }

    public String getJson(String arg) {
        try {
            URL url = new URL(arg);
            URLConnection connection = url.openConnection();
            BufferedReader buffered = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            if ((line = buffered.readLine()) != null) {
                JsonElement jsonElement = (new JsonParser()).parse(line).getAsJsonObject().get("data").getAsJsonObject().get("versions").getAsJsonArray().get(0);
                String latest = jsonElement.getAsJsonObject().get("version").getAsString();
                return latest;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
