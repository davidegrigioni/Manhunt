package cc.davyy.manhunt.listeners;

import cc.davyy.manhunt.Manhunt;
import dev.dejvokep.boostedyaml.YamlDocument;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerTrackerListener implements Listener {

    private final Manhunt instance;
    private final List<String> runners = new ArrayList<>();

    public PlayerTrackerListener(Manhunt instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Player target = event.getPlayer();
        if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && player.getInventory().getItemInMainHand().getType() == Material.COMPASS){
            if (player.getName().equals(target.getName())) {
                BossBar bossBar = BossBar.bossBar(Component.text("You can't target yourself!"), 0, BossBar.Color.RED, BossBar.Overlay.PROGRESS);
                player.showBossBar(bossBar);
            }
            player.setCompassTarget(target.getLocation());
        }
    }

}
