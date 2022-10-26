package jss.advancedchat.commands.argumentmatchers;

import jss.advancedchat.commands.utils.ArgumentMatcher;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class ContainingStringArgumentMatcher implements ArgumentMatcher {

    public List<String> filter(@NotNull List<String> tabCompletions, String argument) {
        return tabCompletions.stream().filter(tabCompletion -> tabCompletion.contains(argument)).collect(Collectors.toList());
    }
}
