package jss.advancedchat.config.player;

import java.io.File;
import java.util.ArrayList;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.bungee.utils.Logger;

public class PlayerManagerFile {
	
	public ArrayList<PlayerFile> playerFiles;
	private AdvancedChat plugin;
	
	public PlayerManagerFile(AdvancedChat plugin) {
		this.playerFiles = new ArrayList<>();
		this.plugin = plugin;
	}
	
	public void setup() {
		createFolder();
		registerPlayerConfig();
	}
	
	public void createFolder() {
		File folder;
		try {
			folder = new File(plugin.getDataFolder() + File.separator + "Data" + File.separator + "Players");
			if(!folder.exists()) {
				folder.mkdirs();
			}
		}catch(SecurityException e) {
			folder = null;
			Logger.error("Could not create folder &9-> &ePlayers");
			e.printStackTrace();
		}
	}
	
	public void registerPlayerConfig() {
		File folder = new File(plugin.getDataFolder() + File.separator + "Data" + File.separator + "Players");
		File[] listOfFiles = folder.listFiles();
		for(int i = 0; i < listOfFiles.length; i++) {
			if(listOfFiles[i].isFile()) {
				String pathName = listOfFiles[i].getName();
				PlayerFile playerFile = new PlayerFile(plugin, pathName);
				playerFile.create();
				playerFiles.add(playerFile);
			}
		}
	}
	
	public ArrayList<PlayerFile> getPlayerConfigs(){
		return this.playerFiles;
	}
	
	public PlayerFile getPlayerConfig(String name){
		for(int i = 0; i < playerFiles.size(); i++) {
			if(playerFiles.get(i).getPath().equals(name)) {
				return playerFiles.get(i);
			}
		}
		return null;
	}
	
	public boolean isRegisteredPlayerConfig(String pathName) {
		for(int i = 0; i < playerFiles.size(); i++) {
			if(playerFiles.get(i).getPath().equals(pathName)) {
				return true;
			}
		}
		return false;
	}
	
	public void removePlayerConfig(String pathName) {
		for(int i = 0; i < playerFiles.size(); i++) {
			if(playerFiles.get(i).getPath().equals(pathName)) {
				playerFiles.remove(i);
			}
		}
	}
	
	
}
