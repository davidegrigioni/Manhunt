package cc.davyy.manhunt.managers;

import cc.davyy.manhunt.Manhunt;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class CountdownManager {
    private final Manhunt instance;
    private final int countdownSeconds;
    private BukkitTask task;
    private int countdownTime;

    public CountdownManager(Manhunt instance, int countdownSeconds) {
        this.instance = instance;
        this.countdownSeconds = countdownSeconds;
    }

    public void start() {
        if (task != null) {
            throw new IllegalStateException("Countdown is already running");
        }

        countdownTime = countdownSeconds;

        task = Bukkit.getScheduler().runTaskTimer(instance, () -> {
            if (countdownTime == 0) {
                stop();
                return;
            }

            countdownTime--;
            onTick(countdownTime);
        }, 0L, 20L);
    }

    public void stop() {
        if (task != null) {
            task.cancel();
            task = null;
        }
    }

    public boolean isRunning() {
        return task != null;
    }

    public int getCountdownSeconds() {
        return countdownSeconds;
    }

    public int getCountdownTime() {
        return countdownTime;
    }

    public void onTick(int secondsLeft) {
        // Override this method to add custom behavior on every tick of the countdown
    }

    public void onFinish() {
        // Override this method to add custom behavior when the countdown finishes
    }
}
