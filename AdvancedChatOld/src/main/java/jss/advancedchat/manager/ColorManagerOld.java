package jss.advancedchat.manager;

import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.storage.mysql.MySql;
import jss.advancedchat.utils.logger.Logger;
import jss.advancedchat.utils.Util;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Random;

public class ColorManagerOld {

    private final String[] ColorCodes = new String[]{"&0", "&1", "&2", "&3", "&4", "&5", "&6", "&7", "&8", "&9", "&b", "&c", "&a"};
    private final String[] HexColorCodes = new String[]{"#000000", "#0000AA", "#00AA00", "#00AAAA", "#AA0000", "#AA00AA", "#FFAA00", "#AAAAAA", "#555555", "#5555FF", "#55FF55", "#55FFFF", "#FF5555", "#FF55FF", "#FFFF55", "#FFFFFF"};

    @Contract(" -> new")
    public static @NotNull ColorManagerOld get() {
        return new ColorManagerOld();
    }

    public String addFormat(Player player, String text) {
        PlayerManager playerManager = new PlayerManager(player);

        if (playerManager.getChatType().contains("color") && !playerManager.getChatType().contains("rainbow") && !playerManager.getChatType().contains("gradient") && !playerManager.getChatType().contains("random")) {
            Logger.debug("Color enable");
            if(Settings.mysql) {
                return convertColor(player, Objects.requireNonNull(MySql.getColor(player)), convertSpecialColor(Objects.requireNonNull(MySql.getSpecialCodes(player)), text));
            } else {
                return convertColor(player, playerManager.getColor(), convertSpecialColor(playerManager.getSpecialColor(), text));
            }
        }

        if (playerManager.isGradient() && !playerManager.isRainbow() && !playerManager.isColor() && !playerManager.isRandom() && !playerManager.isSpecialCodes()) {
            Logger.debug("Gradient enable");
            return setGradient(player, text);
        }

        if (playerManager.isRandom() && !playerManager.isRainbow() && !playerManager.isColor() && !playerManager.isGradient() && !playerManager.isSpecialCodes()) {
            Logger.debug("Random enable");
            return convertRandomColor(text);
        }

        if (playerManager.isRainbow() && !playerManager.isColor() && !playerManager.isGradient() && !playerManager.isRandom() && !playerManager.isSpecialCodes()) {
            Logger.debug("Rainbow enable");
            return setRainbow(playerManager.getRainbow(), text);
        }

        return text;
    }

    public String convertColor(Player player, @NotNull String color, String text) {
        if (color.equalsIgnoreCase("dark_red")) {
            return "&4" + text;
        }
        if (color.equalsIgnoreCase("red")) {
            return "&c" + text;
        }
        if (color.equalsIgnoreCase("dark_blue")) {
            return "&1" + text;
        }
        if (color.equalsIgnoreCase("blue")) {
            return "&9" + text;
        }
        if (color.equalsIgnoreCase("dark_Green")) {
            return "&2" + text;
        }
        if (color.equalsIgnoreCase("green")) {
            return "&a" + text;
        }
        if (color.equalsIgnoreCase("yellow")) {
            return "&e" + text;
        }
        if (color.equalsIgnoreCase("gold")) {
            return "&6" + text;
        }
        if (color.equalsIgnoreCase("dark_aqua")) {
            return "&3" + text;
        }
        if (color.equalsIgnoreCase("aqua")) {
            return "&b" + text;
        }
        if (color.equalsIgnoreCase("light_purple")) {
            return "&d" + text;
        }
        if (color.equalsIgnoreCase("dark_purple")) {
            return "&5" + text;
        }
        if (color.equalsIgnoreCase("gray")) {
            return "&7" + text;
        }
        if (color.equalsIgnoreCase("dark_gray")) {
            return "&8" + text;
        }
        if (color.equalsIgnoreCase("white")) {
            return "&f" + text;
        }
        if (color.equalsIgnoreCase("black")) {
            return "&0" + text;
        }
        return null;
    }

    public String convertRandomColor(@NotNull String text) {
        StringBuilder stringBuffer = new StringBuilder();

        for (char c : text.toCharArray()) {
            stringBuffer.append(setColorRandom()).append(c);
        }
        return stringBuffer.toString();
    }

    public String setRainbow(@NotNull String temp, String text) {
        if (temp.equalsIgnoreCase("rainbow_1")) {
            return Util.color("<RAINBOW:1>" + text + "</RAINBOW>");
        }
        if (temp.equalsIgnoreCase("rainbow_2")) {
            return Util.color("<RAINBOW:5>" + text + "</RAINBOW>");
        }

        if (temp.equalsIgnoreCase("rainbow_3")) {
            return Util.color("<RAINBOW:10>" + text + "</RAINBOW>");
        }

        if (temp.equalsIgnoreCase("rainbow_4")) {
            return Util.color("<RAINBOW:15>" + text + "</RAINBOW>");
        }

        if (temp.equalsIgnoreCase("rainbow_5")) {
            return Util.color("<RAINBOW:20>" + text + "</RAINBOW>");
        }
        if (temp.equalsIgnoreCase("rainbow_6")) {
            return Util.color("<RAINBOW:25>" + text + "</RAINBOW>");
        }

        if (temp.equalsIgnoreCase("rainbow_7")) {
            return Util.color("<RAINBOW:30>" + text + "</RAINBOW>");
        }

        if (temp.equalsIgnoreCase("rainbow_8")) {
            return Util.color("<RAINBOW:35>" + text + "</RAINBOW>");
        }

        return null;
    }

    public String convertHexColor(@NotNull String color) {
        if (color.equalsIgnoreCase("dark_red")) {
            return "AA0000";
        }
        if (color.equalsIgnoreCase("red")) {
            return "FF5555";
        }
        if (color.equalsIgnoreCase("dark_blue")) {
            return "0000AA";
        }
        if (color.equalsIgnoreCase("blue")) {
            return "5555FF";
        }
        if (color.equalsIgnoreCase("dark_Green")) {
            return "00AA00";
        }
        if (color.equalsIgnoreCase("green")) {
            return "55FF55";
        }
        if (color.equalsIgnoreCase("yellow")) {
            return "FFFF55";
        }
        if (color.equalsIgnoreCase("gold")) {
            return "FFAA00";
        }
        if (color.equalsIgnoreCase("dark_aqua")) {
            return "00AAAA";
        }
        if (color.equalsIgnoreCase("aqua")) {
            return "55FFFF";
        }
        if (color.equalsIgnoreCase("light_purple")) {
            return "FF55FF";
        }
        if (color.equalsIgnoreCase("dark_purple")) {
            return "AA00AA";
        }
        if (color.equalsIgnoreCase("gray")) {
            return "AAAAAA";
        }
        if (color.equalsIgnoreCase("dark_gray")) {
            return "555555";
        }
        if (color.equalsIgnoreCase("white")) {
            return "FFFFFF";
        }
        if (color.equalsIgnoreCase("black")) {
            return "000000";
        }
        return null;
    }

    public String convertSpecialColor(@NotNull String specialColor, String text) {
        if (specialColor.equalsIgnoreCase("bold")) {
            return "&l" + text;
        }
        if (specialColor.equalsIgnoreCase("strikethrough")) {
            return "&m" + text;
        }
        if (specialColor.equalsIgnoreCase("underline")) {
            return "&n" + text;
        }
        if (specialColor.equalsIgnoreCase("italic")) {
            return "&o" + text;
        }
        if (specialColor.equalsIgnoreCase("magic")) {
            return "&k" + text;
        }
        if (specialColor.equalsIgnoreCase("none")) {
            return text;
        }
        return null;
    }

    public String convertSpecialColor(String specialColor) {
        return convertSpecialColor(specialColor, "");
    }

    public String setGradient(Player player, String text) {
        PlayerManager playerManager = new PlayerManager(player);
        return Util.color("<GRADIENT:" + playerManager.getFirstGradient() + ">" + text + "</GRADIENT:" + playerManager.getSecondGradient() + ">");
    }

    public String setColorRandom() {
        Random r = new Random();
        return ColorCodes[r.nextInt((this.ColorCodes.length))];
    }

    public String setHexColorRandom() {
        Random r = new Random();
        return this.HexColorCodes[r.nextInt((this.HexColorCodes.length))];
    }

}
