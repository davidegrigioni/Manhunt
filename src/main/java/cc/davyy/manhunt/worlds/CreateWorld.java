package cc.davyy.manhunt.worlds;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

public class CreateWorld {

    public void createPlayerWorlds(Player player) {
        String playerName = player.getName();
        String overworldName = "manhunt-" + playerName + "-overworld";
        String netherName = "manhunt-" + playerName + "-nether";
        String endName = "manhunt-" + playerName + "-end";

        createVanillaWorld(overworldName, World.Environment.NORMAL);
        createVanillaWorld(netherName, World.Environment.NETHER);
        createVanillaWorld(endName, World.Environment.THE_END);
    }

    private void createVanillaWorld(String worldName, World.Environment environment) {
        WorldCreator worldCreator = new WorldCreator(worldName);
        worldCreator.environment(environment);
        World world = worldCreator.createWorld();
        if (world != null) {
            Bukkit.getLogger().info("Successfully created vanilla " + environment.name() + " world: " + worldName);
        } else {
            Bukkit.getLogger().severe("Failed to create vanilla " + environment.name() + " world: " + worldName);
        }
    }

}
