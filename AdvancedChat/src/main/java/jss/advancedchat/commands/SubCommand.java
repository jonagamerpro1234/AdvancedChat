package jss.advancedchat.commands;

import org.bukkit.command.CommandSender;

public abstract class SubCommand {

	public abstract void onCommand(CommandSender sender, String[] args);

	public abstract String name();
	
	public abstract String info();
	
	public abstract String permission();
	
	public abstract boolean console();
	
	public abstract String syntax();
	
	public abstract String[] aliases();
}
