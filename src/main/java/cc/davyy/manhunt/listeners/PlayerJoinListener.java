package cc.davyy.manhunt.listeners;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.scoreboard.GameScoreboard;
import cc.davyy.manhunt.scoreboard.SpectatorScoreboard;
import cc.davyy.manhunt.scoreboard.WaitingScoreboard;
import cc.davyy.manhunt.utils.ColorUtils;
import cc.davyy.manhunt.utils.ItemBuilder;
import cc.davyy.manhunt.utils.MessageUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinListener implements Listener {

    private final Manhunt instance;
    private final WaitingScoreboard waitingScoreboard;
    private final GameScoreboard gameScoreboard;
    private final SpectatorScoreboard spectatorScoreboard;

    public PlayerJoinListener(Manhunt instance) {
        this.instance = instance;
        waitingScoreboard = new WaitingScoreboard(instance);
        gameScoreboard = new GameScoreboard(instance);
        spectatorScoreboard = new SpectatorScoreboard(instance);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        String lobbyWorld = instance.getConfiguration().getString("lobby-world");
        boolean tabListEnabled = instance.getConfiguration().getBoolean("custom-tablist.enabled");

        if (player.getWorld().getName().equals(lobbyWorld)) {
            waitingScoreboard.setWaitingScoreboard(player);
        }

        if (tabListEnabled) {
            customTablist(player);
        }

    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        final Player player = event.getPlayer();
        String lobbyWorld = instance.getConfiguration().getString("lobby-world");

        if (!player.getWorld().getName().equals(lobbyWorld)) {
            gameScoreboard.setGameScoreboard(player);
            waitingScoreboard.removeWaitingScoreboard(player);
            gameItems(player);
        } else {
            waitingScoreboard.setWaitingScoreboard(player);
            gameScoreboard.removeGameScoreboard(player);
            removeItems(player);
        }

    }

    private void setItemJoin(Player player) {

        ItemStack chest = new ItemBuilder(Material.CHEST, 1)
                .displayName("GUI")
                .lore("Click to start")
                .build();

        player.getInventory().setItem(4, chest);

    }

    private void removeItems(Player player) {
        player.getInventory().clear();
    }

    private void gameItems(Player player) {

        ItemStack compass = new ItemBuilder(Material.COMPASS, 1)
                .displayName("Compass")
                .lore("Use this to track runners")
                .build();

    }

    private void customTablist(Player player) {
        final String header = instance.getMessages().getString(MessageUtils.TABLIST_HEADER.getMessage());
        final String footer = instance.getMessages().getString(MessageUtils.TABLIST_FOOTER.getMessage());
        player.sendPlayerListHeader(ColorUtils.colorize(header));
        player.sendPlayerListFooter(ColorUtils.colorize(footer));
    }

}
