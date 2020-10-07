package dp;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.time.format.DateTimeFormatter;

/**
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * <p>
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author guang.li
 * @version MaxProduct_152.java v 1.0 2020/10/7 13:58
 */
public class MaxProduct_152 {

    /**
     * n>0
     * 1) (max * n, n) --> max
     * 2) (min * n, n) --> min
     * <p>
     * n<=0
     * 1) (min*n, n) --> max
     * 2) (max*n, n) --> min
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int max = 1, min = 1;
        int output = Integer.MIN_VALUE;
        for (int n : nums) {
            if (n > 0) {
                max = Math.max(max * n, n);
                min = Math.min(min * n, n);
            } else {
                // 先用临时变量保存最大值
                int t = max;
                max = Math.max(min * n, n);
                min = Math.min(t * n, n);
            }
            output = Math.max(output, max);
        }
        return output;
    }


    public static void main(String[] args) {
        new MaxProduct_152().maxProduct(new int[]{-4, -3, -2});

    }
}
