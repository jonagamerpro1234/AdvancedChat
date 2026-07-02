package jss.advancedchat.files.utils;

import jss.advancedchat.AdvancedChat;

import java.io.File;
import java.io.InputStream;

public class FileManager {
    private final AdvancedChat plugin;

    public FileManager(AdvancedChat plugin) {
        this.plugin = plugin;
    }

    public void createVoidFolder(String name) {
        File folder = new File(getDataFolder(), name);
        if (!folder.exists())
            try {
                folder.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void createFolderAndFile(String nameFolder, String nameFile) {
        File folder = new File(getDataFolder(), nameFolder);
        if (!folder.exists())
            try {
                folder.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        File file = new File(folder, nameFile);
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void saveResources(String filename, boolean replace) {
        this.plugin.saveResource(filename, replace);
    }

    public InputStream getResources(String filename) {
        return this.plugin.getResource(filename);
    }

    public File getDataFolder() {
        return this.plugin.getDataFolder();
    }
}
