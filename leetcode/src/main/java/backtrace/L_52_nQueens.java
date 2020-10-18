package backtrace;

import java.util.Arrays;

public class L_52_nQueens {

    private boolean[][] used;
    private int n;

    public int totalNQueens(int n) {
        used = new boolean[n][n];
        this.n = n;
        return backtrace(0, 0);
    }

    private int backtrace(int row, int count) {
        if (row >= n) {
            return count+1;
        }
        boolean skip;
        for (int i = 0; i < n; i++) {
            skip = false;
            for (int j = 0; j < row; j++) {
                if (used[j][i] || (i + row - j < n && used[j][i + row - j]) || (i - row + j >= 0 && used[j][i - row + j])) {
                    skip = true;
                    break;
                }
            }
            if (skip) {
                continue;
            }

            used[row][i] = true;
            count = backtrace(row + 1, count);
            used[row][i] = false;
        }
        return count;
    }


    public boolean hasGroupsSizeX(int[] deck) {
        if (deck.length<2) {
            return false;
        }
        Arrays.sort(deck);
        int len = deck.length;
        int prevIdx = 0;
        int size = -1;
        // 1 1 2 2 3
        for (int i=0;i<len;i++) {
            if (i>0 && deck[i]!=deck[prevIdx]) {
                if (size==-1) {
                    size = i - prevIdx;
                } else if (size%(i-prevIdx)==0 || (i-prevIdx)%size==0) {
                    size = Math.min(i-prevIdx, size);
                } else {
                    return false;
                }
                prevIdx = i;
            }
        }
        int t  =len - prevIdx;
        if (size==-1) {
            size = t;
        }
        if (size%t==0 || t%size==0) {
            size = Math.min(size,t);
        } else {
            return false;
        }
        return size>=2;
    }

    public static void main(String[] args) {
//        System.out.println(new L_52_nQueens().totalNQueens(4));
//        System.out.println(new L_52_nQueens().totalNQueens(1));
//        System.out.println(new L_52_nQueens().totalNQueens(2));
//        System.out.println(new L_52_nQueens().hasGroupsSizeX(new int[]{1,1}));
//        System.out.println(new L_52_nQueens().hasGroupsSizeX(new int[]{1,1,2,2,3,3,4,4}));
    }
}
