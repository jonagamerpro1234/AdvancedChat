package jss.advancedchat.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import jss.advancedchat.AdvancedChat;

public class UpdateChecker2 {
	
	private AdvancedChat plugin;
	
	public UpdateChecker2(AdvancedChat plugin) {
		this.plugin = plugin;
	}

	
	public void getUpdateVersion() {
        String version = getJson("https://songoda.com/api/v2/products/advancedchat-chat-related/");
        if (version.trim() != null && !version.trim().equalsIgnoreCase(plugin.version)) {
           
        }
	}
	
	
	public String getJson(String arg) {
		try {
			URL url = new URL(arg);
			URLConnection connection = url.openConnection();
			BufferedReader buffered= new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = "";
			if((line = buffered.readLine()) != null) {
				JsonElement jsonElement = (new JsonParser()).parse(line).getAsJsonObject().get("data").getAsJsonObject().get("versions").getAsJsonArray().get(0);
				String latest = jsonElement.getAsJsonObject().get("version").getAsString();
				return latest;
			}
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
