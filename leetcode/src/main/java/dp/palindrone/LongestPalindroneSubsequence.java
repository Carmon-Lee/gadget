package dp.palindrone;

/**
 * @author guang.li
 * @version LongestPalindroneSubsequence.java v 1.0 2020/10/10 9:17
 */
public class LongestPalindroneSubsequence {

    //求最长回文子序列的长度
    // dp[i][j] = 1                            i=j
    // dp[i][j] = dp[i+1][j-1]+2               s[i]=s[j]
    // dp[i][j] = max(dp[i+1][j], dp[i][j-1])  s[i]!=s[j]
    public int longestPalindroneLength(String s) {
        if (s.length()==0) {
            return 0;
        }
        int len = s.length();
        int[][] ints = new int[len][len];
        longestPalindroneLength(s, ints, 0, len - 1);
        return 0;
    }

    private void longestPalindroneLength(String s, int[][] dp, int start, int end) {
        if (start == end) {
            dp[start][end] = 1;
            return;
        }
        if (start > end) {
            return;
        }
        if (dp[start][end] > 0) {
            return;
        }
        if (s.charAt(start) == s.charAt(end)) {
            longestPalindroneLength(s, dp, start + 1, end - 1);
            dp[start][end] = dp[start + 1][end - 1]+2;
        } else {
            longestPalindroneLength(s, dp, start + 1, end);
            longestPalindroneLength(s, dp, start, end - 1);
            dp[start][end] = Math.max(dp[start + 1][end], dp[start][end - 1]);
        }
    }

    public static void main(String[] args) {
        new LongestPalindroneSubsequence().longestPalindroneLength("a");
    }


    // 求最长回文串的字符串表示
    // 核心思想是先将原有字符串逆序，然后将得到的逆序和原有字符串进行比较，获取最长子串即可，思路非常巧妙
    public String longestPalindroneStr(String s) {
        return "";
    }

}
