package cc.davyy.manhunt.managers;

import cc.davyy.manhunt.utils.ColorUtils;
import com.xism4.sternalboard.SternalBoardHandler;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ScoreboardManager {

    private final Map<UUID, SternalBoardHandler> scoreboards;

    public ScoreboardManager() {
        this.scoreboards = new HashMap<>();
    }

    public void createScoreboard(Player player, String title, List<String> lines) {
        SternalBoardHandler scoreboard = new SternalBoardHandler(player);
        scoreboard.updateTitle(ColorUtils.legacy(title));
        scoreboard.updateLines(lines);
        scoreboards.put(player.getUniqueId(), scoreboard);
    }

    public void removeScoreboard(Player player) {
        SternalBoardHandler scoreboard = scoreboards.remove(player.getUniqueId());
        if (scoreboard != null) {
            scoreboard.delete();
        }
    }

}
