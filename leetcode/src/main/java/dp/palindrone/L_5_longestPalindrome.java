/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package dp.palindrone;

import java.util.Arrays;

/**
 * @author liguang
 * @version L_5_longestSubSequence.java, v 0.1 2020年10月16日 19:04
 */
public class L_5_longestPalindrome {

    public String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        int len = s.length();
        int[][] dp = new int[len][len];
        // abba
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j - 1] = 1;
            }
        }
        // abcba
        return "";
    }

}