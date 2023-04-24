package cc.davyy.manhunt.scoreboard;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.managers.ScoreboardManager;
import dev.dejvokep.boostedyaml.block.implementation.Section;
import org.bukkit.entity.Player;

import java.util.List;

public class WaitingScoreboard {

    private final Manhunt instance;
    private final ScoreboardManager scoreboardManager;

    public WaitingScoreboard(Manhunt instance) {
        this.instance = instance;
        scoreboardManager = new ScoreboardManager(instance);
    }

    public void setWaitingScoreboard(Player player) {
        Section waitingScoreboardSection = instance.getConfiguration().getSection("waiting-scoreboard");
        String title = waitingScoreboardSection.getString("title");
        List<String> lines = waitingScoreboardSection.getStringList("lines");
        scoreboardManager.createScoreboard(player, waitingScoreboardSection);
    }

    public void removeWaitingScoreboard(Player player) {
        scoreboardManager.removeScoreboard(player);
    }

}
