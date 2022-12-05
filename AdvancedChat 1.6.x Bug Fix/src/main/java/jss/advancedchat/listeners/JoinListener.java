package jss.advancedchat.listeners;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.config.ChatDataFile;
import jss.advancedchat.config.ChatLogFile;
import jss.advancedchat.config.CommandLogFile;
import jss.advancedchat.config.PlayerDataFile;
import jss.advancedchat.utils.*;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class JoinListener implements Listener {
  private AdvancedChat plugin;

  private final EventUtils eventsUtils = new EventUtils(this.plugin);

  public JoinListener(AdvancedChat plugin) {
    this.plugin = plugin;
    this.eventsUtils.getEventManager().registerEvents(this, plugin);
  }

  @EventHandler
  public void onJoinPlayer(PlayerJoinEvent e) {
    PlayerDataFile dataFile = this.plugin.getPlayerDataFile();
    FileConfiguration config = dataFile.getConfig();
    ChatDataFile chatDataFile = this.plugin.getChatDataFile();
    FileConfiguration chat = chatDataFile.getConfig();
    ChatLogFile chatLogFile = this.plugin.getChatLogFile();
    FileConfiguration chatlog = chatLogFile.getConfig();
    CommandLogFile commandLogFile = this.plugin.getCommandLogFile();
    FileConfiguration command = commandLogFile.getConfig();
    Player j = e.getPlayer();
    if (!config.contains("Players." + j.getName())) {
      config.set("Players." + j.getName() + ".UUID", j.getUniqueId().toString());
      config.set("Players." + j.getName() + ".Color", Settings.default_color);
      config.set("Players." + j.getName() + ".Mute", Boolean.valueOf(false));
      config.set("Players." + j.getName() + ".Chat-Channel", "ALL");
      dataFile.saveConfig();
    }
    if (!chat.contains("Players." + j.getName())) {
      chat.set("Players." + j.getName() + ".UUID", j.getUniqueId().toString());
      chat.set("Players." + j.getName() + ".Log", null);
      chatDataFile.saveConfig();
    }
    if (!chatlog.contains("Players." + j.getName())) {
      chatlog.set("Players." + j.getName() + ".UUID", j.getUniqueId().toString());
      chatlog.set("Players." + j.getName() + ".Chat", "[]");
      chatLogFile.saveConfig();
    }
    if (!command.contains("Players." + j.getName())) {
      command.set("Players." + j.getName() + ".UUID", j.getUniqueId().toString());
      command.set("Players." + j.getName() + ".Command", "[]");
      commandLogFile.saveConfig();
    }
  }

  @EventHandler
  public void onUpdatePlayer(PlayerJoinEvent e) {
    FileConfiguration config = this.plugin.getConfigFile().getConfig();
    Player j = e.getPlayer();
    if (config.getString("Settings.Update").equals("true") && (
            j.isOp() || j.hasPermission("AdvancedChat.Update.Notify")))
      (new UpdateChecker(AdvancedChat.getInstance(), 83889)).getUpdateVersion(version -> {
        if (!AdvancedChat.getInstance().getDescription().getVersion().equalsIgnoreCase(version)) {
          Utils.sendColorMessage(j, Utils.getPrefixPlayer() + " &aThere is a new version available for download");
          Utils.sendColorMessage(j, Utils.getPrefixPlayer() + " &aClick on this message to copy the link");
          TextComponent component = new TextComponent(Utils.color("&5> &6&l(Spigot) "));
          TextComponent component2 = new TextComponent(Utils.color("&8| &d&l(Songoda) "));
          TextComponent component3 = new TextComponent(Utils.color("&8| &8&l(GitHub) "));
          component.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, UpdateSettings.URL_PLUGIN[0]));
          component2.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, UpdateSettings.URL_PLUGIN[1]));
          component3.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, UpdateSettings.URL_PLUGIN[2]));
          component.addExtra(component2);
          component.addExtra(component3);
          j.spigot().sendMessage(component);
        }
      });
  }
}
