package cc.davyy.manhunt.managers;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.utils.ColorUtils;
import cc.davyy.manhunt.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

public class ManhuntManager {

    private final Manhunt instance;

    private final List<String> runnersList;

    public ManhuntManager(Manhunt instance) {
        this.instance = instance;
        this.runnersList = instance.getConfiguration().getStringList("runners");
    }

    public void addManhuntRunner(Player player, Player target) {

        if (Bukkit.getPlayer(target.getName()) != null && Objects.requireNonNull(Bukkit.getPlayer(target.getName())).isOnline()) {

            if (!runnersList.contains(target.getName())) {
                runnersList.add(target.getName());
                instance.getConfiguration().set("runners", runnersList);

                try {
                    instance.getConfiguration().save();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                String message = instance.getMessages().getString(MessageUtils.RUNNER_ADD_MESSAGE.getMessage());
                player.sendMessage(ColorUtils.colorize(message.replace("<player>", player.getName())));

            } else {
                String message = instance.getMessages().getString(MessageUtils.RUNNERS_ALREADY_IN_LIST_MESSAGE.getMessage());
                player.sendMessage(ColorUtils.colorize(message));
            }

        } else {
            String message = instance.getMessages().getString(MessageUtils.PLAYER_NOT_FOUND_MESSAGE.getMessage());
            player.sendMessage(ColorUtils.colorize(message));
        }

    }

    public void listRunners(Player player) {
        String message = instance.getMessages().getString(MessageUtils.RUNNERS_LIST_MESSAGE.getMessage());
        String runners = String.join(", ", runnersList);
        player.sendMessage(ColorUtils.colorize(message + runners));
    }

    public void deleteRunners(Player player, Player target) {

        if (!runnersList.contains(target.getName())) {
            String message = instance.getMessages().getString(MessageUtils.RUNNERS_NOT_IN_LIST_MESSAGE.getMessage());
            player.sendMessage(ColorUtils.colorize(message));

        } else {
            runnersList.remove(target.getName());
            instance.getConfiguration().set("runners", runnersList);

            try {
                instance.getConfiguration().save();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String message = instance.getMessages().getString(MessageUtils.RUNNER_REMOVED_MESSAGE.getMessage());
            player.sendMessage(ColorUtils.colorize(message.replace("<player>", player.getName())));
        }
    }

    public void manhuntReload(Player player) {

        try {
            instance.getConfiguration().reload();
            instance.getMessages().reload();
            String message = instance.getMessages().getString(MessageUtils.CONFIG_RELOADED_MESSAGE.getMessage());
            player.sendMessage(ColorUtils.colorize(message));
        } catch (IOException ex) {
            instance.getLogger().log(Level.SEVERE, "An error occurred whilst loading the config!", ex);
        }
    }

    public void worldTeleport(Player player, World world) {

        if (world != null) {
            player.teleportAsync(world.getSpawnLocation());
            String message = instance.getMessages().getString(MessageUtils.TELEPORT_WORLD_MESSAGE.getMessage());
            player.sendMessage(ColorUtils.colorize(message.replace("<world>", world.getName())));

        } else {
            String message = instance.getMessages().getString(MessageUtils.NO_WORLD_FOUND_MESSAGE.getMessage());
            player.sendMessage(ColorUtils.colorize(message));
        }
    }

}
