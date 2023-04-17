package cc.davyy.manhunt.listeners;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.utils.ColorUtils;
import cc.davyy.manhunt.utils.ItemBuilder;
import cc.davyy.manhunt.utils.MessageUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinListener implements Listener {

    private final Manhunt instance;

    public PlayerJoinListener(Manhunt instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        setItemJoin(player);

    }

    private void setItemJoin(Player player) {

        ItemStack compass = new ItemBuilder(Material.COMPASS, 1)
                .displayName("GUI")
                .lore("Click to start")
                .build();

        player.getInventory().setItem(4, compass);

    }

}
