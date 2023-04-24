package cc.davyy.manhunt.randomevents;

import cc.davyy.manhunt.Manhunt;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
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
            scheduler.runTaskLater(instance, () -> {
                // Set health to 3.0 for 30 seconds
                player.setHealth(3.0);
                scheduler.runTaskLater(instance, () -> {
                    // Set health to full and schedule the task to run again after 30 minutes
                    player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
                    scheduler.runTaskTimer(instance, () -> {
                        // Set health to 3.0 again after 30 minutes
                        player.setHealth(3.0);
                    }, 20 * 60 * 30, 20 * 60 * 30);
                }, 20 * 30);
            }, 20 * 5);
        }
    }

}
