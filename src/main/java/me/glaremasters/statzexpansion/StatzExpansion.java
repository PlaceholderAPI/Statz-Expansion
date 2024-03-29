package me.glaremasters.statzexpansion;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.staartvin.statz.Statz;
import me.staartvin.statz.api.API;
import me.staartvin.statz.datamanager.player.PlayerStat;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

/**
 * Created by GlareMasters on 2/3/2018.
 */
public class StatzExpansion extends PlaceholderExpansion {


    private Statz plugin;
    API api;

    @Override
    public boolean canRegister() {
        Statz statz = (Statz) Bukkit.getServer().getPluginManager().getPlugin(getRequiredPlugin());

        if (statz != null) {
            api = statz.getStatzAPI();
        }
        return api != null;
    }


    @Override
    public boolean register() {
        /*
         * Make sure "SomePlugin" is on the server
         */
        if (!canRegister()) {
            return false;
        }

        plugin = (Statz) Bukkit.getPluginManager().getPlugin(getRequiredPlugin());

        if (plugin == null) {
            return false;
        }

        return super.register();
    }

    @Override
    public String getAuthor() {
        return "Blockslayer22";
    }

    @Override
    public String getIdentifier() {
        return "statz";
    }

    @Override
    public String getRequiredPlugin() {
        return "Statz";
    }

    @Override
    public String getVersion() {
        return "1.0.3";
    }

    @Override
    public String onRequest(OfflinePlayer player, String identifier) {

        if (player == null) {
            return "";
        }

        switch(identifier) {
            case "joins":
                return String.valueOf(api.getSpecificData(PlayerStat.JOINS, player.getUniqueId()).longValue());
            case "death":
                return String.valueOf(api.getSpecificData(PlayerStat.DEATHS, player.getUniqueId()).longValue());
            case "blocks_broken":
                return String.valueOf(api.getSpecificData(PlayerStat.BLOCKS_BROKEN, player.getUniqueId()).longValue());
            case "blocks_placed":
                return String.valueOf(api.getSpecificData(PlayerStat.BLOCKS_PLACED, player.getUniqueId()).longValue());
            case "damage_taken":
                return String.valueOf(api.getSpecificData(PlayerStat.DAMAGE_TAKEN, player.getUniqueId()).longValue());
            case "distance_traveled":
                return String.valueOf(api.getSpecificData(PlayerStat.DISTANCE_TRAVELLED, player.getUniqueId()).longValue());
            case "food_eaten":
                return String.valueOf(api.getSpecificData(PlayerStat.FOOD_EATEN, player.getUniqueId()).longValue());
            case "crafted_items":
                return String.valueOf(api.getSpecificData(PlayerStat.ITEMS_CRAFTED, player.getUniqueId()).longValue());
            case "caught_items":
                return String.valueOf(api.getSpecificData(PlayerStat.ITEMS_CAUGHT, player.getUniqueId()).longValue());
            case "players_killed":
                return String.valueOf(api.getSpecificData(PlayerStat.KILLS_PLAYERS, player.getUniqueId()).longValue());
            case "time_played":
                return String.valueOf(api.getSpecificData(PlayerStat.TIME_PLAYED, player.getUniqueId()).longValue());
            case "times_shorn":
                return String.valueOf(api.getSpecificData(PlayerStat.TIMES_SHORN, player.getUniqueId()).longValue());
            case "xp_gained":
                return String.valueOf(api.getSpecificData(PlayerStat.XP_GAINED, player.getUniqueId()).longValue());
            case "mobs_killed":
                return String.valueOf(api.getSpecificData(PlayerStat.KILLS_MOBS, player.getUniqueId()).longValue());
        }
        return null;
    }

}
