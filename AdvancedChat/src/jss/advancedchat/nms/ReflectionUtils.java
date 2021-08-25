package jss.advancedchat.nms;

import org.bukkit.Bukkit;

public class ReflectionUtils {

	public static Class<?> getNMSClass(String classname){
		try {
			return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + classname);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void getOBSClass(String classname) {
		
	}
	
	public static void sendPacket() {
		
	}
	
}
