package jss.advancedchat.bungee.commands;

import jss.advancedchat.bungee.utils.UtilsBunge;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class AdvancedChatCmd extends Command {

    public AdvancedChatCmd() {
        super("AdvancedChat");
    }

    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            UtilsBunge.sendBungeeColorMessage(sender, "&6Test Sender message ");

            return;
        }
        ProxiedPlayer j = (ProxiedPlayer) sender;
        
        if(args.length >= 1) {
        	if(args[0].equalsIgnoreCase("reload")) {
        		UtilsBunge.sendBungeeColorMessage(j, "&6Test Player message ");
        		return;
        	}
        	
        	if(args[0].equalsIgnoreCase("sync")) {
        		return;
        	}
        	
        }
        return;
    }



}
