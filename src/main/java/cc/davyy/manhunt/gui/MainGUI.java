package cc.davyy.manhunt.gui;

import cc.davyy.ddapi.utils.chat.ColorUtils;
import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.utils.MessageUtils;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class MainGUI {

    private final Manhunt instance;

    public MainGUI(Manhunt instance) {
        this.instance = instance;
    }

    public Gui guiCreation(Player player) {
        String mainGuiTitle = instance.getMessages().getString(MessageUtils.MAIN_GUI_TITLE.getMessage());

        Gui gui = Gui.gui()
                .title(ColorUtils.colorize(mainGuiTitle))
                .rows(5)
                .create();

        gui.setDefaultClickAction(event -> event.setCancelled(true));

        gui.getFiller().fillBorder(new GuiItem(Material.GREEN_STAINED_GLASS_PANE));

        if (player.hasPermission("manhunt.host")) {
            String startManhunt = instance.getMessages().getString(MessageUtils.START_MANHUNT_GUI_TITLE.getMessage());
            GuiItem terracotta = ItemBuilder.from(Material.GREEN_TERRACOTTA)
                    .name(ColorUtils.colorize(startManhunt).decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE))
                    .glow(true)
                    .asGuiItem();
            gui.setItem(22, terracotta);
        } else {
            GuiItem playerHead = ItemBuilder.skull()
                    .owner(player)
                    .texture(player.getUniqueId().toString())
                    .asGuiItem();
            gui.setItem(22, playerHead);
        }

        return gui;
    }

}
