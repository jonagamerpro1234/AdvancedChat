package jss.advancedchat.commands.subcommands;



import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.MessageUtils;
import jss.commandapi.SubCommand;
import org.bukkit.command.CommandSender;

public class HelpCommand extends SubCommand {

    public String name() {
        return "help";
    }

    public String permission() {
        return "help";
    }

    public boolean requiresPermission() {
        return true;
    }

    public boolean onCommand(CommandSender sender, String[] args) {
        for(String msg : Settings.lang_helpCommand){
            MessageUtils.sendColorMessage(sender, msg);
        }
        return false;
    }

    public boolean allowConsole() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public String disabledMessage() {
        return Settings.lang_disableCommand;
    }

}
