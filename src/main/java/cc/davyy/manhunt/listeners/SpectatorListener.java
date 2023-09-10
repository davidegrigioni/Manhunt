package cc.davyy.manhunt.listeners;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.utils.MessageUtils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class SpectatorListener implements Listener {

    private final Manhunt instance;

    public SpectatorListener(Manhunt instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onPlayerDead(PlayerDeathEvent event) {
        final Player player = event.getPlayer();
        deathMode(player);
    }

    private void deathMode(Player player) {
        player.setGameMode(GameMode.SPECTATOR);
        String deathMessage = instance.getMessages().getString(MessageUtils.DEATH_MESSAGE.getMessage());
        player.sendMessage(ColorUtils.colorize(deathMessage));
    }

}
