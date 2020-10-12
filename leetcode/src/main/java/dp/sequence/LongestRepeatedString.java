package dp.sequence;

/**
 * @author guang.li
 * @version LongestRepeatedString.java v 1.0 2020/10/10 14:01
 */
public class LongestRepeatedString {


    // dp[i][j] = 0,                           i=0, j=0
    // dp[i][j] = dp[i-1][j-1]+1,              s[i] = s[j]
    // dp[i][j] = max(dp[i-1][j],dp[i][j-1])   s[i] != s[j]
    public String longestRepeatString(String str) {
        return null;
    }
}
