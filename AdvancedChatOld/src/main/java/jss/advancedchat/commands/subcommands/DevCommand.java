package jss.advancedchat.commands.subcommands;

import jss.advancedchat.files.utils.Settings;
import jss.commandapi.SubCommand;
import org.bukkit.command.CommandSender;

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

    public boolean onCommand(CommandSender commandSender, String[] strings) {
        return false;
    }

    public boolean allowConsole() {
        return true;
    }

    public boolean isEnabled() {
        return false;
    }

    public String disabledMessage() {
        return Settings.lang_disableCommand;
    }
}
