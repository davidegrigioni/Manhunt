package cc.davyy.manhunt.utils;

import cc.davyy.manhunt.Manhunt;

public enum GameUtils {

    COUNTDOWN("countdown");

    private final String configKey;
    private Manhunt instance;

    GameUtils(String configKey) {
        this.configKey = configKey;
    }

    public int getValue() {
        return instance.getConfiguration().getInt(configKey);
    }

}
