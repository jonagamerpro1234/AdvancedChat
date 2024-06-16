package jss.advancedchat.commands.oldcommands;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.mysql.MySql;
import jss.advancedchat.utils.Perms;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MuteCmd implements CommandExecutor {

    public MuteCmd() {
        AdvancedChat plugin = AdvancedChat.get();
        Objects.requireNonNull(plugin.getCommand("AdMute")).setExecutor(this);
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {

            if (args.length >= 1) {
                Player target = Bukkit.getPlayer(args[0]);
                assert target != null;
                PlayerManager playerManager = new PlayerManager(target);
                if (target.isOp() || target.hasPermission(Perms.ac_mute_bypass)) {
                    Util.sendColorMessage(sender, Settings.message_mute_bypass);
                    return true;
                }

                if (Settings.mysql) {
                    MySql.setMute(target, true);
                } else {
                    playerManager.setMute(true);
                }

                Util.sendColorMessage(sender, Util.getPrefix(false) + Util.getVar(target, Settings.message_Mute_Player));
                return true;
            }
            Util.sendColorMessage(sender, Util.getPrefix(false) + Settings.message_Help_Mute);
            return false;
        }
        Player j = (Player) sender;

        if (j.isOp() || j.hasPermission(Perms.ac_cmd_mute)) {
            if (args.length >= 1) {
                Player target = Bukkit.getPlayer(args[0]);
                assert target != null;
                PlayerManager playerManager = new PlayerManager(target);
                if (target.isOp() || target.hasPermission(Perms.ac_mute_bypass)) {
                    Util.sendColorMessage(j, Settings.message_mute_bypass);
                    return true;
                }

                if (Settings.mysql) {
                    //mySQL.setMute(target.getUniqueId().toString(), true);
                } else {
                    playerManager.setMute(true);
                }

                Util.sendColorMessage(j, Util.getPrefix(false) + Util.getVar(target, Settings.message_Mute_Player));
                return true;
            }
        } else {
            Util.sendHover(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
            return true;
        }
        Util.sendColorMessage(j, Util.getPrefix(false) + Util.getVar(j, Settings.message_Help_Mute));
        return true;
    }
}
