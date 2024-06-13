package jss.advancedchat.files;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;
import org.bukkit.configuration.file.FileConfiguration;

public class PreConfigLoader {

    private final AdvancedChat plugin;

    public PreConfigLoader(AdvancedChat plugin) {
        this.plugin = plugin;
    }

    public void loadConfig() {
        FileConfiguration config = plugin.getConfigFile().getConfig();
        try {
            Settings.default_color = config.getString("Settings.Default-Color-Message");
            Settings.message_prefix_custom = config.getString("Settings.Prefix");
            Settings.update = config.getString("Settings.Update").equals("true");

            Settings.hook_vault = config.getString("Hooks.Vault.Enabled").equals("true");
            Settings.hook_discordsrv = config.getString("Hooks.DiscordSRV.Enabled").equals("true");
            Settings.hook_luckperms = config.getString("Hooks.LuckPerms.Enabled").equals("true");
            Settings.hook_luckperms_autoupdate_group = config.getBoolean("Hooks.LuckPerms.AutoUpdateGroup.Enabled");
            Settings.isHook_luckperms_autoupdate_group_time = config.getInt("Hooks.LuckPerms.AutoDetectGroup.Tick");

            Settings.boolean_use_default_prefix = config.getString("Settings.Use-Default-Prefix").equals("true");
            Settings.boolean_chatclear_autoclear = config.getString("ClearChat.AutoClear").equals("true");
            Settings.boolean_clearchat_bypass = config.getString("ClearChat.ByPass-Staff").equals("true");
            Settings.int_clearchat_tick = config.getInt("ClearChat.Tick");
            Settings.int_clearchat_lines = config.getInt("ClearChat.LineClear");

            Settings.chatformat_chattype = config.getString("ChatFormat.Chat-Type");
            Settings.mysql = config.getBoolean("Settings.Use-Database");
            Settings.mysql_host = config.getString("MySQL.Host");
            Settings.mysql_port = config.getInt("MySQL.Port");
            Settings.mysql_username = config.getString("MySQL.Username");
            Settings.mysql_password = config.getString("MySQL.Password");
            Settings.mysql_database = config.getString("MySQL.Database");
            Settings.mysql_table = config.getString("MySQL.Table");

            Settings.chatlogs_log_chat = config.getBoolean("ChatLogs.Messages-in-the-logger");
            Settings.chatlogs_log_command = config.getString("ChatLogs.Commands-in-the-logger").equals("true");
            Settings.list_chatlogs_no_register_commands = config.getStringList("ChatLogs.Commands-List.List");
            Settings.msg_format_recive = config.getString("ChatFormat.Private-Message.Receive-Format");
            Settings.msg_format_send = config.getString("ChatFormat.Private-Message.Send-Format");
            Settings.msg_server_format_recive = config.getString("ChatFormat.Private-Message.Receive-Server-Format");
        } catch (Exception e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadMessage() {
        try {
            FileConfiguration messages = plugin.getMessageFile().get();
            Settings.logger_prefix_info = messages.getString("AdvancedChat.Logger.Info");
            Settings.logger_prefix_error = messages.getString("AdvancedChat.Logger.Error");
            Settings.logger_prefix_warning = messages.getString("AdvancedChat.Logger.Warning");
            Settings.logger_prefix_debug = messages.getString("AdvancedChat.Logger.Debug");
            Settings.logger_prefix_success = messages.getString("AdvancedChat.Logger.Success");
            Settings.logger_prefix_outline = messages.getString("AdvancedChat.Logger.OutLine");
            Settings.logger_prefix_chat = messages.getString("AdvancedChat.Logger.Chat");
            Settings.message_mute_bypass = messages.getString("AdvancedChat.Mute-Bypass");
            Settings.message_depend_plugin = messages.getString("AdvancedChat.Depend-Plugin");
            Settings.message_error_mysql = messages.getString("AdvancedChat.Error-MySql");
            Settings.message_NoPermission = messages.getString("AdvancedChat.No-Permission");
            Settings.message_NoPermission_Label = messages.getString("AdvancedChat.No-Permission-Hover");
            Settings.message_ClearChat_Server = messages.getString("AdvancedChat.ClearChat-Server");
            Settings.message_ClearChat_Player = messages.getString("AdvancedChat.ClearChat-Player");
            Settings.message_ClearChat_Staff = messages.getString("AdvancedChat.ClearChat-Staff");
            Settings.message_Error_Args = messages.getString("AdvancedChat.Error-Args");
            Settings.message_Reload = messages.getString("AdvancedChat.Reload");
            Settings.message_Help_Mute = messages.getString("AdvancedChat.Help-Mute");
            Settings.message_Help_UnMute = messages.getString("AdvancedChat.Help-UnMute");
            Settings.message_Help_Cmd = messages.getString("AdvancedChat.Help-Use-Cmd");
            Settings.list_message_help = messages.getStringList("AdvancedChat.Help-Msg");
            Settings.message_Mute_Player = messages.getString("AdvancedChat.Mute-Player");
            Settings.message_UnMute_Player = messages.getString("AdvancedChat.UnMute-Player");
            Settings.message_No_Online_Player = messages.getString("AdvancedChat.No-Online-Player");
            Settings.message_No_Use_Command = messages.getString("AdvancedChat.No-Use-Command");
            Settings.message_No_Use_Command_Mute = messages.getString("AdvancedChat.No-Use-Command-Mute");
            Settings.message_msg_empty = messages.getString("AdvancedChat.Empty.Msg");
            Settings.message_msg_use = messages.getString("AdvancedChat.Help-Msg-Use");
            Settings.message_Alert_Mute = messages.getString("AdvancedChat.Alert-Mute");
            Settings.message_alert_disable_chat = messages.getString("AdvancedChat.Alert-Disable-Chat");
            Settings.message_alert_disable_mention = messages.getString("AdvancedChat.Alert-Disable-Mention");
            Settings.message_alert_disable_msg = messages.getString("AdvancedChat.Alert-Disable-Msg");
            Settings.update_alert = messages.getString("AdvancedChat.Alert-Update");
            Settings.update_alert_hover = messages.getString("AdvancedChat.Alert-Update-Hover");
        } catch (Exception e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

}
