package jss.advancedchat.commands.subcommands;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.Utils;
import jss.commandapi.SubCommand;
import org.bukkit.command.CommandSender;

public class InfoCommand extends SubCommand {

    private final AdvancedChat plugin = AdvancedChat.get();

    public String name() {
        return "info";
    }

    public String permission() {
        return "info";
    }

    public boolean requiresPermission() {
        return false;
    }

    public boolean onCommand(CommandSender sender, String[] args) {
        Utils.getInfoPlugin(sender, plugin.name, plugin.version, plugin.latestVersion);
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
