package me.ryanhamshire.PopulationDensity.tabcompleters;

import me.ryanhamshire.PopulationDensity.InviteManager;
import me.ryanhamshire.PopulationDensity.PopulationDensity;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

//Dogeveloper: Tab completer for invite and cancelinvite command.
public class InviteTabCompleter implements TabCompleter {
    public List<String> onTabComplete(CommandSender cs, Command cmd, String label, String[] args) {
        if(!(cs instanceof Player)) { return Collections.emptyList();}
        Player plr = (Player) cs;
        List<String> returnList = new ArrayList<>();
        //Dogeveloper: Add offline players, except for those who are already invited.
        Bukkit.getServer().getOnlinePlayers().forEach(offlinePlayer -> {
            if(cmd.getName().equalsIgnoreCase("invite") || cmd.getAliases().contains(cmd.getName())) {
                if(!InviteManager.instance().canTravel(offlinePlayer, plr)) {
                    returnList.add((offlinePlayer).getName());
                }
            }
        });
        if(cmd.getName().equalsIgnoreCase("cancelinvite") || cmd.getAliases().contains(cmd.getName())) {
            if(InviteManager.instance().getInvites(plr).isPresent()) {
                InviteManager.instance().getInvites(plr).get().forEach(plr2 -> returnList.add(plr2.getName()));
            }
        }
        return TabCompletionUtil.processTabCompletion(returnList, args);
    }
}
