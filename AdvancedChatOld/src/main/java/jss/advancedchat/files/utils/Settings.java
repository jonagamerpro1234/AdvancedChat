package jss.advancedchat.files.utils;

import java.util.List;

public class Settings {

    //new
    public static boolean config_debug;
    public static String config_Lang;

    //old
    public static boolean update;
    public static String update_alert;
    public static String update_alert_hover;

    public static String chatformat_chattype;

    public static List<String> list_message_help;
    public static List<String> list_filter_badword;
    public static List<String> list_command_blocker_no_use;
    public static List<String> list_command_blocker_no_use_mute;
    public static List<String> list_chatlogs_no_register_commands;

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

    public static int clearchat_started_ticks;
    public static int clearchat_delay_ticks;
    public static int clearchat_lines;

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

    public static boolean mention_enabled;
    public static String mention_send;
    public static String mention_receive;
    public static boolean mention_sound;
    public static String mention_sound_name;
    public static float mention_sound_pitch;
    public static float mention_sound_volume;

    //New Lang
    public static String lang_prefix;
    public static String lang_noPermission;
    public static String lang_usageMainCommand;
    public static String lang_allowConsoleCommand;
    public static String lang_unknownArguments;
    public static String lang_reloadCommand;
    public static String lang_usageDisplayCommand;
    public static String lang_unknownSound;
    public static String lang_disableCommand;
    public static List<String> lang_updateAlert_console;
    public static List<String> lang_updateAlert_player;
    public static List<String> lang_helpCommand;

}
