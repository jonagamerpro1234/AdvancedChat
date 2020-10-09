package jss.advancedchat.utils;

import java.io.File;

import jss.advancedchat.AdvancedChat;

public class FileManager {
	
	private AdvancedChat plugin;
	
	public FileManager(AdvancedChat plugin) {
		this.plugin = plugin;
	}

	public void createVoidFolder(String name) {
		File folder = new File(getDataFolder(), name);
		if(!folder.exists()){
			try {
				folder.mkdir();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void createFolderAndFile(String namefolder, String namefile) {
		File folder = new File(getDataFolder(), namefolder);
		if(!folder.exists()) {
			try {
				folder.mkdir();
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
		
		File file = new File(folder, namefile);
		if(!file.exists()) {
			try {
				file.createNewFile();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public File getDataFolder() {
		return plugin.getDataFolder();
	}
	
}
