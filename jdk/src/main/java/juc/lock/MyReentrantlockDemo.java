package juc.lock;

/**
 * @author guang.li
 * @version ReentrantlockDemo.java v 1.0 2020/10/12 9:49
 */
public class MyReentrantlockDemo {


    public static void main(String[] args) {
        MyReentrantLock lock = new MyReentrantLock(true);

        int count = 0;
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                count++;
            } finally {
                lock.unlock();
            }
        }
    }
}
