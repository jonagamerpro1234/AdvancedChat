package jss.advancedchat.listeners;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.config.ChatDataFile;
import jss.advancedchat.config.CommandLogFile;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Utils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class CommandListener implements Listener {
  private AdvancedChat plugin;

  private final EventUtils eventsUtils = new EventUtils(this.plugin);

  public CommandListener(AdvancedChat plugin) {
    this.plugin = plugin;
    this.eventsUtils.getEventManager().registerEvents(this, plugin);
  }

  @EventHandler
  public void onCommandChat(PlayerCommandPreprocessEvent e) {
    FileConfiguration config = this.plugin.getConfigFile().getConfig();
    PlayerManager manager = new PlayerManager(this.plugin);
    Player j = e.getPlayer();
    List<String> list = config.getStringList("Command-Blocker.BlackList");
    List<String> mutelist = config.getStringList("Command-Blocker.Mute-BlackList");
    String message = e.getMessage();
    String nousecommand = config.getString("AdvancedChat.No-Use-Command");
    String nousecommandmute = config.getString("AdvancedChat.No-Use-Command-Mute");
    String path = "Command-Blocker.Enabled";
    if (j.isOp() || j.hasPermission("AdvancedChat.Chat.Bypass"))
      return;
    if (config.getString(path).equals("true"))
      for (String a : list) {
        if (message.toLowerCase().contains(a)) {
          e.setCancelled(true);
          Utils.sendColorMessage(j, Utils.getVar(j, nousecommand.replace("<cmd>", a)));
          break;
        }
      }
    if (manager.isMute(j))
      for (String a : mutelist) {
        if (message.toLowerCase().contains(a)) {
          e.setCancelled(true);
          Utils.sendColorMessage(j, Utils.getVar(j, nousecommandmute.replace("<cmd>", a)));
          break;
        }
      }
  }

  @EventHandler
  public void onCommandDataLog(PlayerCommandPreprocessEvent e) {
    ChatDataFile chatDataFile = this.plugin.getChatDataFile();
    FileConfiguration config = chatDataFile.getConfig();
    Player j = e.getPlayer();
    String date = Utils.getDate(System.currentTimeMillis());
    String time = Utils.getTime(System.currentTimeMillis());
    config.set("Players." + j.getName() + ".Log." + date + ".Command." + time, Utils.colorless(e.getMessage()));
    chatDataFile.saveConfig();
  }

  @EventHandler
  public void onCommandLog(PlayerCommandPreprocessEvent e) {
    CommandLogFile commandLogFile = this.plugin.getCommandLogFile();
    FileConfiguration config = commandLogFile.getConfig();
    Player j = e.getPlayer();
    String date = Utils.getDate(System.currentTimeMillis());
    String time = Utils.getTime(System.currentTimeMillis());
    config.set("Players." + j.getName() + ".Command." + date + "." + time, Utils.colorless(e.getMessage()));
    commandLogFile.saveConfig();
  }
}
