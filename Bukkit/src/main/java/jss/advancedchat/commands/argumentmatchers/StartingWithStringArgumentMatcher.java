package jss.advancedchat.commands.argumentmatchers;

import jss.advancedchat.commands.utils.ArgumentMatcher;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class StartingWithStringArgumentMatcher implements ArgumentMatcher {

    public List<String> filter(List<String> tabCompletions, String argument) {

        List<String> result = new ArrayList<>();

        StringUtil.copyPartialMatches(argument,tabCompletions,result);

        return result;
    }

}
