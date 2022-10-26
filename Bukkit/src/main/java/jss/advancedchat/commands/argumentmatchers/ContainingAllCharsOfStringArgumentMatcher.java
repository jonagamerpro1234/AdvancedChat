package jss.advancedchat.commands.argumentmatchers;

import jss.advancedchat.commands.utils.ArgumentMatcher;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ContainingAllCharsOfStringArgumentMatcher implements ArgumentMatcher {


    public List<String> filter(@NotNull List<String> tabCompletions, String argument) {

        List<String> result = new ArrayList<>();

        for(String tabCompletion : tabCompletions){

            boolean passes;

            for(char c : argument.toCharArray()){

                passes = tabCompletion.contains(String.valueOf(c));

                if(passes){
                    result.add(tabCompletion);
                }else{
                    break;
                }
            }
        }

        return result;
    }
}
