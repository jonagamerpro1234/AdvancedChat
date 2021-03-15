package jss.advancedchat.bungee.commands;

import jss.advancedchat.bungee.utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class AdvancedChatCmd extends Command {

    public AdvancedChatCmd() {
        super("AdvancedChat");

    }

    public void execute(CommandSender sender, String[] args) {
        if ((sender instanceof ProxiedPlayer)) {

            ProxiedPlayer j = (ProxiedPlayer) sender;

            Utils.sendBungeeColorMessage(j, "&6Test Player message ");
            Utils.sendBungeeColorMessage(sender, "&6Test Sender message ");
            //this.onCommand(sender, args);
        }

    }



}
