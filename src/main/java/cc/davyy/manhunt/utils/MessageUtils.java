package cc.davyy.manhunt.utils;

public enum MessageUtils {

    TELEPORT_WORLD_MESSAGE("teleport-world-message"),
    NO_WORLD_FOUND("no-world-message");

    private final String message;

    MessageUtils(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
