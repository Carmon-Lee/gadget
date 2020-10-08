package dp.stock_profit;

/**
 * @author guang.li
 * @version L_188_profit.java v 1.0 2020/10/8 17:23
 */
public class L_188_profit {

    /**
     * p[i][k][0] = max(p[i-1][k][0], p[i-1][k][1]+prices[i])
     * p[i][k][1] = max(p[i-1][k][1], p[i-1][k-1][0]-prices[i])
     * <p>
     * p[i][0][1] = -inf
     * p[i][1][1] = -prices[i]
     * p[i][1][0] =
     *
     * @param k
     * @param prices
     * @return
     */
    // 买入计算一次
    public int maxProfit(int k, int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int n = prices.length;
        int[][][] dp = new int[n][k + 1][2];

        dp[0][1][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][1][1] = Math.max(-prices[i], dp[i - 1][1][1]);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        //System.out.println(Arrays.deepToString(dp[0]));
        //System.out.println(Arrays.deepToString(dp[1]));
        // System.out.println(Arrays.deepToString(dp[2]));
        return dp[prices.length - 1][k][0];
    }


    public static void main(String[] args) {
        System.out.println(new L_188_profit().maxProfit(2, new int[]{2, 4, 1}));
        ;
        System.out.println(new L_188_profit().maxProfit(1, new int[]{2, 4, 1}));
        ;
    }
}
