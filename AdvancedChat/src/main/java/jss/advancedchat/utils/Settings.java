package jss.advancedchat.utils;

import java.util.List;

public class Settings {
	
	public static boolean update;
	public static String update_alert;
	public static String update_alert_hover;
	
	public static String chatformat_chattype;
	
	public static List<String> list_message_help;
	public static List<String> list_filter_badword;
	public static List<String> list_command_blocker_no_use;
	public static List<String> list_command_blocker_no_use_mute;
	public static List<String> list_chatlogs_no_register_commands;;
    public static List<String> list_tabcomplete_whitelist;
	
	public static String message_prefix_custom;
	public static String message_ClearChat_Staff;
    public static String message_NoPermission;
    public static String message_NoPermission_Label;
    public static String message_ClearChat_Server;
    public static String message_ClearChat_Player;
    public static String message_Error_Args;
    public static String message_Reload;
    public static String message_Help_Mute;
    public static String message_Help_UnMute;
    public static String message_Help_Cmd;
    public static String message_Mute_Player;
    public static String message_UnMute_Player;
    public static String message_Alert_Mute;
    public static String message_No_Online_Player;
    public static String message_No_Use_Command;
    public static String message_No_Use_Command_Mute;
    public static String message_error_mysql;
    public static String message_msg_empty;
    public static String message_msg_use;
    public static String message_mute_bypass;
    public static String message_player_is_not_mute;
    public static String message_player_is_mute;
    public static String message_protocol_state;
    public static String message_filter;
	public static String message_depend_plugin;
	public static String message_alert_disable_chat;
	public static String message_alert_disable_mention;
	public static String message_alert_disable_msg;
	public static String message_alert_disable_;
	
	public static String msg_format_send;
	public static String msg_format_recive;
	public static String msg_server_format_recive;

    public static String default_color;
    public static String inv_color_back;
    public static boolean tabcomplete;
    public static boolean tabcomplete_whitelist;
    
    public static String mysql_host;
    public static int mysql_port;
    public static String mysql_username;
    public static String mysql_table;
    public static String mysql_password;
    public static String mysql_database;
    public static boolean mysql;

    public static int player_inv_slot_;
    public static int player_inv_slot_settings;
    public static int player_inv_slot_gradients;
    public static int player_inv_slot_channels;
    public static int player_inv_slot_rainbow;
    public static int player_inv_slot_special_codes;
    public static int player_inv_slot_mute;
    public static int player_inv_slot_colors;
    public static int player_inv_slot_last;
    public static int player_inv_slot_next;
    public static int player_inv_slot_exit;
    
    public static int color_inv_slot_red;
    public static int color_inv_slot_darkred;
    public static int color_inv_slot_blue;
    public static int color_inv_slot_darkblue;
    public static int color_inv_slot_green;
    public static int color_inv_slot_darkgreen;
    public static int color_inv_slot_yellow;
    public static int color_inv_slot_gold;
    public static int color_inv_slot_gray;
    public static int color_inv_slot_darkgray;
    public static int color_inv_slot_lightpurple;
    public static int color_inv_slot_darkpurple;
    public static int color_inv_slot_aqua;
    public static int color_inv_slot_darkaqua;
    public static int color_inv_slot_white;
    public static int color_inv_slot_black;
    public static int color_inv_slot_rainbow;
    public static int color_inv_slot_random;
    public static int color_inv_slot_exit;
    public static int color_inv_slot_last;
    public static int color_inv_slot_next;
    
    public static int gradient_inv_slot_red;
    public static int gradient_inv_slot_darkred;
    public static int gradient_inv_slot_blue;
    public static int gradient_inv_slot_darkblue;
    public static int gradient_inv_slot_green;
    public static int gradient_inv_slot_darkgreen;
    public static int gradient_inv_slot_yellow;
    public static int gradient_inv_slot_gold;
    public static int gradient_inv_slot_gray;
    public static int gradient_inv_slot_darkgray;
    public static int gradient_inv_slot_lightpurple;
    public static int gradient_inv_slot_darkpurple;
    public static int gradient_inv_slot_aqua;
    public static int gradient_inv_slot_darkaqua;
    public static int gradient_inv_slot_white;
    public static int gradient_inv_slot_black;
    public static int gradient_inv_slot_exit;
    public static int gradient_inv_slot_last;
    
    public static int int_clearchat_tick;
    public static int int_range_chat;
    public static int int_clearchat_lines;
    public static long long_clearchat_start_tick;
    public static long long_clearchat_tick;
    
    public static boolean hook_vault;
    public static boolean hook_vault_use_group;
    public static boolean hook_luckperms;
    public static int isHook_luckperms_autoupdate_group_time;
    public static boolean hook_luckperms_autoupdate_group;
    public static boolean hook_discordsrv;
    public static String hook_discordsrv_channelid;	
    public static String hook_discordsrv_custom_format;
    public static String hook_discordsrv_group_format;
    
	public static boolean boolean_clearchat;
    public static boolean boolean_clearchat_bypass;
    public static boolean boolean_chatclear_autoclear;
	public static boolean boolean_chat_click_mode;
    public static boolean boolean_antitabcompleted;
    public static boolean boolean_protocollib;
    public static boolean boolean_use_default_prefix;

    public static boolean boolean_filter_use_msg;
    public static boolean boolean_filter;
    public static boolean boolean_command_blocker;
    public static boolean boolean_command_blocker_disable_command;
    public static boolean boolean_command_blocker_disable_command_mute;
    
	public static boolean chatlogs;
	public static boolean chatlogs_log_chat;
	public static boolean chatlogs_log_command;
	public static boolean chatlogs_list_command;
    
	public static String logger_prefix_chat;
	public static String logger_prefix_info;
	public static String logger_prefix_error;
	public static String logger_prefix_warning;
	public static String logger_prefix_debug;
	public static String logger_prefix_outline;
	public static String logger_prefix_success;
	
	public static boolean mention;
	public static String mention_send;
	public static String mention_receive;
	public static boolean mention_sound;
	public static String mention_sound_name;
	public static float mention_sound_pitch;
	public static float mention_sound_volume;
}
