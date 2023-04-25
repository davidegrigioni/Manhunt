package cc.davyy.manhunt.guis;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.managers.QueueHandlerManager;
import cc.davyy.manhunt.utils.ColorUtils;
import cc.davyy.manhunt.utils.MessageUtils;
import com.github.stefvanschie.inventoryframework.adventuresupport.ComponentHolder;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerGUI {

    private final Manhunt instance;
    private final QueueHandlerManager queueManager;

    public PlayerGUI(Manhunt instance) {
        this.instance = instance;
        this.queueManager = new QueueHandlerManager(instance);
    }

    public ChestGui guiCreation() {

        String playerGUITitle = instance.getMessages().getString(MessageUtils.PLAYER_GUI_TITLE.getMessage());

        ChestGui gui = new ChestGui(3, ComponentHolder.of(ColorUtils.colorize(playerGUITitle)));

        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 3, Pane.Priority.LOWEST);
        background.addItem(new GuiItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        gui.addPane(background);

        OutlinePane navigationPane = new OutlinePane(3, 1, 3, 1);

        ItemStack queueItem = new ItemStack(Material.CLOCK);
        ItemMeta queueItemMeta = queueItem.getItemMeta();
        String queueTitle = instance.getMessages().getString(MessageUtils.QUEUE_JOIN_ITEM.getMessage());
        queueItemMeta.displayName(ColorUtils.colorize(queueTitle));
        queueItem.setItemMeta(queueItemMeta);

        navigationPane.addItem(new GuiItem(queueItem, event -> {
            final Player player = (Player) event.getWhoClicked();
        }));

        ItemStack leaveQueueItem = new ItemStack(Material.ANVIL);
        ItemMeta leaveQueueItemMeta = leaveQueueItem.getItemMeta();
        String leaveTitle = instance.getMessages().getString(MessageUtils.QUEUE_LEAVE_ITEM.getMessage());
        leaveQueueItemMeta.displayName(ColorUtils.colorize(leaveTitle));
        leaveQueueItem.setItemMeta(leaveQueueItemMeta);

        navigationPane.addItem(new GuiItem(leaveQueueItem, event -> {
            final Player player = (Player) event.getWhoClicked();
        }));

        gui.addPane(navigationPane);

        return gui;
    }

}
