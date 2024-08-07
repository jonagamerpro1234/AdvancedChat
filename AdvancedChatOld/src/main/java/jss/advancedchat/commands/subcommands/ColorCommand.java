package jss.advancedchat.commands.subcommands;

import jss.advancedchat.files.utils.Settings;
import jss.commandapi.SubCommand;
import org.bukkit.command.CommandSender;

public class ColorCommand extends SubCommand {

    public String name() {
        return "color";
    }

    public String permission() {
        return "color";
    }

    public boolean requiresPermission() {
        return true;
    }

    public boolean onCommand(CommandSender sender, String[] args) {
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
