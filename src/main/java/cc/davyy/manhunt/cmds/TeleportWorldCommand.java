package cc.davyy.manhunt.cmds;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.utils.ColorUtils;
import cc.davyy.manhunt.utils.MessageUtils;
import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import org.bukkit.World;
import org.bukkit.entity.Player;

@Route(name = "teleportworld", aliases = "tpw")
@Permission("manhunt.tpw")
public class TeleportWorldCommand {

    private final Manhunt instance;

    public TeleportWorldCommand(Manhunt instance) {
        this.instance = instance;
    }

    @Execute
    void teleportWorld(Player player, @Arg World world) {

        if (world != null) {
            player.teleport(world.getSpawnLocation());
            String message = instance.getMessages().getString(MessageUtils.TELEPORT_WORLD_MESSAGE.getMessage());
            player.sendMessage(ColorUtils.colorize(message + " " + world.getName()));

        } else {
            String message = instance.getMessages().getString(MessageUtils.NO_WORLD_FOUND.getMessage());
            player.sendMessage(ColorUtils.colorize(message));
        }
    }

}
