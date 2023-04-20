package cc.davyy.manhunt.scoreboard;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.managers.ScoreboardManager;
import cc.davyy.manhunt.utils.ColorUtils;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class GameScoreboard {

    private final Manhunt instance;
    private ScoreboardManager scoreboardManager;

    public GameScoreboard(Manhunt instance) {
        this.instance = instance;
    }

    public void setGameScoreboard(Player player) {
        scoreboardManager = new ScoreboardManager(instance);
        String gameScoreboardTitle = instance.getConfiguration().getString("game-scoreboard.title");
        List<String> gameScoreboardLines = instance.getConfiguration().getStringList("game-scoreboard.lines");
        scoreboardManager.createScoreboard(player, ColorUtils.legacy(gameScoreboardTitle), Collections.singletonList(ColorUtils.legacy(gameScoreboardLines.toString())));
    }

    public void removeGameScoreboard(Player player) {
        scoreboardManager = new ScoreboardManager(instance);
        scoreboardManager.removeScoreboard(player);
    }

}
