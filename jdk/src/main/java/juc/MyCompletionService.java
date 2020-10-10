package juc;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

/**
 * @author guang.li
 * @version MyCompletionService.java v 1.0 2020/10/9 13:59
 */
public class MyCompletionService {

    public static void main(String[] args) {
        CompletionService<String> cs = new ExecutorCompletionService<>(Executors.newFixedThreadPool(10));

        cs.submit(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "sleep 3000");

        cs.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "sleep 1000");

        cs.submit(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "sleep 2000");

        cs.submit(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "sleep 100");

        for (int i = 0; i < 4; i++) {
            try {
                System.out.println(cs.take().get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
