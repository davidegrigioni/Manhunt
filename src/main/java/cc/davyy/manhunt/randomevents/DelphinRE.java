package cc.davyy.manhunt.randomevents;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

public class DelphinRE implements Listener {

    @EventHandler
    public void onWaterJoin(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.isSwimming() || player.getLocation().getBlock().getType() == (Material.WATER)) {
            player.addPotionEffect(PotionEffectType.DOLPHINS_GRACE.createEffect(10000, 1));
        } else {
            player.removePotionEffect(PotionEffectType.DOLPHINS_GRACE);
        }
    }

}
