package jss.advancedchat.manager;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.files.player.PlayerFile;
import jss.advancedchat.utils.logger.Logger;
import jss.advancedchat.files.utils.Settings;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("all")
public class  PlayerManager {

    private final PlayerFile playerFile = AdvancedChat.get().getPlayerFile();
    private FileConfiguration config;

    public PlayerManager(@NotNull Player player) {
        config = playerFile.getConfig(player.getName());
        if (config == null) {
            Logger.debug("Config file for player " + player.getName() + " is null. Creating a new config.");
            create(player, "default"); // Asumiendo que se tiene un valor por defecto
            config = playerFile.getConfig(player.getName()); // Volver a cargar la config después de crearla
        }
    }

    public String getName() {
        Logger.debug("getName() called");
        if (existsPlayer("Name")) return config.getString("Name");
        return null;
    }

    public String getChatType(){
        Logger.debug("getChatType() called");
        if (existsPlayer("Name")) return  config.getString("Chat-Type");
        return "";
    }

    public void setChatType(String value){
        Logger.debug("setChatType() called with value: " + value);
        if (existsPlayer("Name")) config.set("Chat-Type", value);
        save();
    }

    public boolean isColor() {
        Logger.debug("isColor() called");
        if (existsPlayer("Chat-Type")) return config.getString("Chat-Type").equalsIgnoreCase("color");
        return false;
    }

    public boolean isRainbow() {
        Logger.debug("isRainbow() called");
        if (existsPlayer("Chat-Message.Rainbow")) return config.getBoolean("Chat-Message.Rainbow");
        return false;
    }

    public boolean isRandom() {
        Logger.debug("isRandom() called");
        if (existsPlayer("Chat-Message.Random")) return config.getBoolean("Chat-Message.Random");
        return false;
    }

    public void setRandom(boolean value) {
        Logger.debug("setRandom() called with value: " + value);
        if (existsPlayer("Chat-Message.Random")) config.set("Chat-Message.Random", value);
        save();
    }

    public boolean isGradient() {
        Logger.debug("isGradient() called");
        if (existsPlayer("Chat-Message.Gradient")) return config.getBoolean("Chat-Message.Gradient");
        return false;
    }

    public void setGradient(boolean value) {
        Logger.debug("setGradient() called with value: " + value);
        if (existsPlayer("Chat-Message.Gradient")) config.set("Chat-Message.Gradient", value);
        save();
    }

    public boolean isSpecialCodes() {
        Logger.debug("isSpecialCodes() called");
        if (existsPlayer("Chat-Message.Special-Color-Codes"))
            return config.getBoolean("Chat-Message.Special-Color-Codes");
        return false;
    }

    public void setSpecialCodes(boolean value) {
        Logger.debug("setSpecialCodes() called with value: " + value);
        if (existsPlayer("Chat-Message.Special-Color-Codes")) config.set("Chat-Message.Special-Color-Codes", value);
        save();
    }

    public boolean isLowMode() {
        Logger.debug("isLowMode() called");
        if (existsPlayer("Other-Settings.Low-Mode")) return config.getBoolean("Other-Settings.Low-Mode");
        return false;
    }

    public void setLowMode(boolean value) {
        Logger.debug("setLowMode() called with value: " + value);
        if (config.contains("Other-Settings.Low-Mode")) config.set("Other-Settings.Low-Mode", value);
        save();
    }

    public boolean isMention() {
        Logger.debug("isMention() called");
        if (existsPlayer("Other-Settings.Mention")) return config.getBoolean("Other-Settings.Mention");
        return false;
    }

    public void setMention(boolean value) {
        Logger.debug("setMention() called with value: " + value);
        if (existsPlayer("Other-Settings.Mention")) config.set("Other-Settings.Mention", value);
        save();
    }

    public boolean isMsg() {
        Logger.debug("isMsg() called");
        if (existsPlayer("Other-Settings.Msg")) return config.getBoolean("Other-Settings.Msg");
        return false;
    }

    public void setMsg(boolean value) {
        Logger.debug("setMsg() called with value: " + value);
        if (existsPlayer("Other-Settings.Msg")) config.set("Other-Settings.Msg", value);
        save();
    }

    public boolean isChat() {
        Logger.debug("isChat() called");
        if (existsPlayer("Other-Settings.Chat")) return config.getBoolean("Other-Settings.Chat");
        return false;
    }

    public void setChat(boolean value) {
        Logger.debug("setChat() called with value: " + value);
        if (existsPlayer("Other-Settings.Chat")) config.set("Other-Settings.Chat", value);
        save();
    }

    public boolean isMute() {
        Logger.debug("isMute() called");
        if (existsPlayer("Is-Mute")) return config.getBoolean("Is-Mute");
        return false;
    }

    public void setMute(boolean value) {
        Logger.debug("setMute() called with value: " + value);
        if (config.contains("Is-Mute")) config.set("Is-Mute", value);
        save();
    }

    public String getColor() {
        Logger.debug("getColor() called");
        if (existsPlayer("Chat-Message.Color")) return config.getString("Chat-Message.Color");
        return null;
    }

    public void setColor(String color) {
        Logger.debug("setColor() called with color: " + color);
        if (existsPlayer("Chat-Message.Color")) config.set("Chat-Message.Color", color);
        save();
    }

    public void setColor(boolean value) {
        Logger.debug("setColor() called with value: " + value);
        if (existsPlayer("Chat-Message.Color")) config.set("Chat-Message.Color", value);
        save();
    }

    public String getSpecialColor() {
        Logger.debug("getSpecialColor() called");
        if (existsPlayer("Chat-Message.Special-Color"))
            return config.getString("Chat-Message.Special-Color");
        return null;
    }

    public void setSpecialColor(String color) {
        Logger.debug("setSpecialColor() called with color: " + color);
        if (existsPlayer("Chat-Message.Special-Color")) config.set("Chat-Message.Special-Color", color);
        save();
    }

    public String getChannel() {
        Logger.debug("getChannel() called");
        if (existsPlayer("Channel")) return config.getString("Channel");
        return null;
    }

    public void setChannel(String channel) {
        Logger.debug("setChannel() called with channel: " + channel);
        if (existsPlayer("Channel")) config.set("Channel", channel);
        save();
    }

    public String getRange() {
        Logger.debug("getRange() called");
        if (existsPlayer("Chat-Range")) return config.getString("Chat-Range");
        return null;
    }

    public void setRange(int range) {
        Logger.debug("setRange() called with range: " + range);
        if (existsPlayer("Chat-Range")) config.set("Chat-Range", range);
        save();
    }

    public String getFirstGradient() {
        Logger.debug("getFirstGradient() called");
        if (existsPlayer("Chat-Message.First-Gradient")) return config.getString("Chat-Message.First-Gradient");
        return null;
    }

    public void setFirstGradient(String hex) {
        Logger.debug("setFirstGradient() called with hex: " + hex);
        if (existsPlayer("Chat-Message.First-Gradient")) config.set("Chat-Message.First-Gradient", hex);
        save();
    }

    public String getSecondGradient() {
        Logger.debug("getSecondGradient() called");
        if (existsPlayer("Chat-Message.Second-Gradient")) return config.getString("Chat-Message.Second-Gradient");
        return null;
    }

    public void setSecondGradient(String hex) {
        Logger.debug("setSecondGradient() called with hex: " + hex);
        if (existsPlayer("Chat-Message.Second-Gradient")) config.set("Chat-Message.Second-Gradient", hex);
        save();
    }

    public String getUUID() {
        Logger.debug("getUUID() called");
        if (existsPlayer("UUID")) return config.getString("UUID");
        return null;
    }

    public void setUUID(@NotNull Player player) {
        Logger.debug("setUUID() called with player: " + player.getUniqueId());
        if (existsPlayer("UUID")) config.set("UUID", player.getUniqueId().toString());
        save();
    }

    public String getGroup() {
        Logger.debug("getGroup() called");
        if (existsPlayer("Group")) return config.getString("Group");
        return null;
    }

    public void setGroup(String group) {
        Logger.debug("setGroup() called with group: " + group);
        if (existsPlayer("Group")) config.set("Group", group);
        save();
    }

    public String getRainbow() {
        Logger.debug("getRainbow() called");
        if (existsPlayer("Chat-Message.Rainbow")) return config.getString("Chat-Message.Rainbow");
        return null;
    }

    public void setRainbow(String rainbow) {
        Logger.debug("setRainbow() called with rainbow: " + rainbow);
        if (existsPlayer("Chat-Message.Rainbow")) config.set("Chat-Message.Rainbow", rainbow);
        save();
    }

    public void setRainbow(boolean value) {
        Logger.debug("setRainbow() called with value: " + value);
        if (existsPlayer("Chat-Message.Rainbow")) config.set("Chat-Message.Rainbow", value);
        save();
    }

    public void create(@NotNull Player player, String group) {
        Logger.debug("create() called for player: " + player.getName());
        if (!existsPlayer("Name")) {
            config.set("Name", player.getName());
            config.set("UUID", player.getUniqueId().toString());
            config.set("Chat-Message.Color", Settings.default_color);
            config.set("Chat-Message.Rainbow", "rainbow_1");
            config.set("Chat-Message.Special-Color-Codes", "none");
            config.set("Chat-Message.First-Gradient", "FFFFFF");
            config.set("Chat-Message.Second-Gradient", "FFFFFF");
            config.set("Chat-Type", "color");
            config.set("Is-Mute", false);
            config.set("Group", group);
            config.set("Other-Settings.Mention", true);
            config.set("Other-Settings.Low-Mode", false);
            config.set("Other-Settings.Chat", true);
            config.set("Other-Settings.Msg", true);
            save();  // Guarda los cambios
            if (AdvancedChat.get().isDebug()) {
                Logger.debug("Player config created for " + player.getName());
            }
        }
    }

    public boolean existsPlayer(String section) {
        Logger.debug("existsPlayer() called for section: " + section);
        return config != null && config.contains(section);
    }

    private void save() {
        Logger.debug("save() called");
        if (config != null) {
            try {
                playerFile.save();  // Intentamos guardar el archivo
            } catch (Exception e) {
                Logger.debug("Failed to save the player configuration: " + e.getMessage());
            }
        } else {
            Logger.debug("Failed to save, configuration is null!");
        }
    }

}