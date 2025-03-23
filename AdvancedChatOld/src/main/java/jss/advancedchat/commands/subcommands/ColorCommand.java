package jss.advancedchat.commands.subcommands;

import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.mysql.MySql;
import jss.advancedchat.utils.MessageUtils;
import jss.commandapi.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ColorCommand extends SubCommand {

    public String name() {
        return "color";
    }

    public String permission() {
        return "color";
    }

    public boolean requiresPermission() {
        return true;
    }

    public boolean onCommand(CommandSender sender, String @NotNull [] args) {

        if(args.length >= 3){
            Player target = Bukkit.getPlayer(args[1]);

            if (target == null) MessageUtils.sendColorMessage(sender, Settings.message_No_Online_Player);

            assert target != null;
            PlayerManager pm = new PlayerManager(target);

            if(args.length >= 4){

                if(args[3].equalsIgnoreCase("set")){

                    String colorName = args[4];
                    if (colorName == null) MessageUtils.sendColorMessage(sender, "<red> please use color name");

                    if(Settings.mysql){
                        MySql.setColor(target, colorName);
                    }else {
                        pm.setColor(colorName);
                    }
                    MessageUtils.sendColorMessage(sender, "Se ha cambiado el color correctamente");
                    return true;
                }
                MessageUtils.sendColorMessage(sender, "<gold> use /ac color <player> set <color>");
                return true;
            }
            MessageUtils.sendColorMessage(sender, "<yellow> Open Color Menu---");
            return true;
        }
        MessageUtils.sendColorMessage(sender, "<red> Open Color Menu---");
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
