package jss.advancedchat.files;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.files.utils.Settings;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Locale;

@SuppressWarnings("all")
public class LangFile {

    private final AdvancedChat plugin = AdvancedChat.get();
    private FileConfiguration config;
    private File file;
    private final Locale localeObj;
    private final String localeName;
    private final int index;

    public LangFile(@NotNull String localeName, int index){
        this.index = index;
        this.localeName = localeName;
        getConfig(localeName);
        loadMessages();
        localeObj = new Locale(localeName.substring(0,2),localeName.substring(3,5));
    }

    public FileConfiguration getConfig(final String name) {
        if(config == null){
            reload(name);
        }
        return config;
    }

    public void reload(final String name){
        File dir = new File(plugin.getDataFolder(),"langs");

        if(!dir.exists()){
            //noinspection ResultOfMethodCallIgnored
            dir.mkdir();
        }

        if(file == null){
            file = new File(dir.getPath(), name + ".yml");
        }

        if(dir.exists()){
            config = YamlConfiguration.loadConfiguration(file);
        }else{

            if(plugin.getResource("langs/" + name + ".yml") != null){
                plugin.saveResource("langs/" + name + ".yml", true);
                file = new File(plugin.getDataFolder() + File.separator + "langs", name + ".yml");
                config = YamlConfiguration.loadConfiguration(file);
            }
        }
    }

    private void loadMessages(){
        Settings.messages_prefix = config.getString("Prefix");
        Settings.messages_noPermission = config.getString("NoPermission");
        Settings.messages_update_console = config.getStringList("UpdateAlert.Console");
        Settings.messages_update_player = config.getStringList("UpdateAlert.Player");
    }

    public String getLocaleName() {
        return this.localeName;
    }

    public String getLanguageName() {
        if (localeObj == null) {
            return "unknown";
        }
        return localeObj.getDisplayLanguage(localeObj);
    }

    public String getCountryName() {
        if (localeObj == null) {
            return "unknown";
        }
        return localeObj.getDisplayCountry(localeObj);
    }

    public int getIndex() {
        return index;
    }

}
