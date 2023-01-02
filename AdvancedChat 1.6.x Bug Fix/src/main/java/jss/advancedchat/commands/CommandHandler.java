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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandHandler implements TabExecutor {

    private final ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandHandler() {
        AdvancedChat plugin = AdvancedChat.get();
        PluginCommand pluginCommand = plugin.getCommand("AdTest");
        //noinspection ConstantConditions
        pluginCommand.setExecutor(this);
        pluginCommand.setTabCompleter(this);

        this.subCommands.addAll(Arrays.asList(
                new HelpCmd()
        ));
    }

    public ArrayList<SubCommand> getSubCommands() { return this.subCommands; }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if(args.length >= 1){

            for (SubCommand s : getSubCommands()){
                if (args[0].equalsIgnoreCase(s.name())){
                    s.perform(sender,args);
                    return  true;
                }
            }

            Utils.sendColorMessage(sender, Utils.getPrefix(true) + Settings.message_Error_Args);
            return true;
        }
        Utils.sendColorMessage(sender, Utils.getPrefix(true) + Settings.message_Help_Cmd);
        return true;
    }

    @Nullable
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        return null;
    }

}
