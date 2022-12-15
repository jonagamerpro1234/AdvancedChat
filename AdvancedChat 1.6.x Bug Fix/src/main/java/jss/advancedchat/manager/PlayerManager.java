package jss.advancedchat.manager;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.config.ConfigManager;
import jss.advancedchat.listeners.utils.EventUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerManager {
    public UUID uuid;
    public String name;
    private AdvancedChat plugin;
    private final EventUtils eventUtils = new EventUtils(this.plugin);
    private float range;

    private int spam;

    private boolean badword;

    public PlayerManager(AdvancedChat plugin) {
        this.plugin = plugin;
        this.uuid = null;
        this.name = null;
        this.range = 0.0F;
        this.spam = 0;
        this.badword = false;
    }

    public boolean isBadword() {
        return this.badword;
    }

    public void setBadword(boolean badword) {
        this.badword = badword;
    }

    public int getSpam() {
        return this.spam;
    }

    public void setSpam(int spam) {
        this.spam = spam;
    }

    public float getRange() {
        return this.range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMute(Player player) {
        FileConfiguration config = ConfigManager.
        boolean value = false;
        for (String key : config.getConfigurationSection("Players").getKeys(false)) {
            if (key.contains(player.getName()))
                value = config.getString("Players." + key + ".Mute").equals("true");
        }
        return value;
    }

    public String getStateMute(Player player) {
        FileConfiguration config = this.plugin.getPlayerDataFile().getConfig();
        for (String key : config.getConfigurationSection("Players").getKeys(false)) {
            if (key.contains(player.getName())) {
                String w = config.getString("Players." + key + ".Mute");
                return w;
            }
        }
        return null;
    }

    public void setMute(Player player, boolean mute) {
        FileConfiguration config = this.plugin.getPlayerDataFile().getConfig();
        if (config.contains("Players." + player.getName() + ".Mute")) {
            config.set("Players." + player.getName() + ".Mute", Boolean.valueOf(mute));
            this.plugin.getPlayerDataFile().saveConfig();
        }
    }

    public String getColor(Player player) {
        FileConfiguration config = this.plugin.getPlayerDataFile().getConfig();
        for (String key : config.getConfigurationSection("Players").getKeys(false)) {
            if (key.contains(player.getName())) {
                String color = config.getString("Players." + key + ".Color");
                return color;
            }
        }
        return null;
    }

    public String getColorPlayer(Player player, FileConfiguration config) {
        for (String key : config.getConfigurationSection("Players").getKeys(false)) {
            if (key.contains(player.getName())) {
                String color = config.getString("Players." + key + ".Color");
                return color;
            }
        }
        return null;
    }

    public void setColor(Player player, String color) {
        FileConfiguration config = this.plugin.getPlayerDataFile().getConfig();
        if (config.contains("Players." + player.getName() + ".Color")) {
            config.set("Players." + player.getName() + ".Color", color);
            this.plugin.getPlayerDataFile().saveConfig();
        }
    }

    public boolean checkPlayerList(Player player) {
        FileConfiguration config = this.plugin.getPlayerDataFile().getConfig();
        for (String key : config.getConfigurationSection("Players").getKeys(false)) {
            if (key.contains(player.getName()))
                return true;
        }
        return false;
    }

    public boolean removePlayerlist(Player player) {
        FileConfiguration config = this.plugin.getPlayerDataFile().getConfig();
        for (String key : config.getConfigurationSection("Players").getKeys(false)) {
            if (key.contains(player.getName())) {
                config.set("Players." + key, null);
                this.plugin.getPlayerDataFile().saveConfig();
                return true;
            }
        }
        return false;
    }
}
