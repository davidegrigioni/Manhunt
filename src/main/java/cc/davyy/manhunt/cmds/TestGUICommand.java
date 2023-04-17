package cc.davyy.manhunt.cmds;

import cc.davyy.manhunt.guis.MainGUI;
import cc.davyy.manhunt.guis.StartManhuntGUI;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import org.bukkit.entity.Player;

@Route(name = "testgui")
@Permission("manhunt.testgui")
public class TestGUICommand {

    private MainGUI mainGUI;
    private StartManhuntGUI startManhuntGUI;

    @Execute(route = "main")
    void mainGUITest(Player player) {
        mainGUI.guiCreation().show(player);
    }

    @Execute(route = "start")
    void startGUITest(Player player) {
        startManhuntGUI.guiCreation().show(player);
    }

}
