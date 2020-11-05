package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author guang.li
 * @version L_57.java v 1.0 2020/11/4 13:54
 */
public class L_57 {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();

        boolean merged = false;
        int len = intervals.length;

        for (int i = 0; i < len; i++) {
            int[] curInterval = intervals[i];
            if (merged) {
                list.add(curInterval);
            } else {
                // 无交集
                if (newInterval[1] < curInterval[0]) {
                    list.add(newInterval);
                    list.add(curInterval);
                    merged = true;
                } else  {
                    if (newInterval[0] <= curInterval[1]) {
                        newInterval[0] = Math.min(curInterval[0], newInterval[0]);
                        newInterval[1] = Math.max(curInterval[1], newInterval[1]);
                    } else {
                        list.add(curInterval);
                    }
                }
            }
        }

        if (!merged) {
            list.add(newInterval);
        }

        //System.out.println(start+" "+end);
        int[][] output = new int[list.size()][2];
        for (int i = 0; i < output.length; i++) {
            output[i] = list.get(i);
        }
        return output;
    }




    public int[][] updateMatrix(int[][] matrix) {
        for (int i=0;i<matrix.length;i++) {
            for (int j=0;j<matrix[0].length;j++) {
                update(matrix, i, j);
            }
        }
        for (int i=0;i<matrix.length;i++) {
            for (int j=0;j<matrix[0].length;j++) {
                matrix[i][j] *= -1;
            }
        }
        return matrix;
    }

    //[0,1,0,1,2]
    //[1,1,0,0,1]
    //[0,0,0,1,0]
    //[1,0,1,1,2]
    //[1,0,0,0,1]

    //[0,1,1,0,0]
    //[0,1,1,0,0]
    //[0,1,0,0,1]
    //[1,1,1,1,0]
    //[1,0,0,1,0]
    private int[] surX = new int[]{0,0,-1,1};
    private int[] surY = new int[]{-1,1,0,0};
    private int update(int[][] matrix, int x, int y) {
        if (matrix[x][y]<=0) {
            return matrix[x][y];
        }
        int v = Integer.MIN_VALUE;
        for (int i=0;i<4;i++) {
            int nx = x+surX[i];
            int ny = y+surY[i];
            if (nx<0 || nx>=matrix.length || ny<0 || ny>=matrix[0].length) {
                continue;
            }
            if (matrix[nx][ny]>1) {
                continue;
            }
            if (matrix[nx][ny]==0 && matrix[x][y]>0) {
                matrix[x][y] = -1;
                return -1;
            }
            if (matrix[nx][ny]<=0) {
                v = Math.max(v, matrix[nx][ny]);
            } else {
                //System.out.println(nx + " " + ny);
                matrix[nx][ny] ++;
                v = update(matrix, nx, ny);
            }
        }
        if (matrix[x][y]<0) {
            return matrix[x][y];
        }
        matrix[x][y] = v-1;

        return matrix[x][y];
    }


    //    [1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new L_57().updateMatrix(new int[][]{{0,1,1,0,0},{0,1,1,0,0},{0,1,0,0,1},{1,1,1,1,0},{1,0,0,1,0}})));
//        System.out.println(Arrays.deepToString(new L_57().insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8})));
    }
}
