/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package array;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author liguang
 * @version L_977.java, v 0.1 2020年10月16日 9:41
 * <p>
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 示例 2：
 * <p>
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A已按非递减顺序排序。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L_977 {


    public int[] sortedSquares(int[] arr) {
        if (arr.length == 0) {
            return new int[0];
        }
        // -1 2
        int firstPos = arr.length;
        // -20 -18 ... 0 firstPos 2 ...20
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0 && firstPos == arr.length) {
                firstPos = i;
            }
        }
        int posStart = firstPos;
        int negStart = firstPos - 1;
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (posStart < arr.length && negStart >= 0) {
                if (Math.abs(arr[posStart]) < Math.abs(arr[negStart])) {
                    res[i] = arr[posStart] * arr[posStart];
                    posStart++;
                } else {
                    res[i] = arr[negStart] * arr[negStart];
                    negStart--;
                }
            } else if (posStart < arr.length) {
                res[i] = arr[posStart] * arr[posStart];
                posStart++;
            } else {
                res[i] = arr[negStart] * arr[negStart];
                negStart--;
            }
        }


        return res;
    }

    public static void main(String[] args) {
        new L_977().sortedSquares(new int[]{-5, -3, -1, 0, 2, 4, 6});
    }
}