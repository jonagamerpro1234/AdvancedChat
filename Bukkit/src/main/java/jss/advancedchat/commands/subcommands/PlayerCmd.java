package jss.advancedchat.commands.subcommands;

import jss.advancedchat.commands.utils.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.List;

public class PlayerCmd extends SubCommand {

    public String name() {
        return "player";
    }

    public boolean perform(CommandSender sender, String[] args) {

        return false;
    }

    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
