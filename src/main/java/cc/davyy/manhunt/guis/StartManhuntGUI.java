package cc.davyy.manhunt.guis;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.utils.ColorUtils;
import cc.davyy.manhunt.utils.MessageUtils;
import cc.davyy.manhunt.worlds.CreateWorld;
import com.github.stefvanschie.inventoryframework.adventuresupport.TextHolder;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class StartManhuntGUI {

    private final Manhunt instance;

    public StartManhuntGUI(Manhunt instance) {
        this.instance = instance;
    }

    public ChestGui guiCreation() {

        String startManhuntTitleGUI = instance.getMessages().getString(MessageUtils.START_MANHUNT_GUI_TITLE.getMessage());

        ChestGui gui = new ChestGui(3, (TextHolder) ColorUtils.colorize(startManhuntTitleGUI));

        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 3, Pane.Priority.LOWEST);
        background.addItem(new GuiItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        gui.addPane(background);

        OutlinePane navigationPane = new OutlinePane(3, 1, 3, 1);

        ItemStack back = new ItemStack(Material.BARRIER);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.displayName(Component.text("Back"));
        back.setItemMeta(backMeta);

        // Back to the default page
        navigationPane.addItem(new GuiItem(back, event -> {
            final Player player = (Player) event.getWhoClicked();
            MainGUI mainGUI = new MainGUI(instance);
            mainGUI.guiCreation().show(player);
        }));

        // Start Manhunt
        ItemStack start = new ItemStack(Material.GREEN_WOOL);
        ItemMeta startItemMeta = start.getItemMeta();
        startItemMeta.displayName(Component.text("Start Manhunt"));
        start.setItemMeta(startItemMeta);

        navigationPane.addItem(new GuiItem(start, event -> {
            final Player player = (Player) event.getWhoClicked();
            CreateWorld createWorld = new CreateWorld();
            createWorld.createPlayerWorlds(player);
        }));

        gui.addPane(navigationPane);

        return gui;
    }

}
