package jss.advancedchat.utils.interfaces;

import java.util.function.Consumer;

public interface UpdateHelper {
	public void getUpdateVersion(Consumer<String> consumer);
}
