/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package dp.palindrone;

/**
 * @author liguang
 * @version L_5_longestSubSequence.java, v 0.1 2020年10月16日 19:04
 */
public class L_5_longestPalindrome {

    public String longestPalindrome(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        // 0,0
        // 1,0 1,1
        // 2,0 2,1 2,2
        int start = 0;
        int end = 0;
        int max = 0;
        for (int i=0;i<len;i++) {
            for (int j=0;j<=i;j++) {
                if (s.charAt(i)!=s.charAt(j)) {
                    dp[i][j] = false;
                    continue;
                }
                if (i-j<3) {
                    dp[i][j] = true;
                } else {
                    dp[i][j] = dp[i-1][j+1];
                }

                //
                if (dp[i][j] && i-j>max) {
                    max = i-j;
                    start = j;
                    end = i;
                }
            }
        }

        return s.substring(start, end+1);
    }

    public static void main(String[] args) {
        System.out.println(new L_5_longestPalindrome().longestPalindrome("cbbd"));;
        System.out.println(new L_5_longestPalindrome().longestPalindrome("abba"));;
    }

}