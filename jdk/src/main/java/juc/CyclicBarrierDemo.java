package juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.LockSupport;

public class CyclicBarrierDemo {

    public static void main(String[] args) {
        int a = 0xff;
        System.out.println(a);

        LockSupport.park();
        System.out.println();
        Thread.currentThread().interrupt();
        System.out.println();

        CyclicBarrier barrier = new CyclicBarrier(2);

        new Thread(()->{
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            Thread.sleep(1000_000_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();



        System.out.println("hello");
        int count=0;
        for (int i = 0; i < 1000_000; i++) {
            try {
                Thread.sleep(100);
                System.out.println("wakeup");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j = 0; j < 1000_000_000; j++) {
                synchronized (CyclicBarrierDemo.class) {
                    count++;
                }
            }
        }

    }
}
