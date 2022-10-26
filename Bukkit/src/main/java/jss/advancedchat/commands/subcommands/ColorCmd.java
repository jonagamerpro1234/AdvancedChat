package jss.advancedchat.commands.subcommands;

import jss.advancedchat.commands.utils.SubCommand;
import jss.advancedchat.utils.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ColorCmd extends SubCommand {


    public String name() {
        return "color";
    }

    public boolean perform(CommandSender sender, String[] args) {
        if(!(sender instanceof Player)){
            Utils.sendColorMessage(sender, "Este comando no se puede usar en la consola por el momento");
            return false;
        }



        return true;
    }

    public List<String> tabComplete(CommandSender sender, String @NotNull [] args) {
        List<String> listOptions = new ArrayList<>();
        String lastArgs = args.length !=  0 ? args[args.length - 1 ]  : "";
        Player player = (Player) sender;

        if(!player.isOp() || !Utils.setPerm(player, "tabcomplete")) return null;

        return Utils.setLimitTab(listOptions,lastArgs);
    }
}
