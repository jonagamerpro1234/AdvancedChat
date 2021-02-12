package jss.advancedchat.bungee.commands;

import jss.advancedchat.bungee.AdvancedChat;
import jss.advancedchat.utils.interfaces.CommandExecutor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class AdvancedChatCmd extends Command implements CommandExecutor{
	
	@SuppressWarnings("unused")
	private AdvancedChat plugin;
	
	public AdvancedChatCmd(AdvancedChat plugin) {
		super("AdvancedChat");
		this.plugin = plugin;
		
		plugin.registerCommand(this);
	}
	
	@Override
	public void execute(CommandSender arg0, String[] arg1) {
		if(arg0 instanceof ProxiedPlayer) {
			this.onCommand(arg0, arg1);
		}
			
	}
	
	@Override
	public boolean onCommand(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			
			return false;
		}
		
		@SuppressWarnings("unused")
		ProxiedPlayer j = (ProxiedPlayer) sender;
		
		if(args.length <= 1) {
			
			if(args[0].equalsIgnoreCase("reload")) {
				
			}
			
			
			return true;
		}
		
		return true;
	}



}
