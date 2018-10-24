package biz.aaronsworld;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.staartvin.statz.Statz;
import me.staartvin.statz.api.API;
import me.staartvin.statz.database.datatype.RowRequirement;
import me.staartvin.statz.datamanager.player.PlayerStat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class StatzExpansion extends PlaceholderExpansion {

    private Statz plugin;
    API api;

    @Override
    public boolean canRegister() {
        Statz statz = (Statz) Bukkit.getServer().getPluginManager().getPlugin("Statz");

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

        plugin = (Statz) Bukkit.getPluginManager().getPlugin(getPlugin());

        if (plugin == null) {
            return false;
        }

        return PlaceholderAPI.registerPlaceholderHook(getIdentifier(), this);
    }

    @Override
    public String getAuthor() {
        return "Ironic_8b49";
    }

    @Override
    public String getIdentifier() {
        return "statz";
    }

    @Override
    public String getPlugin() {
        return "Statz";
    }

    @Override
    public String getVersion() {
        return "1.0.7";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {

        if (player == null) {
            return "";
        } else if (identifier.startsWith("mobs_killed_")) {
            identifier = identifier.replace("mobs_killed_", "");
            return api.getSpecificData(PlayerStat.KILLS_MOBS, player.getUniqueId()) != null ? String.valueOf(api.getSpecificData(PlayerStat.KILLS_MOBS, player.getUniqueId(), new RowRequirement("mob", identifier.toUpperCase())).longValue()) : "0";
        } else if (identifier.startsWith("blocks_broken_")) {
            identifier = identifier.replace("blocks_broken_", "");
            if (identifier.contains(":")) {
                String[] broke = identifier.split(":", 2);
                return api.getSpecificData(PlayerStat.BLOCKS_BROKEN, player.getUniqueId()) != null ? String.valueOf(api.getSpecificData(PlayerStat.BLOCKS_BROKEN, player.getUniqueId(), new RowRequirement("typeid", broke[0]), new RowRequirement("datavalue", broke[1])).longValue()) : "0";
            }else return "&4Wrong Format";
        } else if (identifier.startsWith("blocks_placed_")) {
            identifier = identifier.replace("blocks_placed_", "");
            if (identifier.contains(":")) {
                String[] place = identifier.split(":", 2);
                return api.getSpecificData(PlayerStat.BLOCKS_PLACED, player.getUniqueId()) != null ? String.valueOf(api.getSpecificData(PlayerStat.BLOCKS_PLACED, player.getUniqueId(), new RowRequirement("typeid", place[0]), new RowRequirement("datavalue", place[1])).longValue()) : "0";
            }else return "&4Wrong Format";
        } else if (identifier.startsWith("villager_trades_")) {
            identifier = identifier.replace("villager_trades_", "");
            return api.getSpecificData(PlayerStat.VILLAGER_TRADES, player.getUniqueId()) != null ? String.valueOf(api.getSpecificData(PlayerStat.VILLAGER_TRADES, player.getUniqueId(), new RowRequirement("trade", identifier.toUpperCase())).longValue()) : "0";
        } else if (identifier.startsWith("food_eaten_")) {
            identifier = identifier.replace("food_eaten_", "");
            return api.getSpecificData(PlayerStat.FOOD_EATEN, player.getUniqueId()) != null ? String.valueOf(api.getSpecificData(PlayerStat.FOOD_EATEN, player.getUniqueId(), new RowRequirement("foodEaten", identifier.toUpperCase())).longValue()) : "0";
        } else if (identifier.startsWith("distance_traveled_allworlds_")) {
            identifier = identifier.replace("distance_traveled_allworlds_", "");
            return api.getSpecificData(PlayerStat.DISTANCE_TRAVELLED, player.getUniqueId()) != null ? String.valueOf(api.getSpecificData(PlayerStat.DISTANCE_TRAVELLED, player.getUniqueId(), new RowRequirement("moveType", identifier.toUpperCase())).longValue()) : "0";
        } else if (identifier.startsWith("distance_traveled_")) {
            identifier = identifier.replace("distance_traveled_", "");
            if (identifier.contains(":")) {
                String[] travel = identifier.split(":", 2);
                return api.getSpecificData(PlayerStat.DISTANCE_TRAVELLED, player.getUniqueId()) != null ? String.valueOf(api.getSpecificData(PlayerStat.DISTANCE_TRAVELLED, player.getUniqueId(), new RowRequirement("world", travel[0]), new RowRequirement("moveType", travel[1].toUpperCase())).longValue()) : "0";
            } else
                return api.getSpecificData(PlayerStat.DISTANCE_TRAVELLED, player.getUniqueId()) != null ? String.valueOf(api.getSpecificData(PlayerStat.DISTANCE_TRAVELLED, player.getUniqueId(), new RowRequirement("world", identifier)).longValue()) : "0";
        } else if (identifier.startsWith("time_formatted_")) {
            identifier = identifier.replace("time_formatted_", "");
            String statz = api.getSpecificData(PlayerStat.TIME_PLAYED, player.getUniqueId()) != null ? String.valueOf(api.getSpecificData(PlayerStat.TIME_PLAYED, player.getUniqueId()).longValue()) : "0";
            int istatz = Integer.parseInt(statz);
            int day = (int) Math.toIntExact(TimeUnit.MINUTES.toDays(istatz));
            int drem = istatz - day * 1440;
            int hour = (int) Math.toIntExact(TimeUnit.MINUTES.toHours(drem));
            int minutes = drem - hour * 60;
            if (identifier.startsWith("dhm")) {
                return "&6" + day + " &aDays &6" + hour + " &aHrs &6" + minutes + " &aMins";
            } else if (identifier.startsWith("dh")) {
                return "&6" + day + " &aDays &6" + hour + " &aHrs";
            } else if (identifier.startsWith("d")) {
                return "&6" + day + " &aDays";
            }
        }else if (identifier.startsWith("time_")) {
            identifier = identifier.replace("time_", "");
            String statz = api.getSpecificData(PlayerStat.TIME_PLAYED, player.getUniqueId()) != null ? String.valueOf(api.getSpecificData(PlayerStat.TIME_PLAYED, player.getUniqueId()).longValue()) : "0";
            int istatz = Integer.parseInt(statz);
            int day = (int) Math.toIntExact(TimeUnit.MINUTES.toDays(istatz));
            int drem = istatz - day * 1440;
            int hour = (int) Math.toIntExact(TimeUnit.MINUTES.toHours(drem));
            int minutes = drem - hour * 60;
            if (identifier.startsWith("day")) {
                return String.valueOf(day);
            } else if (identifier.startsWith("hour")) {
                return String.valueOf(hour);
            } else if (identifier.startsWith("minute")) {
                return String.valueOf(minutes);
            }
        }


        switch (identifier) {
            case "joins":
                return api.getSpecificData(PlayerStat.JOINS, player.getUniqueId()) != null ? String.valueOf(api.getSpecificData(PlayerStat.JOINS, player.getUniqueId()).longValue()) : "0";
            case "deaths":
                return api.getSpecificData(PlayerStat.DEATHS, player.getUniqueId()) != null ? String.valueOf(api.getSpecificData(PlayerStat.DEATHS, player.getUniqueId()).longValue()) : "0";
            case "blocks_broken":
                return api.getSpecificData(PlayerStat.BLOCKS_BROKEN, player.getUniqueId()) != null ? String.valueOf(api.getSpecificData(PlayerStat.BLOCKS_BROKEN, player.getUniqueId()).longValue()) : "0";
            case "blocks_placed":
                return api.getSpecificData(PlayerStat.BLOCKS_PLACED, player.getUniqueId()) != null ? String.valueOf(api.getSpecificData(PlayerStat.BLOCKS_PLACED, player.getUniqueId()).longValue()) : "0";
            case "damage_taken":
                return api.getSpecificData(PlayerStat.DAMAGE_TAKEN, player.getUniqueId()) != null ? String.valueOf(api.getSpecificData(PlayerStat.DAMAGE_TAKEN, player.getUniqueId()).longValue()) : "0";
            case "distance_traveled":
                return api.getSpecificData(PlayerStat.DISTANCE_TRAVELLED, player.getUniqueId()) != null ? String.valueOf(api.getSpecificData(PlayerStat.DISTANCE_TRAVELLED, player.getUniqueId()).longValue()) : "0";
            case "food_eaten":
                return api.getSpecificData(PlayerStat.FOOD_EATEN, player.getUniqueId()) != null ? String.valueOf(api.getSpecificData(PlayerStat.FOOD_EATEN, player.getUniqueId()).longValue()) : "0";
            case "crafted_items":
                return api.getSpecificData(PlayerStat.ITEMS_CRAFTED, player.getUniqueId()) != null ? String.valueOf(api.getSpecificData(PlayerStat.ITEMS_CRAFTED, player.getUniqueId()).longValue()) : "0";
            case "caught_items":
                return api.getSpecificData(PlayerStat.ITEMS_CAUGHT, player.getUniqueId()) != null ? String.valueOf(api.getSpecificData(PlayerStat.ITEMS_CAUGHT, player.getUniqueId()).longValue()) : "0";
            case "players_killed":
                return api.getSpecificData(PlayerStat.KILLS_PLAYERS, player.getUniqueId()) != null ? String.valueOf(api.getSpecificData(PlayerStat.KILLS_PLAYERS, player.getUniqueId()).longValue()) : "0";
            case "time_played":
                return api.getSpecificData(PlayerStat.TIME_PLAYED, player.getUniqueId()) != null ? String.valueOf(api.getSpecificData(PlayerStat.TIME_PLAYED, player.getUniqueId()).longValue()) : "0";
            case "times_shorn":
                return api.getSpecificData(PlayerStat.TIMES_SHORN, player.getUniqueId()) != null ? String.valueOf(api.getSpecificData(PlayerStat.TIMES_SHORN, player.getUniqueId()).longValue()) : "0";
            case "xp_gained":
                return api.getSpecificData(PlayerStat.XP_GAINED, player.getUniqueId()) != null ? String.valueOf(api.getSpecificData(PlayerStat.XP_GAINED, player.getUniqueId()).longValue()) : "0";
            case "mobs_killed":
                return api.getSpecificData(PlayerStat.KILLS_MOBS, player.getUniqueId()) != null ? String.valueOf(api.getSpecificData(PlayerStat.KILLS_MOBS, player.getUniqueId()).longValue()) : "0";
            case "villager_trades":
                return api.getSpecificData(PlayerStat.VILLAGER_TRADES, player.getUniqueId()) != null ? String.valueOf(api.getSpecificData(PlayerStat.VILLAGER_TRADES, player.getUniqueId()).longValue()) : "0";
        }
        return null;
    }
}