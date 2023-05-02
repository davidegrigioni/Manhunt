package cc.davyy.manhunt.guis;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.engine.Game;
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
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class GamesGUI {

    private final Manhunt instance;

    public GamesGUI(Manhunt instance) {
        this.instance = instance;
    }

    public ChestGui guiCreation() {

        String gamesGUITitle = instance.getMessages().getString(MessageUtils.GAMES_GUI_TITLE.getMessage());

        ChestGui gui = new ChestGui(3, ComponentHolder.of(ColorUtils.colorize(gamesGUITitle)));

        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 3, Pane.Priority.LOWEST);
        background.addItem(new GuiItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        gui.addPane(background);

        OutlinePane navigationPane = new OutlinePane(3, 1, 3, 1);

        ItemStack hostGameItem = new ItemStack(Material.GREEN_WOOL);
        ItemMeta hostGameItemMeta = hostGameItem.getItemMeta();

        hostGameItem.setItemMeta(hostGameItemMeta);

        gui.addPane(navigationPane);

        return gui;
    }

    public void updateGamesGUI() {
        List<Player> gameCreators = new ArrayList<>();
        for (Game game : runningGames) {
            if (game.getHost() != null) {
                gameCreators.add(game.getHost());
            }
        }

        // Add player head item for each game creator to joinGUI
        int slot = 0;
        for (Player creator : gameCreators) {
            ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta skullMeta = (SkullMeta) playerHead.getItemMeta();
            skullMeta.setOwningPlayer(creator);
            skullMeta.displayName(creator.displayName());
            playerHead.setItemMeta(skullMeta);

            this.guiCreation()..setItem(slot, new GuiItem(playerHead, event -> {
                // Code for joining game
            }));

            slot++;
        }
    }

}
