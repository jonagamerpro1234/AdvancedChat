package jss.advancedchat.commands.subcommands;

import jdk.internal.org.jline.reader.ParsedLine;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.commands.utils.SubCommand;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class HelpCmd extends SubCommand {

    private final AdvancedChat plugin = AdvancedChat.get();

    public String name() {
        return "help";
    }

    public boolean perform(CommandSender sender, String[] args) {
        if(!(sender instanceof Player)){
            List<String> help = Settings.message_Help_List;
            Utils.sendColorMessage(sender, "&5-=-=-=-=-=-=-=-=-=-=-=&6[&d" + this.plugin.name + "&6]&5=-=-=-=-=-=-=-=-=-=-=-");
            for (String text : help) {
                Utils.sendColorMessage(sender, text);
            }
            Utils.sendColorMessage(sender, "&5-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

            return false;
        }else {

            Player player = (Player) sender;

            if (player.isOp() || player.hasPermission("advancedchat.command.help")){
                List<String> help = Settings.message_Help_List;
                Utils.sendColorMessage(sender, "&5-=-=-=-=-=-=-=-=-=-=-=&6[&d" + this.plugin.name + "&6]&5=-=-=-=-=-=-=-=-=-=-=-");
                for (String text : help) {
                    Utils.sendColorMessage(sender, text);
                }
                Utils.sendColorMessage(sender, "&5-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            }else{
                Utils.sendHoverEvent(player, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
            }
        }
        return true;
    }

    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
