package slidewindow;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author guang.li
 * @version L_239_maxSlidingWindow.java v 1.0 2020/11/2 9:27
 */
public class L_239_maxSlidingWindow {

    private Deque<Integer> deque;
    private int[] nums;

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] output = new int[nums.length - k + 1];
        deque = new ArrayDeque<>();
        this.nums = nums;

        output[0] = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            cleanQueue(i, k);
            deque.offerLast(i);
            output[0] = Math.max(output[0], nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            cleanQueue(i, k);
            deque.offerLast(i);
            output[i - k + 1] = nums[deque.getFirst()];
        }

        return output;
    }

    private void cleanQueue(int i, int k) {
        if (!deque.isEmpty() && deque.getFirst() == i - k) {
            deque.removeFirst();
        }
        while (!deque.isEmpty() && nums[deque.getLast()] < nums[i]) {
            deque.removeLast();
        }
    }


    public static void main(String[] args) {
//        3,3,5,5,6,7
        System.out.println(Arrays.toString(new L_239_maxSlidingWindow().maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }
}
