package jss.advancedchat.commands.utils;

import java.util.List;

public interface ArgumentMatcher {

    List<String> filter(List<String> tabCompletions, String argument);

}
