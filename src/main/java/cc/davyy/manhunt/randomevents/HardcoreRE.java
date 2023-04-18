package cc.davyy.manhunt.randomevents;

import cc.davyy.manhunt.Manhunt;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.scheduler.BukkitScheduler;


public class HardcoreRE implements Listener {

    private final Manhunt instance;

    public HardcoreRE(Manhunt instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent event) {

        Player player = event.getPlayer();
        World world = player.getWorld();
        BukkitScheduler scheduler = Bukkit.getScheduler();

        if (!world.getName().equalsIgnoreCase("lobby")) {
            scheduler.runTaskTimer(instance, () -> {
                player.setHealth(3.0);
            }, 0, 18000);
        }

    }

}
