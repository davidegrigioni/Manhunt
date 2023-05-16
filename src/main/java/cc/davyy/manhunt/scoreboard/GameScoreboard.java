package cc.davyy.manhunt.scoreboard;

import cc.davyy.manhunt.Manhunt;
import org.bukkit.entity.Player;

public class GameScoreboard {

    private final Manhunt instance;
    private ScoreboardManager scoreboardManager;

    public GameScoreboard(Manhunt instance) {
        this.instance = instance;
    }

    public void setGameScoreboard(Player player) {
        scoreboardManager.addPlayer(player);
    }

}
