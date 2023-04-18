package cc.davyy.manhunt.cmds;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.utils.ColorUtils;
import cc.davyy.manhunt.utils.MessageUtils;
import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.List;

@Route(name = "manhunt")
public class ManhuntCommand {

    private final Manhunt instance;

    public ManhuntCommand(Manhunt instance) {
        this.instance = instance;
    }

    @Execute
    void manhunt(Player player) {
        player.sendMessage(Component.text("Plugin made by @davideenoo with love <3", NamedTextColor.BLUE));
    }

    // Adds Runner into Runner list
    @Execute(route = "addrunners")
    @Permission("manhunt.addrunners")
    void manhuntRunners(Player player, @Arg Player target) throws IOException {

        List<String> runnersList = instance.getConfiguration().getStringList("runners");

        if (!runnersList.contains(target.getName())) {
            runnersList.add(target.getName());
            instance.getConfiguration().set("runners", runnersList);
            instance.getConfiguration().save();
            String message = instance.getMessages().getString(MessageUtils.RUNNER_ADD_MESSAGE.getMessage());
            player.sendMessage(ColorUtils.colorize(target.getName() + " " + message));
        } else {
            String message = instance.getMessages().getString(MessageUtils.RUNNERS_ALREADY_IN_LIST_MESSAGE.getMessage());
            player.sendMessage(ColorUtils.colorize(message));
        }
    }

    // Returns runners list
    @Execute(route = "listrunners")
    @Permission("manhunt.listrunners")
    void runnersList(Player player) {
        List<String> runnersList = instance.getConfiguration().getStringList("runners");
        String message = instance.getMessages().getString(MessageUtils.RUNNERS_LIST_MESSAGE.getMessage());
        String runners = String.join(", ", runnersList);
        player.sendMessage(ColorUtils.colorize(message + runners));
    }

    @Execute(route = "deleterunner")
    @Permission("manhunt.deleterunner")
    void runnerDelete(Player player, @Arg Player target) {
        List<String> runnersList = instance.getConfiguration().getStringList("runners");
        if (!runnersList.contains(target.getName())) {
            String message = instance.getMessages().getString(MessageUtils.RUNNERS_NOT_IN_LIST_MESSAGE.getMessage());
            player.sendMessage(ColorUtils.colorize(message));
        } else {
            runnersList.remove(player.getName());
            String message = instance.getMessages().getString(MessageUtils.RUNNER_REMOVED_MESSAGE.getMessage());
            player.sendMessage(ColorUtils.colorize(message));
        }
    }

}
