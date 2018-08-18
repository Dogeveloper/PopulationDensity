package me.ryanhamshire.PopulationDensity.tabcompleters;

import me.ryanhamshire.PopulationDensity.InviteManager;
import me.ryanhamshire.PopulationDensity.PopulationDensity;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SeenTabCompleter implements TabCompleter {
    public List<String> onTabComplete(CommandSender cs, Command cmd, String label, String[] args) {
        if(!(cs instanceof Player)) { return Collections.emptyList();}
        Player plr = (Player) cs;
        List<String> returnList = new ArrayList<String>();
        Bukkit.getServer().getOnlinePlayers().forEach((offlinePlayer -> returnList.add(offlinePlayer.getName())));
        return TabCompletionUtil.processTabCompletion(returnList, args);
    }
}
