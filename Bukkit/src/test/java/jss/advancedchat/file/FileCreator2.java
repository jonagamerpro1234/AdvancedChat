package jss.advancedchat.file;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileCreator2 {

    private final AdvancedChat plugin;
    private FileConfiguration config;
    private File dir;

    public FileCreator2(AdvancedChat plugin) {
        this.plugin = plugin;
    }

    public void create(@NotNull String name){

        String[] a = name.split("/");

        File file;
        if(!a[0].equalsIgnoreCase("none")){
            dir = new File(plugin.getDataFolder() + File.separator + a[0]);

            if(!dir.exists()){
                dir.getParentFile();
            }
            Logger.info("Dir: " + dir + " is  exists: " + dir.exists());

            file = new File(dir, a[1] + ".yml");
            Logger.info("Dir: " + dir + " File: " + file + " is  exists: " + file.exists());
        }else {
            file = new File(plugin.getDataFolder(), a[1] + ".yml");
            Logger.info("Dir: " + dir + " File: " + file + " is  exists: " + file.exists());
        }

        if(!file.exists()){

            if(a[0].equalsIgnoreCase("none")){
                plugin.saveResource(a[1] + ".yml", false);
            }else{
                plugin.saveResource(a[0] + File.separator + a[1] + ".yml", false);
            }

            if(config == null){
                config = YamlConfiguration.loadConfiguration(file);
                Logger.info("Dir: " + dir + " File: " + file + " is config: " + config);

                Reader reader;
                Logger.info("Get Internal Resources: " + plugin.getResource(file.getName()));
                //noinspection ConstantConditions
                reader = new InputStreamReader(plugin.getResource(file.getName()), StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(reader);
                YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(bufferedReader);
                Logger.info("YamlConfig: " + yamlConfiguration);
                config.setDefaults(yamlConfiguration);
            }else{
                if (a[0].equalsIgnoreCase("none")){
                    file = new File(plugin.getDataFolder(), a[1] + ".yml");
                    Logger.info("Dir: " + dir + " File: " + file + " is  exists: " + file.exists());
                }else{
                    file = new File(dir, a[1] + ".yml");
                    Logger.info("Dir: " + dir + " File: " + file + " is  exists: " + file.exists());
                }
                config.options().copyDefaults(true);
                try {
                    config.save(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Logger.info("YamlConfig is Loaded");
            }


        }




    }

}
