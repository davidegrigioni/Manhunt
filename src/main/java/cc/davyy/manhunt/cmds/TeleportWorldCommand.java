package cc.davyy.manhunt.cmds;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.managers.ManhuntManager;
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
    private final ManhuntManager manhuntManager;

    public TeleportWorldCommand(Manhunt instance) {
        this.instance = instance;
        this.manhuntManager = new ManhuntManager(instance);
    }

    @Execute
    void teleportWorld(Player player, @Arg World world) {
        manhuntManager.worldTeleport(player, world);
    }

}
