package jss.advancedchat.commands.subcommands;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HelpCmd implements SubCommand {

    public String getName() {
        return "help";
    }

    public String getDescription() {
        return "muestra todo la informacion del los comandos del plugin";
    }

    public String getSyntax() {
        return "/Ac help";
    }

    public String getPermission() {
        return "advancedchat.command.help";
    }

    public List<String> getTabCompletion(int index, String[] args) {
        return null;
    }

    public void perform(@NotNull CommandSender sender, String[] args) {
        sender.sendMessage("test help sub command");
    }
}
