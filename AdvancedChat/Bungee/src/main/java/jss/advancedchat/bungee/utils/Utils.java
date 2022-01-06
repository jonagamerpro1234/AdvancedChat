package jss.advancedchat.bungee.utils;

import java.io.File;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Utils {
   private static final String prefix = getPrefix();

   public static String getLine() {
	   return "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-";
   }
   
   public static String getLine(String color) {
	   return color(color + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
   }
   
   public static String color(String text) {
      return ChatColor.translateAlternateColorCodes('&', text);
   }

   public static String colorless(String text) {
      return ChatColor.stripColor(text);
   }

   public static void sendBungeeColorMessage(ProxiedPlayer player, String text) {
      player.sendMessage((new ComponentBuilder(color(text))).create());
   }

   public static void sendBungeeColorMessage(CommandSender sender, String text) {
      sender.sendMessage((new ComponentBuilder(color(text))).create());
   }
   
   public static void sendBungeeColorMessageConsole(CommandSender sender, String text) {
	      sender.sendMessage((new ComponentBuilder(color(text))).create());
	   }

   private static void sendBungeeEnable(String prefix, String message) {
      CommandSender sender = ProxyServer.getInstance().getConsole();
      sendBungeeColorMessage(sender, prefix + message);
   }

   public static void setBungeeEnabled(String version) {
      sendBungeeEnable(prefix, "&5 <||============================================----");
      sendBungeeEnable(prefix, "&5 <|| &c* &bThe plugin is &d[&aSuccessfully activated&d]");
      sendBungeeEnable(prefix, "&5 <|| &c* &bVersion: &e[&a" + version + "&e]");
      sendBungeeEnable(prefix, "&5 <|| &c* &bBy: &e[&bjonagamerpro1234&e]");
      sendBungeeEnable(prefix, "&5 <|| &c* &bPlatform &9BungeeCord");
      sendBungeeEnable(prefix, "&5 <|| &c* &bTested Versions &a1.17");
      sendBungeeEnable(prefix, "&5 <||============================================----");
   }

   public static void setBungeeDisabled(String version) {
      sendBungeeEnable(prefix, "&5 <||============================================----");
      sendBungeeEnable(prefix, "&5 <|| &c* &bThe plugin is &d[&cSuccessfully disabled&d]");
      sendBungeeEnable(prefix, "&5 <|| &c* &bVersion: &e[&a" + version + "&e]");
      sendBungeeEnable(prefix, "&5 <|| &c* &bBy: &e[&bjonagamerpro1234&e]");
      sendBungeeEnable(prefix, "&5 <|| &c* &bPlatform &9BungeeCord");
      sendBungeeEnable(prefix, "&5 <|| &c* &bTested Versions &a1.17");
      sendBungeeEnable(prefix, "&5 <||============================================----");
   }

   public static String getPrefix() {
      return color("&e[&dAdvancedChat&e]&7");
   }

   public static String getPrefixPlayer() {
      return color("&6[&dAdvancedChat&6]&7");
   }

   public static File getDataFolder() {
      return ProxyServer.getInstance().getPluginsFolder();
   }
}