package dp.sequence;

/**
 * @author guang.li
 * @version L_115_differentSubSequence.java v 1.0 2020/10/13 16:44
 */
public class L_115_differentSubSequence {

    // 不同子序列的数量
    public int numDistinct(String s, String t) {
        int slen = s.length(), tlen = t.length();
        int[][] dp = new int[slen + 1][tlen + 1];

        for (int i = 0; i <= slen; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < slen; i++) {
            for (int j = 0; j < tlen; j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + dp[i][j + 1];
                } else {
                    dp[i + 1][j + 1] = dp[i][j + 1];
                }
            }
        }
        return dp[slen][tlen];
    }

    public static void main(String[] args) {
        System.out.println(new L_115_differentSubSequence().numDistinct("rabbittt", "rabit"));
    }
}
