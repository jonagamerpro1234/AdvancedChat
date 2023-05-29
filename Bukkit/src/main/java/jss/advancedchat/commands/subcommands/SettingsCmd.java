package jss.advancedchat.commands.subcommands;

import jss.commandapi.SubCommand;
import org.bukkit.command.CommandSender;

public class SettingsCmd extends SubCommand {

    public String name() {
        return "settings";
    }

    public String permission() {
        return "command.settings";
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
