package jss.advancedchat.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

import org.bukkit.Bukkit;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.common.update.UpdateSettings;


public class UpdateChecker {

    private AdvancedChat plugin;
    private int ID;

    public UpdateChecker(AdvancedChat plugin, int ID) {
        this.plugin = plugin;
        this.ID = ID;
    }
    
	public void getUpdateVersionSpigot(Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try (InputStream inputStream = new URL(UpdateSettings.SPIGOT_UPDATE_API + this.ID).openStream(); Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException e) {
                Logger.error("Could not check for updates: &c" + e.getMessage());
            }
        });
    }
   
}
