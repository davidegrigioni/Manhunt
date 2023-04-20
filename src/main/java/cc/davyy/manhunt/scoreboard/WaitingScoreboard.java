package cc.davyy.manhunt.scoreboard;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.managers.ScoreboardManager;
import cc.davyy.manhunt.utils.ColorUtils;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class WaitingScoreboard {

    private final Manhunt instance;
    private ScoreboardManager scoreboardManager;

    public WaitingScoreboard(Manhunt instance) {
        this.instance = instance;
    }

    public void setWaitingScoreboard(Player player) {
        scoreboardManager = new ScoreboardManager(instance);
        String waitingScoreboardTitle = instance.getConfiguration().getString("waiting-scoreboard.title");
        List<String> waitingScoreboardLines = instance.getConfiguration().getStringList("waiting-scoreboard.lines");
        scoreboardManager.createScoreboard(player, ColorUtils.legacy(waitingScoreboardTitle), Collections.singletonList(ColorUtils.legacy(waitingScoreboardLines.toString())));
    }

    public void removeWaitingScoreboard(Player player) {
        scoreboardManager = new ScoreboardManager(instance);
        scoreboardManager.removeScoreboard(player);
    }

}
