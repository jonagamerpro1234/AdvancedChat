package jss.advancedchat.storage.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.storage.utils.IPlayerConfigData;
import jss.advancedchat.storage.utils.IPlayerData;
import jss.advancedchat.utils.Logger;
import net.md_5.bungee.api.ChatColor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

@SuppressWarnings("all")
public class JsonStorage {

    private final AdvancedChat plugin;
    private ArrayList<IPlayerData> iPlayerData = new ArrayList<>();
    private File file;

    public JsonStorage(AdvancedChat plugin) {
        this.plugin = plugin;
    }

    public IPlayerData create(String name) {
        IPlayerData playerData = new IPlayerData(name,"default","color","main",10,"white","FFFFFF",
                "FFFFFF","none",false,0,false,true,true);
        iPlayerData.add(playerData);
        return  playerData;
    }

    public void reload() {

    }

    public void save(String name){
        file = new File(plugin.getDataFolder(), "/Players/" + name + ".json");

        if(!file.getParentFile().exists()){
            file.getParentFile().mkdir();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            Logger.debug("Ya existe el archivo de [" + name + ".json]");
        }

        Gson gson = new Gson();
        try {
            Writer writer = new FileWriter(file, false);
            gson.toJson(iPlayerData, writer);
            writer.flush();
            writer.close();
            Logger.debug("Se han guardado los dataos del jugador correctamente");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
