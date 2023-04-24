package cc.davyy.manhunt.scoreboard;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.managers.ScoreboardManager;
import dev.dejvokep.boostedyaml.block.implementation.Section;
import org.bukkit.entity.Player;

import java.util.List;

public class SpectatorScoreboard {
    private final Manhunt instance;
    private final ScoreboardManager scoreboardManager;

    public SpectatorScoreboard(Manhunt instance) {
        this.instance = instance;
        scoreboardManager = new ScoreboardManager(instance);
    }

    public void setSpectatorScoreboard(Player player) {
        Section spectatorScoreboardSection = instance.getConfiguration().getSection("spectator-scoreboard");
        String title = spectatorScoreboardSection.getString("title");
        List<String> lines = spectatorScoreboardSection.getStringList("lines");
        scoreboardManager.createScoreboard(player, spectatorScoreboardSection);
    }

    public void removeSpectatorScoreboard(Player player) {
        scoreboardManager.removeScoreboard(player);
    }
}
