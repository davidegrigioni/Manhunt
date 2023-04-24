package cc.davyy.manhunt.listeners;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.utils.TimeUtils;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerTrackerListener implements Listener {

    private final Manhunt instance;
    private final List<String> runners = new ArrayList<>();
    private final Map<Player, BossBar> bossBars = new HashMap<>();

    public PlayerTrackerListener(Manhunt instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onCompassInteract(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
        float distance = 100;

        for (Entity entity : player.getNearbyEntities(distance, 250.0D, distance)) {
            if (entity instanceof Player) {
                if (item.getType() == Material.COMPASS) {
                    Player target = (Player) entity;
                    player.setCompassTarget(target.getLocation());
                    int playerDistanceXYZ = (int) player.getLocation().distance(target.getLocation());
                    BossBar bossBar = BossBar.bossBar(Component.text("[!] Nearest Player: " + target.getName() + " [!] Distance: " + playerDistanceXYZ + " blocks [!]"), 0, BossBar.Color.RED, BossBar.Overlay.PROGRESS);
                    player.showBossBar(bossBar);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (player.getInventory().getItemInMainHand().getType() == Material.COMPASS) {
                                bossBar.name(Component.text("[!] Nearest Player: " + target.getName() + " [!] Distance: " + playerDistanceXYZ + " blocks [!]"));
                                player.showBossBar(bossBar);
                            } else {
                                player.hideBossBar(bossBar);
                            }
                        }
                    }.runTaskTimer(instance, 0, TimeUtils.secondsToTicks(3));
                }
            }
        }
    }
}
