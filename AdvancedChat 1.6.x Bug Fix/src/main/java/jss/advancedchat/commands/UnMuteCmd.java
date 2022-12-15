package jss.advancedchat.commands;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.config.ConfigManager;
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

public class UnMuteCmd implements CommandExecutor {
    private final AdvancedChat plugin = AdvancedChat.get();

    public UnMuteCmd() {
        Objects.requireNonNull(plugin.getCommand("AdUnMute")).setExecutor(this);
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        FileConfiguration config = ConfigManager.getConfig();
        PlayerManager manager = new PlayerManager(this.plugin);
        String text = config.getString("AdvancedChat.Help-UnMute");
        String prefix = "";
        if (Settings.boolean_use_default_prefix) {
            prefix = Utils.getPrefixPlayer();
        } else {
            prefix = Settings.message_prefix_custom + " ";
        }
        if (!(sender instanceof Player)) {
            if (args.length >= 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    Utils.sendColorMessage(sender, Settings.message_No_Online_Player);
                    return true;
                }
                if (manager.isMute(target)) {
                    manager.setMute(target, false);
                    Utils.sendColorMessage(sender, Utils.getPrefix() + Utils.getVar(target, Settings.message_UnMute_Player));
                } else {
                    Utils.sendColorMessage(target, Utils.getPrefix() + Utils.getVar(target, Settings.message_player_is_not_mute));
                }
                return true;
            }
            Utils.sendColorMessage(sender, Utils.getPrefix() + text);
            return false;
        }
        Player j = (Player) sender;
        if (j.isOp() || j.hasPermission("AdvancedChat.UnMute")) {
            if (args.length >= 1) {
                text = Utils.getVar(j, text);
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    Utils.sendColorMessage(sender, Settings.message_No_Online_Player);
                    return true;
                }
                if (manager.isMute(target)) {
                    manager.setMute(target, false);
                    Utils.sendColorMessage(j, prefix + Utils.getVar(target, Settings.message_UnMute_Player));
                } else {
                    Utils.sendColorMessage(target, "&cThe player is not muted");
                }
                return true;
            }
        } else {
            Utils.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
            return true;
        }
        Utils.sendColorMessage(j, Utils.getPrefix() + text);
        return true;
    }
}
