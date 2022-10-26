package jss.advancedchat.commands.subcommands;

import org.bukkit.command.CommandSender;

import java.util.List;

public class SettingsCmd implements SubCommand {

    public String getName() {
        return "settings";
    }

    public String getDescription() {
        return null;
    }

    public String getSyntax() {
        return null;
    }

    public String getPermission() {
        return null;
    }

    public List<String> getTabCompletion(int index, String[] args) {
        return null;
    }

    public void perform(CommandSender sender, String[] args) {

    }
}
