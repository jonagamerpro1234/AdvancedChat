package jss.advancedchat;

import org.bukkit.configuration.file.FileConfiguration;

import jss.advancedchat.utils.Logger.Level;
import jss.advancedchat.utils.Settings;

public class PreConfigLoad {

	private AdvancedChat plugin;
	
	public PreConfigLoad(AdvancedChat plugin) {
		this.plugin = plugin;
	}
	
	public void load() {
		FileConfiguration config = plugin.getConfigFile().getConfig();
		try {
			Settings.message_NoPermission = config.getString("AdvancedChat.No-Permission");
			Settings.message_NoPermission_Label = config.getString("AdvancedChat.No-Permission-Label");
			Settings.message_ClearChat_Server = config.getString("AdvancedChat.ClearChat-Server");
			Settings.message_ClearChat_Player = config.getString("AdvancedChat.ClearChat-Player");
			Settings.message_Error_Args = config.getString("AdvancedChat.Error-Args");
			Settings.message_Reload = config.getString("AdvancedChat.Reload");
			Settings.message_Help_Mute = config.getString("AdvancedChat.Help-Mute");
			Settings.message_Help_UnMute = config.getString("AdvancedChat.Help-UnMute");
			Settings.message_Help_Cmd = config.getString("AdvancedChat.Help-Cmd");
			Settings.message_Help_List = config.getStringList("AdvancedChat.Help-Msg");
			Settings.message_Mute_Player = config.getString("AdvancedChat.Mute-Player");
			Settings.message_UnMute_Player = config.getString("AdvancedChat.UnMute-Player");
			Settings.message_No_Online_Player = config.getString("AdvancedChat.No-Online-Player");
			Settings.message_No_Use_Command = config.getString("AdvancedChat.No-Use-Command");
			Settings.message_No_Use_Command_Mute = config.getString("AdvancedChat.No-Use-Command-Mute");
			plugin.logger.Log(Level.INFO, "Pre Config Load completed");
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
	}
}
