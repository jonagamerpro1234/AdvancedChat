package jss.advancedchat.files.utils;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.ConfigManager;
import jss.advancedchat.utils.Logger;
import org.bukkit.configuration.file.FileConfiguration;

public class PreConfigLoader {

    private final AdvancedChat plugin;

    public PreConfigLoader(AdvancedChat plugin) {
        this.plugin = plugin;
    }

    public void load() {
        FileConfiguration config = ConfigManager.getConfig();
        try {

            //ChatFormat section
            Settings.settings_chatformat_enabled = config.getBoolean("ChatFormat.Enabled");
            Settings.settings_chatformat_type = config.getString("ChatFormat.Chat-Type");
            Settings.chatformat_mainFormat = config.getString("ChatFormat.Format");


            Settings.default_color = config.getString("Settings.Default-Color-Message");
            Settings.message_mute_bypass = config.getString("AdvancedChat.Mute-Bypass");
            Settings.message_prefix_custom = "&d[&eAdvancedChat&d] &7";
            Settings.message_protocol_state = config.getString("ProtocolLib-Packet.Enabled");
            Settings.message_error_mysql = config.getString("AdvancedChat.Error-MySql");
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
            Settings.message_msg_empty = config.getString("AdvancedChat.Empty.Msg");
            Settings.message_msg_use = config.getString("AdvancedChat.Help-Msg-Use");
            Settings.message_Alert_Mute = config.getString("AdvancedChat.Alert-Mute");
            Settings.message_filter = config.getString("Filter-Chat.Message");
            Settings.message_player_is_mute = config.getString("AdvancedChat.Player-Is-Mute");
            Settings.message_player_is_not_mute = config.getString("AdvancedChat.Player-Is-Not-Mute");
            Settings.list_filter_badWord = config.getStringList("Filter-Chat.BadWords");
            Settings.hook_discordsrv = config.getBoolean("Hooks.DiscordSRV.Enabled");
            Settings.hook_discordsrv_channelid = config.getString("Hooks.DiscordSRV.Channel-ID");

            Settings.hook_luckperms = config.getBoolean("Hooks.LuckPerms.Enabled");
            Settings.hook_luckperms_use_group = config.getBoolean("Hooks.LuckPerms.Use-Luckperms-In-Groups");

            //Settings.boolean_use_default_prefix = config.getString("Settings.Use-Default-Prefix").equals("true");

            Settings.boolean_filter = config.getBoolean("Filter-Chat.Enabled");
            Settings.boolean_chatclear_autoclear = config.getBoolean("ClearChat.AutoClear");
            Settings.long_clearchat_tick = config.getLong("ClearChat.Tick");
            Settings.long_clearchat_start_tick = config.getLong("ClearChat.Default-Start-Tick");
        } catch (Exception e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
