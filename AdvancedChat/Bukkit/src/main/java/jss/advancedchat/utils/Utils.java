package jss.advancedchat.utils;

import com.cryptomorin.xseries.XMaterial;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.common.update.UpdateSettings;
import jss.advancedchat.common.utils.IridiumColorAPI;
import jss.advancedchat.manager.PlayerManager;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;


@SuppressWarnings({ "deprecation", "unused" })
public class Utils {
   private static final String prefix = getPrefix();
   private static Pattern HEX_PATTERT = Pattern.compile("#[a-fA-F0-9]{6}");
   private static AdvancedChat plugin = AdvancedChat.get();

   public static String getCustomLine(String arg, String color) {
      return color(color + "-=-=-=-=-=-=-=-=-=-=-=" + arg + "=-=-=-=-=-=-=-=-=-=-=-");
   }
   
   public static boolean hasPerm(Player player, String perm) {
	   return player.hasPermission(perm);
   }
   
   public static String getVoidLine(String color) {
      return color(color + "                                             ");
   }

   public static String getLine(String color) {
      return color(color + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
   }

   public static String getLine() {
      return "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-";
   }

   public static String hexcolor2(String text) {
      if (Bukkit.getVersion().contains("1.16") || Bukkit.getVersion().contains("1.17")) {
         for(Matcher match = HEX_PATTERT.matcher(text); match.find(); match = HEX_PATTERT.matcher(text)) {
            String color = text.substring(match.start(), match.end());
            text = text.replace(color, "" + fixColor(color));
         }
      }
      return color(text);
   }

   public static String hexcolor(String text) {
      if (Bukkit.getVersion().contains("1.15")) {
         return color(text);
      } else {
         Matcher match = HEX_PATTERT.matcher(text);
         StringBuffer buffer = new StringBuffer();

         while(match.find()) {
            match.appendReplacement(buffer, fixColor(match.group()).toString());
         }
         return color(match.appendTail(buffer).toString());
      }
   }

   public static String IridiumColor(String text) {
      return IridiumColorAPI.process(text);
   }

   public static String color(String text) {
      return ChatColor.translateAlternateColorCodes('&', text);
   }

   public static String colorless(String text) {
      return ChatColor.stripColor(text);
   }

   public static ChatColor fixColor(String color) {
      return ChatColor.of(color);
   }

   public static ChatColor fixColor2(String color) {
      return ChatColor.of(hexcolor(color));
   }

   public static void sendColorMessage(Player player, String text) {
      player.sendMessage(hexcolor(text));
   }

   public static void sendColorMessage(CommandSender sender, String text) {
      sender.sendMessage(hexcolor(text));
   }

   public static void sendColorConsoleMessage(CommandSender sender, String text) {
      sender.sendMessage(color(text));
   }

   public static void sendColorMessage(String text, TextComponent component) {
      TextComponent textComponent = new TextComponent(text);
      sendAllPlayerBaseComponent(component, (BaseComponent)textComponent);
   }

   public static void sendColorMessage(String msg) {
      Bukkit.broadcastMessage(hexcolor(msg));
   }

   private static void sendEnable(String prefix, String message) {
      CommandSender c = Bukkit.getConsoleSender();
      sendColorMessage((CommandSender)c, (String)(prefix + message));
   }

   private static void sendEnable(String message) {
      CommandSender c = Bukkit.getConsoleSender();
      sendColorMessage((CommandSender)c, (String)message);
   }

   public static void sendTextComponentHover(Player j, String action, String message, String submessage, String color) {
      TextComponent msg = new TextComponent(hexcolor(message));
      msg.setHoverEvent(new HoverEvent(Action.valueOf(getActionHoverType(action)), (new ComponentBuilder(submessage)).color(ChatColor.of(color)).create()));
      j.spigot().sendMessage(msg);
   }

   public static void sendHoverEvent(Player j, String action, String message, String submessage) {
      TextComponent msg = new TextComponent(hexcolor(message));
      msg.setHoverEvent(new HoverEvent(Action.valueOf(getActionHoverType(action)), (new ComponentBuilder(hexcolor(submessage))).create()));
      j.spigot().sendMessage(msg);
   }

   public static void sendAllHoverEvent(String action, String message, String submessage) {
      TextComponent msg = new TextComponent(hexcolor(message));
      msg.setHoverEvent(new HoverEvent(Action.valueOf(getActionHoverType(action)), (new ComponentBuilder(hexcolor(submessage))).create()));
      sendAllPlayerBaseComponent(msg);
   }

   public static void sendClickEvent(Player j, String action, String message, String arg0) {
      TextComponent msg = new TextComponent(hexcolor(message));
      msg.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.valueOf(getActionClickType(action)), arg0));
      j.spigot().sendMessage(msg);
   }

   public static void sendDoubleTextComponent(Player player, String text, String subtext, String hoverAction, String clickAction, String action) {
      TextComponent component = new TextComponent(hexcolor(text));
      component.setHoverEvent(new HoverEvent(Action.valueOf(getActionHoverType(hoverAction)), (new ComponentBuilder(hexcolor(subtext))).create()));
      component.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.valueOf(getActionClickType(clickAction)), action));
      player.spigot().sendMessage(component);
   }


   public static void sendAllPlayerBaseComponent(BaseComponent component) {
      Iterator<?> var2 = Bukkit.getOnlinePlayers().iterator();

      while(var2.hasNext()) {
         Player p = (Player)var2.next();
         p.spigot().sendMessage(component);
      }

   }

   public static void sendAllPlayerBaseComponent(BaseComponent component, BaseComponent component2) {
      Iterator<?> var3 = Bukkit.getOnlinePlayers().iterator();

      while(var3.hasNext()) {
         Player p = (Player)var3.next();
         p.spigot().sendMessage(new BaseComponent[]{component, component2});
      }

   }
   
   public static void sendBaseComponent(BaseComponent component, BaseComponent component2) {
	   CommandSender sender = Bukkit.getConsoleSender();
	   sender.spigot().sendMessage(component, component2);
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

   public static String getActionClickType(String arg) {
      if (arg.equalsIgnoreCase("url")) {
         return "OPER_URL";
      } else if (arg.equalsIgnoreCase("cmd")) {
         return "RUN_COMMAND";
      } else {
         return arg.equalsIgnoreCase("suggest_cmd") ? "SUGGEST_COMMAND" : null;
      }
   }

	public static void setTitle(String version) {
		sendEnable("&d    ___   ______");
		sendEnable("&d   /   | / ____/ &a");
		sendEnable("&d  / /| |/ /      &bBy jonagamerpro1234");
		sendEnable("&d / ___ / /___    &bVersion&7: &3" + version);
		sendEnable("&d/_/  |_\\____/   &eThanks for using AdvancedChat &c<3");
	}
	
	public static void setLoad(String version) {
		sendEnable("&5<||======================&e[&bLoading &dAdvancedChat&e]&5======================----");
	}

	@Deprecated
	public static void setMessageLoad(String title, List<String> list) {
		sendEnable("&5<||=====================&e[" + title + "&e]&5======================----");

		for (int i = 0; i < list.size(); ++i) {
			String text = (String) list.get(i);
			sendEnable("&5<|| &c* " + text);
			if (i == list.size()) {
				break;
			}
		}
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
	
	public static String getPrefix() {
		return color("&e[&dAdvancedChat&e]&7 ");
	}

	public static String getPrefixPlayer() {
		return color("&6[&dAdvancedChat&6]&7 ");
	}

	public static void setEnabled(String version) {
		sendEnable(prefix, "&5<||============================================----");
		sendEnable(prefix, "&5<|| &c* &bThe plugin is &d[&aSuccessfully activated&d]");
		sendEnable(prefix, "&5<|| &c* &bVersion: &e[&a" + version + "&e]");
		sendEnable(prefix, "&5<|| &c* &bBy: &e[&bjonagamerpro1234&e]");
		sendEnable(prefix, "&5<|| &c* &bTested Versions &3|&a1.8.x &3- &a1.18.x&3| &eComing Soon -> &c1.19");
		sendEnable(prefix, "&5<|| &a* &eThanks for using &bAdvancedChat &c<3");
		sendEnable(prefix, "&5<||============================================----");
	}

	public static void setDisabled(String version) {
		sendEnable(prefix, "&5<||============================================----");
		sendEnable(prefix, "&5<|| &c* &bThe plugin is &d[&cSuccessfully disabled&d]");
		sendEnable(prefix, "&5<|| &c* &bVersion: &e[&a" + version + "&e]");
		sendEnable(prefix, "&5<|| &c* &bBy: &e[&bjonagamerpro1234&e]");
		sendEnable(prefix, "&5<|| &c* &bTested Versions &3|&a1.8.x &3- &a1.18.x&3| &eComing Soon -> &c1.19");
		sendEnable(prefix, "&5<|| &a* &eThanks for using &bAdvancedChat &c<3");
		sendEnable(prefix, "&5<||============================================----");
	}

	public static void sendLile() {
		sendEnable(prefix, "&5<||============================================----");
	}

   public static String getVar(Player player, String text) {
      //new PlayerManager(plugin);
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
      return Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI") ? PlaceholderAPI.setPlaceholders(player, text) : text;
   }

   public static boolean doesPluginExist(String plugin) {
      return doesPluginExist(plugin, (String)null);
   }

   public static boolean doesPluginExist(String plugin, boolean s) {
      return doesPluginExist(plugin, (String)null, s);
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

   public static String getOnlinePlayers(String text) {
      int playersOnline = 0;

      try {
         if (Bukkit.class.getMethod("getOnlinePlayers").getReturnType() == Collection.class) {
            playersOnline = ((Collection<?>)Bukkit.class.getMethod("getOnlinePlayers").invoke((Object)null)).size();
         } else {
            playersOnline = ((Player[])Bukkit.class.getMethod("getOnlinePlayers").invoke((Object)null)).length;
         }
      } catch (NoSuchMethodException var3) {
      } catch (InvocationTargetException var4) {
      } catch (IllegalAccessException var5) {
      }

      text = text.replace("<online>", "" + playersOnline);
      text = text.replace("<Online>", "" + playersOnline);
      return text;
   }

   public static ItemStack getPlayerHead(String player) {
      boolean isNewVersion = ((List<?>)Arrays.stream(Material.values()).map(Enum::name).collect(Collectors.toList())).contains("PLAYER_HEAD");
      Material type = Material.matchMaterial(isNewVersion ? "PLAYER_HEAD" : "SKULL_ITEM");
      ItemStack item = new ItemStack(type, 1);
      if (!isNewVersion) {
         item.setDurability((short)3);
      }

      SkullMeta meta = (SkullMeta)item.getItemMeta();
      meta.setOwner(player);
      meta.setDisplayName(color("&b&l" + player));
      item.setItemMeta(meta);
      return item;
   }

   public static ItemStack createSkull(String url) {
      ItemStack head = XMaterial.PLAYER_HEAD.parseItem();
      if (url.isEmpty()) {
         return head;
      } else {
         SkullMeta headMeta = (SkullMeta)head.getItemMeta();
         GameProfile profile = new GameProfile(UUID.randomUUID(), (String)null);
         profile.getProperties().put("textures", new Property("textures", url));

         try {
            Field profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
         } catch (NoSuchFieldException | SecurityException | IllegalAccessException | IllegalArgumentException var5) {
            var5.printStackTrace();
         }

         head.setItemMeta(headMeta);
         return head;
      }
   }

   public static ItemStack createSkull(String url, List<String> lore) {
      ItemStack head = XMaterial.PLAYER_HEAD.parseItem();
      if (url.isEmpty()) {
         return head;
      } else {
         SkullMeta headMeta = (SkullMeta)head.getItemMeta();
         GameProfile profile = new GameProfile(UUID.randomUUID(), (String)null);
         profile.getProperties().put("textures", new Property("textures", url));

         try {
            Field profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
         } catch (NoSuchFieldException | SecurityException | IllegalAccessException | IllegalArgumentException var6) {
            var6.printStackTrace();
         }

         head.setItemMeta(headMeta);
         return head;
      }
   }

   public static String getDate(long millis) {
      Date date = new Date(millis);
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      String fecha = formatter.format(date);
      return fecha;
   }

   public static String getTime(long millis) {
      Date date = new Date(millis);
      SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
      String hour = formatter.format(date);
      return hour;
   }

   public static List<String> setLimitTab(List<String> list, String inic) {
      List<String> returned = new ArrayList<String>();
      Iterator<String> var4 = list.iterator();

      while(var4.hasNext()) {
         String s = (String)var4.next();
         if (s != null && s.toLowerCase().startsWith(inic.toLowerCase())) {
            returned.add(s);
         }
      }
      return returned;
   }

   public static enum TypeEvent {
      Legacy_Hover,
      Hover,
      Click,
      Double,
      Hover_All;
   }
   
   public static void getInfoPlugin(CommandSender sender, String name, String version, String latestversion) {
	    sendColorMessage(sender, "&5-=-=-=-=-=[&b" + name + "&5]=-=-=-=-=-=-");
		sendColorMessage(sender, "&5> &3Name: &b" + name);
		sendColorMessage(sender, "&5> &3Author: &6jonagamerpro1234");
		sendColorMessage(sender, "&5> &3Version: &6" + version);
		sendColorMessage(sender, "&5> &3Update: &a" + latestversion);
		sendColorMessage(sender, "&5> &6Spigot: &a" + UpdateSettings.URL_PLUGIN[0]);
		sendColorMessage(sender, "&5> &dSongoda: &a" + UpdateSettings.URL_PLUGIN[1]);
		sendColorMessage(sender, "&5-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
   }
}