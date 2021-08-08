package timewheel.util;

/**
 * @author liguang
 * Created on 2021-05-13
 */
public class TimeUtils {

    public static int diffInSeconds(long lastTime, long currentTime, int scale) {
        return (int) (scale + (currentTime % scale) - (lastTime % scale)) % scale;
    }

    public static void main(String[] args) {
        System.out.println(diffInSeconds(0L, 1L, 60));
        System.out.println(diffInSeconds(10L, 10L, 60));
        System.out.println(diffInSeconds(10L, 9L, 60));
        System.out.println(diffInSeconds(59L, 0L, 60));
        System.out.println(diffInSeconds(59L, 10L, 60));
    }
}
