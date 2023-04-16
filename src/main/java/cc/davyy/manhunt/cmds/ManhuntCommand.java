package cc.davyy.manhunt.cmds;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.route.Route;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

@Route(name = "manhunt")
public class ManhuntCommand {

    @Execute
    void manhunt(Player player) {
        player.sendMessage(Component.text("Plugin made by @davideenoo with love <3", NamedTextColor.DARK_BLUE));
    }

}
