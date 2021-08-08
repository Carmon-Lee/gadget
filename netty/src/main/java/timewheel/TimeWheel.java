package timewheel;

import static timewheel.util.TimeUtils.diffInSeconds;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.Getter;

/**
 * @author liguang
 * Created on 2021-05-12
 */
public class TimeWheel {
    private final int hourScale = 24;
    private final int minuteScale = 60;
    private final int secondScale = 60;
    Lock lock;
    private ScalableWheel hourWheel;
    private ScalableWheel minuteWheel;
    private ScalableWheel secondWheel;
    private long lastTickTime;
    private long currentTime;
    private ScheduledExecutorService executorService;

    public TimeWheel() {
        hourWheel = new ScalableWheel(hourScale);
        minuteWheel = new ScalableWheel(minuteScale);
        secondWheel = new ScalableWheel(secondScale);
        lock = new ReentrantLock();
        executorService = Executors.newScheduledThreadPool(1);
    }

    public void start() {
        executorService.schedule(new TickTask(lock), 0, TimeUnit.SECONDS);
    }

    public void addJob(JobInfo jobInfo, int timeAfter) {
        Time time = calculateJobTime(timeAfter);
        lock.lock();

        hourWheel.addJob(jobInfo, time.getHour());

        lock.unlock();
    }

    private Time calculateJobTime(int timeAfter) {
        int seconds = timeAfter % secondScale;
        timeAfter = timeAfter / secondScale;
        int minutes = timeAfter % minuteScale;
        timeAfter = timeAfter / minuteScale;
        int hours = timeAfter % hourScale;
        timeAfter = timeAfter / hourScale;
        if (timeAfter > 0) {
            throw new IllegalArgumentException("timeAfter exceeds 1 day");
        }
        return new Time(hours, minutes, seconds);
    }

    @Getter
    static class Time {
        private int hour;
        private int minute;
        private int second;

        public Time(int hour, int minute, int second) {
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        }
    }

    class TickTask implements Runnable {

        private Lock lock;

        public TickTask(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            lastTickTime = currentTime;
            currentTime = System.currentTimeMillis();
            int diff = diffInSeconds(lastTickTime, currentTime, secondScale);
            if (diff != 0) {
                tick(diff);
            }
            lock.unlock();
        }

        private void tick(int diff) {

        }

        private void tickOnce() {

        }
    }
}
