package juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author guang.li
 * @version ReentrantlockDemo.java v 1.0 2020/10/12 9:49
 */
public class MyReentrantlockDemo {


    public static void main(String[] args) throws InterruptedException {
        MyReentrantLock lock = new MyReentrantLock(true);

        Thread t1 = new Thread(() -> {
            lock.lock();
            System.out.println("task 1 locked");
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("task 1 finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
            System.out.println("task 1 unlock");
        });
        t1.setName("t1");
        t1.start();


        TimeUnit.MILLISECONDS.sleep(100);


        Thread t2 = new Thread(() -> {
            lock.lock();
            System.out.println("task 2 locked");
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("task 2 finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
            System.out.println("task 2 unlock");
        });
        t2.setName("t2");
        t2.start();
    }
}
