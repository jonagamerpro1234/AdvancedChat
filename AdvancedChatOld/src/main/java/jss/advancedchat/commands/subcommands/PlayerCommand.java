package jss.advancedchat.commands.subcommands;

import jss.advancedchat.files.utils.Settings;
import jss.commandapi.SubCommand;
import org.bukkit.command.CommandSender;

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
