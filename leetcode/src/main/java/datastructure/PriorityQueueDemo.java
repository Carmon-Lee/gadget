package datastructure;

/**
 * @author liguang
 * @version PriorityQueueDemo.java, v 0.1 2020年09月06日 8:00 下午
 */
public class PriorityQueueDemo {

    private int[] internalArray;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public PriorityQueueDemo() {
        this.internalArray = new int[DEFAULT_CAPACITY];
    }

    public void add(int n) {
        internalArray[size++] = n;
    }

    /**
     * remove top
     * @return
     */
    public int poll() {
        int t = internalArray[0];
        internalArray[0] = internalArray[size-1];
        siftDown();
        return t;
    }

    private void siftUp() {
        int idx = size;
        while (idx>=1 && internalArray[idx]< internalArray[(idx-1)/2]) {
            int t = internalArray[idx];
            internalArray[idx] = internalArray[(idx-1)/2];
            internalArray[(idx-1)/2] = t;
        }
    }

    private void siftDown() {

    }


}
