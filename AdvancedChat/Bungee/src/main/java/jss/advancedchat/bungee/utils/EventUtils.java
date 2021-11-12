package jss.advancedchat.bungee.utils;
import jss.advancedchat.bungee.AdvancedChatBungee;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.PluginManager;

public class EventUtils {
	
   private AdvancedChatBungee plugin;

   public EventUtils(AdvancedChatBungee plugin) {
      this.plugin = plugin;
   }

   public void initEvent(Listener... listeners) {
		for(Listener listener : listeners) {
			getEventManager().registerListener(plugin, listener);
		}
	}

   public PluginManager getEventManager() {
      return plugin.getPluginManager();
   }

   public CommandSender getConsoleSender() {
      return ProxyServer.getInstance().getConsole();
   }

   public static CommandSender getStaticConsoleSender() {
	   return ProxyServer.getInstance().getConsole();
   }

}