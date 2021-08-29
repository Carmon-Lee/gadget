import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.common.util.concurrent.Uninterruptibles;

public class Main {


    public static void main(String[] args) {


        Long[] arr = new Long[] {8424932970L, 8424863273L, 8424430434L, 8424576743L, 8424727269L, 8424930157L, 8424894191L, 8424668783L, 8424782447L, 8424930913L, 8424857380L, 8424869670L, 8424892966L, 8424924927L, 8424892221L, 8424565552L, 8424898098L, 8424662845L, 8424863411L, 8424866677L, 8424355581L, 8424603194L, 8424878603L, 8424316549L, 8424631104L, 8424735554L, 8424764227L, 8424220411L, 8424888014L, 8424553101L, 8424210293L, 8424892164L, 8424927751L, 8424730250L, 8424580616L, 8424846086L, 8424902360L, 8424844056L, 8424896093L, 8424524562L, 8424697631L, 8424502874L, 8424674013L, 8424791829L, 8423814756L, 8424289054L};
        Set<Long> longs = Arrays.asList(arr).stream().collect(Collectors.toSet());

        String[] strings = longs.stream().map(String::valueOf).toArray(String[]::new);
        System.out.println(strings.length);

        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10000);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, queue);

        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            futures.add(poolExecutor.submit(new TestTask(i * 2)));
        }

        futures.forEach(f -> {
            try {
                Uninterruptibles.getUninterruptibly(f, 2, TimeUnit.SECONDS);
            } catch (ExecutionException | TimeoutException e) {
                //
                e.printStackTrace();
            }
        });
    }


    static class TestTask implements Runnable {
        private int sleepTime;

        public TestTask(int sleepTime) {
            this.sleepTime = sleepTime;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(sleepTime * 1000L);
                System.out.println("sleep finish:" + sleepTime);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }

}


