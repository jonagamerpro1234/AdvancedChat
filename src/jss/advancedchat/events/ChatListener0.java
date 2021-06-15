package jss.advancedchat.events;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.chat.Json;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Utils;
import net.md_5.bungee.api.chat.TextComponent;

public class ChatListener0 implements Listener {

    private AdvancedChat plugin;
    private EventUtils eventUtils = new EventUtils(plugin);

    public ChatListener0(AdvancedChat plugin) {
        this.plugin = plugin;
        eventUtils.getEventManager().registerEvents(this, plugin);
    }


    @SuppressWarnings("unused")
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent e) {
        PlayerManager playerManager = new PlayerManager(plugin);
        FileConfiguration config = plugin.getConfigFile().getConfig();
        Player j = e.getPlayer();
        
        Json json;
        
        String path = "Settings.ChatFormat-Type";
        String message = e.getMessage();

        if (config.getString(path).equals("normal")) {
            e.setFormat("<" + j.getName() + "> " + message);
        } else if (config.getString(path).equals("custom")) {

            String format = config.getString("Custom-Format.Text");
            List<String> hover = config.getStringList("Custom-Format.HoverEvent.Text");
            
            String cmd_action = config.getString("Custom-Format.ClickEvent.Command");
            String url_action = config.getString("Custom-Format.ClickEvent.Url");
            

            if ((j.isOp()) || (j.hasPermission("AdvancedChat.Chat.Color"))) {
            	
            }

            if (Settings.boolean_custom_type_normal) {
            	e.setCancelled(true);
            	json = new Json(j, format, message);
            	json.sendDoubleToAll();
            } else if (Settings.boolean_custom_type_hover) {
            	json = new Json(j, format, message);
            	json.setHover(hover).sendDoubleToAll();
            } else if (Settings.boolean_custom_type_click) {
            	e.setCancelled(true);
            	json = new Json(j, format, message);
            	if(cmd_action != null) {
            		json = json.setExecuteCommand(cmd_action);
            	}
            	if(url_action != null) {
            		json = json.setOpenURL(url_action);
            	}
            	json.sendDoubleToAll();
            } else if (Settings.boolean_custom_type_double) {
            	e.setCancelled(true);
            	json = new Json(j, format, message);
            	if(hover != null) {
            		json.setHover(hover);
            	}
            	if(cmd_action != null) {
            		json = json.setExecuteCommand(cmd_action);
            	}
            	if(url_action != null) {
            		json = json.setOpenURL(url_action);
            	}
            	json.sendDoubleToAll();
            } else if (Settings.boolean_custom_type_experimental) {
            	e.setCancelled(true);
            	json = new Json(j, format, message);
            	if(hover != null) {
            		json.setHover(hover);
            	}
            	if(cmd_action != null) {
            		json = json.setExecuteCommand(cmd_action);
            	}
            	if(url_action != null) {
            		json = json.setOpenURL(url_action);
            	}
            	json.sendDoubleToAll();
            } else if (Settings.boolean_custom_type_all) {
            	e.setCancelled(true);
            	json = new Json(j, format, message);
            	if(cmd_action != null) {
            		json = json.setExecuteCommand(cmd_action);
            	}
            	if(url_action != null) {
            		json = json.setOpenURL(url_action);
            	}
            	json.sendDoubleToAll();
            } else {

            }


        } else if (config.getString(path).equals("group")) {

            if (Settings.boolean_group_type_normal) {

            } else if (Settings.boolean_group_type_hover) {

            } else if (Settings.boolean_group_type_click) {

            } else if (Settings.boolean_group_type_double) {

            } else if (Settings.boolean_group_type_experimental) {

            } else if (Settings.boolean_group_type_all) {

            } else {

            }


        } else {
            e.setFormat(Utils.color(config.getString("Default-Format").replace("<name>", j.getName()).replace("<msg>", e.getMessage())));
        }

    }


    //@EventHandler
    public void test(AsyncPlayerChatEvent e) {
        PlayerManager manager = new PlayerManager(plugin);
        Player j = e.getPlayer();
        e.setCancelled(true);

        String message = e.getMessage();
        String name = j.getName();
        String text = Utils.combineText(name + " " + manager.getColor(j, message));

        text = text.replace("<name>", name).replace("<message>", message);

        if ((j.isOp()) || (j.hasPermission("AdvancedChat.Chat.Color"))) {
            text = Utils.color(text);
        }


        TextComponent component = new TextComponent(text);

        Utils.sendAllPlayerBaseComponent(component);
    }

}
