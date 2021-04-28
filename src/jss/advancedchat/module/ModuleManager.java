package jss.advancedchat.module;

import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;

public class ModuleManager extends ModuleDescription {
	

	
	@SuppressWarnings({ "resource", "unused" })
	public void getModule(File file) {
		try {
			JarFile jarFile = new JarFile(file);
			
			
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
