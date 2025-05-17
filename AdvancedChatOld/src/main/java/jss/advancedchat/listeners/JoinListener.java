package jss.advancedchat.listeners;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.manager.PlayerManager;
import jss.advancedchat.storage.json.manager.JsonPlayerStorage;
import jss.advancedchat.storage.json.model.PlayerData;
import jss.advancedchat.hooks.LuckPermsHook;
import jss.advancedchat.manager.HookManager;
import jss.advancedchat.storage.mysql.MySql;
import jss.advancedchat.update.UpdateChecker;
import jss.advancedchat.utils.logger.Logger;
import jss.advancedchat.utils.Perms;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class JoinListener implements Listener {

    private final AdvancedChat plugin = AdvancedChat.get();

    @SuppressWarnings("ConstantConditions")
    @EventHandler
    public void onJoinPlayer(@NotNull PlayerJoinEvent e) {
        LuckPermsHook luckPermsHook = HookManager.get().getLuckPermsHook();
        Player j = e.getPlayer();
        String group;

        // Usar el PlayerManager y el sistema de JSON
        JsonPlayerStorage playerStorage = new JsonPlayerStorage(plugin.getJsonPlayerFile());
        PlayerManager playerManager = new PlayerManager(playerStorage);

        if (!luckPermsHook.isEnabled()) {
            Logger.error("&cThe LuckPerms could not be found to activate the group system");
            Logger.warning("&eplease check that LuckPerms is active or inside your plugins folder");
            group = "default";
        } else {
            group = LuckPermsHook.getApi().getUserManager().getUser(j.getName()).getPrimaryGroup();

            // Comprobar si el grupo del jugador ha cambiado
            PlayerData playerData = playerManager.loadPlayerData(j); // Cargar datos desde JSON
            if (playerData != null && !playerData.getGroup().equalsIgnoreCase(group)) {
                playerData.setGroup(group); // Actualizar el grupo si es necesario
                playerManager.savePlayerData(j.getName(), playerData); // Guardar cambios en JSON
            }
        }

        // Cargar o crear los datos del jugador
        PlayerData playerData = playerManager.loadPlayerData(j);
        if (playerData == null) {
            // Si no existen datos para el jugador, crear una nueva configuración
            playerData = new PlayerData(j);
            playerData.setName(j.getName());
            playerData.setUuid(j.getUniqueId().toString());
            playerData.setGroup(group); // Establecer el grupo
            playerManager.savePlayerData(j.getName(), playerData); // Guardar datos
        }

        // Aquí puedes agregar más acciones con los datos del jugador, como enviar mensajes personalizados
        if (playerData.getPreferences().getMuteSettings().isMute()) {
            j.sendMessage("You are muted.");
        } else {
            j.sendMessage("Welcome " + playerData.getName() + "!");
        }

        // Si está habilitado MySQL y no existe el jugador en la base de datos, se crea
        if (Settings.mysql) {
            if (!MySql.existsInPlayerDataBase(j)) {
                MySql.createPlayer(j, group);
            } else {
                if (plugin.isDebug()) return;
                Logger.debug("The player already exists in the database");
            }
        }
    }

    @EventHandler
    public void onUpdatePlayer(@NotNull PlayerJoinEvent e) {
        Player j = e.getPlayer();
        if (Settings.update && j.isOp() && j.hasPermission(Perms.ac_update)) {
            new UpdateChecker(AdvancedChat.get(), 83889).getUpdateVersionSpigot(version -> {
                if (!AdvancedChat.get().getDescription().getVersion().equalsIgnoreCase(version)) {
                    Util.sendHover(j, "text", Util.getPrefix(true) + Settings.update_alert, Settings.update_alert_hover);
                }
            });
        }
    }
}
