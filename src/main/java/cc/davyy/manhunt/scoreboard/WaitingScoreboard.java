package cc.davyy.manhunt.scoreboard;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.utils.ColorUtils;
import com.xism4.sternalboard.SternalBoardHandler;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class WaitingScoreboard {

    private final Manhunt instance;
    private final Map<UUID, SternalBoardHandler> boards = new HashMap<>();

    public WaitingScoreboard(Manhunt instance) {
        this.instance = instance;
    }

    public void setScoreboard(Player player) {

        SternalBoardHandler board = new SternalBoardHandler(player);

        String scoreboardTitle = instance.getConfiguration().getString("scoreboard.title");
        List<String> scoreboardLines = instance.getConfiguration().getStringList("scoreboard.lines");

        board.updateTitle(ColorUtils.legacy(scoreboardTitle));
        board.updateLines(scoreboardLines);

        this.boards.put(player.getUniqueId(), board);

    }

    public void removeScoreboard(Player player) {

        SternalBoardHandler board = this.boards.remove(player.getUniqueId());

        if (board != null) {
            board.delete();
        }

    }

}
