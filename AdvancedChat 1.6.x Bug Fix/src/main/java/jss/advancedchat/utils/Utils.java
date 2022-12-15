package jss.advancedchat.utils;

import com.cryptomorin.xseries.XMaterial;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.libs.iridium.IridiumColorAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Utils {
  private static final String prefix = getPrefix();

  public static @NotNull String setLine(String color) {
    return color(color + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
  }

  public static @NotNull String color(String text) {
    return IridiumColorAPI.process(text);
  }

  public static String colorless(String text) {
    return ChatColor.stripColor(text);
  }

  public static void sendColorMessage(@NotNull Player player, String text) {
    player.sendMessage(color(text));
  }

  public static void sendColorMessage(@NotNull CommandSender sender, String text) {
    sender.sendMessage(color(text));
  }

  public static void sendColorMessage(String msg) {
    Bukkit.broadcastMessage(color(msg));
  }
  
  private static void sendEnable(String message) {
    ConsoleCommandSender consoleCommandSender = Bukkit.getConsoleSender();
    sendColorMessage(consoleCommandSender, message);
  }

  public static void sendColorConsoleMessage(@NotNull CommandSender sender, String text) {
    sender.sendMessage(color(text));
  }

  @SuppressWarnings("deprecation")
  public static void sendHoverEvent(@NotNull Player j, String action, String message, String subMessage) {
    TextComponent msg = new TextComponent(color(message));
    msg.setHoverEvent(new HoverEvent(HoverEvent.Action.valueOf(getActionHoverType(action)), (new ComponentBuilder(
            color(subMessage))).create()));
    j.spigot().sendMessage(msg);
  }

  public static void sendAllPlayerBaseComponent(BaseComponent component, BaseComponent component2) {
    for (Player p : Bukkit.getOnlinePlayers()) {
      p.spigot().sendMessage(component, component2);
    }
  }

  public static @Nullable String getActionHoverType(@NotNull String arg) {
    if (arg.equalsIgnoreCase("text"))
      return "SHOW_TEXT";
    if (arg.equalsIgnoreCase("item"))
      return "SHOW_ITEM";
    if (arg.equalsIgnoreCase("entity"))
      return "SHOW_ENTITY";
    return null;
  }

  public static void setTitle(String version) {
    sendEnable("&d    ___   ______");
    sendEnable("&d   /   | / ____/ &a");
    sendEnable("&d  / /| |/ /      &bBy jonagamerpro1234");
    sendEnable("&d / ___ / /___    &bVersion&7: &3" + version);
    sendEnable("&d/_/  |_\\____/   &eThanks for using AdvancedChat &c<3");
  }

  public static void setLoad() {
    sendEnable("&5<||======================&e[&bLoading &dAdvancedChat&e]&5======================----");
  }

  public static void setTitleLoad(String title) {
    sendEnable("&5<||=====================&e[" + title + "&e]&5======================----");
  }

  public static void setLineLoad(String message) {
    sendEnable("&5<|| &c* " + message);
  }

    public static void setEndLoad() {
        sendEnable("&5<||================================================----");
    }

    public static @NotNull String getPrefix() {
        return color("&e[&dAdvancedChat&e]&7 ");
    }

    public static @NotNull String getPrefix(boolean ignore) {
        String prefix;
        if (ignore) {
            prefix = "&e[&dAdvancedChat&e] &7";
        } else {
            prefix = Settings.message_prefix_custom + "&7 ";
        }
        return color(prefix);
    }

    public static @NotNull String getPrefixPlayer() {
        return color("&6[&dAdvancedChat&6]&7 ");
    }

    public static void setEnabled(String version) {
        sendEnable(prefix + "&5<||============================================----");
        sendEnable(prefix + "&5<|| &c* &bThe plugin is &d[&aSuccessfully activated&d]");
        sendEnable(prefix + "&5<|| &c* &bVersion: &e[&a" + version + "&e]");
        sendEnable(prefix + "&5<|| &c* &bBy: &e[&bjonagamerpro1234&e]");
    sendEnable(prefix + "&5<|| &c* &bTested Versions &3|&a1.8.x &3- &a1.18.x&3| &eComing Soon -> &c1.19");
    sendEnable(prefix + "&5<|| &a* &eThanks for using &bAdvancedChat &c<3");
    sendEnable(prefix + "&5<||============================================----");
  }

  public static void setDisabled(String version) {
    sendEnable(prefix + "&5<||============================================----");
    sendEnable(prefix + "&5<|| &c* &bThe plugin is &d[&cSuccessfully disabled&d]");
    sendEnable(prefix + "&5<|| &c* &bVersion: &e[&a" + version + "&e]");
    sendEnable(prefix + "&5<|| &c* &bBy: &e[&bjonagamerpro1234&e]");
    sendEnable(prefix + "&5<|| &c* &bTested Versions &3|&a1.8.x &3- &a1.18.x&3| &eComing Soon -> &c1.19");
    sendEnable(prefix + "&5<|| &a* &eThanks for using &bAdvancedChat &c<3");
    sendEnable(prefix + "&5<||============================================----");
  }

  public static @NotNull String getVar(@NotNull Player player, String text) {
    text = text.replace("<name>", player.getName());
    text = text.replace("<displayname>", player.getDisplayName());
    text = text.replace("<Name>", player.getName());
    text = text.replace("<DisplayName>", player.getDisplayName());
    text = text.replaceAll("<world>", player.getWorld().getName());
    text = text.replaceAll("<World>", player.getWorld().getName());
    text = placeholderReplace(text, player);
    text = getOnlinePlayers(text);
    return text;
  }

  private static String placeholderReplace(String text, Player player) {
    if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI"))
      return PlaceholderAPI.setPlaceholders(player, text);
    return text;
  }

  public static @NotNull String getOnlinePlayers(String text) {
    int playersOnline = 0;
    try {
      if (Bukkit.class.getMethod("getOnlinePlayers").getReturnType() == Collection.class) {
        playersOnline = ((Collection<?>) Bukkit.class.getMethod("getOnlinePlayers").invoke(null, new Object[0])).size();
      } else {
        playersOnline = ((Player[]) Bukkit.class.getMethod("getOnlinePlayers").invoke(null, new Object[0])).length;
      }
    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignored) {
    }
    text = text.replace("<online>", "" + playersOnline);
    text = text.replace("<Online>", "" + playersOnline);
    return text;
  }

  @SuppressWarnings("deprecation")
  public static @NotNull ItemStack getPlayerHead(String player) {
    boolean isNewVersion = Arrays.stream(Material.values()).map(Enum::name).collect(Collectors.toList()).contains("PLAYER_HEAD");
    Material type = Material.matchMaterial(isNewVersion ? "PLAYER_HEAD" : "SKULL_ITEM");
    assert type != null;
    ItemStack item = new ItemStack(type, 1);
    if (!isNewVersion)
      item.setDurability((short) 3);
    SkullMeta meta = (SkullMeta) item.getItemMeta();
    assert meta != null;
    meta.setOwner(player);
    meta.setDisplayName(color("&b&l" + player));
    item.setItemMeta(meta);
    return item;
  }

  public static ItemStack createSkull(@NotNull String url) {
    ItemStack head = XMaterial.PLAYER_HEAD.parseItem();
    if (url.isEmpty())
      return head;
    assert head != null;
    SkullMeta headMeta = (SkullMeta) head.getItemMeta();
    GameProfile profile = new GameProfile(UUID.randomUUID(), null);
    profile.getProperties().put("textures", new Property("textures", url));
    try {
      assert headMeta != null;
      Field profileField = headMeta.getClass().getDeclaredField("profile");
      profileField.setAccessible(true);
      profileField.set(headMeta, profile);
    } catch (IllegalArgumentException | NoSuchFieldException | SecurityException | IllegalAccessException error) {
      error.printStackTrace();
    }
    head.setItemMeta(headMeta);
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

  public static @NotNull List<String> setLimitTab(@NotNull List<String> options, String lastArgs) {
    List<String> returned = new ArrayList<>();

    for (String s : options) {

      if (s == null) continue;

      if (s.toLowerCase().startsWith(lastArgs.toLowerCase()))
        returned.add(s);
    }
    return returned;
  }

}
