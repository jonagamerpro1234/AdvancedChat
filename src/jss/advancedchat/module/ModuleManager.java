package jss.advancedchat.module;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.jar.JarFile;

@SuppressWarnings("unused")
public class ModuleManager extends ModuleDescription {
	

	
	@SuppressWarnings("resource")
	public void getModule(File file) {
		try {
			JarFile jarFile = new JarFile(file);
			
			
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
