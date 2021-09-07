package jss.advancedchat.nms;

import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.Server;

public class ReflectionUtils {

	public static String preClassB = "org.bukkit.craftbukkit";
	public static String preClassM = "net.minecraft.server";

	static {

		if (Bukkit.getServer() != null) {

			Server server = Bukkit.getServer();
			Class<?> clazz = server.getClass();
			String[] pas = clazz.getName().split("\\.");
			if (pas.length == 5) {
				String v = pas[3];
				preClassB += "." + v;
			}

			try {

				Method getHandle = clazz.getDeclaredMethod("getHandle");
				Object handle = getHandle.invoke(server);
				Class<? extends Object> handleServerClass = handle.getClass();
				pas = handleServerClass.getName().split("\\.");
				if (pas.length == 5) {
					String verM = pas[3];
					preClassM += "." + verM;
				}

			} catch (Exception e) {
			}
		}

	}

	public static Class<?> getNMSClass(String classname) throws ClassNotFoundException {
		return Class.forName("net.minecraft.server."
				+ Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + classname);
	}

	public static Class<?> getOBSClass(String classname) throws ClassNotFoundException {
		return Class.forName("org.bukkit.craftbukkit."
				+ Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + classname);
	}
}
