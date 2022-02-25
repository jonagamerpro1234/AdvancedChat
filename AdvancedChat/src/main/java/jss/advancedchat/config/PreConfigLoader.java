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
        	Settings.locale = config.getString("Settings.Locale").toLowerCase();
        	Settings.default_color = config.getString("Settings.Default-Color-Message");
        	Settings.message_prefix_custom = config.getString("Settings.Prefix");
        	Settings.hook_vault = config.getString("Hooks.Vault.Enabled").equals("true");
        	Settings.hook_discordsrv = config.getString("Hooks.DiscordSRV.Enabled").equals("true");
        	Settings.hook_luckperms = config.getString("Hooks.LuckPerms.Enabled").equals("true");        	
        	Settings.boolean_use_default_prefix = config.getString("Settings.Use-Default-Prefix").equals("true");
        	Settings.boolean_chatclear_autoclear = config.getString("ClearChat.AutoClear").equals("true");
        	Settings.boolean_clearchat_bypass = config.getString("ClearChat.ByPass-Staff").equals("true");
        	Settings.int_clearchat_tick = config.getInt("ClearChat.Tick");
        	Settings.int_clearchat_lines = config.getInt("ClearChat.LineClear");
        	
        	Settings.chatformat_chattype = config.getString("ChatFormat.Chat-Type");
        	
        	Settings.mysql_use = config.getString("Settings.Use-Database").equals("true");
        	Settings.mysql_host = config.getString("MySQL.Host");
        	Settings.mysql_port = Integer.valueOf(config.getString("MySQL.Port"));
        	Settings.mysql_username = config.getString("MySQL.Username");
        	Settings.mysql_password = config.getString("MySQL.Password");
        	Settings.mysql_database = config.getString("MySQL.Database");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void loadMessage() {
    	Logger.debug("Load Config File");
    	try {
        	FileConfiguration config = plugin.getMessageFile().get();
        	Settings.message_mute_bypass = config.getString("AdvancedChat.Mute-Bypass");
        	Settings.message_depend_plugin = config.getString("AdvancedChat.Depend-Plugin");
        	Settings.message_error_mysql = config.getString("AdvancedChat.Error-MySql");
            Settings.message_NoPermission = config.getString("AdvancedChat.No-Permission");
            Settings.message_NoPermission_Label = config.getString("AdvancedChat.No-Permission-Label");
            Settings.message_ClearChat_Server = config.getString("AdvancedChat.ClearChat-Server");
            Settings.message_ClearChat_Player = config.getString("AdvancedChat.ClearChat-Player");
            Settings.message_ClearChat_Staff = config.getString("AdvancedChat.ClearChat-Staff");
            Settings.message_Error_Args = config.getString("AdvancedChat.Error-Args");
            Settings.message_Reload = config.getString("AdvancedChat.Reload");
            Settings.message_Help_Mute = config.getString("AdvancedChat.Help-Mute");
            Settings.message_Help_UnMute = config.getString("AdvancedChat.Help-UnMute");
            Settings.message_Help_Cmd = config.getString("AdvancedChat.Help-Use-Cmd");
            Settings.list_message_help = config.getStringList("AdvancedChat.Help-Msg");
            Settings.message_Mute_Player = config.getString("AdvancedChat.Mute-Player");
            Settings.message_UnMute_Player = config.getString("AdvancedChat.UnMute-Player");
            Settings.message_No_Online_Player = config.getString("AdvancedChat.No-Online-Player");
            Settings.message_No_Use_Command = config.getString("AdvancedChat.No-Use-Command");
            Settings.message_No_Use_Command_Mute = config.getString("AdvancedChat.No-Use-Command-Mute");
        	Settings.message_msg_empty = config.getString("AdvancedChat.Empty.Msg");
        	Settings.message_msg_use = config.getString("AdvancedChat.Help-Msg-Use");
        	Settings.message_Alert_Mute = config.getString("AdvancedChat.Alert-Mute");
    	}catch(Exception e) {
    		Logger.error(e.getMessage());
    		e.printStackTrace();
    	}
    }

    public void loadGradientInv() {
    	Logger.debug("Load Gradient Inventory File");
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
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void loadColorInv() {
    	Logger.debug("Load Color Inventory File");
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
    	//Settings.color_inv_slot_exit = config.getInt("Items");
    }
    
    public void loadPlayerInv() {
    	Logger.debug("Load Player Inventory File");
    	
    	FileConfiguration config = plugin.getPlayerGuiFile().getConfig();
    	Settings.player_inv_slot_colors = config.getInt("Items.Colors.Slot");
    	Settings.player_inv_slot_last = config.getInt("Items.Last.Slot");
    	Settings.player_inv_slot_next = config.getInt("Items.Next.Slot");
    	Settings.player_inv_slot_exit = config.getInt("Items.Exit.Slot");
    }
}
