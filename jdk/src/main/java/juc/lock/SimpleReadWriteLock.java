package juc.lock;

/**
 * @author liguang
 * Created on 2021-09-18
 */
public class SimpleReadWriteLock {

    private volatile int readCount;
    private volatile int writeCount;
    private volatile boolean hasWriter;

    public synchronized void lockRead() {
        while (writeCount > 0 || hasWriter) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        readCount++;
    }

    public synchronized void unlockRead() {
        readCount--;
        this.notifyAll();
    }

    public synchronized void lockWrite() {
        writeCount++;
        while (readCount > 0 || hasWriter) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        writeCount--;
        hasWriter = true;
    }

    public synchronized void unlockWrite() {
        hasWriter = false;
        this.notifyAll();
    }

    static volatile int counter = 0;

    public static void main(String[] args) {
        SimpleReadWriteLock lock = new SimpleReadWriteLock();

        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                lock.lockRead();
                System.out.println("read1:" + counter);
                lock.unlockRead();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                lock.lockRead();
                System.out.println("read2:" + counter);
                lock.unlockRead();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                lock.lockWrite();
                counter = counter + 1;
                System.out.println("writer1:" + counter);
                lock.unlockWrite();
            }
        }).start();

//        new Thread(() -> {
//            for (int i = 0; i < 500; i++) {
//                lock.lockWrite();
//                counter = counter + 1;
//                System.out.println("writer2:" + counter);
//                lock.unlockWrite();
//            }
//        }).start();


    }
}
