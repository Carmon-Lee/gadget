/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package array.matrix;

/**
 * @author liguang
 * @version L_200_islands.java, v 0.1 2020年10月16日 9:09
 */
public class L_200_islands {

    // 0 1
    // o 已探测为水域；0 水域未探测；1 陆地未探测；2-已探测未岛屿；
    int row = 0;
    int col = 0;

    public int numIslands(char[][] grid) {
        row = grid.length;
        col = grid[0].length;

        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                char c = grid[i][j];
                if (c == '1') {
                    count++;
                    infect(grid, i, j);
                }
            }
        }
        return count;
    }

    private int[] surX = new int[]{0, 0, -1, 1};
    private int[] surY = new int[]{-1, 1, 0, 0};

    private void infect(char[][] grid, int x, int y) {
        if (grid[x][y] != '1') {
            return;
        }
        grid[x][y] = '2';
        for (int i = 0; i < 4; i++) {
            int nx = x + surX[i];
            int ny = y + surY[i];
            if (nx < 0 || nx >= row || ny < 0 || ny >= col) {
                continue;
            }
            if (grid[nx][ny] == '1') {
                infect(grid, nx, ny);
            }
        }
    }


}