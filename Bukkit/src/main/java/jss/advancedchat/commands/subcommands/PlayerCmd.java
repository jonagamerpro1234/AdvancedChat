package jss.advancedchat.commands.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerCmd implements SubCommand {

    public String getName() {
        return "player";
    }

    public String getDescription() {
        return null;
    }

    public String getSyntax() {
        return null;
    }

    public String getPermission() {
        return "advancedchat.command.player";
    }

    public List<String> getTabCompletion(int index, String[] args) {

        if (index == 0) {
            return Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName).collect(Collectors.toList());
        }

        return null;
    }

    public void perform(CommandSender sender, String[] args) {

    }
}
