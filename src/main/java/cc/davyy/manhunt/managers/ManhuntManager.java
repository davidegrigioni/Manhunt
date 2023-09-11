package cc.davyy.manhunt.managers;

import cc.davyy.ddapi.utils.chat.ChatUtils;
import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.engine.PlayerStatus;
import cc.davyy.manhunt.utils.MessageUtils;
import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

public class ManhuntManager {

    private final Manhunt instance;

    private final List<String> runnersList;

    public ManhuntManager(Manhunt instance) {
        this.instance = instance;
        this.runnersList = instance.getConfiguration().getStringList("runners");
    }

    public void addRunner(Player player, Player target) {
        PlayerStatus status = PlayerStatus.getPlayerStatus(target);

        switch (status) {
            case ONLINE -> {
                if (!runnersList.contains(target.getName())) {
                    runnersList.add(target.getName());
                    try {
                        instance.getConfiguration().set("runners", runnersList);
                        instance.getConfiguration().save();
                        String runnerAdded = instance.getMessages().getString(MessageUtils.RUNNER_ADD_MESSAGE.getMessage());
                        ChatUtils.sendMessage(player, runnerAdded.replace("<player>", target.getName()));
                    } catch (IOException ex) {
                        instance.getLogger().log(Level.SEVERE, "Could not save Runner", ex);
                    }
                } else {
                    String alreadyInList = instance.getMessages().getString(MessageUtils.RUNNERS_ALREADY_IN_LIST_MESSAGE.getMessage());
                    ChatUtils.sendMessage(player, alreadyInList);
                }
            }
            case OFFLINE -> {
                String playerNotFound = instance.getMessages().getString(MessageUtils.PLAYER_NOT_FOUND_MESSAGE.getMessage());
                ChatUtils.sendMessage(player, playerNotFound);
            }
        }
    }

    public void deleteRunner(Player player, Player target) {
        if (!runnersList.contains(target.getName())) {
            String runnerNotInList = instance.getMessages().getString(MessageUtils.RUNNERS_NOT_IN_LIST_MESSAGE.getMessage());
            ChatUtils.sendMessage(player, runnerNotInList);
            return;
        }
        runnersList.remove(target.getName());
        try {
            instance.getConfiguration().set("runners", runnersList);
            instance.getConfiguration().save();
            String runnerRemoved = instance.getMessages().getString(MessageUtils.RUNNER_REMOVED_MESSAGE.getMessage());
            ChatUtils.sendMessage(player, runnerRemoved.replace("<player>", target.getName()));
        } catch (IOException ex) {
            instance.getLogger().log(Level.SEVERE, "An error occurred whilst removing a runner", ex);
        }
    }

    public void listRunners(Player player) {
        String runnersListMessage = instance.getMessages().getString(MessageUtils.RUNNERS_LIST_MESSAGE.getMessage());
        String runners = String.join(", ", runnersList);
        ChatUtils.sendMessage(player, runnersListMessage + runners);
    }

    public void manhuntReload(Player player) {
        try {
            instance.getConfiguration().reload();
            instance.getMessages().reload();
            String configReloaded = instance.getMessages().getString(MessageUtils.CONFIG_RELOADED_MESSAGE.getMessage());
            ChatUtils.sendMessage(player, configReloaded);
        } catch (IOException ex) {
            instance.getLogger().log(Level.SEVERE, "An error occurred whilst loading the config!", ex);
        }
    }

    public void worldTeleport(Player player, World world) {
        if (world != null) {
            player.teleportAsync(world.getSpawnLocation());
        }
        String worldTeleport = world != null
                ? instance.getMessages().getString(MessageUtils.TELEPORT_WORLD_MESSAGE.getMessage())
                : instance.getMessages().getString(MessageUtils.NO_WORLD_FOUND_MESSAGE.getMessage());
        ChatUtils.sendMessage(player, worldTeleport.replace("<world>", world != null ? world.getName() : ""));
    }

}
