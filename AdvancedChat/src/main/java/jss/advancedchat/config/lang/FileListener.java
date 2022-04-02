package jss.advancedchat.config.lang;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.bukkit.plugin.java.JavaPlugin;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Logger;

public class FileListener {
	
	private AdvancedChat plugin = AdvancedChat.get();
	
	public List<String> getList() throws IOException{
		List<String> result = new ArrayList<>();
		
		File dir = new File(plugin.getDataFolder(), "lang");
		
		if(dir.exists()) {
			
			FilenameFilter filter = new FilenameFilter() {
				
				public boolean accept(File dir, String name) {
					String lowerCaseName = name.toLowerCase();
					
					if(lowerCaseName.endsWith(".yml") && name.length() == 18 && name.substring(15, 16).equals("-")) {
						return true;
					}else {
						Logger.warning("&eFileName:&a" + name + "&eis not in the correct format for a lang file &8- &bskipping...");
						return false;
					}
				}
			};
			
			for(String files : dir.list(filter)) {
				result.add(files.replace(".yml", ""));
			}		
		}
		
		if(!result.isEmpty()) return result;
		
		File jarFile;
		
		try {
			Method method = JavaPlugin.class.getDeclaredMethod("getFile");
			method.setAccessible(true);
			
			jarFile = (File) method.invoke(plugin);
		}catch(Exception ex) {
			throw new IOException(ex);
		}
		
		JarFile jar = new JarFile(jarFile);
		
		Enumeration<JarEntry> entry = jar.entries();
		while(entry.hasMoreElements()) {
			JarEntry jarEntry = entry.nextElement();
			String path = jarEntry.getName();
			
			if(!path.startsWith("lang")) continue;
			
			if(jarEntry.getName().startsWith(".yml")) {
				String name = jarEntry.getName().replace(".yml", "").replace("lang/", "");
				
				if(name.length() == 18 && name.substring(15, 16).equals("-")) {
					result.add(name);
				}
			}
		}
		jar.close();
		return result;
	}
	
}
