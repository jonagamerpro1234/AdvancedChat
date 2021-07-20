package jss.advancedchat.events;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.ChatDataFile;
import jss.advancedchat.ChatLogFile;
import jss.advancedchat.chat.Json;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.SQLGetter;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Settings;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class ChatListener implements Listener {

    private AdvancedChat plugin;
    public Map<String, Long> delaywords = new HashMap<String, Long>();
    private EventUtils eventsUtils = new EventUtils(plugin);
    private boolean badword = false;
    
    public ChatListener(AdvancedChat plugin) {
        this.plugin = plugin;
        eventsUtils.getEventManager().registerEvents(this, plugin);
    }

    @SuppressWarnings({ "unused", "deprecation" })
	//@EventHandler(priority = EventPriority.HIGH)
    public void chatFormat(AsyncPlayerChatEvent e) {
        PlayerManager manager = new PlayerManager(plugin);
        FileConfiguration config = plugin.getConfigFile().getConfig();
        Player j = e.getPlayer();
        SQLGetter sql = plugin.getSQLGetter();
        Json json;

        String path = config.getString("Settings.ChatFormat-Type");
        
        try {
            if (path.equals("default")) {
                e.setFormat("<" + j.getName() + ">" + " " + e.getMessage());
            } else if (path.equals("custom")) {
                String format = config.getString("Custom-Format.Text");
                String exformat = config.getString("Custom-Format.Experimental-Text");
                String pathtype = "Custom-Format.Type";
                String hovermode = config.getString("Custom-Format.HoverEvent.Mode");
                String clickaction = config.getString("Custom-Format.ClickEvent.Action");
                String clickmode = config.getString("Custom-Format.ClickEvent.Mode");
                String colorbeta = "&r" + manager.getColor(j, e.getMessage());
                
                List<String> hovertext = config.getStringList("Custom-Format.HoverEvent.Text");
                
                format = Utils.getVar(j, format);
                format = format.replace("<msg>", e.getMessage());
                exformat = Utils.getVar(j, exformat);

                if ((j.isOp()) || (j.hasPermission("AdvancedChat.Chat.Color"))) {
                    format = Utils.hexcolor(format);
                }
                
                colorbeta = Utils.hexcolor(colorbeta);
                exformat = Utils.hexcolor(exformat); 
               
                if (config.getString(pathtype).equals("normal")) {
                    e.setFormat(format.replace("<name>", j.getName()).replace("<msg>", e.getMessage()));
                } else if (config.getString(pathtype).equals("experimental")) {
                    e.setCancelled(true);
                    if((j.isOp()) || (j.hasPermission("AdvancedChat.Chat.Bypass"))) {
                    	json = new Json(j, exformat, colorbeta);
                    	json.setHover(hovertext).sendDoubleToAll();
                    }else {
                    	if(Settings.mysql_use) {
                            if (sql.getMuted(plugin.getMySQL(), j.getUniqueId().toString()) || manager.isMute(j) == true|| Settings.boolean_filter_use_msg) {
                                return;
                            }else {
                            	json = new Json(j, exformat, colorbeta);
                            	json.setHover(hovertext).sendDoubleToAll();
                            }
                    	}else {
                            if (manager.isMute(j) == true|| this.badword) {
                            	this.badword = false;
                                return;
                            }else {
                            	json = new Json(j, exformat, colorbeta);
                            	json.setHover(hovertext).sendDoubleToAll();
                            }
                    	}

                    }
                    
                } else if (config.getString(pathtype).equals("hover")) {
                    TextComponent tc = new TextComponent(e.getFormat());
                    tc.setText(format);
                    e.setCancelled(true);
                    if((j.isOp()) || (j.hasPermission("AdvancedChat.Chat.Bypass"))) {
                    	Utils.sendAllPlayerBaseComponent(tc);
                    }else {
                        if (sql.getMuted(plugin.getMySQL(), j.getUniqueId().toString()) || manager.isMute(j) == true) {
                            return;
                        }else {
                        	Utils.sendAllPlayerBaseComponent(tc);
                        }
                    }

                } else if (config.getString(pathtype).equals("click")) {
                    TextComponent tc = new TextComponent(format);
                    ClickEvent click = new ClickEvent(ClickEvent.Action.valueOf(Utils.getActionClickType(clickmode)), clickaction);
                    tc.setClickEvent(click);
                    e.setCancelled(true);
                    if (sql.getMuted(plugin.getMySQL(), j.getUniqueId().toString()) || manager.isMute(j) == true) {
                        return;
                    }else {
                    	Utils.sendAllPlayerBaseComponent(tc);
                    }
                } else if (config.getString(pathtype).equals("double")) {
                    TextComponent tc = new TextComponent(e.getFormat());
                    //HoverEvent hover = new HoverEvent(HoverEvent.Action.valueOf(Utils.getActionHoverType(hovermode)), new ComponentBuilder(Utils.color(hovertext)).create());
                    ClickEvent click = new ClickEvent(ClickEvent.Action.valueOf(Utils.getActionClickType(clickmode)), clickaction);
                    //tc.setHoverEvent(hover);
                    tc.setClickEvent(click);
                    tc.setText(format);
                    e.setCancelled(true);
                    if (sql.getMuted(plugin.getMySQL(), j.getUniqueId().toString()) || manager.isMute(j) == true) {
                        return;
                    }else {
                    	Utils.sendAllPlayerBaseComponent(tc);
                    }
                }
            } else if (path.equals("group")) {
                for (String key : config.getConfigurationSection("Groups").getKeys(false)) {
                    String pathtype = config.getString("Groups." + key + ".Type");
                    String format = config.getString("Groups." + key + ".Format");
                    String perm = config.getString("Groups." + key + ".Permission");
                    String hovertext = config.getString("Groups." + key + "HoverEvent.Text");
                    String hovermode = config.getString("Groups." + key + "HoverEvent.Mode");
                    String clickaction = config.getString("Groups." + key + ".ClickEvent.Action");
                    String clickmode = config.getString("Groups." + key + ".ClickEvent.Mode");
                    hovertext = Utils.getVar(j, hovertext);
                    format = Utils.getVar(j, format);
                    format = format.replace("<msg>", e.getMessage());
                    if (!(j.hasPermission("AdvancedChat.Chat.Color")) || !(j.isOp())) {
                        format = Utils.hexcolor(format);
                    }
                    if (config.getString(pathtype).equals("normal")) {
                        if (j.hasPermission(perm)) {
                            e.setFormat(format.replace("<name>", j.getName()).replace("<msg>", e.getMessage()));

                        }
                    } else if (config.getString(pathtype).equals("experimental")) {
                        if (j.hasPermission(perm)) {
                            e.setFormat(format.replace("<name>", j.getName()).replace("<msg>", e.getMessage()));
                            String msg = e.getMessage();

                            e.setMessage(Utils.color(manager.getColor(j, msg)));
                        }
                    } else if (config.getString(pathtype).equals("hover")) {
                        if (j.hasPermission(perm)) {
                        }
                        TextComponent tc = new TextComponent(e.getFormat());
                        HoverEvent hover = new HoverEvent(HoverEvent.Action.valueOf(Utils.getActionHoverType(hovermode)), new ComponentBuilder(Utils.color(hovertext)).create());
                        tc.setHoverEvent(hover);
                        tc.setText(format);
                        e.setCancelled(true);
                        if (sql.getMuted(plugin.getMySQL(), j.getUniqueId().toString()) || manager.isMute(j) == true) {
                            return;
                        }else {
                        	Utils.sendAllPlayerBaseComponent(tc);
                        }
                        //Utils.sendAllPlayerBaseComponent(tc);
                    } else if (config.getString(pathtype).equals("click")) {
                        TextComponent tc = new TextComponent(e.getFormat());
                        ClickEvent click = new ClickEvent(ClickEvent.Action.valueOf(Utils.getActionClickType(clickmode)), clickaction);
                        tc.setClickEvent(click);
                        tc.setText(format);
                        e.setCancelled(true);
                        if (sql.getMuted(plugin.getMySQL(), j.getUniqueId().toString()) || manager.isMute(j) == true) {
                            return;
                        }else {
                        	Utils.sendAllPlayerBaseComponent(tc);
                        }
                        //Utils.sendAllPlayerBaseComponent(tc);
                    } else if (config.getString(pathtype).equals("double")) {
                        TextComponent tc = new TextComponent(e.getFormat());
                        HoverEvent hover = new HoverEvent(HoverEvent.Action.valueOf(Utils.getActionHoverType(hovermode)), new ComponentBuilder(Utils.color(hovertext)).create());
                        ClickEvent click = new ClickEvent(ClickEvent.Action.valueOf(Utils.getActionClickType(clickmode)), clickaction);
                        tc.setHoverEvent(hover);
                        tc.setClickEvent(click);
                        tc.setText(format);
                        e.setCancelled(true);
                        if (sql.getMuted(plugin.getMySQL(), j.getUniqueId().toString()) || manager.isMute(j) == true) {
                            return;
                        }else {
                        	Utils.sendAllPlayerBaseComponent(tc);
                        }
                        //Utils.sendAllPlayerBaseComponent(tc);
                    }
                }
            } else {
                e.setFormat(Utils.color(config.getString("Default-Format").replace("<name>", j.getName()).replace("<msg>", e.getMessage())));
            }

        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void chatDataLog(AsyncPlayerChatEvent e) {
        ChatDataFile chatDataFile = plugin.getChatDataFile();
        FileConfiguration config = chatDataFile.getConfig();
        Player j = e.getPlayer();

        String date = Utils.getDate(System.currentTimeMillis());
        String time = Utils.getTime(System.currentTimeMillis());

        config.set("Players." + j.getName() + ".Log." + date + ".Chat." + time, Utils.colorless(e.getMessage()));
        chatDataFile.saveConfig();

    }

    @EventHandler
    public void chatLog(AsyncPlayerChatEvent e) {
        ChatLogFile chatLogFile = plugin.getChatLogFile();
        FileConfiguration config = chatLogFile.getConfig();
        Player j = e.getPlayer();

        String date = Utils.getDate(System.currentTimeMillis());
        String time = Utils.getTime(System.currentTimeMillis());

        config.set("Players." + j.getName() + ".Chat." + date + "." + time, Utils.colorless(e.getMessage()));
        chatLogFile.saveConfig();
    }

	@EventHandler(priority = EventPriority.HIGH)
    public void chatFilter(AsyncPlayerChatEvent e) {
        Player j = e.getPlayer();
        FileConfiguration config = plugin.getConfigFile().getConfig();

        String path = "Filter-Chat.Enabled";
        List<String> list = config.getStringList("Filter-Chat.BadWords");
        String censorship = config.getString("Filter-Chat.Form-Of-Censorship");
        String msg = config.getString("Filter-Chat.Message");
        String message = e.getMessage().toLowerCase();
        if (config.getString(path).equals("true")) {
            for (int i = 0; i < list.size(); i++) {
            	
            	if(Settings.boolean_filter_use_msg) {
            		if(message.contains(list.get(i))) {
            			this.badword = true;
            			e.setCancelled(true);
            			msg = Utils.getVar(j, msg);
            			Utils.sendColorMessage(j, msg);
            		}
            	}else {
            		message = message.toLowerCase();
            		if(message.contains(list.get(i))) {
            			String a = " ";
            			for(int g = 0; g < list.size(); g++) {
            				a = a + censorship;
            			}
            			message = message.replace(list.get(i), a);
            			e.setMessage(message);
            		}
            	}
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void chatMute(AsyncPlayerChatEvent e) {
        FileConfiguration config = plugin.getPlayerDataFile().getConfig();
        FileConfiguration cconfig = plugin.getConfigFile().getConfig();
        Player j = e.getPlayer();
        SQLGetter sql = plugin.getSQLGetter();
        
        if(Settings.mysql_use) {
            if ((j.isOp()) || (j.hasPermission("AdvancedChat.Chat.Bypass"))) {
            	return;
            } else {
                if (sql.getMuted(plugin.getMySQL(), j.getUniqueId().toString())) {
                    Utils.sendColorMessage(j, cconfig.getString("AdvancedChat.Alert-Mute").replace("<name>", j.getName()));
                    e.setCancelled(true);
                }
            }
        } else {
            for (String key : config.getConfigurationSection("Players").getKeys(false)) {
                if (key.contains(j.getName())) {
                    String mute = config.getString("Players." + key + ".Mute");
                    if (!(j.isOp()) || !(j.hasPermission("AdvancedChat.Chat.Bypass"))) {
                        if (mute.equals("true")) {
                            Utils.sendColorMessage(j, cconfig.getString("AdvancedChat.Alert-Mute").replace("<name>", j.getName()));
                            e.setCancelled(true);
                        }
                    } else {
                        return;
                    }
                }
            }
        }
        

    }
}
