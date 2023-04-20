package cc.davyy.manhunt.worlds;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CreateWorld {

    public void createPlayerWorlds(Player player) {
        String playerName = player.getName();
        UUID uuid = UUID.randomUUID();
        String overworldName = "manhunt-" + playerName + "-overworld-" + uuid;
        String netherName = "manhunt-" + playerName + "-nether-" + uuid;
        String endName = "manhunt-" + playerName + "-end-" + uuid;

        createVanillaWorld(player, overworldName, World.Environment.NORMAL);
        createVanillaWorld(player, netherName, World.Environment.NETHER);
        createVanillaWorld(player, endName, World.Environment.THE_END);
    }

    private void createVanillaWorld(Player player, String worldName, World.Environment environment) {
        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            // Unload the world if it exists on disk
            if (Bukkit.unloadWorld(worldName, false)) {
                Bukkit.getLogger().info("Unloaded world: " + worldName);
            }

            // Create the world
            WorldCreator worldCreator = new WorldCreator(worldName);
            worldCreator.environment(environment);
            worldCreator.createWorld();

            if (world != null) {
                Bukkit.getLogger().info("Successfully created vanilla " + environment.name() + " world: " + worldName);
                player.sendMessage(Component.text("Successfully created vanilla " + environment.name() + " world: " + worldName, NamedTextColor.GREEN));
            } else {
                Bukkit.getLogger().severe("Failed to create vanilla " + environment.name() + " world: " + worldName);
                player.sendMessage(Component.text("Failed to create vanilla " + environment.name() + " world: " + worldName, NamedTextColor.RED));
            }
        } else {
            Bukkit.getLogger().info("World already exists: " + worldName);
            player.sendMessage(Component.text("World already exists: " + worldName, NamedTextColor.RED));
        }
    }

}
