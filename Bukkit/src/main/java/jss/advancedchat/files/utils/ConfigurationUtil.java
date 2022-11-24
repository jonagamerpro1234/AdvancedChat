package jss.advancedchat.files.utils;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationUtil extends YamlConfiguration {

    private List<String> header = new ArrayList<>();

    public static @NotNull ConfigurationUtil loadConfiguration(@NotNull File file){

        ConfigurationUtil config = new ConfigurationUtil();

        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }

        return config;
    }

    protected String buildHeader(){
        StringBuilder stringBuilder = new StringBuilder();
        boolean starterHeader = false;

        for(int i = header.size() - 1; i >= 0; i--){
            stringBuilder.insert(0, "\n");
            //noinspection ConstantConditions
            if((starterHeader) || header.size() != 0){
                stringBuilder.insert(0,header.get(i));
                stringBuilder.insert(0,"# ");
                starterHeader = true;
            }
        }

        return stringBuilder.toString();
    }

    public void setHeader(List<String> header){
        this.header = header;
    }

}
