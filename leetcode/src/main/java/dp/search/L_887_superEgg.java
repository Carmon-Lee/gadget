/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package dp.search;

/**
 * @author liguang
 * @version L_887_superEgg.java, v 0.1 2020年10月26日 9:21
 */
public class L_887_superEgg {

    private int[][] dp;

    public int superEggDrop(int K, int N) {
        dp = new int[K + 1][N + 1];
        return dp(K, N);
    }

    private int dp(int k, int n) {
        if (dp[k][n] != 0) {
            return dp[k][n];
        }
        if (k == 1) {
            dp[k][n] = n;
            return n;
        }
        if (n <= 1) {
            dp[k][n] = n;
            return n;
        }
        dp[k][n] = Integer.MAX_VALUE;
        int start = 1, end = n;
        while (start <= end) {
            int mid = (start + end) / 2;
            int broken = dp(k - 1, mid - 1);
            int notBroken = dp(k, n - mid);
            if (broken > notBroken) {
                end = mid - 1;
                dp[k][n] = Math.min(dp[k][n], broken + 1);
            } else {
                start = mid + 1;
                dp[k][n] = Math.min(dp[k][n], notBroken + 1);
            }
        }
        return dp[k][n];
    }


    // dp[k][m]  k-表示k个鸡蛋; m-表示走m步; 最多可以达到的楼层
    // dp[k][m] = dp[k-1][m-1] + dp[k][m-1] + 1
    // 假设随机到了一层，走了1步，结果分为2种；鸡蛋碎或者没碎
    // 假如鸡蛋碎了，那么只能往下走，此时剩余m-1步，k-1个鸡蛋，因此最高可以走到dp[k-1][m-1]层，加上本次走的一层
    // 假如鸡蛋没碎，那么可以继续往上尝试，此时剩余m-1步，k个鸡蛋，因此还可以继续爬dp[k][m-1]层
    public int superEggDrop2(int K, int N) {
        dp = new int[N+1][K+1];
        // 1 2 3 4
        // 1 3 4 5
        for (int i = 1; i <= N; i++) {
            dp[i][1] = i;
        }
        for (int i = 1; i <= K; i++) {
            dp[1][i] = 1;
        }
        // dp[1][N] = N
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                dp[i][j] = 1 + dp[i - 1][j - 1] + dp[i - 1][j];
                if (dp[i][j] >= N) {
                    return i;
                }
            }
        }



        return 1;
    }


    public static void main(String[] args) {
        System.out.println(new L_887_superEgg().superEggDrop2(1, 2) == 2);
        System.out.println(new L_887_superEgg().superEggDrop2(2, 6) == 3);
        System.out.println(new L_887_superEgg().superEggDrop2(3, 14) == 4);
        System.out.println(new L_887_superEgg().superEggDrop2(3, 25) == 5);
        System.out.println(new L_887_superEgg().superEggDrop2(5, 10000) == 18);
    }
}