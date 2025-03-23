package jss.advancedchat.commands;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.commands.subcommands.*;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.utils.MessageUtils;
import jss.advancedchat.utils.Utils;
import jss.commandapi.SubCommand;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import org.bukkit.Bukkit;
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

        subCommands.addAll(Arrays.asList(new HelpCommand(), new ReloadCommand(), new InfoCommand(),
                new ColorCommand(), new PlayerCommand(), new GradientCommand(), new SettingsCommand(),
                new DevCommand()));
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

                        if (!sender.isOp() || (s.requiresPermission() && !sender.hasPermission("advancedchat.command." + s.permission()))) {
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
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String cmd, @NotNull String @NotNull [] args) {
        List<String> listOptions = new ArrayList<>();
        String lastArgs = args.length != 0 ? args[args.length - 1] : "";

        if(sender instanceof Player){
            Player player = (Player) sender;
            if(!player.isOp() || !player.hasPermission("advancedchat.command.tabcomplete")) return new ArrayList<>();
        }

        switch (args.length){
            case 0:
            case 1:
                listOptions.add("help");
                listOptions.add("reload");
                listOptions.add("info");
                listOptions.add("color");
                listOptions.add("player");
                listOptions.add("gradient");
                listOptions.add("settings");
                break;
            case 2:
                if (args[0].equalsIgnoreCase("color") || args[0].equalsIgnoreCase("player")
                        || args[0].equalsIgnoreCase("gradient") || args[0].equalsIgnoreCase("settings")) {
                    Bukkit.getOnlinePlayers().forEach((p) -> listOptions.add(p.getName()));
                }
                break;
            case 3:
                if (args[0].equalsIgnoreCase("color") || args[0].equalsIgnoreCase("gradient")) {
                    listOptions.add("set");
                }
                if (args[0].equalsIgnoreCase("settings")) {
                    listOptions.add("low-mode");
                    listOptions.add("group");
                    listOptions.add("msg");
                    listOptions.add("chat");
                }
                break;
            case 4:
                if (args[0].equalsIgnoreCase("color") || args[0].equalsIgnoreCase("gradient")) {
                    listOptions.addAll(Arrays.asList("black", "white", "dark_gray", "gray", "dark_purple",
                            "light_purple", "dark_aqua", "aqua", "gold", "yellow", "green", "dark_green", "blue",
                            "dark_blue", "red", "dark_red"));
                }
                if (args[0].equalsIgnoreCase("settings")) {
                    if (args[2].equalsIgnoreCase("chat") || args[2].equalsIgnoreCase("low-mode") || args[2].equalsIgnoreCase("msg")) {
                        listOptions.add("true");
                        listOptions.add("false");
                        break;
                    }

                    if(args[2].equalsIgnoreCase("group")){
                        listOptions.add("set");
                    }

                    break;
                }
                break;
            case 5:
                if (args[0].equalsIgnoreCase("gradient")) {
                    listOptions.add("first");
                    listOptions.add("second");
                }

                if(args[0].equalsIgnoreCase("settings")){
                    if(args[2].equalsIgnoreCase("group")){
                        if (args[3].equalsIgnoreCase("set")){
                            if(HookManager.get().getLuckPermsHook().isEnabled()){
                                for(Group availableGroups : LuckPermsProvider.get().getGroupManager().getLoadedGroups()){
                                    listOptions.add(availableGroups.getName());
                                }
                            }
                        }
                    }
                }
                break;
        }

        return Utils.setLimitTab(listOptions, lastArgs);
    }

    public ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }
}
