package ru.cft.javaLessons.miner.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class Timer implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(Timer.class);
    private int seconds = 0;
    private long startTime = 0;
    private long endTime = 0;
    private final ModelListener listener;
    private final ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> scheduledFuture;

    boolean isStarted = false;

    Timer(ModelListener listener) {
        this.listener = listener;
    }

    void restartTime() {
        log.info("Таймер запущен");
        startTime = System.currentTimeMillis();
        endTime = System.currentTimeMillis();
        seconds = 0;
        isStarted = true;
        scheduledFuture = timer.scheduleAtFixedRate(this, 1, 1, TimeUnit.SECONDS);
    }

    void stopTime() {
        if (!isStarted)
            return;
        isStarted = false;
        log.info("Таймер остановлен, время " + seconds);
        scheduledFuture.cancel(false);
    }

    int getTime() {
        return seconds;
    }

    @Override
    public void run() {
        endTime = System.currentTimeMillis();
        seconds = (int) ( endTime - startTime) / 1000;
        listener.onTimerChange(seconds);
    }
}