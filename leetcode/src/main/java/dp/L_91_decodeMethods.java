/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package dp;

/**
 * @author liguang
 * @version L_91_decodeMethods.java, v 0.1 2020年10月15日 12:53
 */
public class L_91_decodeMethods {

    public int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;
        if (s.charAt(0) == '0') {
            return 0;
        }
        for (int i = 1; i < s.length(); i++) {
            char prev = s.charAt(i - 1);
            char cur = s.charAt(i);
            if (cur == '0') {
                if (prev != '1' && prev != '2') {
                    return 0;
                }
                dp[i + 1] = dp[i - 1];
            } else if (cur >= '1' && cur <= '6') {
                if (prev == '1' || prev == '2') {
                    dp[i + 1] = dp[i] + dp[i - 1];
                } else {
                    dp[i + 1] = dp[i];
                }
            } else {
                if (prev == '1') {
                    dp[i + 1] = dp[i] + dp[i - 1];
                } else {
                    dp[i + 1] = dp[i];
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        System.out.println(new L_91_decodeMethods().numDecodings("0"));
        System.out.println(new L_91_decodeMethods().numDecodings("1"));
        System.out.println(new L_91_decodeMethods().numDecodings("2"));
        System.out.println(new L_91_decodeMethods().numDecodings("12"));
        System.out.println(new L_91_decodeMethods().numDecodings("226"));
        System.out.println(new L_91_decodeMethods().numDecodings("10126"));
    }


}