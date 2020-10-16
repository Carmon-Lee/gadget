/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package dp.palindrone;

/**
 * @author liguang
 * @version L_5_longestSubSequence.java, v 0.1 2020年10月16日 19:04
 */
public class L_516_longestPalindromeSubseq {

    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
        // 12 443212324
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                if (i==0) {
                    dp[i][j] = dp[i][j-1];
                }
//                dp[i][j] =
            }
        }

        return 0;
    }
}