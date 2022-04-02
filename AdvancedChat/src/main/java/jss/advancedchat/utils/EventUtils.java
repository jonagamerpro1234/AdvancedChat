package jss.advancedchat.utils;

import jss.advancedchat.AdvancedChat;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public class EventUtils {
	
   private AdvancedChat plugin;

   public EventUtils(AdvancedChat plugin) {
      this.plugin = plugin;
   }

   public void initEvent(Listener... listeners) {
		for(Listener listener : listeners) {
			getEventManager().registerEvents(listener, plugin);
		}
	}

   public PluginManager getEventManager() {
      return Bukkit.getPluginManager();
   }

   public ConsoleCommandSender getConsoleSender() {
      return Bukkit.getConsoleSender();
   }

   public static ConsoleCommandSender getStaticConsoleSender() {
      return Bukkit.getConsoleSender();
   }

   public void getServerMessage() {
	   Utils.sendColorMessage(Utils.getPrefix(false) + Settings.message_ClearChat_Server);
   }

   public void getPlayerMessage(Player player) {
	   Utils.sendColorMessage(Utils.getPrefix(false) + Utils.getVar(player, Settings.message_ClearChat_Player));
   }

   public void getClearChatAction(String type) {
      if (type.equalsIgnoreCase("player")) {
         this.loopVoidChat(Settings.int_clearchat_lines);
      } else if (type.equalsIgnoreCase("server")) {
         this.loopVoidChat(Settings.int_clearchat_lines);
      }

   }

   public void loopVoidChat(int value) {
      try {
         for(int i = 0; i < value; ++i) {
            Bukkit.broadcastMessage(" ");
            if (i == value) {
               break;
            }
         }
      } catch (NullPointerException var3) {
         var3.printStackTrace();
      }

   }
}