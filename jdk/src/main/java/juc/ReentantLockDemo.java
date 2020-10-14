package juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ReentantLockDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);

        ReentrantLock lock = new ReentrantLock(true);

        for (int i = 0; i < 1000; i++) {
            executorService.execute(()->{
//                synchronized (ReentantLockDemo.class) {
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//
//                    }
//                }

//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                lock.lock();
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//
//                } finally {
//                    lock.unlock();
//                }
            });
        }
//        try {
//            TimeUnit.SECONDS.sleep(12);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        int count = 0;
        for (int i = 0; i < 1000_000_00; i++) {
            synchronized (ReentantLockDemo.class) {
                count++;
            }
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000_000_00; i++) {
            synchronized (ReentantLockDemo.class) {
                count++;
            }
////
//            lock.lock();
//            try {
//                count++;
//            } finally {
//                lock.unlock();
//            }

        }

        System.out.println(System.currentTimeMillis()-start);
    }
}
