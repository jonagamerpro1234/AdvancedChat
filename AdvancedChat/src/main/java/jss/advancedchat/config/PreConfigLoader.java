package jss.advancedchat.config;

import org.bukkit.configuration.file.FileConfiguration;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Settings;

public class PreConfigLoader {

    private AdvancedChat plugin;

    public PreConfigLoader(AdvancedChat plugin) {
        this.plugin = plugin;
    }

    public void load() {
        FileConfiguration config = plugin.getConfigFile().getConfig();
        try {
        	Settings.message_msg_empty = config.getString("AdvancedChat.Empty.Msg");
        	Settings.message_msg_use = config.getString("AdvancedChat.Help-Msg-Use");
        	Settings.boolean_filter_use_msg = config.getString("Filter-Chat.Use-Custom-Msg").equals("true");
         	Settings.mysql_use = config.getString("Settings.Use-Database").equals("true");
         	
         	//debug section
        	Settings.mysql_use_t = config.getString("Settings.Use-Database").equals("true");
        	Settings.mysql_use_f = config.getString("Settings.Use-Database").equals("false");
        	//---------------------------------
        	
        	Settings.message_mute_bypass = config.getString("AdvancedChat.Mute-Bypass");
        	Settings.boolean_chatclear_autoclear = config.getString("ClearChat.AutoClear").equals("true");
        	Settings.int_clearchat_tick = config.getInt("ClearChat.Tick");
        	Settings.int_clearchat_lines = config.getInt("ClearChat.LineClear");
        	Settings.boolean_use_default_prefix = config.getString("Settings.Use-Default-Prefix").equals("true");
        	Settings.message_prefix_custom = config.getString("Settings.Prefix");
        	Settings.message_protocol_state = config.getString("ProtocolLib-Packet.Enabled");
        	Settings.message_error_mysql = config.getString("AdvancedChat.Error-MySql");
        	Settings.mysql_options = config.getString("MySQL.Options");
        	Settings.mysql_host = config.getString("MySQL.Host");
        	Settings.mysql_port = config.getString("MySQL.Port");
        	Settings.mysql_user = config.getString("MySQL.Username");
        	Settings.mysql_password = config.getString("MySQL.Password");
        	Settings.mysql_database = config.getString("MySQL.Database");
        	Settings.boolean_protocollib = config.getString("ProtocolLib-Packet.Enabled").equals("true");
        	Settings.boolean_antitabcompleted = config.getString("ProtocolLib-Packet.Disable-TabCompleted").equals("true");
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
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
