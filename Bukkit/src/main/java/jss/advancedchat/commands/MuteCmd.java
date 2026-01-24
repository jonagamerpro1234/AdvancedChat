package jss.advancedchat.commands;

import jss.commandapi.BaseCommand;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class MuteCmd extends BaseCommand {

    public MuteCmd() {
        this.name("admute");
    }

    @Override
    public boolean onCommandMain(@NotNull CommandSender sender, String[] args) {
        sender.sendMessage("Test Commands");
        return true;
    }
}
