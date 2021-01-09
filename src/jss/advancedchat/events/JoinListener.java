package jss.advancedchat.events;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.PlayerDataFile;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.UpdateChecker;
import jss.advancedchat.utils.Utils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
	
	private AdvancedChat plugin;
	private EventUtils eventsUtils = new EventUtils(plugin);
	
	public JoinListener(AdvancedChat plugin) {
		this.plugin = plugin;
		eventsUtils.getEventManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onJoinPlayer(PlayerJoinEvent e) {
		PlayerDataFile dataFile = plugin.getPlayerDataFile();
		FileConfiguration config = dataFile.getConfig();
		Player j = e.getPlayer();
		
		if(!config.contains("Players."+j.getName())) {
			config.set("Players."+j.getName()+".UUID", j.getUniqueId().toString());
			config.set("Players."+j.getName()+".Color", "WHITE");
			config.set("Players."+j.getName()+".Mute", false);
			dataFile.saveConfig();
			//Utils.sendColorMessage(eventsUtils.getConsoleSender(), "&aadd " + j.getName());
		}else {
			//Utils.sendColorMessage(eventsUtils.getConsoleSender(), "&cya existe " + j.getName());
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onUpdatePlayer(PlayerJoinEvent e) {
		FileConfiguration config = plugin.getConfigfile().getConfig();
		Player j = e.getPlayer();
		
		if(config.getString("Settings.Update").equals("true")) {
			if (j.isOp() || j.hasPermission("AdvancedChat.Update.Notify")) {
				
				new UpdateChecker(AdvancedChat.getPlugin(), 83889).getUpdateVersion(version ->{
					if(!AdvancedChat.getPlugin().getDescription().getVersion().equalsIgnoreCase(version)) {				
						TextComponent component = new TextComponent(Utils.color(Utils.getPrefixPlayer() + " &aThere is a new version available for download"));
						component.setClickEvent(new ClickEvent(Action.OPEN_URL, Settings.URL_PLUGIN));
						component.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Utils.color("&6Click on this message to copy the link")).create()));
						j.spigot().sendMessage(component);
					}
				});

			}
		}
		


	}
}
