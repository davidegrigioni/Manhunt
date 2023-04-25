package cc.davyy.manhunt.guis;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.utils.ColorUtils;
import cc.davyy.manhunt.utils.MessageUtils;
import com.github.stefvanschie.inventoryframework.adventuresupport.ComponentHolder;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MainGUI {

    private final Manhunt instance;
    private final StartManhuntGUI startManhuntGUI;

    public MainGUI(Manhunt instance) {
        this.instance = instance;
        this.startManhuntGUI = new StartManhuntGUI(instance);
    }

    public ChestGui guiCreation() {

        String mainTitleGUI = instance.getMessages().getString(MessageUtils.MAIN_GUI_TITLE.getMessage());

        ChestGui gui = new ChestGui(3, ComponentHolder.of(ColorUtils.colorize(mainTitleGUI)));

        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 3, Pane.Priority.LOWEST);
        background.addItem(new GuiItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        gui.addPane(background);

        OutlinePane navigationPane = new OutlinePane(3, 1, 3, 1);

        ItemStack settingsMenu = new ItemStack(Material.COMPARATOR);
        ItemMeta settingsMenuMeta = settingsMenu.getItemMeta();
        String settingsTitle = instance.getMessages().getString(MessageUtils.SETTINGS_GUI_TITLE.getMessage());
        settingsMenuMeta.displayName(ColorUtils.colorize(settingsTitle));
        settingsMenu.setItemMeta(settingsMenuMeta);

        navigationPane.addItem(new GuiItem(settingsMenu, event -> {
            final Player player = (Player) event.getWhoClicked();
            SettingsGUI settingsGUI = new SettingsGUI(instance);
            settingsGUI.guiCreation().show(player);
        }));

        ItemStack startMenu = new ItemStack(Material.COMPASS);
        ItemMeta startMenuMeta = startMenu.getItemMeta();
        String startTitle = instance.getMessages().getString(MessageUtils.START_ITEM.getMessage());
        startMenuMeta.displayName(ColorUtils.colorize(startTitle));
        startMenu.setItemMeta(startMenuMeta);

        navigationPane.addItem(new GuiItem(startMenu, event -> {
            final Player player = (Player) event.getWhoClicked();
            startManhuntGUI.guiCreation().show(player);
        }));

        gui.addPane(navigationPane);

        return gui;
    }

}
