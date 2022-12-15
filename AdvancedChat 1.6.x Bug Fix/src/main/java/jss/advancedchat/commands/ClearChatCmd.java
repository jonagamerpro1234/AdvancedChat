package jss.advancedchat.commands;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.config.ConfigManager;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.listeners.utils.EventUtils;
import jss.advancedchat.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ClearChatCmd implements CommandExecutor {

    private final AdvancedChat plugin = AdvancedChat.get();
    private final EventUtils eventUtils = new EventUtils(plugin);

    @SuppressWarnings("ConstantConditions")
    public ClearChatCmd() {
        plugin.getCommand("AdClearChat").setExecutor(this);
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        FileConfiguration config = ConfigManager.getConfig();
        if (!(sender instanceof Player)) {
            this.eventUtils.getClearChatAction("server");
            this.eventUtils.getServerMessage(config);
            return false;
        }

        Player j = (Player) sender;
        if (j.isOp() || j.hasPermission("AdvancedChat.ClearChat")) {
            this.eventUtils.getClearChatAction("player");
            this.eventUtils.getPlayerMessage(j, config);
        } else {
            Utils.sendHoverEvent(j, "text", Settings.message_NoPermission, Settings.message_NoPermission_Label);
        }
        return true;
    }
}
