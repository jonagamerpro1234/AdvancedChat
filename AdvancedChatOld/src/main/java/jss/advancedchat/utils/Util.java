package jss.advancedchat.utils;

import com.cryptomorin.xseries.XMaterial;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import jss.advancedchat.lib.iridium.IridiumColorAPI;
import jss.advancedchat.update.UpdateSettings;
import jss.advancedchat.utils.inventory.TSkullUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Util {

    private static final String prefix = getPrefix(true);

    public static boolean hasPerm(@NotNull Player player, String perm) {
        return player.hasPermission(perm);
    }

    public static @NotNull String getLine(String color) {
        return color(color + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }

    public static @NotNull String color(String text) {
        return IridiumColorAPI.process(text);
    }

    public static String colorless(String text) {
        return ChatColor.stripColor(text);
    }

    public static void sendColorMessage(@NotNull CommandSender sender, String text) {
        sender.sendMessage(color(text));
    }

    public static void sendColorMessage(String text, TextComponent component) {
        TextComponent textComponent = new TextComponent(text);
        sendAllPlayerBaseComponent(component, textComponent);
    }

    public static void sendColorMessage(String msg) {
        Bukkit.broadcastMessage(color(msg));
    }

    private static void sendEnable(String prefix, String message) {
        sendEnable(prefix + message);
    }

    private static void sendEnable(String message) {
        CommandSender c = Bukkit.getConsoleSender();
        sendColorMessage(c, message);
    }

    @SuppressWarnings("deprecation")
    public static void sendHover(@NotNull Player j, String action, String message, String submessage) {
        TextComponent msg = new TextComponent(color(message));
        msg.setHoverEvent(new HoverEvent(Action.valueOf(getActionHoverType(action)), (new ComponentBuilder(color(submessage))).create()));
        j.spigot().sendMessage(msg);
    }

    public static void sendAllPlayerBaseComponent(BaseComponent component) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.spigot().sendMessage(component);
        }
    }

    public static void sendAllPlayerBaseComponent(BaseComponent component, BaseComponent component2) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.spigot().sendMessage(component, component2);
        }
    }

    public static void sendAllBaseComponent(BaseComponent... component) {
        Bukkit.getOnlinePlayers().forEach((player) -> player.spigot().sendMessage(component));
    }

    public static String getActionHoverType(String arg) {
        if (arg.equalsIgnoreCase("text")) {
            return "SHOW_TEXT";
        } else if (arg.equalsIgnoreCase("item")) {
            return "SHOW_ITEM";
        } else {
            return arg.equalsIgnoreCase("entity") ? "SHOW_ENTITY" : null;
        }
    }

    public static void setTitle(String version) {
        sendEnable("&d    ___   ______");
        sendEnable("&d   /   | / ____/ &a");
        sendEnable("&d  / /| |/ /      &bBy jonagamerpro1234");
        sendEnable("&d / ___ / /___    &bVersion&7: &3" + version);
        sendEnable("&d/_/  |_\\____/   &eThanks for using AdvancedChat &c<3");
        sendEnable("  ");
    }

    @SuppressWarnings("unused")
    public static void setLoad(String version) {
        sendEnable("&5<||======================&e[&bLoading &dAdvancedChat&e]&5======================----");
    }

    public static @NotNull String getPrefix(boolean ignoreCustomPrefix) {
        return "&e[&dAdvancedChat&e]&7 ";
    }

    public static void setEnabled(String version) {
        sendEnable(prefix, "&5|============================================----");
        sendEnable(prefix, "&5| &c* &bThe plugin is &d[&aSuccessfully activated&d]");
        sendEnable(prefix, "&5| &c* &bVersion: &e[&a" + version + "&e]");
        sendEnable(prefix, "&5| &c* &bBy: &e[&bjonagamerpro1234&e]");
        sendEnable(prefix, "&5| &c* &bTested Versions &3|&a1.20.x &3- &a1.21.x&3|");
        sendEnable(prefix, "&5| &a* &eThanks for using &bAdvancedChat &c<3");
        sendEnable(prefix, "&5|============================================----");
    }

    public static void setDisabled(String version) {
        sendEnable(prefix, "&5|============================================----");
        sendEnable(prefix, "&5| &c* &bThe plugin is &d[&cSuccessfully disabled&d]");
        sendEnable(prefix, "&5| &c* &bVersion: &e[&a" + version + "&e]");
        sendEnable(prefix, "&5| &c* &bBy: &e[&bjonagamerpro1234&e]");
        sendEnable(prefix, "&5| &c* &bTested Versions &3|&a1.20.x &3- &a1.21.x&3|");
        sendEnable(prefix, "&5| &a* &eThanks for using &bAdvancedChat &c<3");
        sendEnable(prefix, "&5|============================================----");
    }

    public static @NotNull String getVar(@NotNull Player player, String text) {
        text = text.replace("<name>", player.getName());
        text = text.replace("<displayname>", player.getDisplayName());
        text = text.replaceAll("<world>", player.getWorld().getName());
        text = placeholderReplace(text, player);
        text = getOnlinePlayers(text);
        return text;
    }

    private static String placeholderReplace(String text, Player player) {
        return Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI") ? PlaceholderAPI.setPlaceholders(player, text) : text;
    }

    @SuppressWarnings("unused")
    public static boolean doesPluginExist(String plugin) {
        return doesPluginExist(plugin, "");
    }

    public static boolean doesPluginExist(String plugin, String msg, boolean s) {
        boolean hooked = Bukkit.getPluginManager().getPlugin(plugin) != null;
        if (hooked) {
            if (s) {
                Logger.defaultMessage("&5<|| &c* &b[&a" + plugin + "&b> " + (msg != null ? "&7(" + msg + ")" : ""));
            } else {
                Logger.defaultMessage("&5<|| &c* &b[&c" + plugin + "&b> " + (msg != null ? "&7(Not " + msg + ")" : ""));
            }
        }
        return hooked;
    }

    public static boolean doesPluginExist(String plugin, String msg) {
        boolean hooked = Bukkit.getPluginManager().getPlugin(plugin) != null;
        if (hooked) {
            Logger.defaultMessage("&5<|| &c* &a" + plugin + (msg != null ? msg : ""));
        }
        return hooked;
    }

    public static @NotNull String getOnlinePlayers(String text) {
        int playersOnline = 0;

        try {
            if (Bukkit.class.getMethod("getOnlinePlayers").getReturnType() == Collection.class) {
                playersOnline = ((Collection<?>) Bukkit.class.getMethod("getOnlinePlayers").invoke((Object) null)).size();
            } else {
                playersOnline = ((Player[]) Bukkit.class.getMethod("getOnlinePlayers").invoke((Object) null)).length;
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) {
        }

        text = text.replace("<online>", "" + playersOnline);
        text = text.replace("<Online>", "" + playersOnline);
        return text;
    }

    public static @NotNull String getPrefixVar(String str) {
        str = str.replace("{default}", getPrefix(true));
        return str;
    }

    @SuppressWarnings("deprecation")
    public static @NotNull ItemStack getPlayerHead(String player) {
        boolean isNewVersion = Arrays.stream(Material.values()).map(Enum::name).collect(Collectors.toList()).contains("PLAYER_HEAD");
        Material type = Material.matchMaterial(isNewVersion ? "PLAYER_HEAD" : "SKULL_ITEM");
        assert type != null;
        ItemStack item = new ItemStack(type, 1);
        if (!isNewVersion) {
            item.setDurability((short) 3);
        }

        SkullMeta meta = (SkullMeta) item.getItemMeta();
        assert meta != null;
        meta.setOwner(player);
        meta.setDisplayName(color("&b&l" + player));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createSkullNew(@NotNull String texture) {
        ItemStack head = XMaterial.PLAYER_HEAD.parseItem();
        if (!texture.isEmpty()) {
            assert head != null;
            SkullMeta headMeta = (SkullMeta) head.getItemMeta();
            PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID());
            PlayerTextures textures = profile.getTextures();
            try {
                textures.setSkin(new URL(texture));
                profile.setTextures(textures);
            } catch (MalformedURLException e) {
                Logger.error(e.getMessage());
                throw new RuntimeException(e);
            }
            assert headMeta != null;
            headMeta.setOwnerProfile(profile);
            head.setItemMeta(headMeta);
        }
        return head;
    }

    public static @NotNull URL getUrlFromBase64(String base64) throws MalformedURLException {
        String decoded = new String(Base64.getDecoder().decode(base64));
        Logger.debug("Type: Base64 | Decoder: " + decoded);
        // We simply remove the "beginning" and "ending" part of the JSON, so we're left with only the URL. You could use a proper
        // JSON parser for this, but that's not worth it. The String will always start exactly with this stuff anyway
        return new URL(decoded.substring("{\"textures\":{\"SKIN\":{\"url\":\"".length(), decoded.length() - "\"}}}".length()));
    }

    @Contract(pure = true)
    public static @Nullable ItemStack createSkull(String id){
        if(beforeVersion(20.6)){
            return createSkull_118(TSkullUtils.replace(id));
        }else{
            return createSkull_117(TSkullUtils.replace(id));
        }
    }

    @SuppressWarnings("deprecation")
    public static @NotNull ItemStack createCustomSkull(@NotNull String url){
        boolean isNewVersion = Arrays.stream(Material.values()).map(Enum::name).collect(Collectors.toList()).contains("PLAYER_HEAD");
        Material type = Material.matchMaterial(isNewVersion ? "PLAYER_HEAD" : "SKULL_ITEM");
        assert type != null;
        ItemStack head = new ItemStack(type, 1);
        if (!isNewVersion) {
            head.setDurability((short) 3);
        }
        if (!url.isEmpty()) {
            SkullMeta headMeta = (SkullMeta) head.getItemMeta();
            GameProfile profile = new GameProfile(UUID.randomUUID(), "");
            profile.getProperties().put("textures", new Property("textures", url));
            Field profileField;
            try {
                assert headMeta != null;
                profileField = headMeta.getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(headMeta, profile);
            } catch (NoSuchFieldException | SecurityException | IllegalAccessException | IllegalArgumentException ex) {
                Logger.error(ex.getMessage());
            }
            head.setItemMeta(headMeta);
        }
        return head;
    }

    public static boolean beforeVersion(double version) {
        String a = Bukkit.getServer().getClass().getPackage().getName();
        String v = a.substring(a.lastIndexOf('v') + 1);
        double vNum = Double.parseDouble(v.substring(2, v.lastIndexOf('_')) + "." + v.charAt(v.length() - 1));
        Logger.debug("Before Version" + vNum + " < " + version);
        return vNum < version;
    }

    public static @NotNull ItemStack createSkull_118(String textureId){

        ItemStack item = XMaterial.PLAYER_HEAD.parseItem();
        assert item != null;
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID());
        PlayerTextures textures = profile.getTextures();
        URL url;
        try {
            url = getUrlFromBase64(textureId);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        textures.setSkin(url);
        profile.setTextures(textures);
        assert meta != null;
        meta.setOwnerProfile(profile);
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack createSkull_117(@NotNull String url) {
        ItemStack head = XMaterial.PLAYER_HEAD.parseItem();
        if (!url.isEmpty()) {
            assert head != null;
            SkullMeta headMeta = (SkullMeta) head.getItemMeta();
            GameProfile profile = new GameProfile(UUID.randomUUID(), UUID.randomUUID().toString());
            profile.getProperties().put("textures", new Property("textures", url));
            Field profileField;
            try {
                assert headMeta != null;
                profileField = headMeta.getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(headMeta, profile);
            } catch (NoSuchFieldException | SecurityException | IllegalAccessException | IllegalArgumentException ex) {
                Logger.error(ex.getMessage());
            }
            head.setItemMeta(headMeta);
        }
        return head;
    }

    public static ItemStack createSkull_117(@NotNull String url, List<String> lore) {
        ItemStack head = XMaterial.PLAYER_HEAD.parseItem();
        if (!url.isEmpty()) {
            assert head != null;
            SkullMeta headMeta = (SkullMeta) head.getItemMeta();
            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            profile.getProperties().put("textures", new Property("textures", url));

            try {
                assert headMeta != null;
                Field profileField = headMeta.getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(headMeta, profile);
            } catch (NoSuchFieldException | SecurityException | IllegalAccessException | IllegalArgumentException e) {
                Logger.error(e.getMessage());
                throw new RuntimeException(e);
            }

            head.setItemMeta(headMeta);
        }
        return head;
    }

    public static @NotNull String getDate(long millis) {
        Date date = new Date(millis);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    public static @NotNull String getTime(long millis) {
        Date date = new Date(millis);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(date);
    }

    public static @NotNull String getDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(calendar.getTime());
    }

    public static @NotNull String getTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(calendar.getTime());
    }

    public static void getInfoPlugin(CommandSender sender, String name, String version, String latestVersion) {
        sendColorMessage(sender, "&5-=-=-=-=-=[&b" + name + "&5]=-=-=-=-=-=-");
        sendColorMessage(sender, "&5> &3Name: &b" + name);
        sendColorMessage(sender, "&5> &3Author: &6jonagamerpro1234");
        sendColorMessage(sender, "&5> &3Version: &6" + version);
        sendColorMessage(sender, "&5> &3Update: &a" + latestVersion);
        sendColorMessage(sender, "&5> &6Spigot: &a" + UpdateSettings.URL_PLUGIN[0]);
        sendColorMessage(sender, "&5> &dSongoda: &a" + UpdateSettings.URL_PLUGIN[1]);
        sendColorMessage(sender, "&5-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
}