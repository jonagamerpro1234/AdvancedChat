package jss.advancedchat.commands;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MsgCmd implements CommandExecutor, TabCompleter {

    public MsgCmd() {
        AdvancedChat plugin = AdvancedChat.get();
        plugin.getCommand("AdMsg").setExecutor(this);
        plugin.getCommand("AdMsg").setTabCompleter(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length >= 1) {

                Player p = Bukkit.getPlayer(args[0]);

                if (p == null) {
                    Util.sendColorMessage(sender, Settings.message_No_Online_Player);
                    return true;
                }

                if (args.length >= 2) {
                    String message = args[1];

                    if (message == null) {
                        Util.sendColorMessage(sender, Settings.message_msg_empty);
                        return true;
                    }

                    PlayerManager playerManager = new PlayerManager(p);

                    if (!playerManager.isMsg()) {
                        Util.sendColorMessage(sender, Settings.message_alert_disable_msg);
                        return true;
                    }

                    Util.sendColorMessage(sender, Util.getVar(p, Settings.msg_format_send.replace("<msg>", message)));
                    Util.sendColorMessage(p, Settings.msg_server_format_recive.replace("<msg>", message));

                    return true;
                }
                Util.sendColorMessage(sender, Util.getPrefix(false) + Settings.message_msg_use);
                return true;
            }
            Util.sendColorMessage(sender, Util.getPrefix(false) + Settings.message_msg_use);
            return false;
        }

        Player j = (Player) sender;

        if (args.length >= 1) {

            Player p = Bukkit.getPlayer(args[0]);

            if (p == null) {
                Util.sendColorMessage(j, Settings.message_No_Online_Player);
                return true;
            }

            if (args.length >= 2) {
                String message = args[1];
                if (message == null) {
                    Util.sendColorMessage(j, Settings.message_msg_empty);
                    return true;
                }

                PlayerManager playerManager = new PlayerManager(p);

                if (!playerManager.isMsg()) {
                    Util.sendColorMessage(j, Settings.message_alert_disable_msg);
                    return true;
                }

                Util.sendColorMessage(j, Util.getVar(p, Settings.msg_format_send.replace("<msg>", message)));
                Util.sendColorMessage(p, Util.getVar(j, Settings.msg_format_recive.replace("<msg>", message)));
                return true;
            }
            Util.sendColorMessage(j, Util.getPrefix(false) + Settings.message_msg_use);
            return true;
        }
        Util.sendColorMessage(j, Util.getPrefix(false) + Settings.message_msg_use);
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> list = new ArrayList<>();
        String lastArgs = args.length != 0 ? args[args.length - 1] : "";
        if (!(sender instanceof Player)) {
            switch (args.length) {
                case 0:
                case 1:
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        list.add(p.getName());
                    }
                    break;
            }
            return Util.setLimitTab(list, lastArgs);
        }

        switch (args.length) {
            case 0:
            case 1:
                for (Player p : Bukkit.getOnlinePlayers()) {
                    list.add(p.getName());
                }
                break;
        }
        return Util.setLimitTab(list, lastArgs);
    }

}
