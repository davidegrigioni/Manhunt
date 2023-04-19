package cc.davyy.manhunt.placeholders;

import cc.davyy.manhunt.Manhunt;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class ManhuntExpansion extends PlaceholderExpansion {

    private final Manhunt instance;

    public ManhuntExpansion(Manhunt instance) {
        this.instance = instance;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "manhunt";
    }

    @Override
    public @NotNull String getAuthor() {
        return "davideenoo";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {

        if (params.equalsIgnoreCase("runners")){
            return instance.getConfiguration().getStringList("runners").toString();
        }

        if (params.equalsIgnoreCase("onlineplayers")) {
            return String.valueOf(Bukkit.getOnlinePlayers().size());
        }

        return null; // Placeholder is unknown by the Expansion
    }

}
