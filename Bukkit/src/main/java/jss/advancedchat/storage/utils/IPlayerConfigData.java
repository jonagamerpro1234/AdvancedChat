package jss.advancedchat.storage.utils;

import java.io.IOException;

public interface IPlayerConfigData {

    void create(String name) throws IOException;

    void reload();


}
