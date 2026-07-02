package jss.advancedchat.storage.json.model;

import jss.advancedchat.storage.json.model.submodel.PlayerPreferences;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerData {

    private String name;
    private String uuid;
    private String group;
    private final PlayerPreferences preferences;
    public PlayerData(@NotNull Player player) {
        this.name = player.getName();
        this.uuid = player.getUniqueId().toString();
        this.group = "default";
        this.preferences = new PlayerPreferences();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUUID(){
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public PlayerPreferences getPreferences() {
        return preferences;
    }

}
