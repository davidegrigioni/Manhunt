package cc.davyy.manhunt.managers;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.handlers.QueueHandler;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.Queue;

public class QueueHandlerManager implements QueueHandler {

    private final Manhunt instance;
    private final Queue<Player> queue = new LinkedList<>();

    public QueueHandlerManager(Manhunt instance) {
        this.instance = instance;
    }

    public void addPlayer(Player player) {
        queue.offer(player);
    }

    public boolean hasPlayer() {
        return false;
    }

    public void removePlayer(Player player) {
        queue.poll();
    }

    public int getSize() {
        queue.size();
        return 0;
    }
}
