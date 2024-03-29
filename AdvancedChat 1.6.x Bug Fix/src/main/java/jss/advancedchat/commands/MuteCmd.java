package jss.advancedchat.commands;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.ConfigManager;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MuteCmd implements CommandExecutor {
    private final AdvancedChat plugin = AdvancedChat.get();

    public MuteCmd() {
        Objects.requireNonNull(plugin.getCommand("AdMute")).setExecutor(this);
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        FileConfiguration config = ConfigManager.getConfig();
        String text = config.getString("AdvancedChat.Help-Mute");

        if (!(sender instanceof Player)) {
            if (args.length >= 1) {
                Player target = Bukkit.getPlayer(args[0]);

                if (target == null) {
                    Utils.sendColorMessage(sender, Settings.message_No_Online_Player);
                    return true;
                }

                PlayerManager manager = new PlayerManager(target);

                if (target.isOp() || target.hasPermission("AdvancedChat.Mute.Bypass")) {
                    Utils.sendColorMessage(sender, Settings.message_mute_bypass);
                    return true;
                }

                if (!manager.isMute()) {
                    manager.setMute(true);
                    Utils.sendColorMessage(sender, Utils.getPrefix(false) + Utils.getVar(target, Settings.message_Mute_Player));
                } else {
                    Utils.sendColorMessage(sender, Utils.getPrefix(false) + Utils.getVar(target, Settings.message_player_is_mute));
                }
                return true;
            }
            Utils.sendColorMessage(sender, Utils.getPrefix() + text);
            return false;
        }
        Player j = (Player) sender;
        if (j.isOp() || j.hasPermission("AdvancedChat.Mute")) {
            if (args.length >= 1) {
                Player target = Bukkit.getPlayer(args[0]);

                if (target == null) {
                    Utils.sendColorMessage(j, Settings.message_No_Online_Player);
                    return true;
                }

                PlayerManager manager = new PlayerManager(target);

                if (target.isOp() || target.hasPermission("AdvancedChat.Mute.Bypass")) {
                    Utils.sendColorMessage(j, Settings.message_mute_bypass);
                    return true;
                }
                manager.setMute(true);
                Utils.sendColorMessage(j, Utils.getPrefix(false) + Utils.getVar(target, Settings.message_Mute_Player));
                return true;
            }
        } else {
            Utils.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
            return true;
        }
        Utils.sendColorMessage(j, Utils.getPrefix(false) + text);
        return true;
    }
}
