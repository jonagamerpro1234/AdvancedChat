package jss.advancedchat.commands.subcommands;

import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.Utils;
import jss.commandapi.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PlayerCmd extends SubCommand {

    public String name() {
        return "player";
    }

    public String permission() {
        return "command.player";
    }

    public boolean requiresPermission() {
        return true;
    }

    public boolean onCommand(CommandSender sender, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;

        if(!player.isOp() || !Utils.hasPerm(player,"command.player")){
            Utils.sendColorMessage(player, Utils.getPrefix(false) + Settings.messages_noPermission);
            return true;
        }

        if(args.length >= 1){

            return true;
        }

        return true;
    }

    public List<String> tabComplete(CommandSender sender, String @NotNull [] args) {
        List<String> options = new ArrayList<>();
        String lastArgs = args.length != 0 ? args[args.length - 1] : "";

        switch (args.length){
            case 0:
            case 1:
                Bukkit.getOnlinePlayers().forEach( p -> options.add(p.getName()));
                break;
        }

        return Utils.setLimitTab(options, lastArgs);
    }

    public boolean allowConsole() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public String disabledMessage() {
        return null;
    }

}
