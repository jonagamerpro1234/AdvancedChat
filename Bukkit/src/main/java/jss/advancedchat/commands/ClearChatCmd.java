package jss.advancedchat.commands;

import jss.commandapi.BaseCommand;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ClearChatCmd extends BaseCommand {

    public ClearChatCmd(){
        this.name("adclearchat");
    }


    public boolean onCommandMain(@NotNull CommandSender sender, String[] args) {

        return true;
    }
}
