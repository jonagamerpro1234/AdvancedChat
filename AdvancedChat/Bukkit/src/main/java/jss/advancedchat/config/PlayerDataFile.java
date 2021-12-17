package jss.advancedchat.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Logger;
import jss.advancedchat.utils.file.FileManager;

public class PlayerDataFile extends FileManager {
	
	private String path;
	private String folder;
	private File file;
	private JSONObject json;
	private JSONParser parser = new JSONParser();
	
	public PlayerDataFile(AdvancedChat plugin, String path, String folder) {
		super(plugin);
		this.path = path;
		this.folder = folder;
	}
	
	public void create() {
		Logger.debug("&e {File} -> create json file player data ");
		this.file = new File(getDataFolder() + File.separator + this.folder, this.path);
		try {
			if(!this.file.exists()) {
				PrintWriter printWriter;
					printWriter = new PrintWriter(this.file, "UTF-8");
					printWriter.append("{");
					printWriter.append("}");
					printWriter.flush();
					printWriter.close();

			}
			this.json = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream(this.file), "UTF-8"));	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void reload() {
		
	}
	
	public void save() {
		
	}
	
}
