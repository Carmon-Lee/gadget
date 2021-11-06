package type;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liguang
 * Created on 2021-10-09
 */
public class LargeObjectFinalizer extends PhantomReference<Object> {

    public LargeObjectFinalizer(
            Object referent, ReferenceQueue<? super Object> q) {
        super(referent, q);
    }

    public void finalizeResources() {
        // free resources
        System.out.println("clearing ...");
    }

    public static void main(String[] args) throws InterruptedException {
//        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
//        List<LargeObjectFinalizer> references = new ArrayList<>();
//        List<Object> largeObjects = new ArrayList<>();
//
//        for (int i = 0; i < 10; ++i) {
//            Object largeObject = new Object();
//            largeObjects.add(largeObject);
//            references.add(new LargeObjectFinalizer(largeObject, referenceQueue));
//        }
//
//        largeObjects = null;
//        System.gc();
//
//        Reference<?> referenceFromQueue;
//        for (PhantomReference<Object> reference : references) {
//            System.out.println(reference.isEnqueued());
//        }
//
//        while ((referenceFromQueue = referenceQueue.poll()) != null) {
//            ((LargeObjectFinalizer)referenceFromQueue).finalizeResources();
//            referenceFromQueue.clear();
//        }

        ReferenceQueue<Map<String, String>> queue = new ReferenceQueue<>();
        DemoWeak ref = new DemoWeak(new HashMap<>(), queue, 10);
        System.out.println(ref.get());
        System.out.println(queue.poll());
        System.gc();
        Thread.sleep(1_000);
        System.out.println(ref.get());
        Reference<? extends Map<String, String>> poll = queue.poll();
        DemoWeak demoWeak = (DemoWeak) poll;
        System.out.println(demoWeak.getSize());
    }

    static class DemoWeak extends WeakReference<Map<String, String>> {

        private int size;
        public DemoWeak(Map<String, String> referent) {
            this(referent, null, 0);
        }

        public DemoWeak(Map<String, String> referent,
                ReferenceQueue<? super Map<String, String>> q, int size) {
            super(referent, q);
            this.size = size;
        }

        public int getSize() {
            return size;
        }
    }
}
