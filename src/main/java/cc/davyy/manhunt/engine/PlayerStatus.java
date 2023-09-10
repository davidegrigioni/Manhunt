package cc.davyy.manhunt.engine;

import org.bukkit.entity.Player;

public enum PlayerStatus {

    ONLINE, OFFLINE;

    public static PlayerStatus getPlayerStatus(Player player) {
        if (player.isOnline()) {
            return PlayerStatus.ONLINE;
        } else {
            return PlayerStatus.OFFLINE;
        }
    }

}
