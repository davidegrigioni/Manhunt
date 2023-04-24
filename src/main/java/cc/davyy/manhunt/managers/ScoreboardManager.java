package cc.davyy.manhunt.managers;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.utils.ColorUtils;
import com.xism4.sternalboard.SternalBoardHandler;
import dev.dejvokep.boostedyaml.block.implementation.Section;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ScoreboardManager {

    private final Manhunt instance;
    private final Map<UUID, SternalBoardHandler> scoreboards;

    public ScoreboardManager(Manhunt instance) {
        this.instance = instance;
        this.scoreboards = new ConcurrentHashMap<>();
    }

    public void createScoreboard(Player player, Section scoreboardSection) {
        String title = ColorUtils.legacy(scoreboardSection.getString("title"));
        List<String> lines = new ArrayList<>();

        for (String line : scoreboardSection.getStringList("lines")) {
            // Replace any placeholders in the line
            String parsedLine = PlaceholderAPI.setPlaceholders(player, line);
            lines.add(ColorUtils.legacy(parsedLine));
        }

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
