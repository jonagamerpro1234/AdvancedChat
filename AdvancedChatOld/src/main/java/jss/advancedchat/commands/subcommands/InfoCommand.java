package jss.advancedchat.commands.subcommands;

import jss.commandapi.SubCommand;
import org.bukkit.command.CommandSender;

public class InfoCommand extends SubCommand {
    public String name() {
        return "info";
    }

    public String permission() {
        return "";
    }

    public boolean requiresPermission() {
        return false;
    }

    public boolean onCommand(CommandSender commandSender, String[] strings) {
        return false;
    }

    public boolean allowConsole() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public String disabledMessage() {
        return "";
    }
}
