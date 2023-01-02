package jss.advancedchat.manager;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class ColorManager {
    private final Map<String, String> colors = new HashMap<>();
    private final String[] colorCodes = new String[]{
            "&0", "&1", "&2", "&3", "&4", "&5", "&6", "&7", "&8", "&9",
            "&b", "&c", "&a"};
    private final Random r = new Random();

    public ColorManager() {
        colors.put("dark_red", "&4");
        colors.put("red", "&c");
        colors.put("dark_blue", "&1");
        colors.put("blue", "&9");
        colors.put("dark_green", "&2");
        colors.put("green", "&a");
        colors.put("yellow", "&e");
        colors.put("gold", "&6");
        colors.put("dark_aqua", "&3");
        colors.put("aqua", "&b");
        colors.put("light_purple", "&d");
        colors.put("dark_purple", "&5");
        colors.put("gray", "&7");
        colors.put("dark_gray", "&8");
        colors.put("white", "&f");
        colors.put("black", "&0");
    }

    public String convertColor(@NotNull String color, String text) {
        String code = colors.get(color.toLowerCase());
        if (code != null) {
            return code + text;
        } else if (color.equalsIgnoreCase("rainbow")) {
            StringBuilder stringBuffer = new StringBuilder();
            for (char c : text.toCharArray()) {
                stringBuffer.append(colorCodes[r.nextInt(colorCodes.length)]).append(c);
            }
            return stringBuffer.toString();
        } else if (color.equalsIgnoreCase("gradient")) {
            return setGradient(text);
        } else {
            return null;
        }
    }

    public String setGradient(String text) {
        /*FileConfiguration config = AdvancedChat.getInstance().getPlayerDataFile().getConfig();
        String gradient1 = "";
        String gradient2 = "";
        for (String key : Objects.requireNonNull(config.getConfigurationSection("Player")).getKeys(false)) {
            gradient1 = config.getString("Player." + key + ".Gradient.Color-1");
            gradient2 = config.getString("Player." + key + ".Gradient.Color-2");
        }*/
        return ""; //
    }

}