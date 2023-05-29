package jss.advancedchat.commands;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.commands.subcommands.*;
import jss.advancedchat.utils.Utils;
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
        PluginCommand pluginCommand = plugin.getCommand("advancedchat");
        assert pluginCommand != null;
        pluginCommand.setExecutor(this);
        pluginCommand.setTabCompleter(this);

        subCommands.addAll(Arrays.asList(
                new HelpCmd(),
                new ReloadCmd(),
                new InfoCmd(),
                new PlayerCmd(),
                new ColorCmd(),
                new GradientCmd(),
                new SettingsCmd()
        ));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String @NotNull [] args) {
        if(args.length >= 1){
            for(SubCommand s : getSubCommands()){
                if (args[0].equalsIgnoreCase(s.name())){
                    if (s.isEnabled()){

                        if (!s.allowConsole() && !(sender instanceof Player)) {
                            Utils.sendColorMessage(sender, "nousagecommadninconsole");
                            return true;
                        }

                        if (s.requiresPermission() && !sender.hasPermission("advancedchat." + s.permission())) {
                            Utils.sendColorMessage(sender, "nopermission message");
                            return true;
                        }

                        s.onCommand(sender, args);
                        return true;
                    } else {
                        Utils.sendColorMessage(sender, s.disabledMessage());
                    }
                    return true;
                }
            }

            Utils.sendColorMessage(sender, "error not exists subcommand");
            return true;
        }
        Utils.sendColorMessage(sender, "use /ac help");
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String alias, String @NotNull [] args) {
        List<String> listOptions = new ArrayList<>();
        String lastArgs = args.length != 0 ? args[args.length - 1] : "";

        Player player = (Player) sender;

        if(!player.isOp() || !player.hasPermission("advancedchat.command.tabcomplete")) return new ArrayList<>();

        switch (args.length){
            case 0:
            case 1:
                listOptions.add("info");
                listOptions.add("help");
                listOptions.add("reload");
                listOptions.add("player");
                listOptions.add("color");
                listOptions.add("gradient");
                listOptions.add("settings");
                break;
        }

        return Utils.setLimitTab(listOptions, lastArgs);
    }


    public ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }

}
