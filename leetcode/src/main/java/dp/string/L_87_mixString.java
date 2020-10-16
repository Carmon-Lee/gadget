/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package dp.string;

/**
 * @author liguang
 * @version L_87_mixString.java, v 0.1 2020年10月16日 15:59
 */
public class L_87_mixString {

    byte[][][] dp;
    String s1;
    String s2;
    int length;

    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        length = s1.length();
        dp = new byte[length][length][length];
        this.s1 = s1;
        this.s2 = s2;
        return check(0, 0, length - 1);
    }

    private boolean check(int start1, int start2, int curLen) {
        if (start1 >= length || start2 >= length || curLen >= length) {
            return false;
        }
        if (dp[start1][start2][curLen] != 0) {
            return dp[start1][start2][curLen] == 1;
        }
        if (curLen == 0) {
            boolean equals = s1.charAt(start1) == s2.charAt(start2);
            dp[start1][start2][0] = (byte) (equals ? 1 : -1);
            return equals;
        }
        boolean t1, t2;
        for (int i = 0; i < curLen; i++) {
            // 123 213
            t1 = check(start1, start2, i);
            t2 = check(start1 + i + 1, start2 + i + 1, curLen - i - 1);
            if (t1 && t2) {
                dp[start1][start2][curLen] = 1;
                return true;
            }
            // 123 213
            t1 = check(start1, start2 + curLen  - i, i);
            t2 = check(start1 + i + 1, start2, curLen - i - 1);
            if (t1 && t2) {
                dp[start1][start2][curLen] = 1;
                return true;
            }
        }
        dp[start1][start2][curLen] = -1;
        return false;
    }


    public static void main(String[] args) {
        System.out.println(new L_87_mixString().isScramble("ts", "st"));
        System.out.println(new L_87_mixString().isScramble("123", "123"));
        System.out.println(new L_87_mixString().isScramble("123", "132"));
        System.out.println(new L_87_mixString().isScramble("123", "321"));
//         s1 = "abcde", s2 = "caebd"
        System.out.println(new L_87_mixString().isScramble("abcde", "caebd"));
    }

}