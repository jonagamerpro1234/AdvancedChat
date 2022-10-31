package jss.advancedchat.commands.subcommands;

import jss.advancedchat.commands.utils.SubCommand;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HelpCmd extends SubCommand {

    public String name() {
        return "help";
    }

    public boolean perform(@NotNull CommandSender sender, String[] args) {
        sender.sendMessage("test help sub command");
        return false;
    }

    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
