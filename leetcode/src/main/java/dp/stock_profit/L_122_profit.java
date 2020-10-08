package dp.stock_profit;

/**
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 *
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *  
 *
 * 提示：
 *
 * 1 <= prices.length <= 3 * 10 ^ 4
 * 0 <= prices[i] <= 10 ^ 4
 *
 *
 * @author guang.li
 * @version L_122_profit.java v 1.0 2020/10/8 9:24
 */
public class L_122_profit {

    public int maxProfit(int[] prices) {
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i]>prices[i-1]) {
                profit += prices[i] - prices[i-1];
            }
        }
        return profit;
    }

    /**
     * 使用动态规划解决这个问题
     * 1、定义dp数组
     * 影响总体利润有两个因素，a)当前时间,数量和日期数量相同；b)当前持有股票状态，只有2种状态：持有和不持有
     * 根据两个因素可以定义dp数组为dp[prices.length][2]
     * 2、状态转移方程
     * 根据dp数组定义，可以继续推导状态转移方程，首先看第i天不持有股票的利润
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1]+prices[i])
     * 意思是说，第i天不持有股票的利润是分为两种情况：
     * 1）前一天不持有股票，并且第i天不进行股票操作，那么状态还是不持有股票；
     * 2）前一天已经持有股票，并且第i天出售股票（这里不限制次数，因此总是可以成功出售），那么状态也变成不持有股票；
     * 接着看第i天持有股票的转移方程
     * dp[i][1] = max(dp[i-1][1], dp[i-1][0]-prices[i])
     * 同理，这里也是包含两种情况：
     * 1）前一天已经持有股票，并且第i天不进行股票操作，那么状态保持为持有股票；
     * 2）前一天不持有股票，并且第i天买入股票，因此状态变为持有股票，注意买入股票是会减少利润的，因此这里是减去当前价格
     * @param prices
     * @return
     */
    public int maxProfit_dp(int[] prices) {
        // 0- 天数；2-是否持有
        // T[i][0] = max(T[i-1][0], T[i-1][1]+prices[i])
        // T[i][1] = max(T[i-1][1], T[i-1][0]-prices[i]);
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]+prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0]-prices[i]);
        }

        return dp[prices.length-1][0];
    }

    public int maxProfit_dp2(int[] prices) {
        int unhold = 0, hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            int t = unhold;
            unhold = Math.max(unhold, hold+prices[i]);
            hold = Math.max(hold, t-prices[i]);
        }
        return unhold;
    }
}
