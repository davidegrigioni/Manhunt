package cc.davyy.manhunt.guis;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.utils.ColorUtils;
import cc.davyy.manhunt.utils.MessageUtils;
import com.github.stefvanschie.inventoryframework.adventuresupport.TextHolder;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class MainGUI {

    private final Manhunt instance;

    public MainGUI(Manhunt instance) {
        this.instance = instance;
    }

    public ChestGui guiCreation() {

        String mainTitleGUI = instance.getMessages().getString(MessageUtils.MAIN_GUI_TITLE.getMessage());

        ChestGui gui = new ChestGui(3, (TextHolder) ColorUtils.colorize(mainTitleGUI));

        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 3, Pane.Priority.LOWEST);
        background.addItem(new GuiItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        gui.addPane(background);

        OutlinePane navigationPane = new OutlinePane(3, 1, 3, 1);

        gui.addPane(navigationPane);

        return gui;
    }

}
