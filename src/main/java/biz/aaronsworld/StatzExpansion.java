package biz.aaronsworld;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.staartvin.statz.Statz;
import me.staartvin.statz.api.API;
import me.staartvin.statz.database.datatype.RowRequirement;
import me.staartvin.statz.datamanager.PlayerStat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
        return "1.0.3";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {

        if (player == null) {
            return "";
        }
        if (identifier.startsWith("mobs_killed_")) {
            identifier = identifier.replace("mobs_killed_", "");
            return String.valueOf(this.api.getSpecificData(PlayerStat.KILLS_MOBS, player.getUniqueId(), new RowRequirement("mob", identifier.toUpperCase())).longValue());
        }
        if (identifier.startsWith("blocks_broken_")) {
            identifier = identifier.replace("blocks_broken_", "");
            if (identifier.contains(":")) {
                String[] broke = identifier.split(":", 2);
                return String.valueOf(this.api.getSpecificData(PlayerStat.BLOCKS_BROKEN, player.getUniqueId(), new RowRequirement("typeid", broke[0]), new RowRequirement("datavalue", broke[1])).longValue());
            }else return "&4Wrong Format";
        }

        if (identifier.startsWith("blocks_placed_")) {
            identifier = identifier.replace("blocks_placed_", "");
            if (identifier.contains(":")) {
                String[] place = identifier.split(":", 2);
                return String.valueOf(this.api.getSpecificData(PlayerStat.BLOCKS_BROKEN, player.getUniqueId(), new RowRequirement("typeid", place[0]), new RowRequirement("datavalue", place[1])).longValue());
            }else return "&4Wrong Format";
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