package array;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author guang.li
 * @version L_334.java v 1.0 2020/11/7 10:17
 */
public class L_334 {

    public boolean increasingTriplet(int[] nums) {
        int len = nums.length;
        if (len < 3) {
            return false;
        }
        int[] left = new int[len];
        int[] right = new int[len];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        System.out.println(Arrays.toString(left));

        stack.clear();
        for (int i = len-1; i >=0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        System.out.println(Arrays.toString(right));
        for (int i = 0; i < len; i++) {
            if (left[i]!=-1 && right[i]!=-1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new L_334().increasingTriplet(new int[]{1,3,2,4,4});
    }
}
