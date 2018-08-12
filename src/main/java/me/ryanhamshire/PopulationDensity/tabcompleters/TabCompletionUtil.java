package me.ryanhamshire.PopulationDensity.tabcompleters;

import me.ryanhamshire.PopulationDensity.PopulationDensity;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.logging.Level;

//Dogeveloper: simple class to help with handling tab completion
public class TabCompletionUtil {
    protected static List<String> processTabCompletion(List<String> items, String[] args) {
        List<String> returnList = new ArrayList<>(items);
        if(args.length > 0) {
            returnList.removeIf(name -> !name.toLowerCase().startsWith(PopulationDensity.join(args).toLowerCase()));
        }
        returnList.sort(String.CASE_INSENSITIVE_ORDER);
        return returnList;
    }
}