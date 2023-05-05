package cc.davyy.manhunt.managers;

import cc.davyy.manhunt.Manhunt;
import me.catcoder.sidebar.ProtocolSidebar;
import me.catcoder.sidebar.Sidebar;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class ScoreboardManager {

    private final Manhunt instance;
    private final Sidebar<Component> sidebar;

    public ScoreboardManager(Manhunt instance, Component title) {
        this.instance = instance;
        sidebar = ProtocolSidebar.newAdventureSidebar(title, instance);
    }

    public void addPlayer(Player player) {
        sidebar.addViewer(player);
    }

    public void removePlayer(Player player) {
        sidebar.removeViewer(player);
    }

    public void setLine(Component text) {
        sidebar.addLine(text);
    }

    public void updateLinesPeriodically(long initialDelay, long delay) {
        sidebar.updateLinesPeriodically(initialDelay, delay);
    }

}
