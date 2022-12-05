package jss.advancedchat.utils.interfaces;

import java.util.function.Consumer;

public interface UpdateHelper {
    void getUpdateVersion(Consumer<String> paramConsumer);
}
