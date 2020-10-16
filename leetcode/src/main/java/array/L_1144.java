/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package array;

/**
 * @author liguang
 * @version L_1144.java, v 0.1 2020年10月16日 10:55
 * <p>
 * 给你一个整数数组nums，每次 操作会从中选择一个元素并 将该元素的值减少1。
 * <p>
 * 如果符合下列情况之一，则数组A就是 锯齿数组：
 * <p>
 * 每个偶数索引对应的元素都大于相邻的元素，即A[0] > A[1] < A[2] > A[3] < A[4] > ...
 * 或者，每个奇数索引对应的元素都大于相邻的元素，即A[0] < A[1] > A[2] < A[3] > A[4] < ...
 * 返回将数组nums转换为锯齿数组所需的最小操作次数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：2
 * 解释：我们可以把 2 递减到 0，或把 3 递减到 1。
 * 示例 2：
 * <p>
 * 输入：nums = [9,6,1,6,2]
 * 输出：4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decrease-elements-to-make-array-zigzag
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L_1144 {

    // dp[i][0]  偶数索引最大
    // dp[i][1]  奇数索引最大
    // dp[i][0] = dp[i-1][0]   arr[i-2]<arr[i-1]>arr[i]              i%2 == 1
    // dp[i][1] = dp[i-1][1]              i%2 == 1

    // dp[i][0] = dp[i-1][0]              i%2 == 0
    // dp[i][1] = dp[i-1][1]              i%2 == 0
    public int movesToMakeZigzag(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][2];
        for (int i = 1; i < len; i++) {
            if (i % 2 == 1) {
                //  1 2 3 4
                if (nums[i]>nums[i-1]) {
                    dp[i][1] = dp[i-1][1];
                    dp[i][0] = dp[i-1][1];
                } else {

                }
            } else {

            }
        }
        return 0;
    }
}