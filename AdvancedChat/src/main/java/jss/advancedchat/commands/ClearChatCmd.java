package jss.advancedchat.commands;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Perms;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ClearChatCmd implements CommandExecutor {

	private AdvancedChat plugin = AdvancedChat.get();
	private EventUtils eventUtils = new EventUtils(plugin);

	public ClearChatCmd() {
		plugin.getCommand("AdClearChat").setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			eventUtils.getClearChatAction();
			eventUtils.getServerMessage();
			return false;
		}

		Player j = (Player) sender;

		if (j.isOp() || j.hasPermission(Perms.ac_cmd_clearchat)) {
			if (Settings.boolean_clearchat_bypass && j.isOp() || j.hasPermission(Perms.ac_clearchat_bypass)) {
				Util.sendColorMessage(j, Util.getPrefix(false) + Util.getVar(j, Settings.message_ClearChat_Staff));
			} else {
				eventUtils.getClearChatAction();
			}
			eventUtils.getPlayerMessage(j);
		} else {
			Util.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
		}
		return true;
	}

}
