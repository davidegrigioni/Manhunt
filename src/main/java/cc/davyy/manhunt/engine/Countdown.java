package cc.davyy.manhunt.engine;

import cc.davyy.manhunt.Manhunt;
import cc.davyy.manhunt.utils.GameUtils;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {

    private final Manhunt instance;
    private final Game game;
    private int countdown;

    public Countdown(Manhunt instance, Game game) {
        this.instance = instance;
        this.game = game;
        this.countdown = GameUtils.COUNTDOWN.getValue();
    }

    public void start() {
        game.setGameState(GameState.WAITING);
        runTaskTimer(instance, 0, 20);
    }

    @Override
    public void run() {
        if (countdown == 0) {
            cancel();

            return;
        }

        if (countdown <= 10 || countdown % 15 == 0) {

        }

        countdown--;
    }

    public int getCountdown() {
        return countdown;
    }
}
