package jss.advancedchat.commands.subcommands;

import jss.advancedchat.utils.Utils;
import jss.commandapi.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColorCmd extends SubCommand {

    public String name() {
        return "color";
    }

    public String permission() {
        return "command.color";
    }

    public boolean requiresPermission() {
        return true;
    }

    public boolean onCommand(CommandSender commandSender, String[] strings) {
        return false;
    }

    public List<String> tabComplete(CommandSender sender, String @NotNull [] args) {
        List<String> listOptions = new ArrayList<>();
        String lastArgs = args.length !=  0 ? args[args.length - 1 ]  : "";
        Player player = (Player) sender;

        if(!player.isOp() || !Utils.hasPerm(player, "tabcomplete")) return new ArrayList<>();

        switch (args.length){
            case 0:
            case 1:
                Bukkit.getOnlinePlayers().forEach(p -> listOptions.add(p.getName()));
                break;
            case 2:
                listOptions.add("set");
                break;
            case 3:
                listOptions.addAll(Arrays.asList("black", "white", "dark_gray", "gray", "dark_purple",
                        "light_purple", "dark_aqua", "aqua", "gold", "yellow", "green", "dark_green", "blue",
                        "dark_blue", "red", "dark_red"));
                break;
        }

        return Utils.setLimitTab(listOptions,lastArgs);
    }

    public boolean allowConsole() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public String disabledMessage() {
        return "";
    }
}
