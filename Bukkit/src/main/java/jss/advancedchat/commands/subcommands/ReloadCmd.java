package jss.advancedchat.commands.subcommands;

import jss.advancedchat.utils.MessageUtils;
import jss.advancedchat.utils.Utils;
import jss.commandapi.SubCommand;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.graalvm.compiler.core.common.util.Util;
import org.jetbrains.annotations.NotNull;

public class ReloadCmd extends SubCommand {

    public String name() {
        return "reload";
    }

    public String permission() {
        return "command.reload";
    }

    public boolean requiresPermission() {
        return true;
    }

    public boolean onCommand(CommandSender sender, String @NotNull [] args) {

        if(args.length >= 2){
            Utils.sendColorMessage(sender, "Color Name: " + MessageUtils.convertColorWhitTag(args[1]));
            return true;
        }
        Utils.sendColorMessage(sender, "Test reload");
        return true;
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
