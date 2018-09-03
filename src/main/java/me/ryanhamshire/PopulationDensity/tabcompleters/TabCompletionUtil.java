package me.ryanhamshire.PopulationDensity.tabcompleters;

import me.ryanhamshire.PopulationDensity.PopulationDensity;
import org.bukkit.Bukkit;
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
        PopulationDensity.instance.getLogger().log(Level.WARNING, "Is items null? " + (items == null));
        PopulationDensity.instance.getLogger().log(Level.WARNING, "Is argus null? " + (args == null));
        List<String> returnList = new ArrayList<>(items);
        if(args.length > 0) {
            returnList.removeIf(name -> {
                PopulationDensity.instance.getLogger().log(Level.WARNING, "IS NAME NULL? " + (name == null));
                return !name.toLowerCase().startsWith(PopulationDensity.join(args).toLowerCase());
            });
        }
        /*
        if(PopulationDensity.join(args).startsWith("TABCOMPLETESTRESSTEST")) {
            //Dogeveloper: Tab complete stress test. to be removed in final version. for now we use the seed as it's a nice long number and adding to it doesn't take much compute power.
            final int stressTestValue = 50; //multiplied by 3
            Bukkit.getServer().getWorlds().forEach(world -> {
                int counter = 0;
                while(counter <= stressTestValue) {
                    returnList.add(String.valueOf(world.getSeed() + counter));
                    counter++;
                }
            });
        }
        */
        Bukkit.getServer().getWorld("world").getSeed();
        returnList.sort(String.CASE_INSENSITIVE_ORDER);
        return returnList;
    }
}