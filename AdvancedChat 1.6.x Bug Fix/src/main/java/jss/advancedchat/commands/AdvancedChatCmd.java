package jss.advancedchat.commands;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.gui.GuiColor;
import jss.advancedchat.gui.GuiPlayer;
import jss.advancedchat.listeners.utils.EventUtils;
import jss.advancedchat.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdvancedChatCmd implements CommandExecutor, TabCompleter {
  private final AdvancedChat plugin = AdvancedChat.get();

  private final EventUtils eventUtils = new EventUtils(this.plugin);

  public AdvancedChatCmd() {
    PluginCommand command = plugin.getCommand("AdvancedChat");
    assert command != null;
    command.setExecutor(this);
    command.setTabCompleter(this);
  }

  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
    if (!(sender instanceof Player)) {
      if (args.length >= 1) {
        if (args[0].equalsIgnoreCase("info") || args[0].equalsIgnoreCase("?")) {
          Utils.sendColorMessage(this.eventUtils.getConsoleSender(), "&5-=-=-=-=-=[&b" + this.plugin.name + "&5]=-=-=-=-=-=-");
          Utils.sendColorMessage(this.eventUtils.getConsoleSender(), "&5> &3Name: &b" + this.plugin.name);
          Utils.sendColorMessage(this.eventUtils.getConsoleSender(), "&5> &3Author: &6jonagamerpro1234");
          Utils.sendColorMessage(this.eventUtils.getConsoleSender(), "&5> &3Version: &6" + this.plugin.version);
          Utils.sendColorMessage(this.eventUtils.getConsoleSender(), "&5> &3Discord: &6https://discord.gg/c5GhQDQCK5");
          Utils.sendColorMessage(this.eventUtils.getConsoleSender(), "&5> &3GitHub: &6https://github.com/jonagamerpro1234/AdvancedChat/");
          Utils.sendColorMessage(this.eventUtils.getConsoleSender(), "&5> &3Spigot: &6https://www.spigotmc.org/resources/advancedchat-1-7-x-1-17-x.83889/");
          Utils.sendColorMessage(this.eventUtils.getConsoleSender(), "&5> &3Songoda: &6https://songoda.com/marketplace/product/advancedchat-chat-related.542");
          Utils.sendColorMessage(this.eventUtils.getConsoleSender(), "&5> &3Bstats: &6https://bstats.org/plugin/bukkit/AdvancedChat/8826");
          Utils.sendColorMessage(this.eventUtils.getConsoleSender(), "&5> &3Wiki&c(Working Progress)&3: &6https://jonagamerpro1234.gitbook.io/advancedchat/");
          Utils.sendColorMessage(this.eventUtils.getConsoleSender(), "&5-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        } else if (args[0].equalsIgnoreCase("help")) {
          List<String> help = Settings.message_Help_List;
          Utils.sendColorMessage(this.eventUtils.getConsoleSender(), "&5-=-=-=-=-=-=-=-=-=-=-=&6[&d" + this.plugin.name + "&6]&5=-=-=-=-=-=-=-=-=-=-=-");
          for (String text : help) {
            Utils.sendColorMessage(this.eventUtils.getConsoleSender(), text);
          }
          Utils.sendColorMessage(this.eventUtils.getConsoleSender(), "&5-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        } else if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
          this.plugin.reloadAllFiles();
          if (Settings.boolean_use_default_prefix) {
            Utils.sendColorMessage(this.eventUtils.getConsoleSender(), Utils.getPrefix() + Settings.message_Reload);
          } else {
            Utils.sendColorMessage(this.eventUtils.getConsoleSender(), Settings.message_prefix_custom + " " + Settings.message_Reload);
          }
        } else if (Settings.boolean_use_default_prefix) {
          Utils.sendColorMessage(this.eventUtils.getConsoleSender(), Utils.getPrefix() + Settings.message_Error_Args);
        } else {
          Utils.sendColorMessage(this.eventUtils.getConsoleSender(), Settings.message_prefix_custom + " " + Settings.message_Error_Args);
        }
        return true;
      }
      if (Settings.boolean_use_default_prefix) {
        Utils.sendColorMessage(this.eventUtils.getConsoleSender(), Utils.getPrefix() + Settings.message_Help_Cmd);
      } else {
        Utils.sendColorMessage(this.eventUtils.getConsoleSender(), Settings.message_prefix_custom + " " + Settings.message_Help_Cmd);
      }
      return false;
    }
    Player j = (Player) sender;
    if (args.length >= 1) {
      if (args[0].equalsIgnoreCase("info") || args[0].equalsIgnoreCase("?")) {
        Utils.sendColorMessage(j, "&5-=-=-=-=-=[&b" + this.plugin.name + "&5]=-=-=-=-=-=-");
        Utils.sendColorMessage(j, "&5> &3Name: &b" + this.plugin.name);
        Utils.sendColorMessage(j, "&5> &3Author: &6jonagamerpro1234");
        Utils.sendColorMessage(j, "&5> &3Version: &6" + this.plugin.version);
        Utils.sendColorMessage(j, "&5> &3Discord: &6https://discord.gg/c5GhQDQCK5");
        Utils.sendColorMessage(j, "&5> &3GitHub: &6https://github.com/jonagamerpro1234/AdvancedChat/");
        Utils.sendColorMessage(j, "&5> &3Spigot: &6https://www.spigotmc.org/resources/advancedchat-1-7-x-1-17-x.83889/");
        Utils.sendColorMessage(j, "&5> &3Songoda: &6https://songoda.com/marketplace/product/advancedchat-chat-related.542");
        Utils.sendColorMessage(j, "&5> &3Bstats: &6https://bstats.org/plugin/bukkit/AdvancedChat/8826");
        Utils.sendColorMessage(j, "&5> &3Wiki&c(Working Progress)&3: &6https://jonagamerpro1234.gitbook.io/advancedchat/");
        Utils.sendColorMessage(j, "&5-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        return true;
      }
      if (args[0].equalsIgnoreCase("help")) {
        if (j.isOp() || j.hasPermission("AdvancedChat.Help")) {
          List<String> help = Settings.message_Help_List;
          Utils.sendColorMessage(j, "&5-=-=-=-=-=-=-=-=-=-=-=&6[&d" + this.plugin.name + "&6]&5=-=-=-=-=-=-=-=-=-=-=-");
          for (String text : help) {
            Utils.sendColorMessage(j, text);
          }
          Utils.sendColorMessage(j, "&5-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        } else {
          Utils.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
        }
        return true;
      }
      if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
        if (j.isOp() || j.hasPermission("AdvancedChat.Reload")) {
          this.plugin.reloadAllFiles();
          if (Settings.boolean_use_default_prefix) {
            Utils.sendColorMessage(j, Utils.getPrefixPlayer() + Settings.message_Reload);
          } else {
            Utils.sendColorMessage(j, Settings.message_prefix_custom + " " + Settings.message_Reload);
          }
        } else {
          Utils.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
        }
        return true;
      }
      if (args[0].equalsIgnoreCase("color")) {
        if (j.isOp() || j.hasPermission("AdvancedChat.Gui.Color")) {
          GuiColor guiColor = new GuiColor(this.plugin);
          if (args.length >= 2) {
            String name = args[1];
            Player p = Bukkit.getPlayer(name);
            if (p == null) {
              Utils.sendColorMessage(j, Settings.message_No_Online_Player);
              return true;
            }
            guiColor.open(j, p.getName());
          } else {
            guiColor.open(j, j.getName());
            return true;
          }
        } else {
          Utils.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
        }
        return true;
      }
      if (args[0].equalsIgnoreCase("player")) {
        if (j.isOp() || j.hasPermission("AdvancedChat.Gui.Player")) {
          GuiPlayer guiPlayer = new GuiPlayer(this.plugin);
          if (args.length >= 2) {
            String name = args[1];
            Player p = Bukkit.getPlayer(name);
            if (p == null) {
              Utils.sendColorMessage(j, Settings.message_No_Online_Player);
              return true;
            }
            guiPlayer.openPlayerGui(j, p.getName());
          } else {
            guiPlayer.openPlayerGui(j, j.getName());
            return true;
          }
        } else {
          Utils.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
        }
        return true;
      }
      if (Settings.boolean_use_default_prefix) {
        Utils.sendColorMessage(j, Utils.getPrefixPlayer() + Settings.message_Error_Args);
      } else {
        Utils.sendColorMessage(j, Settings.message_prefix_custom + " " + Settings.message_Error_Args);
      }
      return true;
    }
    if (Settings.boolean_use_default_prefix) {
      Utils.sendColorMessage(j, Utils.getPrefixPlayer() + Settings.message_Help_Cmd);
    } else {
      Utils.sendColorMessage(j, Settings.message_prefix_custom + " " + Settings.message_Help_Cmd);
    }
    return true;
  }

  public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String @NotNull [] args) {
    List<String> listOptions = new ArrayList<>();
    String lastArgs = (args.length != 0) ? args[args.length - 1] : "";
    if (!(sender instanceof Player)) {
      switch (args.length) {
        case 0:
        case 1:
          listOptions.add("help");
          listOptions.add("reload");
          listOptions.add("info");
          break;
      }
      return Utils.setLimitTab(listOptions, lastArgs);
    }
    Player j = (Player) sender;
    if (j.isOp() || j.hasPermission("AdvancedChat.Tab")) return new ArrayList<>();

    switch (args.length) {
        case 0:
        case 1:
          listOptions.add("help");
          listOptions.add("reload");
          listOptions.add("info");
          listOptions.add("color");
          listOptions.add("player");
          break;
        case 2:
          if (args[0].equalsIgnoreCase("color") || args[0].equalsIgnoreCase("player"))
            for (Player p : Bukkit.getOnlinePlayers())
              listOptions.add(p.getName());
          break;
    }
    return Utils.setLimitTab(listOptions, lastArgs);
  }
}
