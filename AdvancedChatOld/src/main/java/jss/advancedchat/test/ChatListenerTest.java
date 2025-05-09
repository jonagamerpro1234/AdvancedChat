package jss.advancedchat.test;

import github.scarsz.discordsrv.util.DiscordUtil;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.chat.Json;
import jss.advancedchat.hooks.DiscordSRVHook;
import jss.advancedchat.manager.ColorManagerOld;
import jss.advancedchat.manager.GroupHelper;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.mysql.MySql;
import jss.advancedchat.utils.logger.Logger;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.MessageUtils;
import jss.advancedchat.utils.Util;
import jss.advancedchat.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * modelo de prueba para mejora en el codigo del chat con el fin de eliminar y simplificar
 * metodos y la candidad de lineas sin perder la funcionalidad del complemento
 * y mejorando la optimizacion
 */

public class ChatListenerTest implements Listener {

    private final AdvancedChat plugin = AdvancedChat.get();
    private final ColorManagerOld colorManagerOld = new ColorManagerOld();
    //Se prueba de metodos de color en el chat para poder añadir con solo tener el permiso y usando el simbolo '&'
    private final Pattern MAGIC_REGEN = Pattern.compile("(?i)&(K)");
    private final Pattern BOLD_REGEX = Pattern.compile("(?i)&(L)");
    private final Pattern STRIKETHROUGH_REGEX = Pattern.compile("(?i)&(M)");
    private final Pattern UNDERLINE_REGEX = Pattern.compile("(?i)&(N)");
    private final Pattern ITALIC_REGEX = Pattern.compile("(?i)&(O)");
    //fin ---

    //mapa de canales para enviar mensaje en distintas instancias de canales sin interferir uno del otro
    //se almanecenan el Map El nombre jugador como primer string y como segundo string seria el nombre del canal
    @SuppressWarnings("unused")
    private final HashMap<String, String> channel = new HashMap<>();
    // booleano que permite detectar palabras malas que se encuentra en una lista negra la  palabra que se envia al chat es alguna
    // que se encuetra en esta lista se de vuelde un verdadero y esto activa una condicion if que al ser verdadero, detiene el mensaje y devuelve una
    // advertencia indicando que no se puede decir esa palabra solo al jugador que la dijo
    private boolean badword;
    private boolean ismention;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(@NotNull AsyncPlayerChatEvent e) {
        FileConfiguration config = plugin.getConfigFile().getConfig();
        DiscordSRVHook discordSRVHook = HookManager.get().getDiscordSRVHook();

        Player j = e.getPlayer();
        PlayerManager playerManager = new PlayerManager(j);
        //Main Path
        String path = Settings.chatformat_chattype;

        boolean isDefault = path.equalsIgnoreCase("default");
        boolean isNormal = path.equalsIgnoreCase("normal");
        boolean isGroup = path.equalsIgnoreCase("group");

        String format = config.getString("ChatFormat.Format");
        String msg = formatColor(e.getMessage(), j);
        Logger.debug(msg);
        String message = " &r" + colorManagerOld.addFormat(j, formatColor(e.getMessage(), j));
        Logger.debug(message);
        format = Util.getVar(j, format);
        message = Util.getVar(j, message);

        boolean isMute = false;

        if (Settings.mysql) {
            isMute = MySql.isMute(j);
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

        if (!playerManager.isChat()) {
            Util.sendColorMessage(j, Settings.message_alert_disable_chat);
            return;
        }

        if (isDefault) {

            e.setCancelled(true);
            MessageUtils.sendColorMessage(j, format + " " + message);

        } else if (isNormal) {
            e.setCancelled(true);
            Json json = new Json(j, format, message);

            if (config.getBoolean("Settings.Show-Chat-In-Console")) {
                Logger.info(format + message);
                Logger.chat(json.getFormat());
            }

            if (discordSRVHook.isEnabled()) {
                if (Settings.hook_discordsrv_channelid.equalsIgnoreCase("none")) return;

                DiscordUtil.sendMessage(DiscordUtil.getTextChannelById(Settings.hook_discordsrv_channelid), json.getFormat());
            }

            boolean hover = config.getBoolean("ChatFormat.HoverEvent.Enabled");
            List<String> hoverText = config.getStringList("ChatFormat.HoverEvent.Hover");

            boolean click = config.getBoolean("ChatFormat.ClickEvent.Enabled");
            String cmd_action = config.getString("ChatFormat.ClickEvent.Actions.Command");
            String click_mode = config.getString("ChatFormat.ClickEvent.Mode");
            String url_action = config.getString("ChatFormat.ClickEvent.Actions.Url");
            String suggest_action = config.getString("ChatFormat.ClickEvent.Actions.Suggest-Command");

            cmd_action = Util.getVar(j, cmd_action);
            suggest_action = Util.getVar(j, suggest_action);

            if (hover) {
                if (click) {
                    assert click_mode != null;

                    if (click_mode.equalsIgnoreCase("command")) {
                        json.setHover(hoverText).setExecuteCommand(cmd_action).sendDoubleToAll();
                    } else if (click_mode.equalsIgnoreCase("url")) {
                        json.setHover(hoverText).setOpenURL(url_action).sendDoubleToAll();
                    } else if (click_mode.equalsIgnoreCase("suggest")) {
                        json.setHover(hoverText).setSuggestCommand(suggest_action).sendDoubleToAll();
                    }

                } else {
                    json.setHover(hoverText).sendDoubleToAll();
                }
            } else {
                if (click) {
                    assert click_mode != null;
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

            GroupHelper groupHelper = GroupHelper.get().setGroup(playerManager.getGroup());
            groupHelper.sendGroup(j, message);
        }
    }

    @EventHandler
    public void chatMention(@NotNull AsyncPlayerChatEvent e) {
        e.setCancelled(true);
        Player j = e.getPlayer();
        String message = e.getMessage();

        PlayerManager playerManager = new PlayerManager(j);

        if (message.contains(j.getName()) && !playerManager.isMention()) {
            Util.sendColorMessage(j, Settings.message_alert_disable_mention);
            return;
        }

        if (Settings.mention_enabled) {
            if (message.contains(j.getName())) {
                MessageUtils.sendColorMessage(j, Settings.mention_send);
                this.ismention = true;

                if(Settings.mention_sound){
                    Bukkit.getOnlinePlayers().forEach((p) -> {
                        p.playSound(p.getLocation(), Sound.valueOf(Settings.mention_sound_name), Settings.mention_sound_volume, Settings.mention_sound_pitch);
                        MessageUtils.sendColorMessage(p, Settings.mention_receive);
                    });
                }
            }
        }
    }

    public String formatColor(@NotNull String msg, Player player) {
        if (!msg.isEmpty()) {
            boolean canReset = false;
            if (!player.isOp() || !player.hasPermission("AdvancedChat.Chat.Color")) {
                msg = Utils.colorized(msg);
                canReset = true;
            }

            if (!player.isOp() || !player.hasPermission("AdvancedChat.Chat.Magic")) {
                msg = this.MAGIC_REGEN.matcher(msg).replaceAll("§$1");
                canReset = true;
            }

            if (!player.isOp() || !player.hasPermission("AdvancedChat.Chat.Bold")) {
                msg = this.BOLD_REGEX.matcher(msg).replaceAll("§$1");
                canReset = true;
            }

            if (!player.isOp() || !player.hasPermission("AdvancedChat.Chat.Strikethrough")) {
                msg = this.STRIKETHROUGH_REGEX.matcher(msg).replaceAll("§$1");
                canReset = true;
            }

            if (!player.isOp() || !player.hasPermission("AdvancedChat.Chat.Underline")) {
                msg = this.UNDERLINE_REGEX.matcher(msg).replaceAll("§$1");
                canReset = true;
            }

            if (!player.isOp() || !player.hasPermission("AdvancedChat.Chat.Italic")) {
                msg = this.ITALIC_REGEX.matcher(msg).replaceAll("§$1");
                canReset = true;
            }

            if (canReset) {
                msg = Util.colorless(msg);
            }
        }
        return msg;
    }

}
