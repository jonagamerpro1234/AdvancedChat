package jss.advancedchat.utils.update;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Logger;
import org.bukkit.Bukkit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker {
    private final AdvancedChat plugin;

    private final Logger logger = new Logger();

    private final int ID;

    public UpdateChecker(AdvancedChat plugin, int ID) {
        this.plugin = plugin;
        this.ID = ID;
    }

    public void getUpdateVersion(Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try {
                InputStream inputStream = (new URL(UpdateSettings.SPIGOT_UPDATE_API + this.ID)).openStream();
                try {
                    Scanner scanner = new Scanner(inputStream);
                    try {
                        if (scanner.hasNext())
                            consumer.accept(scanner.next());
                        scanner.close();
                    } catch (Throwable throwable) {
                        try {
                            scanner.close();
                        } catch (Throwable throwable1) {
                            throwable.addSuppressed(throwable1);
                        }
                        throw throwable;
                    }
                    inputStream.close();
                } catch (Throwable throwable) {
                    if (inputStream != null)
                        try {
                            inputStream.close();
                        } catch (Throwable throwable1) {
                            throwable.addSuppressed(throwable1);
                        }
                    throw throwable;
                }
            } catch (IOException e) {
                this.logger.Log(Logger.Level.INFO, "Could not check for updates:&c" + e.getMessage());
            }
        });
    }

    /*public void getUpdateVersion() {
        String version = getJson("https://songoda.com/api/v2/products/advancedchat-chat-related/");
        if (!version.trim().equalsIgnoreCase(this.plugin.version)) ;
    }*/

    public String getJson(String arg) {
        try {
            URL url = new URL(arg);
            URLConnection connection = url.openConnection();
            BufferedReader buffered = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            if ((line = buffered.readLine()) != null) {
                JsonElement jsonElement = JsonParser.parseString(line).getAsJsonObject().get("data").getAsJsonObject().get("versions").getAsJsonArray().get(0);
                return jsonElement.getAsJsonObject().get("version").getAsString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
