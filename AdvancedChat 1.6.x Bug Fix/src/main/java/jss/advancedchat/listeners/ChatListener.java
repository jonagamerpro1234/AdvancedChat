package jss.advancedchat.listeners;

import github.scarsz.discordsrv.util.DiscordUtil;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.chat.MessageBuilder;
import jss.advancedchat.manager.ConfigManager;
import jss.advancedchat.files.ChatLogFile;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.hooks.DiscordSRVHook;
import jss.advancedchat.hooks.LuckPermsHook;
import jss.advancedchat.hooks.VaultHook;
import jss.advancedchat.manager.ColorManager;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Utils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class ChatListener implements Listener {
    private final AdvancedChat plugin = AdvancedChat.get();
    private boolean badWord;
    private final ColorManager colorManager = new ColorManager();

    public ChatListener() {
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onChat(@NotNull AsyncPlayerChatEvent e) {
        FileConfiguration config = ConfigManager.getConfig();
        Player j = e.getPlayer();
        PlayerManager manager = new PlayerManager(j);
        String path = config.getString("Settings.ChatFormat-Type");
        DiscordSRVHook discordSRVHook = HookManager.getHookManager().getDiscordSRV();
        VaultHook vaultHook = HookManager.getHookManager().getVaultHook();
        LuckPermsHook luckPermsHook = HookManager.getHookManager().getLuckPermsHook();
        String msg = e.getMessage();
        String message = e.getMessage().toLowerCase();
        if (Settings.boolean_filter)
            for (int i = 0; i < Settings.list_filter_badword.size(); i++) {
                if (message.contains(Settings.list_filter_badword.get(i))) {
                    this.badWord = true;
                    Utils.sendColorMessage(j, Utils.getVar(j, Settings.message_filter));
                    e.setCancelled(true);
                }
            }
        assert path != null;
        if (path.equalsIgnoreCase("default")) {
            e.setFormat("<" + j.getName() + ">" + " " + e.getMessage());
        } else if (path.equals("custom")) {
            e.setCancelled(true);
            String format = config.getString("Custom-Format.Format");
            String type = config.getString("Custom-Format.Type");
            String color = manager.getColor();
            format = Utils.color(Utils.getVar(j, format));
            if (manager.isMute() || this.badWord || Settings.boolean_filter_use_msg) {
                this.badWord = false;
                return;
            }
            MessageBuilder json = new MessageBuilder(j, format, Utils.color(this.colorManager.convertColor(Utils.getVar(j, color), msg)));
            if (config.getBoolean("Settings.Show-Chat-In-Console"))
                Logger.info(json.getText() + json.getExtraText());
            assert type != null;


            switch (type){
                case "normal":
                case "group":
                    break;
            }

            if (type.equalsIgnoreCase("normal")) {

                json.sendToAll();
                if (!discordSRVHook.isEnabled()) {
                    if (Settings.hook_discordsrv_channelid.equalsIgnoreCase("none"))
                        return;
                    DiscordUtil.sendMessageBlocking(DiscordUtil.getTextChannelById(Settings.hook_discordsrv_channelid), Utils.colorless(json.getFormatFinal()));
                }
                return;
            }
            if (type.equalsIgnoreCase("modify")) {
                boolean hover = config.getBoolean("Custom-Format.HoverEvent.Enabled");
                List<String> hoverText = config.getStringList("Custom-Format.HoverEvent.Hover");
                boolean click = config.getBoolean("Custom-Format.ClickEvent.Enabled");
                String cmd_action = config.getString("Custom-Format.ClickEvent.Actions.Command");
                String click_mode = config.getString("Custom-Format.ClickEvent.Mode");
                String url_action = config.getString("Custom-Format.ClickEvent.Actions.Url");
                String suggest_action = config.getString("Custom-Format.ClickEvent.Actions.Suggest-Command");
                cmd_action = Utils.getVar(j, cmd_action);
                suggest_action = Utils.getVar(j, suggest_action);
                if (hover) {
                    if (click) {
                        assert click_mode != null;
                        if (click_mode.equalsIgnoreCase("command")) {
                            json.setHover(hoverText).setExecuteCommand(cmd_action).sendToAll();
                        } else if (click_mode.equalsIgnoreCase("url")) {
                            json.setHover(hoverText).setOpenURL(url_action).sendToAll();
                        } else if (click_mode.equalsIgnoreCase("suggest")) {
                            json.setHover(hoverText).setSuggestCommand(suggest_action).sendToAll();
                        }
                    } else {
                        json.setHover(hoverText).sendToAll();
                    }
                } else if (click) {
                    assert click_mode != null;
                    if (click_mode.equalsIgnoreCase("command")) {
                        json.setExecuteCommand(cmd_action).sendToAll();
                    } else if (click_mode.equalsIgnoreCase("url")) {
                        json.setOpenURL(url_action).sendToAll();
                    } else if (click_mode.equalsIgnoreCase("suggest")) {
                        json.setSuggestCommand(suggest_action).sendToAll();
                    }
                } else {
                    json.sendToAll();
                }
                if (!discordSRVHook.isEnabled()) {
                    if (Settings.hook_discordsrv_channelid.equalsIgnoreCase("none"))
                        return;
                    DiscordUtil.sendMessageBlocking(DiscordUtil.getTextChannelById(Settings.hook_discordsrv_channelid), Utils.colorless(json.getFormatFinal()));
                }
            }
        } else if (path.equalsIgnoreCase("group")) {
            e.setCancelled(true);
            String color = " &r" + manager.getColor();

            if (Settings.hook_vault_use_group) {
                sendVaultGroups(vaultHook, discordSRVHook, manager, config, j, Utils.color(this.colorManager.convertColor(Utils.getVar(j, color), msg)));
                return;
            }

            if (Settings.hook_luckperms_use_group) {
                sendLuckPermsGroups(luckPermsHook, discordSRVHook, manager, config, j, Utils.color(this.colorManager.convertColor(Utils.getVar(j, color), msg)));
            }

        } else {
            String defaultFormat = config.getString("Default-Format");
            defaultFormat = StringUtils.replace(defaultFormat,"<name>",j.getName());
            defaultFormat = StringUtils.replace(defaultFormat,"<msg>",e.getMessage());
            e.setFormat(Utils.color(defaultFormat));
        }
    }

    @EventHandler
    public void onChatLog(@NotNull AsyncPlayerChatEvent e) {
        ChatLogFile chatLogFile = ConfigManager.getChatLogFile();
        FileConfiguration config = chatLogFile.config();
        Player j = e.getPlayer();
        String date = Utils.getDate(System.currentTimeMillis());
        String time = Utils.getTime(System.currentTimeMillis());
        config.set("Players." + j.getName() + ".Chat." + date + "." + time, Utils.colorless(e.getMessage()));
        chatLogFile.save();
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onChatMute(@NotNull AsyncPlayerChatEvent e) {
        /*FileConfiguration config = this.plugin.getPlayerDataFile().getConfig();
        Player j = e.getPlayer();
        for (String key : config.getConfigurationSection("Players").getKeys(false)) {
            if (key.contains(j.getName())) {
                boolean mute = config.getBoolean("Players." + key + ".Mute");
                if (!j.isOp() || !j.hasPermission("AdvancedChat.Mute.Bypass")) {
                    if (mute) {
                        if (this.plugin.isDebug())
                            Logger.debug("&e Is Muted");
                        e.setCancelled(true);
                        Utils.sendColorMessage(j, Utils.getVar(j, Settings.message_Alert_Mute));
                    }
                    continue;
                }
                if (this.plugin.isDebug())
                    Logger.debug("&eIs Muted Bypass");
                return;
            }
        }*/
    }

    private void sendVaultGroups(@NotNull VaultHook vaultHook, DiscordSRVHook discordSRVHook, PlayerManager manager, FileConfiguration config, Player j, String color) {
        if (!vaultHook.isEnabled()) {
            if (this.plugin.isDebug())
                Logger.debug("&eUsing Vault group system");
            String key = VaultHook.getVaultHook().getChat().getPrimaryGroup(j);
            String format = config.getString("Groups." + key + ".Format");
            String type = config.getString("Groups." + key + ".Type");
            format = Utils.getVar(j, format);
            format = Utils.color(format);
            MessageBuilder json = new MessageBuilder(j, format, color);
            if (config.getBoolean("Settings.Show-Chat-In-Console"))
                Logger.info(json.getText() + json.getExtraText());
            assert type != null;
            if (type.equalsIgnoreCase("normal")) {
                if (manager.isMute() || this.badWord || Settings.boolean_filter_use_msg) {
                    this.badWord = false;
                    return;
                }
                json.sendToAll();
                if (!discordSRVHook.isEnabled()) {
                    if (Settings.hook_discordsrv_channelid.equalsIgnoreCase("none"))
                        return;
                    DiscordUtil.sendMessageBlocking(DiscordUtil.getTextChannelById(Settings.hook_discordsrv_channelid), Utils.colorless(json.getFormatFinal()));
                }
                return;
            }
            if (type.equalsIgnoreCase("modify")) {
                if (manager.isMute() || this.badWord || Settings.boolean_filter_use_msg) {
                    this.badWord = false;
                    return;
                }
                boolean hover = config.getBoolean("Groups." + key + ".HoverEvent.Enabled");
                List<String> hoverText = config.getStringList("Groups." + key + ".HoverEvent.Hover");
                boolean click = config.getBoolean("Groups." + key + ".ClickEvent.Enabled");
                String cmd_action = config.getString("Groups." + key + ".ClickEvent.Actions.Command");
                String click_mode = config.getString("Groups." + key + ".ClickEvent.Mode");
                String url_action = config.getString("Groups." + key + ".ClickEvent.Actions.Url");
                String suggest_action = config.getString("Groups." + key + ".ClickEvent.Actions.Suggest-Command");
                cmd_action = Utils.getVar(j, cmd_action);
                suggest_action = Utils.getVar(j, suggest_action);
                if (hover) {
                    if (click) {
                        assert click_mode != null;
                        if (click_mode.equalsIgnoreCase("command")) {
                            json.setHover(hoverText).setExecuteCommand(cmd_action).sendToAll();
                        } else if (click_mode.equalsIgnoreCase("url")) {
                            json.setHover(hoverText).setOpenURL(url_action).sendToAll();
                        } else if (click_mode.equalsIgnoreCase("suggest")) {
                            json.setHover(hoverText).setSuggestCommand(suggest_action).sendToAll();
                        }
                    } else {
                        json.setHover(hoverText).sendToAll();
                    }
                } else if (click) {
                    assert click_mode != null;
                    if (click_mode.equalsIgnoreCase("command")) {
                        json.setExecuteCommand(cmd_action).sendToAll();
                    } else if (click_mode.equalsIgnoreCase("url")) {
                        json.setOpenURL(url_action).sendToAll();
                    } else if (click_mode.equalsIgnoreCase("suggest")) {
                        json.setSuggestCommand(suggest_action).sendToAll();
                    }
                } else {
                    json.sendToAll();
                }
                if (!discordSRVHook.isEnabled()) {
                    if (Settings.hook_discordsrv_channelid.equalsIgnoreCase("none"))
                        return;
                    DiscordUtil.sendMessageBlocking(DiscordUtil.getTextChannelById(Settings.hook_discordsrv_channelid), Utils.colorless(json.getFormatFinal()));
                }
            }
        } else {
            Logger.error("&cthe vault could not be found to activate the group system");
            Logger.warning("&eplease check that Vault is active or inside your plugins folder");
        }
    }

    private void sendLuckPermsGroups(@NotNull LuckPermsHook luckPermsHook, DiscordSRVHook discordSRVHook, PlayerManager manager, FileConfiguration config, Player j, String color) {
        if (!luckPermsHook.isEnabled()) {
            if (this.plugin.isDebug())
                Logger.debug("&eUsing Luckperms group system");
            LuckPerms lp = LuckPermsProvider.get();
            String key = Objects.requireNonNull(lp.getUserManager().getUser(j.getName())).getPrimaryGroup();
            String format = config.getString("Groups." + key + ".Format");
            String type = config.getString("Groups." + key + ".Type");
            format = Utils.color(Utils.getVar(j, format));
            MessageBuilder json = new MessageBuilder(j, format, color);
            if (config.getBoolean("Settings.Show-Chat-In-Console"))
                Logger.info(json.getText() + json.getExtraText());
            assert type != null;
            if (type.equalsIgnoreCase("normal")) {
                if (manager.isMute() || this.badWord || Settings.boolean_filter_use_msg) {
                    this.badWord = false;
                    return;
                }
                json.sendToAll();
                if (!discordSRVHook.isEnabled()) {
                    if (Settings.hook_discordsrv_channelid.equalsIgnoreCase("none"))
                        return;
                    DiscordUtil.sendMessageBlocking(DiscordUtil.getTextChannelById(Settings.hook_discordsrv_channelid), Utils.colorless(json.getFormatFinal()));
                }
                return;
            }
            if (type.equalsIgnoreCase("modify")) {
                if (manager.isMute() || this.badWord || Settings.boolean_filter_use_msg) {
                    this.badWord = false;
                    return;
                }
                boolean hover = config.getBoolean("Groups." + key + ".HoverEvent.Enabled");
                List<String> hoverText = config.getStringList("Groups." + key + ".HoverEvent.Hover");
                boolean click = config.getBoolean("Groups." + key + ".ClickEvent.Enabled");
                String cmd_action = config.getString("Groups." + key + ".ClickEvent.Actions.Command");
                String click_mode = config.getString("Groups." + key + ".ClickEvent.Mode");
                String url_action = config.getString("Groups." + key + ".ClickEvent.Actions.Url");
                String suggest_action = config.getString("Groups." + key + ".ClickEvent.Actions.Suggest-Command");
                cmd_action = Utils.getVar(j, cmd_action);
                suggest_action = Utils.getVar(j, suggest_action);
                if (hover) {
                    if (click) {
                        assert click_mode != null;
                        if (click_mode.equalsIgnoreCase("command")) {
                            json.setHover(hoverText).setExecuteCommand(cmd_action).sendToAll();
                        } else if (click_mode.equalsIgnoreCase("url")) {
                            json.setHover(hoverText).setOpenURL(url_action).sendToAll();
                        } else if (click_mode.equalsIgnoreCase("suggest")) {
                            json.setHover(hoverText).setSuggestCommand(suggest_action).sendToAll();
                        }
                    } else {
                        json.setHover(hoverText).sendToAll();
                    }
                } else if (click) {
                    assert click_mode != null;
                    if (click_mode.equalsIgnoreCase("command")) {
                        json.setExecuteCommand(cmd_action).sendToAll();
                    } else if (click_mode.equalsIgnoreCase("url")) {
                        json.setOpenURL(url_action).sendToAll();
                    } else if (click_mode.equals("suggest")) {
                        json.setSuggestCommand(suggest_action).sendToAll();
                    }
                } else {
                    json.sendToAll();
                }
                if (!discordSRVHook.isEnabled()) {
                    if (Settings.hook_discordsrv_channelid.equalsIgnoreCase("none"))
                        return;
                    DiscordUtil.sendMessageBlocking(DiscordUtil.getTextChannelById(Settings.hook_discordsrv_channelid), Utils.colorless(json.getFormatFinal()));
                }
            }
        } else {
            Logger.error("&cThe luckperms could not be found to activate the group system");
            Logger.warning("&eplease check that Vault is active or inside your plugins folder");
        }
    }
}
