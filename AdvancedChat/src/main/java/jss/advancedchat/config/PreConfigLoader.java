package jss.advancedchat.config;

import org.bukkit.configuration.file.FileConfiguration;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Settings;

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
			Settings.hook_luckperms_use_group = config.getString("Hooks.LuckPerms.Auto-Detect-Group").equals("true");
			Settings.boolean_use_default_prefix = config.getString("Settings.Use-Default-Prefix").equals("true");
			Settings.boolean_chatclear_autoclear = config.getString("ClearChat.AutoClear").equals("true");
			Settings.boolean_clearchat_bypass = config.getString("ClearChat.ByPass-Staff").equals("true");
			Settings.int_clearchat_tick = config.getInt("ClearChat.Tick");
			Settings.int_clearchat_lines = config.getInt("ClearChat.LineClear");
			Settings.chatformat_chattype = config.getString("ChatFormat.Chat-Type");
			Settings.mysql = config.getString("Settings.Use-Database").equals("true");
			Settings.mysql_host = config.getString("MySQL.Host");
			Settings.mysql_port = Integer.valueOf(config.getString("MySQL.Port"));
			Settings.mysql_username = config.getString("MySQL.Username");
			Settings.mysql_password = config.getString("MySQL.Password");
			Settings.mysql_database = config.getString("MySQL.Database");
			Settings.mysql_table = config.getString("MySQL.Table");
			Settings.chatlogs_log_chat = config.getString("ChatLogs.Messages-in-the-logger").equals("true");
			Settings.chatlogs_log_command = config.getString("ChatLogs.Commands-in-the-logger").equals("true");
			Settings.chatlogs_list_command = config.getString("ChatLogs.Commands-List.Enabled").equals("true");
			Settings.list_chatlogs_no_register_commands = config.getStringList("ChatLogs.Commands-List.List");
			Settings.msg_format_recive = config.getString("ChatFormat.Private-Message.Receive-Format");
			Settings.msg_format_send = config.getString("ChatFormat.Private-Message.Send-Format");
			Settings.msg_server_format_recive = config.getString("ChatFormat.Private-Message.Receive-Server-Format");
		} catch (Exception e) {
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

	public void loadGradientInv() {
		try {
			FileConfiguration config = plugin.getGradientColorFile().getConfig();
			Settings.gradient_inv_slot_exit = config.getInt("Items.Exit.Slot");
			Settings.gradient_inv_slot_last = config.getInt("Items.Last.Slot");
			Settings.gradient_inv_slot_red = config.getInt("Items.Red.Slot");
			Settings.gradient_inv_slot_darkred = config.getInt("Items.Dark-Red.Slot");
			Settings.gradient_inv_slot_blue = config.getInt("Items.Blue.Slot");
			Settings.gradient_inv_slot_darkblue = config.getInt("Items.Dark-Blue.Slot");
			Settings.gradient_inv_slot_green = config.getInt("Items.Green.Slot");
			Settings.gradient_inv_slot_darkgreen = config.getInt("Items.Dark-Green.Slot");
			Settings.gradient_inv_slot_yellow = config.getInt("Items.Yellow.Slot");
			Settings.gradient_inv_slot_gold = config.getInt("Items.Gold.Slot");
			Settings.gradient_inv_slot_aqua = config.getInt("Items.Aqua.Slot");
			Settings.gradient_inv_slot_darkaqua = config.getInt("Items.Dark-Aqua.Slot");
			Settings.gradient_inv_slot_lightpurple = config.getInt("Items.Light-Purple.Slot");
			Settings.gradient_inv_slot_darkpurple = config.getInt("Items.Dark-Purple.Slot");
			Settings.gradient_inv_slot_black = config.getInt("Items.Black.Slot");
			Settings.gradient_inv_slot_white = config.getInt("Items.White.Slot");
			Settings.gradient_inv_slot_darkgray = config.getInt("Items.Dark-Gray.Slot");
			Settings.gradient_inv_slot_gray = config.getInt("Items.Gray.Slot");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadColorInv() {
		FileConfiguration config = plugin.getColorFile().getConfig();
		Settings.color_inv_slot_exit = config.getInt("Items.Exit.Slot");
		Settings.color_inv_slot_last = config.getInt("Items.Last.Slot");
		Settings.color_inv_slot_next = config.getInt("Items.Next.Slot");
		Settings.color_inv_slot_red = config.getInt("Items.Red.Slot");
		Settings.color_inv_slot_darkred = config.getInt("Items.Dark-Red.Slot");
		Settings.color_inv_slot_blue = config.getInt("Items.Blue.Slot");
		Settings.color_inv_slot_darkblue = config.getInt("Items.Dark-Blue.Slot");
		Settings.color_inv_slot_green = config.getInt("Items.Green.Slot");
		Settings.color_inv_slot_darkgreen = config.getInt("Items.Dark-Green.Slot");
		Settings.color_inv_slot_yellow = config.getInt("Items.Yellow.Slot");
		Settings.color_inv_slot_gold = config.getInt("Items.Gold.Slot");
		Settings.color_inv_slot_aqua = config.getInt("Items.Aqua.Slot");
		Settings.color_inv_slot_darkaqua = config.getInt("Items.Dark-Aqua.Slot");
		Settings.color_inv_slot_lightpurple = config.getInt("Items.Light-Purple.Slot");
		Settings.color_inv_slot_darkpurple = config.getInt("Items.Dark-Purple.Slot");
		Settings.color_inv_slot_black = config.getInt("Items.Black.Slot");
		Settings.color_inv_slot_white = config.getInt("Items.White.Slot");
		Settings.color_inv_slot_darkgray = config.getInt("Items.Dark-Gray.Slot");
		Settings.color_inv_slot_gray = config.getInt("Items.Gray.Slot");
		Settings.color_inv_slot_rainbow = config.getInt("Items.Rainbow.Slot");
	}

	public void loadPlayerInv() {
		FileConfiguration config = plugin.getPlayerGuiFile().getConfig();
		Settings.player_inv_slot_colors = config.getInt("Items.Colors.Slot");
		Settings.player_inv_slot_channels = config.getInt("Items.Channel.Slot");
		Settings.player_inv_slot_gradients = config.getInt("Items.Gradient.Slot");
		Settings.player_inv_slot_rainbow = config.getInt("Items.Rainbow.Slot");
		Settings.player_inv_slot_settings = config.getInt("Items.Settings.Slot");
		Settings.player_inv_slot_special_codes = config.getInt("Items.SpecialCodes.Slot");
		Settings.player_inv_slot_mute = config.getInt("Especial-Items.Mute.Slot");
		Settings.player_inv_slot_last = config.getInt("Items.Last.Slot");
		Settings.player_inv_slot_next = config.getInt("Items.Next.Slot");
		Settings.player_inv_slot_exit = config.getInt("Items.Exit.Slot");
	}
}
