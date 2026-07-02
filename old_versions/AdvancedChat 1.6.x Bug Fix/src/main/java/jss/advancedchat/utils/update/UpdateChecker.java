package jss.advancedchat.utils.update;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Logger;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker {

    private final AdvancedChat plugin;
    private final int ID;

    public UpdateChecker(AdvancedChat plugin, int ID) {
        this.plugin = plugin;
        this.ID = ID;
    }

    public void getUpdateVersion(Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try {
                InputStream inputStream = new URL(UpdateSettings.SPIGOT_UPDATE_API + this.ID).openStream();
                try {
                    Scanner scanner = new Scanner(inputStream);
                    try {
                        if (scanner.hasNext()){
                            consumer.accept(scanner.next());
                            scanner.close();
                        }
                    } catch (Throwable throwable) {
                        try {
                            scanner.close();
                        } catch (Throwable throwable1) {
                            throwable.addSuppressed(throwable1);
                        }
                        throw throwable;
                    }
                    inputStream.close();
                } catch (Throwable throwable) {
                    if (inputStream != null)
                        try {
                            inputStream.close();
                        } catch (Throwable throwable1) {
                            throwable.addSuppressed(throwable1);
                        }
                    throw throwable;
                }
            } catch (IOException e) {
                Logger.info("Could not check for updates: &c" + e.getMessage());
            }
        });
    }
}
