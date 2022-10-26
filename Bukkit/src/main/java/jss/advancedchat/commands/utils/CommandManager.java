package jss.advancedchat.commands.utils;

import org.bukkit.command.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class CommandManager implements TabExecutor {

    protected final Set<SubCommand> subCommands = new HashSet<>();
    protected final ArgumentMatcher argumentMatcher;
    protected final String noPermMessage;

    public CommandManager(String noPermissionMessage, ArgumentMatcher argumentMatcher) {
        this.noPermMessage = noPermissionMessage;
        this.argumentMatcher = argumentMatcher;
        registerSubCommands();

    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if(args.length == 0){

            SubCommand helpSubCommand = getHelpSubCommand();

            if(helpSubCommand != null && sender.hasPermission(helpSubCommand.getPermission())){
                helpSubCommand.perform(sender,args);
                return true;
            }
            return  false;
        }

        SubCommand subCommand = subCommands.stream().filter(subCommand1 -> subCommand1.getName().equalsIgnoreCase(args[0])).findAny().orElse(getHelpSubCommand());

        if(subCommand == null) return false;

        if(sender.hasPermission(subCommand.getPermission())){
            subCommand.perform(sender, args);
        }else{
            sender.sendMessage("No Tienes Permisos");
        }

        return true;
    }

    @Nullable
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String @NotNull [] args) {

        if(args.length == 0) return null;

        if(args.length == 1){
            List<String> subCommandTab = subCommands.stream().filter(SubCommand -> sender.isOp() || sender.hasPermission(SubCommand.getPermission())).map(SubCommand::getName).collect(Collectors.toList());
            return getMatchingStrings(subCommandTab, args[args.length - 1],argumentMatcher);
        }

        SubCommand subCommand = subCommands.stream().filter(subCommand1 -> subCommand1.getName().equalsIgnoreCase(args[0])).findAny().orElse(null);
        if (subCommand == null) return null;

        List<String> subCommandTabComplete = subCommand.getTabCompletion(args.length - 2, args);

        return getMatchingStrings(subCommandTabComplete, args[args.length - 1], argumentMatcher);
    }

    public List<String> getMatchingStrings(List<String> tabCompletions, String arg, ArgumentMatcher argumentMatcher){

        if(tabCompletions == null || arg == null) return null;

        List<String> result = argumentMatcher.filter(tabCompletions,arg);

        Collections.sort(result);

        return result;
    }

    public void registerCommandManager(@NotNull JavaPlugin main, String cmdName) {
        PluginCommand cmd = main.getCommand(cmdName);

        assert cmd != null;
        cmd.setExecutor(this);
        cmd.setTabCompleter(this);
        cmd.setPermissionMessage(noPermMessage);
    }

    protected abstract void registerSubCommands();

    protected SubCommand getHelpSubCommand(){
        return subCommands.stream().filter(subCommand -> subCommand.getName().equalsIgnoreCase("help")).findAny().orElse(null);
    }

    public Set<SubCommand> getSubCommands() {
        return new HashSet<>(subCommands);
    }
}
