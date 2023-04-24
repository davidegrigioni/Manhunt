package cc.davyy.manhunt.utils;

public class TimeUtils {

    private TimeUtils() {}

    public static int minuteToTicks(int minutes) {
        return minutes * 1200;
    }

    public static int ticksToMinute(int ticks) {
        return ticks / 1200;
    }

    public static int secondsToTicks(int seconds) {
        return seconds * 20;
    }

    public static int ticksToSeconds(int ticks) {
        return ticks / 20;
    }

    public static int hoursToTicks(int hours) {
        return hours * 72000;
    }

    public static int ticksToHours(int ticks) {
        return ticks / 72000;
    }

}
