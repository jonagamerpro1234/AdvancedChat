package jss.advancedchat.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.interfaces.UpdateHelper;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker implements UpdateHelper {
  private AdvancedChat plugin;

  private final Logger logger = new Logger(plugin);

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
          if (inputStream != null)
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

  public void getUpdateVersion() {
    String version = getJson("https://songoda.com/api/v2/products/advancedchat-chat-related/");
    if (version.trim() == null || !version.trim().equalsIgnoreCase(this.plugin.version)) ;
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
