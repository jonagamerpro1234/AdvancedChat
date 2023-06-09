package jss.advancedchat.utils;

import com.google.common.collect.ImmutableMap;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class MessageUtils {

    private static final ImmutableMap<String,String> colorMap = ImmutableMap.<String, String>builder()
            .put("&1", "dark_blue")
            .put("&2", "dark_green")
            .put("&3", "dark_aqua")
            .put("&4", "dark_red")
            .put("&5", "dark_purple")
            .put("&6", "gold")
            .put("&7", "gray")
            .put("&8", "dark_gray")
            .put("&9", "blue")
            .put("&0", "black")
            .put("&a", "green")
            .put("&b", "aqua")
            .put("&c", "red")
            .put("&d", "light_purple")
            .put("&e", "yellow")
            .put("&f", "white")
            .build();

    public static String convertColorToTag(@NotNull String colorCode){
        return colorMap.getOrDefault(colorCode, "");
    }

    public static @NotNull TextComponent colorize(String msg){
        return LegacyComponentSerializer.legacyAmpersand().deserialize(msg);
    }

}
