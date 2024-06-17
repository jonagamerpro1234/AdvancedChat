package jss.advancedchat.commands.subcommands;

import jss.advancedchat.files.utils.Settings;
import jss.commandapi.SubCommand;
import org.bukkit.command.CommandSender;

public class ReloadCommand extends SubCommand {

    public String name() {
        return "reload";
    }

    public String permission() {
        return "reload";
    }

    public boolean requiresPermission() {
        return true;
    }

    public boolean onCommand(CommandSender commandSender, String[] strings) {
        return false;
    }

    public boolean allowConsole() {
        return false;
    }

    public boolean isEnabled() {
        return false;
    }

    public String disabledMessage() {
        return Settings.lang_disableCommand;
    }

}
