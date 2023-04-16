package cc.davyy.manhunt.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class ColorUtils {

    private ColorUtils() {}

    public static Component colorize(String message) {
        return MiniMessage.miniMessage().deserialize(message);
    }

}
