package jss.advancedchat.commands.subcommands;

import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.Util;
import jss.advancedchat.utils.logger.Logger;
import jss.commandapi.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SettingsCommand extends SubCommand {

    public String name() {
        return "settings";
    }

    public String permission() {
        return "settings";
    }

    public boolean requiresPermission() {
        return true;
    }

    public boolean onCommand(CommandSender sender, String @NotNull [] args) {

        if(args.length >= 2){

            String playerName = args[1];

            if (playerName.isEmpty()) Logger.debug("&6Select the player name for open inventory");

            Player target = Bukkit.getPlayer(playerName);

            if (target == null)
                Util.sendColorMessage(sender, Settings.message_No_Online_Player);

            if(args.length >= 3){

                if(args[2].equalsIgnoreCase("low-mode")){

                    if(args.length >= 4){
                        if(args[3].equalsIgnoreCase("true")){
                            Logger.debug(" args 4 - 3 true ");
                            return true;
                        }
                        if(args[3].equalsIgnoreCase("false")){
                            Logger.debug(" args 4 - 3 false ");
                            return true;
                        }

                        Logger.debug(" args 4 - 3 usage true/false ");
                        return true;
                    }

                    Logger.debug("args 3 - 2 usage a/b/c/d/e");
                    return true;
                }
                Logger.debug("n/a args");
                return true;
            }
            return true;
        }
        //open inv settings
        Logger.debug("n/a args");
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
