package cc.davyy.manhunt.listeners;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.scoreboard.GameScoreboard;
import cc.davyy.manhunt.scoreboard.WaitingScoreboard;
import cc.davyy.manhunt.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinListener implements Listener {

    private final Manhunt instance;
    private WaitingScoreboard waitingScoreboard;
    private GameScoreboard gameScoreboard;

    public PlayerJoinListener(Manhunt instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        waitingScoreboard = new WaitingScoreboard(instance);
        gameScoreboard = new GameScoreboard(instance);
        String lobbyWorld = instance.getConfiguration().getString("lobby-world");

        if (!(player.getWorld().getName().equals(lobbyWorld))) {
            gameScoreboard.setGameScoreboard(player);
        } else {
            gameScoreboard.removeGameScoreboard(player);
        }

        if (player.getWorld().getName().equals(lobbyWorld)) {
            setItemJoin(player);
            waitingScoreboard.setWaitingScoreboard(player);
        } else {
            removeItemJoin(player);
            waitingScoreboard.removeWaitingScoreboard(player);
        }

    }

    private void setItemJoin(Player player) {

        ItemStack compass = new ItemBuilder(Material.CHEST, 1)
                .displayName("GUI")
                .lore("Click to start")
                .build();

        player.getInventory().setItem(4, compass);

    }

    private void removeItemJoin(Player player) {
        player.getInventory().clear();
    }

    private void gameItems() {

    }

}
