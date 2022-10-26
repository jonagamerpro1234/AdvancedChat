package jss.advancedchat.commands.utils;

import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class SubCommand {

    public abstract String name();

    public abstract boolean perform(CommandSender sender, String[] args);

    public abstract List<String> tabComplete(CommandSender sender, String[] args);

}
