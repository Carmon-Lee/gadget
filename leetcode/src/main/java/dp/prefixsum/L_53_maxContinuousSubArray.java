package dp.prefixsum;

/**
 * @author guang.li
 * @version MaxContinuousSubArray_53.java v 1.0 2020/10/7 16:40
 */
public class L_53_maxContinuousSubArray {

    public int maxSubArray(int[] nums) {
        int cur = 0;
        int max = Integer.MIN_VALUE;

        for (int n : nums) {
            cur = Math.max(cur + n, n);
            max = Math.max(max, cur);
        }
        return max;
    }
}
