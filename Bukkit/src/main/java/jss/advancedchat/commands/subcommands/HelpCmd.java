package jss.advancedchat.commands.subcommands;

import jss.advancedchat.inventories.ColorInventory;
import jss.advancedchat.utils.Logger;
import jss.commandapi.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCmd extends SubCommand {

    public String name() {
        return "help";
    }

    public String permission() {
        return null;
    }

    public boolean requiresPermission() {
        return false;
    }

    public boolean onCommand(CommandSender sender, String[] strings) {
        Player p = (Player) sender;
        ColorInventory colorInventory = new ColorInventory();
        colorInventory.open(p);
        Logger.debug("Open Inventory: Color");
        return true;
    }

    public boolean allowConsole() {
        return false;
    }

    public boolean isEnabled() {
        return false;
    }

    public String disabledMessage() {
        return null;
    }


}
