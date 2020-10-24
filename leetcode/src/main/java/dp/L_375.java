/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package dp;

/**
 * @author liguang
 * @version L_375.java, v 0.1 2020年10月23日 9:33
 */
public class L_375 {

    public int getMoneyAmount(int n) {
        int[][] dp = new int[n+1][n+1];
        return getAmount(dp, 1, n);
    }

    public int getMoneyAmount2(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int len = 2; len <= n; len++) {
            for (int start = 1; start <= n - len + 1; start++) {
                int minres = Integer.MAX_VALUE;
                for (int piv = start; piv < start + len - 1; piv++) {
                    int res = piv + Math.max(dp[start][piv - 1], dp[piv + 1][start + len - 1]);
                    minres = Math.min(res, minres);
                }
                dp[start][start + len - 1] = minres;
            }
        }
        return dp[1][n];
    }


    private int getAmount(int[][] dp, int i, int j) {
        if (i >= j) return 0;
        if (dp[i][j] != 0) return dp[i][j];
        int res = Integer.MAX_VALUE;

        for(int x=i; x<=j; x++){
            int tmp = x + Math.max(getAmount(dp, i, x-1), getAmount(dp, x+1, j));
            res = Math.min(res, tmp);
        }
        dp[i][j] = res;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new L_375().getMoneyAmount(1));
        System.out.println(new L_375().getMoneyAmount(2));
        System.out.println(new L_375().getMoneyAmount(3));
        System.out.println(new L_375().getMoneyAmount(4));
        System.out.println(new L_375().getMoneyAmount(5));
        System.out.println(new L_375().getMoneyAmount(6));
        System.out.println(new L_375().getMoneyAmount(7));
        System.out.println(new L_375().getMoneyAmount(10));
    }

}