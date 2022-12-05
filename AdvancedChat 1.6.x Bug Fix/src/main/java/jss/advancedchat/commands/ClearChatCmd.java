package jss.advancedchat.commands;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.config.ConfigFile;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ClearChatCmd implements CommandExecutor {
  private AdvancedChat plugin;

  private final EventUtils eventUtils = new EventUtils(this.plugin);

  public ClearChatCmd(AdvancedChat plugin) {
    this.plugin = plugin;
    plugin.getCommand("ClearChat").setExecutor(this);
  }

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    ConfigFile configFile = this.plugin.getConfigFile();
    FileConfiguration config = configFile.getConfig();
    if (!(sender instanceof Player)) {
      this.eventUtils.getClearChatAction("server");
      this.eventUtils.getServerMessage(config);
      return false;
    }
    Player j = (Player) sender;
    if (j.isOp() || j.hasPermission("AdvancedChat.ClearChat")) {
      this.eventUtils.getClearChatAction("player");
      this.eventUtils.getPlayerMessage(j, config);
    } else {
      Utils.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
    }
    return true;
  }
}
