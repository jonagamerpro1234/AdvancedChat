package jss.advancedchat.commands;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Perms;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.Util;
import jss.advancedchat.utils.logger.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ClearChatCmd implements CommandExecutor {

    private final EventUtils eventUtils = new EventUtils();

    public ClearChatCmd() {
        AdvancedChat plugin = AdvancedChat.get();
        Objects.requireNonNull(plugin.getCommand("AdClearChat")).setExecutor(this);
        Logger.debug("Command 'AdClearChat' registered successfully.");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        Logger.debug("Executing command /" + label + " with arguments: " + String.join(" ", args));

        if (!(sender instanceof Player)) {
            Logger.debug("Command executed from console. Performing global chat clear.");
            eventUtils.getClearChatAction();
            eventUtils.getServerMessage();
            Logger.debug("Console clear chat actions executed successfully.");
            return false;
        }

        Player player = (Player) sender;
        Logger.debug("Command executed by player: " + player.getName());

        if (player.isOp() || player.hasPermission(Perms.ac_cmd_clearchat)) {
            Logger.debug("Player has permission to use /clearchat.");

            if ((Settings.boolean_clearchat_bypass && player.isOp()) || player.hasPermission(Perms.ac_clearchat_bypass)) {
                Logger.debug("Player " + player.getName() + " has bypass permission for clear chat.");
                Util.sendColorMessage(player, Util.getPrefix(false) + Util.getVar(player, Settings.message_ClearChat_Staff));
                Logger.debug("Bypass message sent to player.");
                return true;
            } else {
                Logger.debug("Performing clear chat action for players.");
                eventUtils.getClearChatAction();
            }

            eventUtils.getPlayerMessage(player);
            Logger.debug("Clear chat confirmation message sent to player: " + player.getName());

        } else {
            Logger.debug("Player " + player.getName() + " does not have permission to use /clearchat.");
            Util.sendHover(player, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
        }

        Logger.debug("Command /" + label + " execution completed.");
        return true;
    }
}
