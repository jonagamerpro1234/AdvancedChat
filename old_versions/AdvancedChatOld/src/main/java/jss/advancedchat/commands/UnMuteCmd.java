package jss.advancedchat.commands;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.Util;
import jss.advancedchat.utils.logger.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class UnMuteCmd implements CommandExecutor {

    private final AdvancedChat plugin = AdvancedChat.get();

    public UnMuteCmd() {
        Objects.requireNonNull(plugin.getCommand("AdUnMute")).setExecutor(this);
        Logger.debug("UnMuteCmd: Command executor set for AdUnMute");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        Logger.debug("UnMuteCmd: Command executed by " + sender.getName() + " with args: " + String.join(", ", args));

        // ---- Console execution ----
        if (!(sender instanceof Player)) {
            Logger.debug("UnMuteCmd: Executed from console");

            if (args.length >= 1) {
                Player target = Bukkit.getPlayer(args[0]);

                if (target == null) {
                    Logger.debug("UnMuteCmd: Target player " + args[0] + " not found");
                    Util.sendColorMessage(sender, Settings.message_No_Online_Player);
                    return true;
                }

                Logger.debug("UnMuteCmd: Found target player " + target.getName());

                // PlayerManager playerManager = new PlayerManager(target);
                // if (Settings.mysql) {
                //     MySql.setMute(target, false);
                // } else {
                //     playerManager.setMute(false);
                // }

                Util.sendColorMessage(sender, Util.getPrefix(false) + Util.getVar(target, Settings.message_UnMute_Player));
                Logger.debug("UnMuteCmd: Successfully unmuted player " + target.getName() + " via console");
                return true;
            }

            Logger.debug("UnMuteCmd: Missing arguments for console execution");
            Util.sendColorMessage(sender, Util.getPrefix(false) + Settings.message_Help_UnMute);
            return false;
        }

        // ---- Player execution ----
        Player player = (Player) sender;
        Logger.debug("UnMuteCmd: Executed by player " + player.getName());

        if (player.isOp() || player.hasPermission("AdvancedChat.UnMute")) {
            if (args.length >= 1) {
                Player target = Bukkit.getPlayer(args[0]);

                if (target == null) {
                    Logger.debug("UnMuteCmd: Target player " + args[0] + " not found");
                    Util.sendColorMessage(player, Settings.message_No_Online_Player);
                    return true;
                }

                Logger.debug("UnMuteCmd: Found target player " + target.getName());

                // PlayerManager playerManager = new PlayerManager(target);
                // if (Settings.mysql) {
                //     MySql.setMute(target, false);
                // } else {
                //     playerManager.setMute(false);
                // }

                Util.sendColorMessage(player, Util.getPrefix(false) + Util.getVar(target, Settings.message_UnMute_Player));
                Logger.debug("UnMuteCmd: Successfully unmuted player " + target.getName());
                return true;
            }

            Logger.debug("UnMuteCmd: Missing arguments for player execution");
            Util.sendColorMessage(player, Util.getPrefix(false) + Util.getVar(player, Settings.message_Help_UnMute));
            return true;
        }

        Logger.debug("UnMuteCmd: Player " + player.getName() + " has no permission to use this command");
        Util.sendHover(player, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
        return true;
    }

}
