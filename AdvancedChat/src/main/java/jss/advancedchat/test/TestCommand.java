package jss.advancedchat.test;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.GroupHelper;
import jss.advancedchat.manager.GroupManager;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Utils;

public class TestCommand implements CommandExecutor{

	public TestCommand() {
		AdvancedChat.get().getCommand("Dev").setExecutor(this);
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return false;
		}
		
		Player j = (Player) sender;
		String gg = args[0];
		GroupManager g = GroupManager.get();
		GroupHelper gh = GroupHelper.get().setGroup(gg);
		
		if( gg == null) Logger.error("Select group");
		
		Utils.sendColorMessage(j, "&e" + g.getGroup(gg) + "");
		Utils.sendColorMessage(j, "&a" + g.getGroupColor(gg) + "");
		Utils.sendColorMessage(j, "&6" + g.getGroupList() + "");
		Utils.sendColorMessage(j, g.getFormat(gg) + "");
		Utils.sendColorMessage(j, "&c" + g.isClick(gg) + "");
		Utils.sendColorMessage(j, "&b" + g.isHover(gg) + "");
		Utils.sendColorMessage(j, "&c" + g.getClickMode(gg) + "");
		Utils.sendColorMessage(j, "&b" + g.getHover(gg) + "");
		
		gh.sendGroup(j, "test group helper method");
		return true;
	}
	
}
