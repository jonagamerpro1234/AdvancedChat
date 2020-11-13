package jss.advancedchat.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

import org.bukkit.Bukkit;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Logger.Level;
import jss.advancedchat.utils.interfaces.UpdateHelper;


public class UpdateChecker implements UpdateHelper{

	private AdvancedChat plugin;
	private Logger logger = new Logger(plugin);
	private int ID;
	
	public UpdateChecker(AdvancedChat plugin) {
		this.plugin = plugin;
	}
	
	
	public void getUpdateVersion(Consumer<String> consumer) {
		Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
			try(InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.ID).openStream(); Scanner scanner = new Scanner(inputStream)){
				if(scanner.hasNext()) {
					consumer.accept(scanner.next());
				}
			}catch(IOException e) {
				logger.Log(Level.INFO, "Could not check for updates:&c" + e.getMessage());
			}
		});
	}
	
}
