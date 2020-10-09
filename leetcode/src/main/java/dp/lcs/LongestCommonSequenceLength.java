package dp.lcs;

import java.util.Arrays;
import java.util.List;

/**
 * @author guang.li
 * @version LongestCommonSequence.java v 1.0 2020/10/9 8:50
 */
public class LongestCommonSequenceLength {

    /**
     * dp[i][j] = dp[i-1][j-1]+1                   s1[i]=s2[j]
     * dp[i][j] = max(dp[i-1][j], dp[i][j-1])      s1[i]!=s2[j]
     *
     * @param s1
     * @param s2
     * @return
     */
    public int longestCommonSequence(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 == 0 || len2 == 0) {
            return 0;
        }
        int[][] lcs = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (s1.charAt(i)==s2.charAt(j)) {
                    lcs[i+1][j+1] = lcs[i][j]+1;
                } else {
                    lcs[i+1][j+1] = Math.max(lcs[i][j+1], lcs[i+1][j]);
                }
            }
        }
        System.out.println(Arrays.deepToString(lcs));

        return lcs[len1][len2];
    }


    public static void main(String[] args) {
        System.out.println(new LongestCommonSequenceLength().longestCommonSequence("ABCBDAB","BDCABA"));
    }
}
