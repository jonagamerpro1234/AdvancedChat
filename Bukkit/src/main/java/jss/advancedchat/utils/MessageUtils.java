package jss.advancedchat.utils;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.NotNull;

public class MessageUtils {


    public static @NotNull TextComponent colorize(String msg){
        return LegacyComponentSerializer.legacyAmpersand().deserialize(msg);
    }

}
