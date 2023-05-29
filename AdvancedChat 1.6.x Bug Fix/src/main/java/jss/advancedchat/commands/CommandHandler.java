package jss.advancedchat.commands;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.commands.subcommands.HelpCmd;
import jss.advancedchat.commands.utils.SubCommand;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class CommandHandler implements TabExecutor {

    //private final ArrayList<SubCommand> subCommands = new ArrayList<>();
    private final HashSet<SubCommand> subCommands = new HashSet<>();


    public CommandHandler() {
        AdvancedChat plugin = AdvancedChat.get();
        PluginCommand pluginCommand = plugin.getCommand("AdTest");
        //noinspection ConstantConditions
        pluginCommand.setExecutor(this);
        pluginCommand.setTabCompleter(this);

        getSubCommands();
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (args.length >= 1){


            SubCommand subCommand = getSubCommands().stream().filter( s1 -> s1.name().equalsIgnoreCase(args[0])).findFirst().orElse(null);

            if(subCommand != null){
                if(sender instanceof Player && !sender.isOp() && !sender.hasPermission(subCommand.permission())){
                    Utils.sendColorMessage(sender, Utils.getPrefix(true) + Settings.message_NoPermission);
                    return true;
                }

                subCommand.perform(sender, args);
                return true;
            }

            Utils.sendColorMessage(sender, Utils.getPrefix(true) + Settings.message_Error_Args);
            return true;
        }
        Utils.sendColorMessage(sender, Utils.getPrefix(true) + Settings.message_Help_Cmd);
        return true;



        /* if(args.length >= 1){
            //busca el comando
            for (SubCommand s : getSubCommands()){
                //verifica que el comando coincida para ser ejecutado
                if (args[0].equalsIgnoreCase(s.name())){
                    //se ejecuta el comando
                    s.perform(sender,args);
                    return  true;
                }
            }
            //mensaje de error al no ser encontrado el subcomando
            Utils.sendColorMessage(sender, Utils.getPrefix(true) + Settings.message_Error_Args);
            return true;
        }
        //envia un mensaje de ayuda con el subcomando help de sugerencia para ver mas infomacion de otros comandos
        Utils.sendColorMessage(sender, Utils.getPrefix(true) + Settings.message_Help_Cmd);
        return true;*/
    }

    @Nullable
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }

    private Set<SubCommand> getSubCommands(){
        if(subCommands.isEmpty()){
            subCommands.add(new HelpCmd());
        }
        return subCommands;
    }

}
