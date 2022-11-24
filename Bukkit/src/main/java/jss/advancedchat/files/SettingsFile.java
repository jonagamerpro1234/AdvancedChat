package jss.advancedchat.files;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.nio.file.Paths;

public class SettingsFile {

    public SettingsFile() {

        YamlConfigurationLoader loader = YamlConfigurationLoader.builder()
                .path(Paths.get("settings.yml")).build();

        final CommentedConfigurationNode node;

        try {
            node = loader.load();
        } catch (ConfigurateException e) {
            throw new RuntimeException(e);
        }


    }
}
