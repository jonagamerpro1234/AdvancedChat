package jss.advancedchat.commands;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.Util;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.logger.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MsgCmd implements CommandExecutor, TabCompleter {

    public MsgCmd() {
        AdvancedChat plugin = AdvancedChat.get();
        Objects.requireNonNull(plugin.getCommand("AdMsg")).setExecutor(this);
        Objects.requireNonNull(plugin.getCommand("AdMsg")).setTabCompleter(this);
        Logger.debug("[MsgCmd] Command 'AdMsg' registered successfully with executor and tab completer.");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        Logger.debug("[MsgCmd] Executing command /" + label + " with arguments: " + String.join(" ", args));

        // --- Console Execution ---
        if (!(sender instanceof Player)) {
            Logger.debug("[MsgCmd] Command executed from console.");

            if (args.length >= 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    Logger.debug("[MsgCmd] Target player not found or offline.");
                    Util.sendColorMessage(sender, Settings.message_No_Online_Player);
                    return true;
                }

                if (args.length >= 2) {
                    String message = args[1];
                    if (message == null) {
                        Logger.debug("[MsgCmd] Message argument is null.");
                        Util.sendColorMessage(sender, Settings.message_msg_empty);
                        return true;
                    }

                    Logger.debug("[MsgCmd] Sending private message from console to " + target.getName() + ": " + message);
                    Util.sendColorMessage(sender, Util.getVar(target, Settings.msg_format_send.replace("<msg>", message)));
                    Util.sendColorMessage(target, Settings.msg_server_format_recive.replace("<msg>", message));
                    Logger.debug("[MsgCmd] Message sent successfully from console to " + target.getName() + ".");
                    return true;
                }

                Logger.debug("[MsgCmd] Not enough arguments. Usage message sent to console.");
                Util.sendColorMessage(sender, Util.getPrefix(false) + Settings.message_msg_use);
                return true;
            }

            Logger.debug("[MsgCmd] No arguments provided. Showing usage message to console.");
            Util.sendColorMessage(sender, Util.getPrefix(false) + Settings.message_msg_use);
            return false;
        }

        // --- Player Execution ---
        Player player = (Player) sender;
        Logger.debug("[MsgCmd] Command executed by player: " + player.getName());

        if (args.length >= 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                Logger.debug("[MsgCmd] Target player not found or offline for " + player.getName());
                Util.sendColorMessage(player, Settings.message_No_Online_Player);
                return true;
            }

            if (args.length >= 2) {
                String message = args[1];
                if (message == null) {
                    Logger.debug("[MsgCmd] Message argument is null for " + player.getName());
                    Util.sendColorMessage(player, Settings.message_msg_empty);
                    return true;
                }

                Logger.debug("[MsgCmd] Player " + player.getName() + " sending private message to " + target.getName() + ": " + message);
                Util.sendColorMessage(player, Util.getVar(target, Settings.msg_format_send.replace("<msg>", message)));
                Util.sendColorMessage(target, Util.getVar(player, Settings.msg_format_recive.replace("<msg>", message)));
                Logger.debug("[MsgCmd] Private message delivered successfully from " + player.getName() + " to " + target.getName() + ".");
                return true;
            }

            Logger.debug("[MsgCmd] Not enough arguments. Sending usage message to player " + player.getName());
            Util.sendColorMessage(player, Util.getPrefix(false) + Settings.message_msg_use);
            return true;
        }

        Logger.debug("[MsgCmd] No arguments provided. Showing usage message to player " + player.getName());
        Util.sendColorMessage(player, Util.getPrefix(false) + Settings.message_msg_use);
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String @NotNull [] args) {
        Logger.debug("[MsgCmd] Tab completion triggered for /" + label + " with args length: " + args.length);
        List<String> list = new ArrayList<>();
        String lastArg = args.length != 0 ? args[args.length - 1] : "";

        if (!(sender instanceof Player)) {
            Logger.debug("[MsgCmd] Tab completion executed from console.");
            switch (args.length) {
                case 0:
                case 1:
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        list.add(p.getName());
                    }
                    break;
            }
            Logger.debug("[MsgCmd] Tab completion result (console): " + list.size() + " player names found.");
            return Utils.setLimitTab(list, lastArg);
        }

        Logger.debug("[MsgCmd] Tab completion executed by player.");
        switch (args.length) {
            case 0:
            case 1:
                for (Player p : Bukkit.getOnlinePlayers()) {
                    list.add(p.getName());
                }
                break;
        }

        Logger.debug("[MsgCmd] Tab completion result (player): " + list.size() + " player names found.");
        return Utils.setLimitTab(list, lastArg);
    }
}
