package cc.davyy.manhunt.scoreboard;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.managers.ScoreboardManager;
import dev.dejvokep.boostedyaml.block.implementation.Section;
import org.bukkit.entity.Player;

import java.util.List;

public class GameScoreboard {

    private final Manhunt instance;
    private final ScoreboardManager scoreboardManager;

    public GameScoreboard(Manhunt instance) {
        this.instance = instance;
        scoreboardManager = new ScoreboardManager(instance);
    }

    public void setGameScoreboard(Player player) {
        Section gameScoreboardSection = instance.getConfiguration().getSection("game-scoreboard");
        String title = gameScoreboardSection.getString("title");
        List<String> lines = gameScoreboardSection.getStringList("lines");
        scoreboardManager.createScoreboard(player, gameScoreboardSection);
    }

    public void removeGameScoreboard(Player player) {
        scoreboardManager.removeScoreboard(player);
    }

}
