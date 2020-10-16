/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package dp;

/**
 * @author liguang
 * @version L_1477.java, v 0.1 2020年10月15日 13:50
 * 给你一个整数数组arr 和一个整数值target。
 * <p>
 * 请你在 arr中找 两个互不重叠的子数组且它们的和都等于target。可能会有多种方案，请你返回满足要求的两个子数组长度和的 最小值 。
 * <p>
 * 请返回满足要求的最小长度和，如果无法找到这样的两个子数组，请返回 -1。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [3,2,2,4,3], target = 3
 * 输出：2
 * 解释：只有两个子数组和为 3 （[3] 和 [3]）。它们的长度和为 2 。
 * 示例 2：
 * <p>
 * 输入：arr = [7,3,4,7], target = 7
 * 输出：2
 * 解释：尽管我们有 3 个互不重叠的子数组和为 7 （[7], [3,4] 和 [7]），但我们会选择第一个和第三个子数组，因为它们的长度和 2 是最小值。
 * 示例 3：
 * <p>
 * 输入：arr = [4,3,2,6,2,3,4], target = 6
 * 输出：-1
 * 解释：我们只有一个和为 6 的子数组。
 * 示例 4：
 * <p>
 * 输入：arr = [5,5,4,4,5], target = 3
 * 输出：-1
 * 解释：我们无法找到和为 3 的子数组。
 * 示例 5：
 * <p>
 * 输入：arr = [3,1,1,1,5,1,2,1], target = 3
 * 输出：3
 * 解释：注意子数组 [1,2] 和 [2,1] 不能成为一个方案因为它们重叠了。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 1000
 * 1 <= target <= 10^8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L_1477 {


    public int minSumOfLengths(int[] arr, int target) {
        int len = arr.length;
        int[][] dp = new int[len][len];
        int[] prefixSum = new int[len + 1];

        for (int i = 0; i < len; i++) {
            prefixSum[i + 1] = prefixSum[i] + arr[i];
        }

        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                dp[i][j] = prefixSum[j + 1] - prefixSum[i];
            }
        }

        return 0;
    }

    public static void main(String[] args) {

    }
}