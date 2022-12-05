package jss.advancedchat.utils;

import jss.advancedchat.AdvancedChat;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class EventUtils {
  private final AdvancedChat plugin;

  public EventUtils(AdvancedChat plugin) {
    this.plugin = plugin;
  }

  public static CommandSender getStaticConsoleSender() {
    return Bukkit.getConsoleSender();
  }

  public void addEventList(Listener listener) {
    getEventManager().registerEvents(listener, this.plugin);
  }

  public PluginManager getEventManager() {
    return Bukkit.getPluginManager();
  }

  public ConsoleCommandSender getConsoleSender() {
    return Bukkit.getConsoleSender();
  }

  public void getServerMessage(FileConfiguration config) {
    String path = "Settings.Use-Default-Prefix";
    String text = config.getString("AdvancedChat.ClearChat-Server");
    String prefix = config.getString("Settings.Prefix");
    if (config.getString(path).equals("true")) {
      Utils.sendColorMessage(Utils.getPrefixPlayer() + text);
    } else if (config.getString(path).equals("false")) {
      Utils.sendColorMessage(prefix + " " + text);
    }
  }

  public void getPlayerMessage(Player player, FileConfiguration config) {
    String path = "Settings.Use-Default-Prefix";
    String text = config.getString("AdvancedChat.ClearChat-Player");
    String prefix = config.getString("Settings.Prefix");
    text = Utils.getVar(player, text);
    if (config.getString(path).equals("true")) {
      Utils.sendColorMessage(Utils.getPrefixPlayer() + " " + text);
    } else if (config.getString(path).equals("false")) {
      Utils.sendColorMessage(prefix + " " + text);
    }
  }

  public void getClearChatAction(String type) {
    if (type.equalsIgnoreCase("player")) {
      loopVoidChat(100);
    } else if (type.equalsIgnoreCase("server")) {
      loopVoidChat(100);
    }
  }

  public void loopVoidChat(int value) {
    try {
      for (int i = 0; i < value; i++) {
        Bukkit.broadcastMessage(" ");
        if (i == value)
          break;
      }
    } catch (NullPointerException var3) {
      var3.printStackTrace();
    }
  }
}
