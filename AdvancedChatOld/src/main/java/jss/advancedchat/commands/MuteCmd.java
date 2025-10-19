package jss.advancedchat.commands;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.json.manager.JsonPlayerStorage;
import jss.advancedchat.storage.json.model.PlayerData;
import jss.advancedchat.storage.mysql.MySql;
import jss.advancedchat.utils.MessageUtils;
import jss.advancedchat.utils.Perms;
import jss.advancedchat.utils.Util;
import jss.advancedchat.utils.logger.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MuteCmd implements CommandExecutor {

    private final AdvancedChat plugin = AdvancedChat.get();

    public MuteCmd() {
        AdvancedChat plugin = AdvancedChat.get();
        Objects.requireNonNull(plugin.getCommand("AdMute")).setExecutor(this);
        Logger.debug("MuteCmd: Command executor set for AdMute");
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String @NotNull [] args) {

        Logger.debug("MuteCmd: onCommand called by " + sender.getName() + " with args: " + String.join(", ", args));

        if (args.length >= 1) {

            if (!sender.isOp() && !sender.hasPermission("advancedchat.command.mute")) {
                Logger.debug("MuteCmd: Sender " + sender.getName() + " lacks mute permission");
                MessageUtils.sendColorMessage(sender, Settings.lang_noPermission);
                return true;
            }

            if (args[0] == null) {
                Logger.debug("MuteCmd: args[0] is null");
                Util.sendColorMessage(sender, Util.getPrefix(false) + Settings.message_Help_Mute);
                return true;
            } else {
                Player target = Bukkit.getPlayer(args[0]);

                if (target == null) {
                    Logger.debug("MuteCmd: Target player " + args[0] + " not found");
                    MessageUtils.sendColorMessage(sender, "&c Use de valid Player Name!");
                    return true;
                }

                Logger.debug("MuteCmd: Found target player " + target.getName());

                JsonPlayerStorage storage = new JsonPlayerStorage(plugin.getJsonPlayerFile());
                PlayerManager manager = new PlayerManager(storage);
                PlayerData data = manager.loadPlayerData(target);

                if(canBypassMute(target,true)) {
                    Logger.debug("MuteCmd: Target " + target.getName() + " can bypass mute");
                    return true;
                }

                if (data.getPreferences().getMuteSettings().isMute()) {
                    Logger.debug("MuteCmd: Target " + target.getName() + " is already muted");
                    MessageUtils.sendColorMessage(sender, "&c Player is muted!");
                    return true;
                }

                if (Settings.mysql) {
                    Logger.debug("MuteCmd: Using MySQL to set mute for " + target.getName());
                    MySql.setMute(target, true);
                } else {
                    Logger.debug("MuteCmd: Using JSON storage to set mute for " + target.getName());
                    data.getPreferences().getMuteSettings().setMute(true);
                    manager.savePlayerData(target.getName(), data);
                }

                Util.sendColorMessage(target, Util.getPrefix(false) + Util.getVar(target, Settings.message_Mute_Player));
                Util.sendColorMessage(sender, Util.getPrefix(false) + Util.getVar(target, Settings.message_Mute_Player));

                Logger.debug("MuteCmd: Successfully muted player " + target.getName());
                return true;
            }
        }

        Logger.debug("MuteCmd: Insufficient arguments");
        Util.sendColorMessage(sender, Util.getPrefix(false) + Settings.message_Help_Mute);
        return true;
    }

    @SuppressWarnings("SameParameterValue")
    private boolean canBypassMute(@NotNull Player player,  boolean isEnabled) {
        Logger.debug("canBypassMute: Checking bypass for " + player.getName());

        if(isEnabled){
            if (player.hasPermission(Perms.ac_mute_bypass_all) || player.isOp()) {
                Logger.debug("canBypassMute: Player " + player.getName() + " has bypass_all or isOp");
                Util.sendColorMessage(player, Settings.message_mute_bypass);
                return true;
            }

            if (Settings.AllowBypassMute) {
                if (player.hasPermission(Perms.ac_mute_bypass)) {
                    Logger.debug("canBypassMute: Player " + player.getName() + " has bypass");
                    Util.sendColorMessage(player, Settings.message_mute_bypass);
                    return true;
                }
            }
        }

        Logger.debug("canBypassMute: Player " + player.getName() + " cannot bypass");
        return false;
    }

}
