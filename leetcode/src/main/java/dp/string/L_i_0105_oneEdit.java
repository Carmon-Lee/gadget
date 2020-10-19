/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package dp.string;

/**
 * @author liguang
 * @version L_i_0105_oneEdit.java, v 0.1 2020年10月17日 14:55
 */
public class L_i_0105_oneEdit {

    /**
     * 判断两个字符串，是否可以经过最多一次修改之后完全相同
     * dp法求编辑距离
     *
     * @param first
     * @param second
     * @return
     */
    public boolean oneEditAway(String first, String second) {
        int len1 = first.length();
        int len2 = second.length();
        if (Math.abs(len1 - len2) > 1) {
            return false;
        }

        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= len2; i++) {
            dp[0][i] = i;
        }

        // 1->2->3->4
        // 5->6->7->8
        // 9 1->2->3
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (first.charAt(i) == second.charAt(j)) {
                    dp[i + 1][j + 1] = Math.min(Math.min(dp[i][j + 1], dp[i + 1][j]) + 1, dp[i][j]);
                } else {
                    dp[i + 1][j + 1] = Math.min(Math.min(dp[i][j + 1], dp[i + 1][j]), dp[i][j]) + 1;
                }
            }
        }
        return dp[len1][len2] == 1;
    }


    /**
     * 优化求解方式
     *
     * @param first
     * @param second
     * @return
     */
    public boolean oneEditAway2(String first, String second) {
        int len1 = first.length();
        int len2 = second.length();
        if (Math.abs(len1 - len2) > 1) {
            return false;
        }

        int min = Math.min(len1, len2);

        for (int i = 0; i < min; i++) {
            if (first.charAt(i) != second.charAt(i)) {
                return first.substring(i + 1).equals(second.substring(len1 == len2 ? i + 1 : i));
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(new L_i_0105_oneEdit().oneEditAway2("pale", "pace"));
        System.out.println(new L_i_0105_oneEdit().oneEditAway2("pale", "pacd"));
        System.out.println(new L_i_0105_oneEdit().oneEditAway2("a", "ab"));
        System.out.println(new L_i_0105_oneEdit().oneEditAway2("ba", "ca"));
        System.out.println(new L_i_0105_oneEdit().oneEditAway2("ba", "a"));
    }
}