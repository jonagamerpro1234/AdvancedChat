package jss.advancedchat.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import jss.advancedchat.AdvancedChat;

public abstract class Reflection extends EventsUtils{
	
	
	public static void sendIChatBaseComponent(AdvancedChat plugin,Player player,String arg0, String value, String action) {
		SendModifyChatEvent sendmodifychat = new SendModifyChatEvent(player, arg0, value,action);
		getManager().callEvent(sendmodifychat);
		
		arg0 = Utils.color(arg0);
		arg0 = replacePlaceholderAPI(plugin, player, arg0);
		arg0 = getAllVars(plugin, player, arg0);
		value = Utils.color(value);
		value = replacePlaceholderAPI(plugin, player, value);
		value = getAllVars(plugin, player, value);
		
		try {
			Object a;
			Object chat;
			Constructor<?> chatConstructor;
			Object chatPacket;
			
			a = getNMSClass("PacketPlayOutChat").getDeclaredClasses()[0].getField("TITLE").get((Object) null);
			chat = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] {String.class}).invoke((Object) null, new Object[] {"{\"text\":\""+arg0+"\",\"hoverEvent\":{\"action\":\""+getActionModify(action)+"\",\"value\":\""+Utils.color(value)+"\"}}"});
			chatConstructor = getNMSClass("PacketPlayOutChat").getConstructor(new Class[]{getNMSClass("PacketPlayOutChat").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent")});		
			chatPacket = chatConstructor.newInstance(new Object[]{a, chat});
			sendPacket(player, chatPacket);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
	}
	
	public static String getActionModify(String arg) {
		
		String temp = arg;
		if(temp.equalsIgnoreCase("text")) {
			return "SHOW_TEXT";
 		}
		if(temp.equalsIgnoreCase("item")) {
			return "SHOW_ITEM";
		}
		if(temp.equalsIgnoreCase("entity")) {
			return "SHOW_ENTITY";
		}
		//click event
		if(temp.equalsIgnoreCase("url")) {
			return "OPER_URL";
 		}
		if(temp.equalsIgnoreCase("cmd")) {
			return "RUN_COMMAND";
		}
		
		return null;
	}
	
	public String getActionHoverType(String arg) {
		
		String temp = arg;
		
		if(temp.equalsIgnoreCase("text")) {
			return "SHOW_TEXT";
 		}
		if(temp.equalsIgnoreCase("item")) {
			return "SHOW_ITEM";
		}
		if(temp.equalsIgnoreCase("entity")) {
			return "SHOW_ENTITY";
		}
		
		return null;
	}
	
	public String getActionClickType(String arg) {
		
		String temp = arg;
		
		if(temp.equalsIgnoreCase("url")) {
			return "OPER_URL";
 		}
		if(temp.equalsIgnoreCase("cmd")) {
			return "RUN_COMMAND";
		}
		return null;
	}
	
	public static String getVersion(){
		String name = Bukkit.getServer().getClass().getPackage().getName();
		String version = name.substring(name.lastIndexOf('.') + 1) + ".";
		return version;
	}

	
	public static void sendPacket(Player player, Object obj) {
		try {
			Object handle = player.getClass().getMethod("getHandle").invoke(player);
			Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
			playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, obj);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static Class<?> getNMSClass(String className){
		String fullName = "net.minecraft.server." + getVersion() + className;
		Class<?> clazz = null;
		try{
			clazz = Class.forName(fullName);
		}catch (Exception e){
			e.printStackTrace();
		}
		return clazz;
	}

	public static Class<?> getOBCClass(String className){
		String fullName = "org.bukkit.craftbukkit." + getVersion() + className;
		Class<?> clazz = null;
		try{
			clazz = Class.forName(fullName);
		}catch (Exception e){
			e.printStackTrace();
		}
		return clazz;
	}

	public static Object getHandle(Object obj){
		try{
			return getMethod(obj.getClass(), "getHandle", new Class[0]).invoke(obj, new Object[0]);
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static Field getField(Class<?> clazz, String name){
		try{
			Field field = clazz.getDeclaredField(name);
			field.setAccessible(true);
			return field;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static Method getMethod(Class<?> clazz, String name, Class<?>... args){
		Method[] arrayOfMethod;
		int j = (arrayOfMethod = clazz.getMethods()).length;
		for (int i = 0; i < j; i++){
			Method m = arrayOfMethod[i];
			if ((m.getName().equals(name)) && ((args.length == 0) || (ClassListEqual(args, m.getParameterTypes())))){
				m.setAccessible(true);
				return m;
			}
		}
		return null;
	}

	public static boolean ClassListEqual(Class<?>[] l1, Class<?>[] l2){
		boolean equal = true;
		if (l1.length != l2.length) {
			return false;
		}
		for (int i = 0; i < l1.length; i++) {
			if (l1[i] != l2[i]){
				equal = false;
				break;
			}
		}
		return equal;
	}
}
