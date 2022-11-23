package jss.advancedchat.commands.subcommands;

import jss.advancedchat.commands.utils.SubCommand;
import jss.advancedchat.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ColorCmd extends SubCommand {

    public String name() {
        return "color";
    }

    public boolean perform(CommandSender sender, String[] args) {
        if(!(sender instanceof Player)){
            Utils.sendColorMessage(sender, "This command cannot be used in the console por el momento");
            return false;
        }

        return true;
    }

    public List<String> tabComplete(CommandSender sender, String @NotNull [] args) {
        List<String> listOptions = new ArrayList<>();
        String lastArgs = args.length !=  0 ? args[args.length - 1 ]  : "";
        Player player = (Player) sender;

        if(!player.isOp() || !Utils.setPerm(player, "tabcomplete")) return new ArrayList<>();

        switch (args.length){
            case 0:
            case 1:
                Bukkit.getOnlinePlayers().forEach( p -> listOptions.add(p.getName()));
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
}
