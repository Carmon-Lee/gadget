package dp.knapsack;

public class L_416_PartitionArray {

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int len = nums.length;
        boolean[][] dp = new boolean[len + 1][sum / 2 + 1];
        dp[0][0] = true;

        int diff;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= sum / 2; j++) {
                dp[i + 1][j] = dp[i][j];
                if ((diff = j - nums[i]) >= 0) {
                    dp[i + 1][j] |= dp[i][diff];
                }
            }
        }
        return dp[len][sum/2];
    }

    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        boolean[] dp = new boolean[sum / 2 + 1];
        dp[0] = true;

        int diff;

        for (int num : nums) {
            for (int j = sum / 2; j > 0; j--) {
                if ((diff = j - num) >= 0) {
                    dp[j] |= dp[diff];
                }
            }
        }
        return dp[sum/2];
    }
}
