package jss.advancedchat.listeners;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.files.ChatDataFile;
import jss.advancedchat.files.CommandLogFile;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CommandListener implements Listener {
  private final AdvancedChat plugin = AdvancedChat.get();

  public CommandListener() {
    Bukkit.getPluginManager().registerEvents(this, plugin);
  }

  @EventHandler
  public void onCommandChat(@NotNull PlayerCommandPreprocessEvent e) {
    FileConfiguration config = this.plugin.getConfigFile().getConfig();
    PlayerManager manager = new PlayerManager(this.plugin);
    Player j = e.getPlayer();
    List<String> list = config.getStringList("Command-Blocker.BlackList");
    List<String> muteList = config.getStringList("Command-Blocker.Mute-BlackList");
    String message = e.getMessage();
    String noUseCommand = config.getString("AdvancedChat.No-Use-Command");
    String noUseCommandMute = config.getString("AdvancedChat.No-Use-Command-Mute");
    String path = "Command-Blocker.Enabled";
    if (j.isOp() || j.hasPermission("AdvancedChat.Chat.Bypass"))
      return;
    if (config.getBoolean(path))
      for (String a : list) {
        if (message.toLowerCase().contains(a)) {
          e.setCancelled(true);
          assert noUseCommand != null;
          Utils.sendColorMessage(j, Utils.getVar(j, noUseCommand.replace("<cmd>", a)));
          break;
        }
      }
    if (manager.isMute(j))
      for (String a : muteList) {
        if (message.toLowerCase().contains(a)) {
          e.setCancelled(true);
          assert noUseCommandMute != null;
          Utils.sendColorMessage(j, Utils.getVar(j, noUseCommandMute.replace("<cmd>", a)));
          break;
        }
      }
  }

  @EventHandler
  public void onCommandDataLog(@NotNull PlayerCommandPreprocessEvent e) {
    ChatDataFile chatDataFile = this.plugin.getChatDataFile();
    FileConfiguration config = chatDataFile.getConfig();
    Player j = e.getPlayer();
    String date = Utils.getDate(System.currentTimeMillis());
    String time = Utils.getTime(System.currentTimeMillis());
    config.set("Players." + j.getName() + ".Log." + date + ".Command." + time, Utils.colorless(e.getMessage()));
    chatDataFile.saveConfig();
  }

  @EventHandler
  public void onCommandLog(@NotNull PlayerCommandPreprocessEvent e) {
    CommandLogFile commandLogFile = this.plugin.getCommandLogFile();
    FileConfiguration config = commandLogFile.getConfig();
    Player j = e.getPlayer();
    String date = Utils.getDate(System.currentTimeMillis());
    String time = Utils.getTime(System.currentTimeMillis());
    config.set("Players." + j.getName() + ".Command." + date + "." + time, Utils.colorless(e.getMessage()));
    commandLogFile.saveConfig();
  }

}
