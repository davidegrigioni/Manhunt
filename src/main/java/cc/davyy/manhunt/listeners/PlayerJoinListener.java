package cc.davyy.manhunt.listeners;

import cc.davyy.manhunt.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (player.getWorld().getName().equals("lobby")) {
            setItemJoin(player);
        } else {
            removeItemJoin(player);
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
