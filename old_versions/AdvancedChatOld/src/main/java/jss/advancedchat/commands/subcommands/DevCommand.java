package jss.advancedchat.commands.subcommands;

import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.storage.mysql.MySql;
import jss.advancedchat.utils.MessageUtils;
import jss.commandapi.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DevCommand extends SubCommand {
    public String name() {
        return "dev";
    }

    public String permission() {
        return "dev";
    }

    public boolean requiresPermission() {
        return true;
    }

    public boolean onCommand(CommandSender sender, String @NotNull [] args) {
        Player p = (Player) sender;
        MySql.setColor(p, args[1]);
        MessageUtils.sendColorMessage(sender,"Change Color: " + args[1]);
        return false;
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
