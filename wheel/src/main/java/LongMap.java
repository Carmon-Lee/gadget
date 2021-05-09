/**
 * Created on 2021-04-30
 */
public class LongMap {

    private Object[] arr;
    private int capacity;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    public LongMap() {
        this(DEFAULT_CAPACITY);
    }

    public LongMap(int capacity) {
        this.capacity = capacity;
        this.arr = new Object[capacity];
        this.size = 0;
    }

    public Object get(long key) {
        int pos = (int) (key % this.capacity);
        return arr[pos];
    }

    private void ensureCapacity() {
        if (size > capacity * LOAD_FACTOR) {
            capacity = capacity >> 1;
            Object[] newArr = new Object[capacity];
        }
    }

    static class Entry {
        long key;
        Object value;
    }

    private void resize() {

    }

    public void put(long k, Object v) {
        int pos = (int) (k % this.capacity);
        this.arr[pos] = v;
    }

    static volatile int v = 10;

    public static void main(String[] args) {
//        long l = System.currentTimeMillis();
//        int size = 1024 * 1024 * 10;
//        LongMap map = new LongMap(size);
//        for (int i = 0; i < size; i++) {
//            map.put(i, "string:" + i);
//        }
//        for (int i = 0; i < size; i++) {
//            map.get(i);
//        }
//        System.out.println(System.currentTimeMillis() - l);
        long start = System.currentTimeMillis();
        for (long i = 0; i < 10_000_000_000L; i++) {
            v++; // load v;v+1;store v
        }
        System.out.println(System.currentTimeMillis()-start);
    }
}
