package dp.sequence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 求两个字符串的所有最长子序列
 * 注意：这里说的子序列，可以是非连续的，但是要保持原有的顺序
 *
 * @author guang.li
 * @version LongestCommonSequenceAll.java v 1.0 2020/10/9 9:31
 */
public class LongestCommonSequenceAll {

    public List<String> findAllLongestCommonSequence(String s1, String s2) {
        int[][] dp = getDpArray(s1, s2);
        return findAllLongestCommonSequence(s1, s1.length() - 1, s2, s2.length() - 1, dp);
    }

    private List<String> findAllLongestCommonSequence(String s1, int idx1, String s2, int idx2, int[][] dp) {
        if (idx1 < 0 || idx2 < 0) {
            return new ArrayList<>(Collections.singletonList(""));
        }

        if (s1.charAt(idx1) == s2.charAt(idx2)) {
            List<String> sequence = findAllLongestCommonSequence(s1, idx1 - 1, s2, idx2 - 1, dp);
            return sequence.stream()
                    .map(s -> s + s1.charAt(idx1))
                    .collect(Collectors.toList());
        }
        if (dp[idx1][idx2 + 1] == dp[idx1 + 1][idx2]) {
            List<String> l1 = findAllLongestCommonSequence(s1, idx1 - 1, s2, idx2, dp);
            List<String> l2 = findAllLongestCommonSequence(s1, idx1, s2, idx2 - 1, dp);
            l1.addAll(l2);
            return l1;
        }
        if (dp[idx1][idx2 + 1] > dp[idx1 + 1][idx2]) {
            return findAllLongestCommonSequence(s1, idx1 - 1, s2, idx2, dp);
        } else {
            return findAllLongestCommonSequence(s1, idx1, s2, idx2 - 1, dp);
        }
    }

    private int[][] getDpArray(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        System.out.println(new LongestCommonSequenceAll().findAllLongestCommonSequence("ABCBDAB","BDCABA"));;
    }
}
