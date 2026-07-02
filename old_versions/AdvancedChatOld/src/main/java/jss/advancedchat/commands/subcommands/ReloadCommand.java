package jss.advancedchat.commands.subcommands;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.MessageUtils;
import jss.commandapi.SubCommand;
import org.bukkit.command.CommandSender;

public class ReloadCommand extends SubCommand {

    private final AdvancedChat plugin = AdvancedChat.get();

    public String name() {
        return "reload";
    }

    public String permission() {
        return "reload";
    }

    public boolean requiresPermission() {
        return true;
    }

    public boolean onCommand(CommandSender sender, String[] args) {
        plugin.reloadAllFiles();
        MessageUtils.sendColorMessage(sender, Settings.lang_prefix + Settings.lang_reloadCommand);
        return true;
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
