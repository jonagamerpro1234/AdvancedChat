package jss.advancedchat.commands.oldcommands;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.hooks.LuckPermsHook;
import jss.advancedchat.inventory.GuiColor;
import jss.advancedchat.inventory.GuiGradient;
import jss.advancedchat.inventory.GuiPlayer;
import jss.advancedchat.inventory.GuiSettings;
import jss.advancedchat.manager.ColorManagerOld;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.mysql.MySql;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Perms;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.Util;
import jss.advancedchat.utils.Utils;
import net.luckperms.api.model.group.Group;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdvancedChatCmd implements CommandExecutor, TabCompleter {
    private final AdvancedChat plugin = AdvancedChat.get();

    @SuppressWarnings("ConstantConditions")
    public AdvancedChatCmd() {
        plugin.getCommand("AdvancedChat").setExecutor(this);
        plugin.getCommand("AdvancedChat").setTabCompleter(this);
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("info")) {
                    Utils.getInfoPlugin(sender, plugin.name, plugin.version, plugin.latestVersion);
                    return true;
                }
                if (args[0].equalsIgnoreCase("help")) {
                    sendHelp(sender);
                    return true;
                }
                if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
                    plugin.reloadAllFiles();
                    Util.sendColorMessage(sender, Util.getPrefix(false) + Settings.message_Reload);
                    return true;
                }
                Util.sendColorMessage(sender, Util.getPrefix(false) + Settings.message_Error_Args);
                return true;
            }
            Util.sendColorMessage(sender, Util.getPrefix(false) + Settings.message_Help_Cmd);
            return false;
        }

        Player j = (Player) sender;
        if (args.length >= 1) {

            if (args[0].equalsIgnoreCase("help")) {
                if (!j.isOp() || !j.hasPermission(Perms.ac_cmd_help))
                    Util.sendHover(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
                sendHelp(j);
                return true;
            }

            if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
                if ((j.isOp()) || (j.hasPermission(Perms.ac_cmd_reload))) {
                    plugin.reloadAllFiles();
                    Util.sendColorMessage(j, Util.getPrefix(false) + Settings.message_Reload);
                } else {
                    Util.sendHover(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
                }
                return true;
            }

            if (args[0].equalsIgnoreCase("color")) {
                if ((j.isOp()) || (j.hasPermission(Perms.ac_cmd_color))) {
                    GuiColor guiColor = new GuiColor();

                    if (args.length >= 2) {

                        String playerName = args[1];
                        Player target = Bukkit.getPlayer(playerName);

                        if (target == null) Util.sendColorMessage(j, Settings.message_No_Online_Player);
                        assert target != null;
                        PlayerManager playerManager = new PlayerManager(target);

                        if (args.length >= 3) {
                            if (args[2].equalsIgnoreCase("set")) {

                                String color = args[3];

                                if (color == null)
                                    Util.sendColorMessage(j, "&6Please select a color");

                                if (Settings.mysql) {
                                    MySql.setColor(target, color);
                                } else {
                                    playerManager.setColor(color);
                                }
                            }
                            return true;
                        }
                        guiColor.open(j, playerName);
                        return true;
                    }
                    guiColor.open(j, j.getName());
                } else {
                    Util.sendHover(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
                }
                return true;
            }

            if (args[0].equalsIgnoreCase("gradient")) {
                if ((j.isOp()) || (j.hasPermission(Perms.ac_cmd_gradient))) {
                    GuiGradient guiGradient = new GuiGradient();

                    if (args.length >= 2) {

                        String playerName = args[1];

                        if (playerName == null)
                            Util.sendColorMessage(j, "&cPlease use the name of the player you want to set the color");

                        assert playerName != null;
                        Player target = Bukkit.getPlayer(playerName);

                        if (target == null)
                            Util.sendColorMessage(j, Settings.message_No_Online_Player);

                        assert target != null;
                        PlayerManager playerManager = new PlayerManager(target);

                        if (args.length >= 3) {

                            if (args[2].equalsIgnoreCase("set")) {

                                String hex = args[3];

                                if (hex == null)
                                    Logger.debug("&6Please select a hex color");

                                if (args[4].equalsIgnoreCase("first")) {
                                    if (Settings.mysql) {
                                       MySql.setFirstGradient(target,hex);
                                    } else {
                                        assert hex != null;
                                        playerManager.setFirstGradient(ColorManagerOld.get().convertHexColor(hex));
                                    }
                                    return true;
                                }
                                if (args[4].equalsIgnoreCase("second")) {
                                    if (Settings.mysql) {
                                        MySql.setSecondGradient(target, hex);
                                    } else {
                                        assert hex != null;
                                        playerManager.setSecondGradient(ColorManagerOld.get().convertHexColor(hex));
                                    }
                                    return true;
                                }
                            }
                            return true;
                        }
                        guiGradient.open(j, playerName);
                        return true;
                    }
                    guiGradient.open(j, j.getName());
                } else {
                    Util.sendHover(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
                }
                return true;
            }

            if (args[0].equalsIgnoreCase("settings")) {
                if ((j.isOp()) || (j.hasPermission(Perms.ac_cmd_settings))) {
                    GuiSettings guiSettings = new GuiSettings();

                    if (args.length >= 2) {

                        String playerName = args[1];

                        if (playerName == null)
                            Logger.debug("&6Select the player for open inventory");

                        assert playerName != null;
                        Player target = Bukkit.getPlayer(playerName);

                        if (target == null)
                            Util.sendColorMessage(j, Settings.message_No_Online_Player);

                        if (args.length >= 3) {
                            assert target != null;
                            PlayerManager playerManager = new PlayerManager(target);
                            if (args[2].equalsIgnoreCase("low-mode")) {
                                if (args.length >= 4) {
                                    if (args[3].equalsIgnoreCase("true")) {

                                        if (Settings.mysql) {
                                            MySql.setLowMode(target, true);
                                        }else{
                                            playerManager.setLowMode(true);
                                        }
                                        return true;
                                    }
                                    if (args[3].equalsIgnoreCase("false")) {
                                        if (Settings.mysql) {
                                            MySql.setLowMode(target,false);
                                        }else{
                                            playerManager.setLowMode(false);
                                        }
                                        return true;
                                    }
                                }
                                return true;
                            }

                            if (args[2].equalsIgnoreCase("chat")) {

                                if (args.length >= 4) {

                                    if (args[3].equalsIgnoreCase("true")) {
                                        if (Settings.mysql) {
                                            MySql.setChat(target,true);
                                        }else{
                                            playerManager.setChat(true);
                                        }
                                        return true;
                                    }
                                    if (args[3].equalsIgnoreCase("false")) {
                                        if (Settings.mysql) {
                                            MySql.setChat(target,false);
                                        }else{
                                            playerManager.setChat(false);
                                        }
                                        return true;
                                    }
                                }
                                return true;
                            }

                            if (args[2].equalsIgnoreCase("msg")) {
                                if (args.length >= 4) {
                                    if (args[3].equalsIgnoreCase("true")) {
                                        playerManager.setLowMode(true);
                                        return true;
                                    }
                                    if (args[3].equalsIgnoreCase("false")) {
                                        playerManager.setLowMode(false);
                                        return true;
                                    }
                                }
                                return true;
                            }

                            if(args[2].equalsIgnoreCase("group")){
                                if(args.length >= 4){

                                    if (args[3].equalsIgnoreCase("set")){

                                        String groupName = args[4];

                                        playerManager.setGroup(groupName);


                                        return true;
                                    }

                                    return true;
                                }
                                return true;
                            }

                            Util.sendColorMessage(j, "please select the setting");
                            return true;
                        }
                        assert target != null;
                        guiSettings.open(j, target.getName());
                        return true;
                    }
                    guiSettings.open(j, j.getName());
                } else {
                    Util.sendHover(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
                }
                return true;
            }

            if (args[0].equalsIgnoreCase("player")) {
                if ((j.isOp()) || (j.hasPermission(Perms.ac_cmd_player))) {
                    GuiPlayer guiPlayer = new GuiPlayer();
                    if (args.length >= 2) {

                        String playerName = args[1];

                        if (playerName.isEmpty())
                            Logger.debug("&6Select the player for open inventory");

                        Player target = Bukkit.getPlayer(playerName);

                        if (target == null)
                            Util.sendColorMessage(j, Settings.message_No_Online_Player);

                        guiPlayer.open(j, playerName);
                    } else {
                        guiPlayer.open(j, j.getName());
                        return true;
                    }
                } else {
                    Util.sendHover(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
                }
                return true;
            }

            if (args[0].equalsIgnoreCase("info")) {
                Util.getInfoPlugin(j, plugin.name, plugin.version, plugin.latestVersion);
                return true;
            }

            Util.sendColorMessage(j, Util.getPrefix(false) + Settings.message_Error_Args);
            return true;
        }
        Util.sendColorMessage(j, Util.getPrefix(false) + Settings.message_Help_Cmd);
        return true;
    }

    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String @NotNull [] args) {
        List<String> listOptions = new ArrayList<>();
        String lastArgs = args.length != 0 ? args[args.length - 1] : "";
        if (!(sender instanceof Player)) {
            switch (args.length) {
                case 0:
                case 1:
                    listOptions.addAll(Arrays.asList("help", "reload", "info"));
                    break;
            }
        } else {
            final Player j = (Player) sender;

            if (!j.isOp() || !j.hasPermission(Perms.ac_cmd_tabcomplete)) return new ArrayList<>();

            switch (args.length) {
                case 0:
                case 1:

                    listOptions.add("help");
                    listOptions.add("reload");
                    listOptions.add("info");
                    listOptions.add("color");
                    listOptions.add("player");
                    listOptions.add("gradient");
                    listOptions.add("settings");

                    break;
                case 2:
                    if (args[0].equalsIgnoreCase("color") || args[0].equalsIgnoreCase("player")
                            || args[0].equalsIgnoreCase("gradient")) {
                        Bukkit.getOnlinePlayers().forEach((p) -> listOptions.add(p.getName()));
                    }
                    if (args[0].equalsIgnoreCase("settings")) {
                        listOptions.add("low-mode");
                        listOptions.add("group");
                        listOptions.add("msg");
                        listOptions.add("chat");
                    }
                    break;

                case 3:
                    if (args[0].equalsIgnoreCase("color") || args[0].equalsIgnoreCase("gradient")) {
                        listOptions.add("set");
                    }
                    if (args[0].equalsIgnoreCase("settings")) {
                        listOptions.add("set");
                        listOptions.add("true");
                        listOptions.add("false");
                    }
                    break;
                case 4:
                    if (args[0].equalsIgnoreCase("color") || args[0].equalsIgnoreCase("gradient")) {
                        listOptions.addAll(Arrays.asList("black", "white", "dark_gray", "gray", "dark_purple",
                                "light_purple", "dark_aqua", "aqua", "gold", "yellow", "green", "dark_green", "blue",
                                "dark_blue", "red", "dark_red"));
                        if(HookManager.get().getLuckPermsHook().isEnabled()){
                            if(args[0].equalsIgnoreCase("settings")){
                                for(Group availableGroups : LuckPermsHook.getApi().getGroupManager().getLoadedGroups()){
                                    listOptions.add(availableGroups.getName());
                                }
                            }
                        }else{
                            Logger.debug("Not load group list from LuckPerms Plugin!!");
                        }
                    }
                    break;
                case 5:
                    if (args[0].equalsIgnoreCase("gradient")) {
                        listOptions.add("first");
                        listOptions.add("second");
                    }
                    break;
            }
        }
        return Utils.setLimitTab(listOptions, lastArgs);
    }

    private void sendHelp(CommandSender sender) {
        Util.sendColorMessage(sender, "&5-=-=-=-=-=-=-=-=-=-=-=&6[&d" + plugin.name + "&6]&5=-=-=-=-=-=-=-=-=-=-=-");
        for (String msg : Settings.list_message_help) {
            Util.sendColorMessage(sender, msg);
        }
        Util.sendColorMessage(sender, "&5-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }

}
