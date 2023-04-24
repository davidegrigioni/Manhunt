package cc.davyy.manhunt.placeholders;

import cc.davyy.manhunt.Manhunt;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public class ManhuntExpansion extends PlaceholderExpansion {

    private final Manhunt instance;

    public ManhuntExpansion(Manhunt instance) {
        this.instance = instance;
    }

    /*

    %manhunt_runners_count%: Returns the total number of runners currently in the game.
    %manhunt_hunters_count%: Returns the total number of hunters currently in the game.
    %manhunt_time_remaining%: Returns the remaining time in the game.
    %manhunt_target_name%: Returns the name of the current target for the runners.
    %manhunt_compass_direction%: Returns the direction of the compass for the runners to follow.
    %manhunt_is_running%: Returns whether the game is currently running or not.
    %manhunt_runner_kills_count_<player>%: Returns the total number of kills for a specific runner player.

     */

    @Override
    public @NotNull String getIdentifier() {
        return "manhunt";
    }

    @Override
    public @NotNull String getAuthor() {
        return "davideenoo";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {

        if (params.equalsIgnoreCase("runners")){
            return instance.getConfiguration().getStringList("runners").toString();
        }

        if (params.equalsIgnoreCase("onlineplayers")) {
            return String.valueOf(Bukkit.getOnlinePlayers().size());
        }

        if (params.equalsIgnoreCase("player")) {
            return player.getPlayer().getName();
        }

        if (params.equalsIgnoreCase("world")) {
            World world = player.getPlayer().getWorld();
            return world.getName();
        }

        return null; // Placeholder is unknown by the Expansion
    }

}
