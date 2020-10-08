package dp.prefixsum;

/**
 * @author guang.li
 * @version LongestAscArray_300.java v 1.0 2020/10/7 16:44
 */
public class L_300_longestAscArray {

    //    [10,9,2,5,3,7,101,18]
    //    [1,3,6,7,9,4,10,5,6]
//    1 -- 1
//    3 -- 2
//    6 -- 3
//    7 -- 4
//    9 -- 5
//    4 -- 3
//    10 -- 6
//    5 -- 4
//    6 -- 5
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

    public static void main(String[] args) {
        new L_300_longestAscArray().lengthOfLIS(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6});
    }
}
