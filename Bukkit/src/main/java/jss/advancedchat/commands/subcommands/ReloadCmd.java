package jss.advancedchat.commands.subcommands;

import jss.commandapi.SubCommand;
import org.bukkit.command.CommandSender;

public class ReloadCmd extends SubCommand {

    public String name() {
        return "reload";
    }

    public String permission() {
        return "command.reload";
    }

    public boolean requiresPermission() {
        return true;
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
        return null;
    }

}
