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

    @Execute(route = "main")
    void mainGUITest(Player player) {
        MainGUI mainGUI = new MainGUI();
        mainGUI.guiCreation().show(player);
    }

    @Execute(route = "start")
    void startGUITest(Player player) {
        StartManhuntGUI startManhuntGUI = new StartManhuntGUI();
        startManhuntGUI.guiCreation().show(player);
    }

}
