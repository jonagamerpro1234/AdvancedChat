package jss.advancedchat.commands;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.commands.subcommands.HelpCommand;
import jss.advancedchat.commands.subcommands.InfoCommand;
import jss.advancedchat.commands.subcommands.ReloadCommand;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.MessageUtils;
import jss.commandapi.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandHandler implements TabExecutor {

    private final AdvancedChat plugin = AdvancedChat.get();
    private final ArrayList<SubCommand> subCommands = new ArrayList<>();

    public void register(){
        PluginCommand pluginCommand = this.plugin.getCommand("AdvancedChatTest");
        assert pluginCommand != null;
        pluginCommand.setExecutor(this);
        pluginCommand.setTabCompleter(this);

        subCommands.addAll(Arrays.asList(new HelpCommand(), new ReloadCommand(), new InfoCommand()));
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String @NotNull [] args) {
        if(args.length >= 1){

            for(SubCommand s :  getSubCommands()){
                if (args[0].equalsIgnoreCase(s.name())){
                    if (s.isEnabled()){

                        if (!s.allowConsole() && !(sender instanceof Player)) {
                            MessageUtils.sendColorMessage(sender, Settings.lang_allowConsoleCommand);
                            return true;
                        }

                        if (!sender.isOp() || (s.requiresPermission() && !sender.hasPermission("advancedchat." + s.permission()))) {
                            MessageUtils.sendColorMessage(sender, Settings.lang_noPermission);
                            return true;
                        }

                        s.onCommand(sender, args);
                        return true;
                    } else {
                        MessageUtils.sendColorMessage(sender, s.disabledMessage());
                    }
                    return true;
                }
            }

            MessageUtils.sendColorMessage(sender, Settings.lang_unknownArguments);
            return true;
        }

        MessageUtils.sendColorMessage(sender, Settings.lang_usageMainCommand);
        return true;
    }

    @Nullable
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }

    public ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }
}
