package jss.advancedchat.listeners;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.files.PlayerFile;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.hooks.LuckPermsHook;
import jss.advancedchat.manager.ConfigManager;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.Utils;
import jss.advancedchat.utils.update.UpdateChecker;
import jss.advancedchat.utils.update.UpdateSettings;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class JoinListener implements Listener {

    private final AdvancedChat plugin = AdvancedChat.get();

    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent e) {
        LuckPermsHook luckPermsHook = HookManager.get().getLuckPermsHook();
        Player p = e.getPlayer();

        String group;

        if (luckPermsHook.isEnabled) {
            group = luckPermsHook.getGroup(p);
        }else{
            group = "default";
        }

        if(Settings.settings_debug){
            Logger.debug(" &eLuckPermHook is: " + luckPermsHook.isEnabled());
        }

        PlayerFile playerFile = new PlayerFile(plugin, p.getName());
        playerFile.create();

        PlayerManager playerManager = new PlayerManager(p);
        playerManager.create(group);

        if(Settings.settings_chatformat_type.equalsIgnoreCase("group")){
            if (luckPermsHook.isEnabled) {
                if (!playerManager.getGroup().equalsIgnoreCase(luckPermsHook.getGroup(p))){
                    playerManager.setGroup(luckPermsHook.getGroup(p));
                }
            } else {
                Logger.error("&cThe LuckPerms could not be found to activate the group system");
                Logger.warning("&eplease check that LuckPerms is active or inside your plugins folder");
            }
        }

    }

    @EventHandler
    public void onUpdate(@NotNull PlayerJoinEvent e) {
        FileConfiguration config = ConfigManager.getConfig();
        Player j = e.getPlayer();
        if (config.getBoolean("Settings.Update") && (j.isOp() || j.hasPermission("AdvancedChat.Update.Notify")))
            (new UpdateChecker(AdvancedChat.getInstance(), 83889)).getUpdateVersion(version -> {
                if (!AdvancedChat.getInstance().getDescription().getVersion().equalsIgnoreCase(version)) {
                    Utils.sendColorMessage(j, Utils.getPrefixPlayer() + " &aThere is a new version available for download");
                    Utils.sendColorMessage(j, Utils.getPrefixPlayer() + " &aClick on this message to copy the link");
                    TextComponent component = new TextComponent(Utils.color("&5> &6&l(Spigot) "));
                    TextComponent component2 = new TextComponent(Utils.color("&8| &d&l(Songoda) "));
                    TextComponent component3 = new TextComponent(Utils.color("&8| &8&l(GitHub) "));
                    component.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, UpdateSettings.URL_PLUGIN[0]));
                    component2.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, UpdateSettings.URL_PLUGIN[1]));
                    component3.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, UpdateSettings.URL_PLUGIN[2]));
                    component.addExtra(component2);
                    component.addExtra(component3);
                    j.spigot().sendMessage(component);
                }
            });
    }

}
