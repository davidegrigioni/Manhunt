package cc.davyy.manhunt.cmds;

import cc.davyy.manhunt.Manhunt;
import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.command.async.Async;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

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

    @Async
    @Execute(route = "addrunner", aliases = "ar")
    @Permission("manhunt.addrunner")
    void addRunner(Player player, @Arg Player target) { instance.getManhuntManager().addRunner(player, target); }

    @Execute(route = "listrunners", aliases = "lr")
    @Permission("manhunt.listrunners")
    void listRunners(Player player) { instance.getManhuntManager().listRunners(player); }

    @Execute(route = "deleterunner", aliases = "dr")
    @Permission("manhunt.deleterunner")
    void deleteRunner(Player player, @Arg Player target) { instance.getManhuntManager().deleteRunner(player, target); }

    @Execute(route = "teleportworld", aliases = "tpw")
    @Permission("manhunt.teleportworld")
    void teleportWorld(Player player, @Arg World world) { instance.getManhuntManager().worldTeleport(player, world); }



}
