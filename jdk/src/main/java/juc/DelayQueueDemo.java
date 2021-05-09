package juc;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created on 2021-03-19
 */
public class DelayQueueDemo {

    public static void main(String[] args) throws InterruptedException {


        DelayQueue<LiveVoicePartyDelayStatusFlowLog> delayQueue = new DelayQueue<>();
        String statusFlowLog = "NEW";
        for (int i = 0; i < 10; i++) {
            delayQueue.add(new LiveVoicePartyDelayStatusFlowLog(statusFlowLog, System.currentTimeMillis()));
        }
        System.out.println("consumer");
        while (!delayQueue.isEmpty()) {
            LiveVoicePartyDelayStatusFlowLog take = delayQueue.take();
            System.out.println("get");
        }
    }


    static class LiveVoicePartyDelayStatusFlowLog implements Delayed {

        private String statusFlowLog;
        private long availableTime;

        public LiveVoicePartyDelayStatusFlowLog(String statusFlowLog,
                long receiveTime) {
            this.statusFlowLog = statusFlowLog;
            this.availableTime = receiveTime + 10000;
        }

        public String getStatusFlowLog() {
            return statusFlowLog;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(availableTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.availableTime - ((LiveVoicePartyDelayStatusFlowLog) o).availableTime);
        }
    }
}
