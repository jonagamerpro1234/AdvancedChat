package jss.advancedchat.commands.subcommands;

import jss.commandapi.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.List;

public class GradientCmd extends SubCommand {

    public String name() {
        return "gradient";
    }

    public String permission() {
        return "command.gradient";
    }

    public boolean requiresPermission() {
        return true;
    }

    public boolean onCommand(CommandSender commandSender, String[] strings) {
        return false;
    }

    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
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
