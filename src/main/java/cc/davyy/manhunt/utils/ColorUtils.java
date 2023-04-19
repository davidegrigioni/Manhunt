package cc.davyy.manhunt.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.ChatColor;

public class ColorUtils {

    private ColorUtils() {}

    public static Component colorize(String message) {
        return MiniMessage.miniMessage().deserialize(message);
    }

    // DEPRECATED METHOD,
    // ONLY TO SUPPORT COLORS ON THE SCOREBOARD TILL THEY DON'T ADD SUPPORT FOR MINIMESSAGE
    public static String legacy(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
