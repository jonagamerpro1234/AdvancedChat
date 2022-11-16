package jss.advancedchat.files.utils;

import jss.advancedchat.files.LangFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PreConfigLoader {

    public void loadSettings(){

    }

    public boolean loadLangs(){
        HashMap<String, LangFile> availableLangs = new HashMap<>();

        FileList fileList = new FileList();
        int index = 1;

        try{
            for(String code : fileList.list()){
                availableLangs.put(code, new LangFile(code, index++));
            }
        }catch (IOException ex){
            throw  new RuntimeException(ex);
        }

        if(!availableLangs.containsKey(Settings.settings_lang_name)){
            Settings.settings_lang_name = "en_US";
            availableLangs.put(Settings.settings_lang_name, new LangFile(Settings.settings_lang_name,0));
        }

        return true;
    }

}
