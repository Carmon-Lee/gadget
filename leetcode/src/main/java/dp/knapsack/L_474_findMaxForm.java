package dp.knapsack;

public class L_474_findMaxForm {

    public int findMaxForm(String[] strs, int m, int n) {
        int slen = strs.length;
        int[][][] dp = new int[m + 1][n + 1][slen + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k < slen; k++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    String s = strs[k];
                    int c0 = 0, c1 = 0;
                    for (char c : s.toCharArray()) {
                        if (c == '0') {
                            c0++;
                        } else {
                            c1++;
                        }
                    }
                    if (c0 > i || c1 > j) {
                        dp[i][j][k + 1] = dp[i][j][k];
                    } else {
                        dp[i][j][k + 1] = Math.max(dp[i - c0][j - c1][k] + 1, dp[i][j][k]);
                    }
                }
            }
        }


        return dp[m][n][slen];
    }

    public static void main(String[] args) {
//        ["10","0","1"]
//1
//1
        new L_474_findMaxForm().findMaxForm(new String[]{"10", "0", "1"}, 1, 1);
    }
}
