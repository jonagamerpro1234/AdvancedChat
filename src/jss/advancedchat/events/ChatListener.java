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
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.EventUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class ChatListener implements Listener {

    private AdvancedChat plugin;
    public Map<String, Long> delaywords = new HashMap<String, Long>();
    private EventUtils eventsUtils = new EventUtils(plugin);

    public ChatListener(AdvancedChat plugin) {
        this.plugin = plugin;
        eventsUtils.getEventManager().registerEvents(this, plugin);
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onChatFormat(AsyncPlayerChatEvent e) {
        PlayerManager manager = new PlayerManager(plugin);
        FileConfiguration config = plugin.getConfigFile().getConfig();
        Player j = e.getPlayer();

        String path = config.getString("Settings.ChatFormat-Type");

        try {
            if (path.equals("default")) {
                e.setFormat("<" + j.getName() + ">" + " " + e.getMessage());
            } else if (path.equals("custom")) {
                String format = config.getString("Custom-Format.Text");
                String exformat = config.getString("Custom-Format.Experimental-Text");
                String pathtype = "Custom-Format.Type";
                String hovertext = config.getString("Custom-Format.HoverEvent.Text");
                String hovermode = config.getString("Custom-Format.HoverEvent.Mode");
                String clickaction = config.getString("Custom-Format.ClickEvent.Action");
                String clickmode = config.getString("Custom-Format.ClickEvent.Mode");
                String colorbeta = "&r" + manager.getColor(j, e.getMessage());

                format = Utils.getVar(j, format);
                format = format.replace("<msg>", e.getMessage());
                exformat = Utils.getVar(j, exformat);
                hovertext = Utils.getVar(j, hovertext);

                if ((j.isOp()) || (j.hasPermission("AdvancedChat.Chat.Color"))) {
                    format = Utils.hexcolor(format);
                    colorbeta = Utils.hexcolor(colorbeta);
                    exformat = Utils.hexcolor(exformat);
                }
                if (config.getString(pathtype).equals("normal")) {
                    e.setFormat(format.replace("<name>", j.getName()).replace("<msg>", e.getMessage()));
                } else if (config.getString(pathtype).equals("experimental")) {
                    e.setCancelled(true);
                    TextComponent component = new TextComponent(exformat + colorbeta);
                    HoverEvent hover = new HoverEvent(HoverEvent.Action.valueOf(Utils.getActionHoverType(hovermode)), new ComponentBuilder(Utils.color(hovertext)).create());
                    //ClickEvent click = new ClickEvent(ClickEvent.Action.valueOf(Utils.getActionClickType(clickmode)), clickaction);
                    component.setHoverEvent(hover);
                    //component.setClickEvent(click);
                    Utils.sendAllPlayerBaseComponent(component);
                } else if (config.getString(pathtype).equals("hover")) {
                    TextComponent tc = new TextComponent(e.getFormat());
                    HoverEvent hover = new HoverEvent(HoverEvent.Action.valueOf(Utils.getActionHoverType(hovermode)), new ComponentBuilder(Utils.color(hovertext)).create());
                    tc.setHoverEvent(hover);
                    tc.setText(format);
                    e.setCancelled(true);
                    if (manager.isMute(j) == true || manager.isBadword() == true) {
                        return;
                    }
                    Utils.sendAllPlayerBaseComponent(tc, e);

                } else if (config.getString(pathtype).equals("click")) {
                    TextComponent tc = new TextComponent(format);
                    ClickEvent click = new ClickEvent(ClickEvent.Action.valueOf(Utils.getActionClickType(clickmode)), clickaction);
                    tc.setClickEvent(click);
                    e.setCancelled(true);
                    if (manager.isMute(j) == true || manager.isBadword() == true) {
                        return;
                    }
                    Utils.sendAllPlayerBaseComponent(tc, e);
                } else if (config.getString(pathtype).equals("double")) {
                    TextComponent tc = new TextComponent(e.getFormat());
                    HoverEvent hover = new HoverEvent(HoverEvent.Action.valueOf(Utils.getActionHoverType(hovermode)), new ComponentBuilder(Utils.color(hovertext)).create());
                    ClickEvent click = new ClickEvent(ClickEvent.Action.valueOf(Utils.getActionClickType(clickmode)), clickaction);
                    tc.setHoverEvent(hover);
                    tc.setClickEvent(click);
                    tc.setText(format);
                    e.setCancelled(true);
                    if (manager.isMute(j) == true) return;
                    if (manager.isBadword() == true) return;
                    Utils.sendAllPlayerBaseComponent(tc);
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
                        if (manager.isMute(j) == true || manager.isBadword() == true) {
                            return;
                        }
                        Utils.sendAllPlayerBaseComponent(tc);
                    } else if (config.getString(pathtype).equals("click")) {
                        TextComponent tc = new TextComponent(e.getFormat());
                        ClickEvent click = new ClickEvent(ClickEvent.Action.valueOf(Utils.getActionClickType(clickmode)), clickaction);
                        tc.setClickEvent(click);
                        tc.setText(format);
                        e.setCancelled(true);
                        if (manager.isMute(j) == true || manager.isBadword() == true) {
                            return;
                        }
                        Utils.sendAllPlayerBaseComponent(tc);
                    } else if (config.getString(pathtype).equals("double")) {
                        TextComponent tc = new TextComponent(e.getFormat());
                        HoverEvent hover = new HoverEvent(HoverEvent.Action.valueOf(Utils.getActionHoverType(hovermode)), new ComponentBuilder(Utils.color(hovertext)).create());
                        ClickEvent click = new ClickEvent(ClickEvent.Action.valueOf(Utils.getActionClickType(clickmode)), clickaction);
                        tc.setHoverEvent(hover);
                        tc.setClickEvent(click);
                        tc.setText(format);
                        e.setCancelled(true);
                        if (manager.isMute(j) == true) return;
                        if (manager.isBadword() == true) return;
                        Utils.sendAllPlayerBaseComponent(tc);
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
    public void onChatDataLog(AsyncPlayerChatEvent e) {
        ChatDataFile chatDataFile = plugin.getChatDataFile();
        FileConfiguration config = chatDataFile.getConfig();
        Player j = e.getPlayer();

        String date = Utils.getDate(System.currentTimeMillis());
        String time = Utils.getTime(System.currentTimeMillis());

        config.set("Players." + j.getName() + ".Log." + date + ".Chat." + time, Utils.colorless(e.getMessage()));
        chatDataFile.saveConfig();

    }

    @EventHandler
    public void onChatLog(AsyncPlayerChatEvent e) {
        ChatLogFile chatLogFile = plugin.getChatLogFile();
        FileConfiguration config = chatLogFile.getConfig();
        Player j = e.getPlayer();

        String date = Utils.getDate(System.currentTimeMillis());
        String time = Utils.getTime(System.currentTimeMillis());

        config.set("Players." + j.getName() + ".Chat." + date + "." + time, Utils.colorless(e.getMessage()));
        chatLogFile.saveConfig();

    }


    @SuppressWarnings("unused")
    @EventHandler //1.6 change
    public void onChatFilter(AsyncPlayerChatEvent e) {
        PlayerManager manager = new PlayerManager(plugin);
        Player j = e.getPlayer();
        FileConfiguration config = plugin.getConfigFile().getConfig();

        String path = "Filter-Chat.Enabled";
        List<String> list = config.getStringList("Filter-Chat.BadWords");
        String censorship = config.getString("Filter-Chat.Form-Of-Censorship");
        String msg = config.getString("Filter-Chat.Message");
        String usemsg = config.getString("Filter-Chat.Use-Custom-Msg");
        String message = e.getMessage().toLowerCase();
        if (config.getString(path).equals("true")) {
            for (int i = 0; i < list.size(); i++) {
                if (usemsg.equals("false")) {
                    if (message.toLowerCase().contains(list.get(i))) {
                        String a = "";
                        for (int c = 0; c < list.size(); c++) {
                            a = a + censorship;
                        }
                        message = message.replace(list.get(i), a);


                        //return;
                    }

                }/*else if(usemsg.equals("true")) {
					if(message.contains(list.get(i))) {
						manager.setBadword(true);
						Utils.sendColorMessage(j, msg);
						e.setCancelled(true);
					}
					return;
				}*/

            }
            //return;
        }
        e.setMessage(message);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onChatMute(AsyncPlayerChatEvent e) {
        FileConfiguration config = plugin.getPlayerDataFile().getConfig();
        FileConfiguration cconfig = plugin.getConfigFile().getConfig();
        Player j = e.getPlayer();
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
