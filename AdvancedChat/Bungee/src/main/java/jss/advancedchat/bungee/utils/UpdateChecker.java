package jss.advancedchat.bungee.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

import jss.advancedchat.bungee.AdvancedChatBungee;
import jss.advancedchat.bungee.utils.Logger.Level;
import jss.advancedchat.update.UpdateSettings;

public class UpdateChecker  {

    private AdvancedChatBungee plugin;
    private Logger logger = new Logger(plugin);

    public UpdateChecker(AdvancedChatBungee plugin) {
		this.plugin = plugin;
	}
    
	public void getUpdateVersionBungee(Consumer<String> consumer) {
		plugin.getProxy().getScheduler().runAsync(plugin, () ->{
            try (InputStream inputStream = new URL(UpdateSettings.SPIGOT_UPDATE_API + UpdateSettings.ID[0]).openStream(); Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException e) {
                logger.Log(Level.INFO, "Could not check for updates: &c" + e.getMessage());
            }	
		});
    }
    
}
