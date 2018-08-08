package me.ryanhamshire.PopulationDensity;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.*;

public class InviteManager {

    //private HashMap<UUID, List<UUID>> inviteMap = new HashMap<UUID, List<UUID>>();

    private static InviteManager ourInstance = new InviteManager();

    public static InviteManager instance() {
        return ourInstance;
    }

    public void addInvite(OfflinePlayer inviter, OfflinePlayer invitee) {
        List<String> invites;
        if(PopulationDensity.instance.getConfig().getList("invites." + inviter.getUniqueId()) == null) {
            invites = new ArrayList<String>();
            PopulationDensity.instance.getConfig().set("invites." + inviter.getUniqueId(), invites);
            PopulationDensity.instance.saveConfig();
            PopulationDensity.instance.reloadConfig();
        }
        else {
            invites = (List<String>) PopulationDensity.instance.getConfig().getList("invites." + inviter.getUniqueId().toString());
        }
        //System.out.println("INVITES IS NULL: " + (invites == null ? "YES" : "NO"));
        //System.out.println("INVITES IS NULL: " + (invites == null ? "YES" : "NO"));

        if(!invites.contains(invitee.getUniqueId().toString())) {
            invites.add(invitee.getUniqueId().toString());
        }
        PopulationDensity.instance.getConfig().set("invites." + inviter.getUniqueId(), invites);
        PopulationDensity.instance.saveConfig();
        PopulationDensity.instance.reloadConfig();
    }

    public Optional<HashMap<OfflinePlayer, List<OfflinePlayer>>> toHashMap() {
        HashMap<OfflinePlayer, List<OfflinePlayer>> map = new HashMap<>();
        if(PopulationDensity.instance.getConfig().getConfigurationSection("invites.") == null) {
            return Optional.empty();
        }
        Map<String, Object> objs = PopulationDensity.instance.getConfig().getConfigurationSection("invites.").getValues(true);
        objs.forEach((key, value) -> {
            List<OfflinePlayer> offlines = new ArrayList<OfflinePlayer>();
            for(String s : (List<String>) value) {
                offlines.add(Bukkit.getServer().getOfflinePlayer(UUID.fromString(s)));
            }
            map.put(Bukkit.getServer().getOfflinePlayer(UUID.fromString(key)), offlines);
        });
        return Optional.of(map);
    }

    public boolean canTravel(OfflinePlayer visitor, OfflinePlayer host) {
        if(PopulationDensity.instance.getConfig().getList("invites." + host.getUniqueId()) == null) {
            return false; //no invites given yet
        }
        if(PopulationDensity.instance.getConfig().getList("invites." + host.getUniqueId()).contains(visitor.getUniqueId().toString())) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean deleteInvite(OfflinePlayer inviter, OfflinePlayer invitee) {
        if(PopulationDensity.instance.getConfig().getList("invites." + inviter.getUniqueId()) == null) {
            return false; //did not work
        }
        if(!PopulationDensity.instance.getConfig().getList("invites." + inviter.getUniqueId()).contains(invitee.getUniqueId().toString())) {
            return false; //did not work
        }
        PopulationDensity.instance.getConfig().getList("invites." + inviter.getUniqueId()).remove(invitee.getUniqueId().toString());
        PopulationDensity.instance.saveConfig();
        PopulationDensity.instance.reloadConfig();
        return true;
    }

    public Optional<List<OfflinePlayer>> getInvites(OfflinePlayer inviter) {
        if(PopulationDensity.instance.getConfig().getList("invites." + inviter.getUniqueId()) == null || (PopulationDensity.instance.getConfig().getList("invites." + inviter.getUniqueId()).isEmpty())) {
            return Optional.empty();
        } {
            List<OfflinePlayer> players = new ArrayList<OfflinePlayer>();
            List<String> uuids = ((List<String>) PopulationDensity.instance.getConfig().getList("invites." + inviter.getUniqueId()));
            uuids.forEach(s -> {
                players.add(Bukkit.getServer().getOfflinePlayer(UUID.fromString(s)));
            });
            return Optional.of(players);
        }
    }


    private InviteManager() {

    }


}
