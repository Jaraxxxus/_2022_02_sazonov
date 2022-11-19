package ru.cft.javaLessons.miner.app;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class Timer implements Runnable {

    private int seconds = 0;
    private final ModelListener listener;
    private final ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> scheduledFuture;

    boolean isStarted = false;
    Timer(ModelListener listener) {
        this.listener = listener;
    }

    void restartTime() {
        seconds = 0;
        isStarted = true;
        scheduledFuture = timer.scheduleAtFixedRate(this, 1, 1, TimeUnit.SECONDS);
    }

    void stopTime() {
        if (!isStarted)
            return;
        isStarted = false;
        scheduledFuture.cancel(false);
    }

    int getTime() {
        return seconds;
    }

    @Override
    public void run() {
        seconds++;
        listener.onTimerChange(seconds);
    }
}