package dp.stock_profit;

/**
 * @author guang.li
 * @version L_123_profit.java v 1.0 2020/10/8 12:50
 */
public class L_123_profit {

    /**
     * 以买入计算交易
     * p[i][2][0] = max(p[i-1][2][0], p[i-1][2][1]+prices[i])
     * p[i][2][1] = max(p[i-1][2][1], p[i-1][1][0]-prices[i])
     * p[i][1][0] = max(p[i-1][1][0], p[i-1][1][1]+prices[i])
     * p[i][1][1] = max(p[i-1][1][1], -prices[i])
     * <p>
     * 以卖出算交易
     * p[i][2][1] = max(p[i-1][2][1], p[i-1][2][0]-prices[i])
     * p[i][2][0] = max(p[i-1][2][0], p[i-1][1][1]+prices[i])
     * p[i][1][1] = max(p[i-1][1][1], p[i][1][0]-prices[i])
     * p[i][1][0] = max(p[i-1][1][0], p[i-1][0][1]+prices[i])
     * p[i][0][1] = max(p[i-1][0][1], -prices[i])
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int pi20 = 0, pi21 = Integer.MIN_VALUE, pi10 = 0, pi11 = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            pi20 = Math.max(pi20, pi21 + prices[i]);
            pi21 = Math.max(pi21, pi10 - prices[i]);
            pi10 = Math.max(pi10, pi11 + prices[i]);
            pi11 = Math.max(pi11, -prices[i]);
        }
        return pi20;
    }
}
