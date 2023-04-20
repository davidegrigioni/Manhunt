package cc.davyy.manhunt.scoreboard;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.managers.ScoreboardManager;
import cc.davyy.manhunt.utils.ColorUtils;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class SpectatorScoreboard {
    private final Manhunt instance;
    private ScoreboardManager scoreboardManager;

    public SpectatorScoreboard(Manhunt instance) {
        this.instance = instance;
    }

    public void setSpectatorScoreboard(Player player) {
        scoreboardManager = new ScoreboardManager(instance);
        String spectatorScoreboardTitle = instance.getConfiguration().getString("game-scoreboard.title");
        List<String> spectatorScoreboardLines = instance.getConfiguration().getStringList("game-scoreboard.lines");
        scoreboardManager.createScoreboard(player, ColorUtils.legacy(spectatorScoreboardTitle), Collections.singletonList(ColorUtils.legacy(spectatorScoreboardLines.toString())));
    }

    public void removeSpectatorScoreboard(Player player) {
        scoreboardManager = new ScoreboardManager(instance);
        scoreboardManager.removeScoreboard(player);
    }
}
