package dp.matrix;

/**
 * @author guang.li
 * @version L_1139.java v 1.0 2020/10/13 16:15
 */
public class L_1139 {

    public int largest1BorderedSquare(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        // dp[][][0]  left
        // dp[][][1]  upper
        int[][][] dp = new int[rows + 1][cols + 1][2];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    dp[i + 1][j + 1][0] = dp[i + 1][j][0] + 1;
                    dp[i + 1][j + 1][1] = dp[i][j + 1][1] + 1;
                }
            }
        }
        int maxLen;
        int largest = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) continue;
                for (maxLen = Math.min(dp[i + 1][j + 1][0], dp[i + 1][j + 1][1]); maxLen > 0; maxLen--) {
                    if (dp[i + 1][j + 2 - maxLen][1] >= maxLen && dp[i + 2 - maxLen][j + 1][0] >= maxLen) {
                        largest = Math.max(largest, maxLen);
                        break;
                    }
                }
            }
        }
        return largest * largest;
    }

    public static void main(String[] args) {
        System.out.println(new L_1139().largest1BorderedSquare(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}));;
        System.out.println(new L_1139().largest1BorderedSquare(new int[][]{{1, 1}, {0, 0}}));;
    }


}
