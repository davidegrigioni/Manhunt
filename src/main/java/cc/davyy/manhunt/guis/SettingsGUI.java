package cc.davyy.manhunt.guis;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.utils.ColorUtils;
import cc.davyy.manhunt.utils.MessageUtils;
import com.github.stefvanschie.inventoryframework.adventuresupport.ComponentHolder;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SettingsGUI {

    private final Manhunt instance;

    public SettingsGUI(Manhunt instance) {
        this.instance = instance;
    }

    public ChestGui guiCreation() {

        String settingsGUITitle = instance.getMessages().getString(MessageUtils.SETTINGS_GUI_TITLE.getMessage());

        ChestGui gui = new ChestGui(3, ComponentHolder.of(ColorUtils.colorize(settingsGUITitle)));

        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 3, Pane.Priority.LOWEST);
        background.addItem(new GuiItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        gui.addPane(background);

        OutlinePane navigationPane = new OutlinePane(3, 1, 3, 1);

        ItemStack doDayCycle = new ItemStack(Material.TORCH);
        ItemMeta doDayCycleMeta = doDayCycle.getItemMeta();
        doDayCycleMeta.displayName(Component.text("Do Daylight Cycle"));
        doDayCycle.setItemMeta(doDayCycleMeta);

        navigationPane.addItem(new GuiItem(doDayCycle, event -> {
            final Player player = (Player) event.getWhoClicked();
            GameRule<Boolean> rule = GameRule.DO_DAYLIGHT_CYCLE;
            try {
                boolean currentValue = player.getWorld().getGameRuleValue(rule);
                player.getWorld().setGameRule(rule, !currentValue);
                String status = currentValue ? "disabled" : "enabled";
                player.sendMessage(Component.text("Do Daylight Cycle " + status));
            } catch (NullPointerException e) {
                Bukkit.getLogger().severe("Failed to toggle Do Daylight Cycle: " + e.getMessage());
                player.sendMessage(Component.text("Failed to toggle Do Daylight Cycle: " + e.getMessage()));
            }
        }));

        ItemStack doWeatherCycle = new ItemStack(Material.CLOCK);
        ItemMeta doWeatherCycleMeta = doWeatherCycle.getItemMeta();
        doWeatherCycleMeta.displayName(Component.text("Do Weather Cycle"));
        doWeatherCycle.setItemMeta(doWeatherCycleMeta);

        navigationPane.addItem(new GuiItem(doWeatherCycle, event -> {
            final Player player = (Player) event.getWhoClicked();
            GameRule<Boolean> rule = GameRule.DO_WEATHER_CYCLE;
            try {
                boolean currentValue = player.getWorld().getGameRuleValue(rule);
                player.getWorld().setGameRule(rule, !currentValue);
                String status = currentValue ? "disabled" : "enabled";
                player.sendMessage(Component.text("Do Weather Cycle " + status));
            } catch (NullPointerException e) {
                Bukkit.getLogger().severe("Failed to toggle Do Weather Cycle: " + e.getMessage());
                player.sendMessage(Component.text("Failed to toggle Do Weather Cycle: " + e.getMessage()));
            }
        }));

        gui.addPane(navigationPane);

        return gui;
    }

}
