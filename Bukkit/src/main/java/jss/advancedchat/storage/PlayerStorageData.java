package jss.advancedchat.storage;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.storage.json.JsonStorage;
import jss.advancedchat.storage.yaml.YamlStorage;

public class PlayerStorageData {

    private final AdvancedChat plugin = AdvancedChat.get();

    private final JsonStorage jsonStorage = new JsonStorage(plugin);
    private final YamlStorage yamlStorage = new YamlStorage(plugin);

    public enum StorageType{
        YAML,JSON,MYSQL
    }

    public JsonStorage getJsonStorage() {
        return jsonStorage;
    }

    public YamlStorage getYamlStorage() {
        return yamlStorage;
    }


}
