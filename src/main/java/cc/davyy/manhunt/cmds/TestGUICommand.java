package cc.davyy.manhunt.cmds;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.guis.MainGUI;
import cc.davyy.manhunt.guis.SettingsGUI;
import cc.davyy.manhunt.guis.StartManhuntGUI;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import org.bukkit.entity.Player;

@Route(name = "testgui")
@Permission("manhunt.testgui")
public class TestGUICommand {

    private final Manhunt instance;

    public TestGUICommand(Manhunt instance) {
        this.instance = instance;
    }

    @Execute(route = "main")
    void mainGUITest(Player player) {
        MainGUI mainGUI = new MainGUI(instance);
        mainGUI.guiCreation().show(player);
    }

    @Execute(route = "start")
    void startGUITest(Player player) {
        StartManhuntGUI startManhuntGUI = new StartManhuntGUI(instance);
        startManhuntGUI.guiCreation().show(player);
    }

    @Execute(route = "settings")
    void settingsGUITest(Player player) {
        SettingsGUI settingsGUI = new SettingsGUI();
        settingsGUI.guiCreation().show(player);
    }

}
