package jss.advancedchat.commands.subcommands;

import jdk.internal.org.jline.reader.ParsedLine;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.commands.utils.SubCommand;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HelpCmd extends SubCommand {

    private final AdvancedChat plugin = AdvancedChat.get();

    public String name() {
        return "help";
    }

    @Override
    public String permission() {
        return "advancedchat.command.help";
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

        List<String> list = new ArrayList<>();

        switch (args.length){
            //al ser el argumento 0 no devuelve nada
            case 0:
                //al ser el primer argumento obtiene la lista de opciones diponibles para el args[1]
            case 1:

                list.add("option1");
                break;
                //al ser el primer argumento obtiene la lista de opciones diponibles para el args[2]
            case 2:

                list.add("option2");
                break;
                //al ser el primer argumento obtiene la lista de opciones diponibles para el args[3]
            case 3:
                //solo se auto completara si el el argumento coincide
                if(args[0].equalsIgnoreCase("option2")){
                    list.add("option8");
                }
                list.add("option3");
                list.add("option5");
                break;
                //al ser el primer argumento obtiene la lista de opciones diponibles para el args[4]
            case 4:

                list.add("option13");
                list.add("option12");
                break;
        }

        return list;
    }
}
