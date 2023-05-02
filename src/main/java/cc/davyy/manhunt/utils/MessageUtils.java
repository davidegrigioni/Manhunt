package cc.davyy.manhunt.utils;

public enum MessageUtils {

    TELEPORT_WORLD_MESSAGE("teleport-world-message"),
    RUNNERS_ALREADY_IN_LIST_MESSAGE("runners-already-in-list-message"),
    RUNNER_ADD_MESSAGE("runner-added-message"),
    DO_DAYLIGHT_CYCLE_MESSAGE("settings-do-daylight-cycle"),
    BACK_MESSAGE("settings-back"),
    DEATH_MESSAGE("death-message"),
    RUNNERS_LIST_MESSAGE("runners-list-message"),
    RUNNERS_NOT_IN_LIST_MESSAGE("runner-not-in-list-message"),
    RUNNER_REMOVED_MESSAGE("runner-removed-message"),
    CONFIG_RELOADED_MESSAGE("config-reload-message"),
    PLAYER_NOT_FOUND_MESSAGE("player-not-found-message"),
    START_MANHUNT_GUI_TITLE("start-manhunt-gui-title"),
    MAIN_GUI_TITLE("main-gui-title"),
    PLAYER_GUI_TITLE("player-gui-title"),
    QUEUE_ADD_MESSAGE("queue-add"),
    QUEUE_REMOVE_MESSAGE("queue-remove"),
    QUEUE_JOIN_ITEM("queue-join-item"),
    QUEUE_LEAVE_ITEM("queue-leave-item"),
    START_ITEM("start-item"),
    START_MANHUNT_ITEM("start-manhunt-item"),
    GAMES_GUI_TITLE("games-gui-title"),
    COMPASS_NAME("compass.name"),
    COMPASS_LORE("compass.lore"),
    SETTINGS_GUI_TITLE("settings-gui-title"),
    BROADCAST_MESSAGE("game-broadcast-message"),
    TABLIST_HEADER("tablist-header"),
    TABLIST_FOOTER("tablist-footer"),
    ENDERDRAGON_KILL_MESSAGE("kill-enderdragon-message"),
    NO_WORLD_FOUND_MESSAGE("no-world-message");

    private final String message;

    MessageUtils(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
