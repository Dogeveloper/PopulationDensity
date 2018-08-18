package me.ryanhamshire.PopulationDensity.tabcompleters;

import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import me.ryanhamshire.PopulationDensity.InviteManager;
import me.ryanhamshire.PopulationDensity.PopulationDensity;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

//DOGEVELOPER: Handles tab completion for /visit.
public class VisitTabCompleter implements TabCompleter {
    public List<String> onTabComplete(CommandSender cs, Command cmd, String label, String[] args) {
        if(!(cs instanceof Player)) { return Collections.emptyList();}
        Player plr = (Player) cs;
        List<String> returnList = new ArrayList<String>();
        //Dogeveloper: Add regions player has been invited to.
        InviteManager.instance().getVisitList(plr).forEach(oplr -> {
            returnList.add(oplr.getName());
        });
        //Dogeveloper: Add named regions.
        returnList.addAll(PopulationDensity.instance.dataStore.coordsToNameMap.values());
        //Dogeveloper: Add players living in public regions.
        //TODO: Make this more efficient.
        Bukkit.getServer().getOnlinePlayers().forEach(plr2 -> {
            if(PopulationDensity.instance.dataStore.getRegionName(PopulationDensity.instance.dataStore.getPlayerData(plr2).homeRegion) != null) {
                returnList.add(plr2.getName());
            }
        });

        //Dogeveloper: If the player has already put something in, sort those out. I joined the arguments here in case the region name contains spaces.
        return TabCompletionUtil.processTabCompletion(returnList, args);
    }
}
