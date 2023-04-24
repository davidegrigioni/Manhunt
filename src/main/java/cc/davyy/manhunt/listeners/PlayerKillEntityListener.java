package cc.davyy.manhunt.listeners;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.engine.GameState;
import cc.davyy.manhunt.utils.ColorUtils;
import cc.davyy.manhunt.utils.MessageUtils;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class PlayerKillEntityListener implements Listener {

    private final Manhunt instance;

    public PlayerKillEntityListener(Manhunt instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        LivingEntity livingEntity = event.getEntity();

        if (livingEntity.getType() == EntityType.ENDER_DRAGON) {
            String message = instance.getMessages().getString(MessageUtils.ENDERDRAGON_KILL_MESSAGE.getMessage().replace("<player>", livingEntity.getKiller().getName()));
            livingEntity.getKiller().sendMessage(ColorUtils.colorize(message));
        }

    }

}
