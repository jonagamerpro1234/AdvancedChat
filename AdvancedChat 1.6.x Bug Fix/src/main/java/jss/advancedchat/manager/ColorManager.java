package jss.advancedchat.manager;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Utils;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Random;

public class ColorManager {
    private final String[] ColorCodes = new String[]{
            "&0", "&1", "&2", "&3", "&4", "&5", "&6", "&7", "&8", "&9",
            "&b", "&c", "&a"};

    public String convertColor(@NotNull String color, String text) {
        if (color.equalsIgnoreCase("dark_red"))
            return "&4" + text;
        if (color.equalsIgnoreCase("red"))
            return "&c" + text;
        if (color.equalsIgnoreCase("dark_blue"))
            return "&1" + text;
        if (color.equalsIgnoreCase("blue"))
            return "&9" + text;
        if (color.equalsIgnoreCase("dark_Green"))
            return "&2" + text;
        if (color.equalsIgnoreCase("green"))
            return "&a" + text;
        if (color.equalsIgnoreCase("yellow"))
            return "&e" + text;
        if (color.equalsIgnoreCase("gold"))
            return "&6" + text;
        if (color.equalsIgnoreCase("dark_aqua"))
            return "&3" + text;
        if (color.equalsIgnoreCase("aqua"))
            return "&b" + text;
        if (color.equalsIgnoreCase("light_purple"))
            return "&d" + text;
        if (color.equalsIgnoreCase("dark_purple"))
            return "&5" + text;
        if (color.equalsIgnoreCase("gray"))
            return "&7" + text;
        if (color.equalsIgnoreCase("dark_gray"))
            return "&8" + text;
        if (color.equalsIgnoreCase("white"))
            return "&f" + text;
        if (color.equalsIgnoreCase("black"))
            return "&0" + text;
        if (color.equalsIgnoreCase("rainbow")) {
            StringBuilder stringBuffer = new StringBuilder();
            for (char c : text.toCharArray())
                stringBuffer.append(setColorRandom()).append(c);
            return stringBuffer.toString();
        }
        if (color.equalsIgnoreCase("gradient"))
            return setGradient(text);
        return null;
    }

    public String setGradient(String text) {
        FileConfiguration config = AdvancedChat.getInstance().getPlayerDataFile().getConfig();
        String gradient1 = "";
        String gradient2 = "";
        for (String key : Objects.requireNonNull(config.getConfigurationSection("Player")).getKeys(false)) {
            gradient1 = config.getString("Player." + key + ".Gradient.Color-1");
            gradient2 = config.getString("Player." + key + ".Gradient.Color-2");
        }
        return Utils.color("<GRADIENT:" + gradient1 + ">" + text + "</GRADIENT:" + gradient2 + ">");
    }

    public String setColorRandom() {
        Random r = new Random();
        return this.ColorCodes[r.nextInt(this.ColorCodes.length)];
    }

}
