package jss.advancedchat.commands.utils;

import jss.advancedchat.commands.argumentmatchers.ContainingAllCharsOfStringArgumentMatcher;
import jss.advancedchat.commands.subcommands.HelpCmd;
import jss.advancedchat.commands.subcommands.ReloadCmd;

public class CommandUtils extends CommandManager{

    public CommandUtils() {
        super("No tienes permisos para usar este comando", new ContainingAllCharsOfStringArgumentMatcher());
    }

    @Override
    protected void registerSubCommands() {
        subCommands.add(new HelpCmd());
        subCommands.add(new ReloadCmd());
    }
}
