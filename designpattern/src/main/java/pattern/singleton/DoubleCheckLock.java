package pattern.singleton;

public class DoubleCheckLock {

    private DoubleCheckLock() {
    }

    private static volatile DoubleCheckLock instance;

    public static DoubleCheckLock getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckLock.class) {
                if (instance == null) {
                    instance = new DoubleCheckLock();
                }
            }
        }
        return instance;
    }
}
