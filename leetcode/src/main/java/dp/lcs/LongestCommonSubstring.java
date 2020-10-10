package dp.lcs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 查找两个字符串的最长子串
 *
 * @author guang.li
 * @version LongestCommonSubstring.java v 1.0 2020/10/9 9:53
 */
public class LongestCommonSubstring {


    /**
     * dp[i][j] = dp[i-1][j-1]+1       s1[i]=s2[j]
     *
     * @param s1
     * @param s2
     * @return
     */
    public Set<String> commonSubstring(String s1, String s2) {
        Set<String> set = new HashSet<>();
        if (s1.length() == 0 || s2.length() == 0) {
            return set;
        }
        int maxLen = 0;
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    if (dp[i + 1][j + 1] > maxLen) {
                        maxLen = Math.max(maxLen, dp[i + 1][j + 1]);
                        set.clear();
                        set.add(s1.substring(i + 1 - maxLen, i + 1));
                    } else if (dp[i + 1][j + 1] == maxLen) {
                        set.add(s1.substring(i + 1 - maxLen, i + 1));
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));

        return set;
    }

    public static void main(String[] args) {
        System.out.println(new LongestCommonSubstring().commonSubstring("ABABC", "BABCD"));
    }
}
