package cc.davyy.manhunt.worlds;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

public class CreateWorld {

    /* Credits to Debit Card on LearnSpigot */
    public static void generateWorld(World world) {
        World existingWorld = Bukkit.getWorld(world.getName());
        if (existingWorld != null) return;

        WorldCreator wc = new WorldCreator(world.getName());
        wc.environment(World.Environment.NORMAL);
        wc.type(WorldType.NORMAL);

        WorldChunk mineChunks = new WorldChunk();
        wc.generator(mineChunks);

        wc.createWorld();

    }

}
