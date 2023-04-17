package cc.davyy.manhunt.worlds;

import net.kyori.adventure.text.Component;
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

        createVanillaWorld(player, overworldName, World.Environment.NORMAL);
        createVanillaWorld(player, netherName, World.Environment.NETHER);
        createVanillaWorld(player, endName, World.Environment.THE_END);
    }

    private void createVanillaWorld(Player player, String worldName, World.Environment environment) {
        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            WorldCreator worldCreator = new WorldCreator(worldName);
            worldCreator.environment(environment);
            world = worldCreator.createWorld();
            if (world != null) {
                Bukkit.getLogger().info("Successfully created vanilla " + environment.name() + " world: " + worldName);
                player.sendMessage(Component.text("Successfully created vanilla " + environment.name() + " world: " + worldName));
            } else {
                Bukkit.getLogger().severe("Failed to create vanilla " + environment.name() + " world: " + worldName);
                player.sendMessage(Component.text("Failed to create vanilla " + environment.name() + " world: " + worldName));
            }
        } else {
            Bukkit.getLogger().info("World already exists: " + worldName);
            player.sendMessage(Component.text("World already exists: " + worldName));
        }
    }

}
