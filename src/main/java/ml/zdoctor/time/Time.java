package ml.zdoctor.time;

import java.util.concurrent.TimeUnit;

public class Time {

    public static long convertTime(long ticks, TimeUnit unit) {
        long milliseconds = 50 * ticks;
        return TimeUnit.MILLISECONDS.convert(milliseconds, unit);
    }
}
