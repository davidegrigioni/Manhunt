package cc.davyy.manhunt.utils;

public enum MessageUtils {

    WELCOME_MESSAGE("welcome-message");

    private final String message;

    MessageUtils(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
