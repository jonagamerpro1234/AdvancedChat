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
        if (!(sender instanceof ProxiedPlayer)) {
            Utils.sendBungeeColorMessage(sender, "&6Test Sender message ");
            //this.onCommand(sender, args);
            return;
        }
        ProxiedPlayer j = (ProxiedPlayer) sender;
        
        if(args.length >= 1) {
        	if(args[0].equalsIgnoreCase("test")) {
        		Utils.sendBungeeColorMessage(j, "&6Test Player message ");
        		return;
        	}
        	
        }
        return;
    }



}
