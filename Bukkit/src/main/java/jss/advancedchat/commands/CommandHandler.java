package jss.advancedchat.commands;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.commands.subcommands.*;
import jss.advancedchat.commands.utils.SubCommand;
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

    public CommandHandler(){
        AdvancedChat plugin = AdvancedChat.get();
        PluginCommand pluginCommand = plugin.getCommand("AdvancedChat");
        assert pluginCommand != null;
        pluginCommand.setExecutor(this);
        pluginCommand.setTabCompleter(this);
        subCommands.addAll(Arrays.asList(new ReloadCmd(), new HelpCmd(), new InfoCmd(), new ColorCmd(), new PlayerCmd(), new SettingsCmd()));
    }

    public ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if(args.length >= 1){
            for(SubCommand s : getSubCommands()){
                if(args[0].equalsIgnoreCase(s.name())){
                    //run subcommand
                    s.perform(sender,args);
                    return true;
                }
            }
            Utils.sendColorMessage(sender, "");
            return true;
        }
        //send usage of message of the main command
        Utils.sendColorMessage(sender, "");
        return true;
    }

    @Nullable
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        for(SubCommand s : getSubCommands()){
            if(args[0].equalsIgnoreCase(s.name())){
                return s.tabComplete(sender,args);
            }
        }
        return null;
    }


}
