package jss.advancedchat.storage.json;

import com.google.gson.Gson;
import jss.advancedchat.AdvancedChat;
import jss.advancedchat.storage.PlayerData;
import jss.advancedchat.storage.utils.IStorage;
import jss.advancedchat.utils.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;


public class JsonStorage implements IStorage {

    private final AdvancedChat plugin;
    private ArrayList<PlayerData> iPlayerData = new ArrayList<>();
    private File file;

    public JsonStorage(AdvancedChat plugin) {
        this.plugin = plugin;
    }

    public PlayerData create(String name) {
        PlayerData playerData = new PlayerData(name,"default","color","main",10,"white","FFFFFF",
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

    public void savePlayerData(PlayerData playerData) {

    }

    public PlayerData loadPlayerData(String playerName) {
        return null;
    }
}
