package cc.davyy.manhunt.cmds;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.guis.MainGUI;
import cc.davyy.manhunt.guis.PlayerGUI;
import cc.davyy.manhunt.managers.ManhuntManager;
import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.command.async.Async;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

@Route(name = "manhunt", aliases = "mh")
public class ManhuntCommand {

    private final PlayerGUI playerGUI;
    private final MainGUI mainGUI;
    private final ManhuntManager manhuntManager;

    public ManhuntCommand(Manhunt instance) {
        this.playerGUI = new PlayerGUI(instance);
        this.mainGUI = new MainGUI(instance);
        this.manhuntManager = instance.getManhuntManager();
    }

    @Execute
    void manhunt(Player player) {
        player.sendMessage(Component.text("----------------------------------------", NamedTextColor.BLUE));
        player.sendMessage(Component.text("Available Commands:", NamedTextColor.YELLOW));
        player.sendMessage(Component.text(""));
        player.sendMessage(Component.text("- /manhunt gui: Opens the main GUI.", NamedTextColor.GRAY));
        player.sendMessage(Component.text("- /manhunt addrunner: Adds a runner to the runners list.", NamedTextColor.GRAY));
        player.sendMessage(Component.text("- /manhunt removerunner: Removes a runner from the runners list.", NamedTextColor.GRAY));
        player.sendMessage(Component.text("- /testgui: Opens the GUI to test if it works (dev mode).", NamedTextColor.GRAY));
        player.sendMessage(Component.text("- /teleportworld: Teleports to worlds.", NamedTextColor.GRAY));
        player.sendMessage(Component.text("- /manhunt listrunners: Gives a list of runners.", NamedTextColor.GRAY));
        player.sendMessage(Component.text("- /manhunt reload: Reloads the config and messages.", NamedTextColor.GRAY));
        player.sendMessage(Component.text("- /manhunt join: Join a game", NamedTextColor.GRAY));
        player.sendMessage(Component.text(""));
        player.sendMessage(Component.text("Made by @davideenoo", NamedTextColor.AQUA));
        player.sendMessage(Component.text("----------------------------------------", NamedTextColor.BLUE));
    }

    // Adds Runner into Runner list
    @Async
    @Execute(route = "addrunner")
    @Permission("manhunt.addrunner")
    void runnerAdd(Player player, @Arg Player target) {
        manhuntManager.addManhuntRunner(player, target);
    }

    // Returns runners list
    @Async
    @Execute(route = "listrunners")
    @Permission("manhunt.listrunners")
    void runnersList(Player player) {
        manhuntManager.listRunners(player);
    }

    @Async
    @Execute(route = "removerunner")
    @Permission("manhunt.deleterunner")
    void runnerDelete(Player player, @Arg Player target) {
        manhuntManager.deleteRunners(player, target);
    }

    @Execute(route = "gui")
    @Permission("manhunt.gui")
    void gui(Player player) {
        mainGUI.guiCreation().show(player);
    }

    @Async
    @Execute(route = "reload")
    @Permission("manhunt.reload")
    void reload(Player player) {
        manhuntManager.manhuntReload(player);
    }

    @Execute(route = "join")
    void join(Player player) {
        playerGUI.guiCreation().show(player);
    }
}
