/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package array.matrix;

/**
 * @author liguang
 * @version L_searchMatNum.java, v 0.1 2020年10月17日 17:05
 */
public class L_searchMatNum {

    // 1 2 3
    // 3 4 5
    // 6 7 8
    //
    public boolean searchMat(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (target < matrix[0][0] || target > matrix[rows - 1][cols - 1]) {
            return false;
        }
        int row = rowSearch(matrix, target);
        return colSearch(matrix, row, target);
    }

    private int rowSearch(int[][] mat, int target) {
        int start = 0, end = mat.length - 1;
        int mid;
        // 1 2 3 4
        // 1 2
        if (target >= mat[end][0]) {
            return end;
        }
        // 1  5   10
        while (start + 1 < end) {
            mid = (start + end) / 2;
            if (target == mat[mid][0]) {
                return mid;
            } else if (target < mat[mid][0]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (target > mat[end][0]) {
            return end;
        }
        return start;
    }

    private boolean colSearch(int[][] mat, int row, int target) {
        int[] rowArr = mat[row];
        int start = 0, end = mat[row].length - 1;
        if (rowArr[start] == target || rowArr[end] == target) {
            return true;
        }
        int mid;
        while (start + 1 < end) {
            mid = (start + end) / 2;
            if (target == rowArr[mid]) {
                return true;
            }
            if (target > rowArr[mid]) {
                start = mid;
            } else {
                end = mid;
            }
        }

        return rowArr[start] == target || rowArr[end] == target;
    }


    public static void main(String[] args) {
        System.out.println(new L_searchMatNum().searchMat(new int[][]{{1}}, 1));;
        System.out.println(new L_searchMatNum().searchMat(new int[][]{{1,2,3,4}}, 3));;
        System.out.println(new L_searchMatNum().searchMat(new int[][]{{1,3,5,6}}, 4));;
        System.out.println(new L_searchMatNum().searchMat(new int[][]{{1,3,5,6}}, 4));;
        System.out.println(new L_searchMatNum().searchMat(new int[][]{
                {1,3,5,6},
                {10,12,15,16},
                }, 14));;
        System.out.println(new L_searchMatNum().searchMat(new int[][]{
                {1,3,5,6},
                {10,12,15,16},
        }, 15));;
    }


}