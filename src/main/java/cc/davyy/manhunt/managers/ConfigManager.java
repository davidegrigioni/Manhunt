package cc.davyy.manhunt.managers;

import cc.davyy.manhunt.Manhunt;
import dev.dejvokep.boostedyaml.YamlDocument;

public class ConfigManager {

    private static YamlDocument config;

    public static void setConfig(Manhunt instance) {
        ConfigManager.config = instance.getConfiguration();
        instance.saveDefaultConfig();
    }

    public static int getMinRequiredPlayers() {
        return config.getInt("min-required-players");
    }

    public static int getMaxRequiredPlayers() {
        return config.getInt("max-required-players");
    }

    public static int getMinRequiredHunters() {
        return config.getInt("min-required-hunters");
    }

    public static int getMinRequiredRunners() {
        return config.getInt("min-required-runners");
    }

    public static int getCountdown() {
        return config.getInt("countdown");
    }

    public static int runnersHeadstart() {
        return config.getInt("runners-headstart");
    }

}
