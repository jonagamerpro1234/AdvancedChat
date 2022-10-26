package jss.advancedchat.commands.subcommands;

import jss.advancedchat.commands.utils.SubCommand;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ReloadCmd implements SubCommand {
    
    public String getName() {
        return "reload";
    }
    
    public String getDescription() {
        return "Recarga todo los achivos del plugin";
    }

    
    public String getSyntax() {
        return "/Ad reload";
    }

    public String getPermission() {
        return "advancedchat.command.reload";
    }

    public List<String> getTabCompletion(int index, String[] args) {
        return null;
    }

    public void perform(@NotNull CommandSender sender, String[] args) {
        sender.sendMessage("test relaod sub command");
    }
}
