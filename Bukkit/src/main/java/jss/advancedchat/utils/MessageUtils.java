package jss.advancedchat.utils;

import com.google.common.collect.ImmutableMap;
import jss.advancedchat.AdvancedChat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageUtils {

    // Map to convert color codes to tag names
    private static final ImmutableMap<String, String> colorMap = ImmutableMap.<String, String>builder()
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

    // Map to convert special color codes to tag names
    private static final ImmutableMap<String, String> specialColorMap = ImmutableMap.<String, String>builder()
            .put("&l", "bold")
            .put("&m", "strikethrough")
            .put("&n", "underline")
            .put("&o", "italic")
            .put("&k", "obfuscated")
            .put("&r", "reset")
            .build();

    // Replace color codes in a message with tag names
    public static @NotNull String replaceColorCodes(String message) {
        Pattern pattern = Pattern.compile("&[0-9a-fA-FlLnNoOkKrR]");
        Matcher matcher = pattern.matcher(message);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String colorCode = matcher.group();
            String tagName = convertColorToTag(colorCode);
            matcher.appendReplacement(sb, "<" + tagName + ">");
        }
        matcher.appendTail(sb);

        return sb.toString();
    }

    // Convert a color code to a tag name
    private static String convertColorToTag(@NotNull String colorCode) {
        String tag = colorMap.getOrDefault(colorCode, specialColorMap.getOrDefault(colorCode, ""));
        assert tag != null;
        return tag.isEmpty() ? colorCode : tag;
    }

    // Colorize a text using MiniMessage
    public static @NotNull Component colorize(String text){
        MiniMessage miniMessage = MiniMessage.builder().build();
        return miniMessage.deserialize(replaceColorCodes(text));
    }

    // Send a colorized message to a CommandSender
    public static void sendColorMessage(@NotNull CommandSender sender, String message){
        MiniMessage miniMessage = MiniMessage.builder().build();
        Component component = miniMessage.deserialize(replaceColorCodes(message));
        AdvancedChat.get().adventure().sender(sender).sendMessage(component);
    }

    // Send a colorized message to a Player
    public static void sendColorMessage(@NotNull Player player, String message){
        MiniMessage miniMessage = MiniMessage.builder().build();
        Component component = miniMessage.deserialize(replaceColorCodes(message));
        AdvancedChat.get().adventure().player(player).sendMessage(component);
    }

    public static void showTitle(){

    }

    public static void showActionbar(){

    }

}
