package jss.advancedchat.commands.subcommands;

import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.MessageUtils;
import jss.advancedchat.utils.Util;
import jss.advancedchat.utils.logger.Logger;
import jss.commandapi.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerCommand extends SubCommand {

    public String name() {
        return "player";
    }

    public String permission() {
        return "player";
    }

    public boolean requiresPermission() {
        return true;
    }

    public boolean onCommand(CommandSender sender, String @NotNull [] args) {

        if(args.length >= 2){

            String playerName = args[1];

            if (playerName.isEmpty()) Logger.debug("&6Select the player name for open inventory");

            Player target = Bukkit.getPlayer(playerName);

            if (target == null)
                Util.sendColorMessage(sender, Settings.message_No_Online_Player);

            return true;
        }
        MessageUtils.sendColorMessage(sender, " ! > Usa /ac player <player name> <option> set <true|false|value>");
        return true;
    }

    public boolean allowConsole() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public String disabledMessage() {
        return Settings.lang_disableCommand;
    }

}
