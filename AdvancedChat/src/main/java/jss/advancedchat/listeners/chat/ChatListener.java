package jss.advancedchat.listeners.chat;

import github.scarsz.discordsrv.util.DiscordUtil;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.chat.Json;
import jss.advancedchat.hooks.DiscordSRVHook;
import jss.advancedchat.manager.ColorManager;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Perms;
import jss.advancedchat.utils.Settings;
import jss.advancedchat.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.regex.Pattern;

public class ChatListener implements Listener {

    private final AdvancedChat plugin = AdvancedChat.get();
    private final Pattern COLOR_REGEX = Pattern.compile("(?i)&([\\dA-F])");
    private final Pattern MAGIC_REGEN = Pattern.compile("(?i)&(K)");
    private final Pattern BOLD_REGEX = Pattern.compile("(?i)&(L)");
    private final Pattern STRIKETHROUGH_REGEX = Pattern.compile("(?i)&(M)");
    private final Pattern UNDERLINE_REGEX = Pattern.compile("(?i)&(N)");
    private final Pattern ITALIC_REGEX = Pattern.compile("(?i)&(O)");
    private final Pattern RESET_REGEX = Pattern.compile("(?i)&(R)");
    private ColorManager colorManager;
    private boolean badword;
    private boolean ismention;

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void chatMute(@NotNull AsyncPlayerChatEvent e) {
        Player j = e.getPlayer();
        PlayerManager playerManager = new PlayerManager(j);

        if (Settings.mysql) {
            if (j.isOp() || Util.hasPerm(j, Perms.ac_mute_bypass)) return;
        } else {
            if (j.isOp() || Util.hasPerm(j, Perms.ac_mute_bypass)) return;
            if (playerManager.isMute()) {
                Util.sendColorMessage(j, Util.getVar(j, Settings.message_Alert_Mute));
                e.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onChat(@NotNull AsyncPlayerChatEvent e) {
        FileConfiguration config = plugin.getConfigFile().getConfig();
        DiscordSRVHook discordSRVHook = HookManager.get().getDiscordSRVHook();

        Player j = e.getPlayer();
        PlayerManager playerManager = new PlayerManager(j);
        String path = Settings.chatformat_chattype;

        boolean isDefault = path.equalsIgnoreCase("default");
        boolean isNormal = path.equalsIgnoreCase("normal");
        boolean isGroup = path.equalsIgnoreCase("group");

        String format = config.getString("ChatFormat.Format");
        String message = "";

        String msg = formatColor(j, e.getMessage());

        Logger.debug(msg);

        if (Settings.mysql) {
            //message = " &r" + colorManager.convertColor(j, plugin.getMySQL().getColor0(j.getUniqueId().toString()), msg);
        } else {
            message = " &r" + colorManager.convertColor(j, playerManager.getColor(), msg);
        }

        format = Util.getVar(j, format);
        message = Util.getVar(j, message);

        boolean isMute = false;

        if (Settings.mysql) {
            //isMute = plugin.getMySQL().isMute(j.getUniqueId().toString());
        } else {
            isMute = playerManager.isMute();
        }

        if (isMute || this.badword) {
            this.badword = false;
            return;
        }

        if (this.ismention) {
            this.ismention = false;
            return;
        }

        if (isDefault) {
            return;
        } else if (isNormal) {
            e.setCancelled(true);
            Json json = new Json(j, format, message);

            if (config.getString("Settings.Show-Chat-In-Console").equals("true")) {
                Logger.info(json.getText() + json.getExtraText());
            }

            if (discordSRVHook.isEnabled()) {
                if (Settings.hook_discordsrv_channelid.equalsIgnoreCase("none")) return;
                DiscordUtil.sendMessage(DiscordUtil.getTextChannelById(Settings.hook_discordsrv_channelid), json.getFormat());
            }

            boolean hover = config.getString("ChatFormat.HoverEvent.Enabled").equals("true");
            List<String> hovertext = config.getStringList("ChatFormat.HoverEvent.Hover");

            boolean click = config.getString("ChatFormat.ClickEvent.Enabled").equals("true");
            String cmd_action = config.getString("ChatFormat.ClickEvent.Actions.Command");
            String click_mode = config.getString("ChatFormat.ClickEvent.Mode");
            String url_action = config.getString("ChatFormat.ClickEvent.Actions.Url");
            String suggest_action = config.getString("ChatFormat.ClickEvent.Actions.Suggest-Command");

            cmd_action = Util.getVar(j, cmd_action);
            suggest_action = Util.getVar(j, suggest_action);

            if (hover) {
                if (click) {
                    if (click_mode.equalsIgnoreCase("command")) {
                        json.setHover(hovertext).setExecuteCommand(cmd_action).sendDoubleToAll();
                    } else if (click_mode.equalsIgnoreCase("url")) {
                        json.setHover(hovertext).setOpenURL(url_action).sendDoubleToAll();
                    } else if (click_mode.equalsIgnoreCase("suggest")) {
                        json.setHover(hovertext).setSuggestCommand(suggest_action).sendDoubleToAll();
                    }
                } else {
                    json.setHover(hovertext).sendDoubleToAll();
                }
            } else {
                if (click) {
                    if (click_mode.equalsIgnoreCase("command")) {
                        json.setExecuteCommand(cmd_action).sendDoubleToAll();
                    } else if (click_mode.equalsIgnoreCase("url")) {
                        json.setOpenURL(url_action).sendDoubleToAll();
                    } else if (click_mode.equalsIgnoreCase("suggest")) {
                        json.setSuggestCommand(suggest_action).sendDoubleToAll();
                    }
                } else {
                    json.sendDoubleToAll();
                }
            }
        } else if (isGroup) {
            e.setCancelled(true);

        }
    }

    @EventHandler(ignoreCancelled = true)
    public void chatMention(@NotNull AsyncPlayerChatEvent e) {
        e.setCancelled(true);
        Player j = e.getPlayer();
        String message = e.getMessage();

        if (Settings.mention) {
            Util.sendColorMessage(j, Settings.mention_send);
            if (message.contains(j.getName())) {
                this.ismention = true;
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.playSound(p.getLocation(), Sound.valueOf(Settings.mention_sound_name), Settings.mention_sound_volume, Settings.mention_sound_pitch);
                    Util.sendColorMessage(p, Settings.mention_receive);
                }
            }
        }
    }

    public String formatColor(Player player, String msg) {
        if (msg == null)
            return "";

        boolean canReset = false;

        if (!player.hasPermission("AdvancedChat.Chat.Color")) {
            msg = COLOR_REGEX.matcher(msg).replaceAll("\u00A7$1");
            canReset = true;
        }

        if (!player.hasPermission("AdvancedChat.Chat.Magic")) {
            msg = MAGIC_REGEN.matcher(msg).replaceAll("\u00A7$1");
            canReset = true;
        }

        if (!player.hasPermission("AdvancedChat.Chat.Bold")) {
            msg = BOLD_REGEX.matcher(msg).replaceAll("\u00A7$1");
            canReset = true;
        }

        if (!player.hasPermission("AdvancedChat.Chat.Strikethrough")) {
            msg = STRIKETHROUGH_REGEX.matcher(msg).replaceAll("\u00A7$1");
            canReset = true;
        }

        if (!player.hasPermission("AdvancedChat.Chat.Underline")) {
            msg = UNDERLINE_REGEX.matcher(msg).replaceAll("\u00A7$1");
            canReset = true;
        }

        if (!player.hasPermission("AdvancedChat.Chat.Italic")) {
            msg = ITALIC_REGEX.matcher(msg).replaceAll("\u00A7$1");
            canReset = true;
        }

        if (canReset) {
            msg = RESET_REGEX.matcher(msg).replaceAll("\u00A7$1");
        }

        return msg;
    }
}
