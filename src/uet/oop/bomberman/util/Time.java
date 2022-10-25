package uet.oop.bomberman.util;
import java.util.*;


public class Time {
    static int interval;
    static Timer timer;
    static String GAME_TIME = "300";
    public int timeCount = 0;

    public void runTime() {
        int delay = 1000;
        int period = 1000;
        timer = new Timer();

        interval = Integer.parseInt(GAME_TIME);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeCount++;
            }
        },delay, period);
    }

    private static final int setInterval() {
        if (interval == 0) {
            timer.cancel();
        }
        return --interval;
    }

}
