package cc.davyy.manhunt.managers;

import cc.davyy.ddapi.utils.chat.ChatUtils;
import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.engine.PlayerStatus;
import cc.davyy.manhunt.utils.MessageUtils;
import org.bukkit.entity.Player;

import java.util.List;

public class ManhuntManager {

    private final Manhunt instance;

    private final List<String> runnersList;

    public ManhuntManager(Manhunt instance) {
        this.instance = instance;
        this.runnersList = instance.getConfiguration().getStringList("runners");
    }

    public void addRunner(Player player, Player target) {
        PlayerStatus status = PlayerStatus.getPlayerStatus(target);

        switch (status) {
            case ONLINE -> {
                if (!runnersList.contains(target.getName())) {
                    runnersList.add(target.getName());
                    String runnerAdded = instance.getMessages().getString(MessageUtils.RUNNER_ADD_MESSAGE.getMessage());
                    ChatUtils.sendMessage(player, runnerAdded.replace("<player>", target.getName()));
                } else {
                    String alreadyInList = instance.getMessages().getString(MessageUtils.RUNNERS_ALREADY_IN_LIST_MESSAGE.getMessage());
                    ChatUtils.sendMessage(player, alreadyInList);
                }
            }
            case OFFLINE -> {
                String playerNotFound = instance.getMessages().getString(MessageUtils.PLAYER_NOT_FOUND_MESSAGE.getMessage());
                ChatUtils.sendMessage(player, playerNotFound);
            }
        }
    }

}
