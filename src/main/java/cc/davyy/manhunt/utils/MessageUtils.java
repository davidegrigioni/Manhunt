package cc.davyy.manhunt.utils;

public enum MessageUtils {

    TELEPORT_WORLD_MESSAGE("teleport-world-message"),
    RUNNERS_ALREADY_IN_LIST_MESSAGE("runners-already-in-list-message"),
    RUNNER_ADD_MESSAGE("runner-added-message"),
    RUNNERS_LIST_MESSAGE("runners-list-message"),
    RUNNERS_NOT_IN_LIST_MESSAGE("runner-not-in-list-message"),
    RUNNER_REMOVED_MESSAGE("runner-removed-message"),
    NO_WORLD_FOUND_MESSAGE("no-world-message");

    private final String message;

    MessageUtils(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
