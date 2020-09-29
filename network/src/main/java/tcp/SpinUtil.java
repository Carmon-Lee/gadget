package tcp;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

/**
 * @author guang.li
 * @version WaitUtil.java v 1.0 2020/9/29 9:42
 */
public class SpinUtil {


    public static void spinUtil(Predicate<String> predicate, String content) {
        while (!predicate.test(content)) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                //
            }
        }
    }
}
