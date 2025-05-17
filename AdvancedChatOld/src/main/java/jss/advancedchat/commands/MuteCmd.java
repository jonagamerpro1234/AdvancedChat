package jss.advancedchat.commands;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.json.manager.JsonPlayerStorage;
import jss.advancedchat.storage.json.model.PlayerData;
import jss.advancedchat.storage.mysql.MySql;
import jss.advancedchat.utils.MessageUtils;
import jss.advancedchat.utils.Perms;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MuteCmd implements CommandExecutor {

    private final AdvancedChat plugin = AdvancedChat.get();

    public MuteCmd() {
        AdvancedChat plugin = AdvancedChat.get();
        Objects.requireNonNull(plugin.getCommand("AdMute")).setExecutor(this);
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String @NotNull [] args) {

        if(args.length >= 1){

            if (!sender.isOp() && !sender.hasPermission("advancedchat.command.mute")) {
                MessageUtils.sendColorMessage(sender, Settings.lang_noPermission);
                return true;
            }

            if(args[0] == null){
                Util.sendColorMessage(sender, Util.getPrefix(false) + Settings.message_Help_Mute);
            }else{
                Player target = Bukkit.getPlayer(args[0]);

                if(target == null) {
                    MessageUtils.sendColorMessage(sender, "&c Use de valid Player Name!");
                    return true;
                }

                JsonPlayerStorage storage = new JsonPlayerStorage(plugin.getJsonPlayerFile());
                PlayerManager manager = new PlayerManager(storage);
                PlayerData data = manager.loadPlayerData(target);

                /* Temp Disabled
                if (target.isOp() || target.hasPermission(Perms.ac_mute_bypass)) {
                    Util.sendColorMessage(j, Settings.message_mute_bypass);
                    return true;
                }*/

                if(data.getPreferences().getMuteSettings().isMute()){
                    MessageUtils.sendColorMessage(sender, "&c Player is muted!");
                    return true;
                }

                if (Settings.mysql) {
                    MySql.setMute(target, true);
                } else {
                    data.getPreferences().getMuteSettings().setMute(true);

                    manager.savePlayerData(target.getName(),data);
                }

                Util.sendColorMessage(target, Util.getPrefix(false) + Util.getVar(target, Settings.message_Mute_Player));

                Util.sendColorMessage(sender, Util.getPrefix(false) + Util.getVar(target, Settings.message_Mute_Player));
                return true;
            }
        }
        Util.sendColorMessage(sender, Util.getPrefix(false) + Settings.message_Help_Mute);
        return true;
    }
}


        /*if (!(sender instanceof Player)) {

            if (args.length >= 1) {
                Player target = Bukkit.getPlayer(args[0]);
                assert target != null;
                PlayerManager playerManager = new PlayerManager(target);
                if (target.isOp() || target.hasPermission(Perms.ac_mute_bypass)) {
                    Util.sendColorMessage(sender, Settings.message_mute_bypass);
                    return true;
                }

                if (Settings.mysql) {
                    MySql.setMute(target, true);
                } else {
                    playerManager.setMute(true);
                }

                Util.sendColorMessage(sender, Util.getPrefix(false) + Util.getVar(target, Settings.message_Mute_Player));
                return true;
            }
            Util.sendColorMessage(sender, Util.getPrefix(false) + Settings.message_Help_Mute);
            return false;
        }
        Player j = (Player) sender;

        if (j.isOp() || j.hasPermission(Perms.ac_cmd_mute)) {
            if (args.length >= 1) {
                Player target = Bukkit.getPlayer(args[0]);
                assert target != null;

                JsonPlayerStorage storage = new JsonPlayerStorage(plugin.getJsonPlayerFile());
                PlayerManager manager = new PlayerManager(storage);
                PlayerData data = manager.loadPlayerData(target);

                if (target.isOp() || target.hasPermission(Perms.ac_mute_bypass)) {
                    Util.sendColorMessage(j, Settings.message_mute_bypass);
                    return true;
                }

                if(data.getPreferences().getMuteSettings().isMute()){
                    MessageUtils.sendColorMessage(sender, "&c Player is muted!");
                    return true;
                }

                if (Settings.mysql) {
                    MySql.setMute(target, true);
                } else {
                    data.getPreferences().getMuteSettings().setMute(true);
                    manager.savePlayerData(j.getName(),data);
                }

                Util.sendColorMessage(j, Util.getPrefix(false) + Util.getVar(target, Settings.message_Mute_Player));
                return true;
            }
        } else {
            Util.sendHover(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
            return true;
        }
        Util.sendColorMessage(j, Util.getPrefix(false) + Util.getVar(j, Settings.message_Help_Mute));
        return true;*/


