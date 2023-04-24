package cc.davyy.manhunt.cmds;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.guis.MainGUI;
import cc.davyy.manhunt.utils.ColorUtils;
import cc.davyy.manhunt.utils.MessageUtils;
import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.command.async.Async;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

@Route(name = "manhunt", aliases = "mh")
public class ManhuntCommand {

    private final Manhunt instance;

    public ManhuntCommand(Manhunt instance) {
        this.instance = instance;
    }

    @Execute
    void manhunt(Player player) {
        player.sendMessage(Component.text("----------------------------------------", NamedTextColor.BLUE));
        player.sendMessage(Component.text("Available Commands:", NamedTextColor.YELLOW));
        player.sendMessage(Component.text(""));
        player.sendMessage(Component.text("- /manhunt gui: Opens the main GUI.", NamedTextColor.GRAY));
        player.sendMessage(Component.text("- /manhunt add/addrunners: Adds a runner to the runners list.", NamedTextColor.GRAY));
        player.sendMessage(Component.text("- /manhunt rem/removerunner: Removes a runner from the runners list.", NamedTextColor.GRAY));
        player.sendMessage(Component.text("- /testgui: Opens the GUI to test if it works (dev mode).", NamedTextColor.GRAY));
        player.sendMessage(Component.text("- /teleportworld: Teleports to worlds.", NamedTextColor.GRAY));
        player.sendMessage(Component.text("- /manhunt list/listrunners: Gives a list of runners.", NamedTextColor.GRAY));
        player.sendMessage(Component.text("- /manhunt reload: Reloads the config and messages.", NamedTextColor.GRAY));
        player.sendMessage(Component.text(""));
        player.sendMessage(Component.text("Made by @davideenoo", NamedTextColor.AQUA));
        player.sendMessage(Component.text("----------------------------------------", NamedTextColor.BLUE));
    }

    // Adds Runner into Runner list
    @Async
    @Execute(route = "addrunner")
    @Permission("manhunt.addrunner")
    void runnerAdd(Player player, @Arg Player target) throws IOException {

        List<String> runnersList = instance.getConfiguration().getStringList("runners");

        if (Bukkit.getPlayer(target.getName()) != null && Bukkit.getPlayer(target.getName()).isOnline()) {
            if (!runnersList.contains(target.getName())) {
                runnersList.add(target.getName());
                instance.getConfiguration().set("runners", runnersList);
                instance.getConfiguration().save();
                String message = instance.getMessages().getString(MessageUtils.RUNNER_ADD_MESSAGE.getMessage());
                player.sendMessage(ColorUtils.colorize(message));
            } else {
                String message = instance.getMessages().getString(MessageUtils.RUNNERS_ALREADY_IN_LIST_MESSAGE.getMessage());
                player.sendMessage(ColorUtils.colorize(message));
            }
        } else {
            String message = instance.getMessages().getString(MessageUtils.PLAYER_NOT_FOUND_MESSAGE.getMessage());
            player.sendMessage(ColorUtils.colorize(message));
        }

    }

    // Returns runners list
    @Async
    @Execute(route = "listrunners")
    @Permission("manhunt.listrunners")
    void runnersList(Player player) {
        List<String> runnersList = instance.getConfiguration().getStringList("runners");
        String message = instance.getMessages().getString(MessageUtils.RUNNERS_LIST_MESSAGE.getMessage());
        String runners = String.join(", ", runnersList);
        player.sendMessage(ColorUtils.colorize(message + runners));
    }

    @Async
    @Execute(route = "removerunner")
    @Permission("manhunt.deleterunner")
    void runnerDelete(Player player, @Arg Player target) throws IOException {
        List<String> runnersList = instance.getConfiguration().getStringList("runners");
        if (!runnersList.contains(target.getName())) {
            String message = instance.getMessages().getString(MessageUtils.RUNNERS_NOT_IN_LIST_MESSAGE.getMessage());
            player.sendMessage(ColorUtils.colorize(message));
        } else {
            runnersList.remove(target.getName());
            instance.getConfiguration().set("runners", runnersList);
            instance.getConfiguration().save();
            String message = instance.getMessages().getString(MessageUtils.RUNNER_REMOVED_MESSAGE.getMessage());
            player.sendMessage(ColorUtils.colorize(message));
        }
    }

    @Execute(route = "gui")
    @Permission("manhunt.gui")
    void gui(Player player) {
        MainGUI mainGUI = new MainGUI(instance);
        mainGUI.guiCreation().show(player);
    }

    @Async
    @Execute(route = "reload")
    @Permission("manhunt.reload")
    void reload(Player player) {
        try {
            instance.getConfiguration().reload();
            instance.getMessages().reload();
            String message = instance.getMessages().getString(MessageUtils.CONFIG_RELOADED_MESSAGE.getMessage());
            player.sendMessage(ColorUtils.colorize(message));
        } catch (IOException ex) {
            instance.getLogger().log(Level.SEVERE, "An error occurred whilst loading the config!", ex);
        }
    }

}
