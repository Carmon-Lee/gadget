/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package heap;

import java.util.PriorityQueue;

/**
 * @author liguang
 * @version L_i_1709_kthNum.java, v 0.1 2020年10月17日 9:12
 */
public class L_i_1709_kthNum {

    public int getKthMagicNumber(int k) {
        int[] uglyList = new int[k + 1];
        uglyList[0] = 1;
        int i3 = 0, i5 = 0, i7 = 0;
        int n3, n5, n7;
        for (int i = 0; i < k; i++) {
            uglyList[i + 1] = Math.min(Math.min((n3 = uglyList[i3] * 3), (n5 = uglyList[i5] * 5)), (n7 = uglyList[i7] * 7));
            // 以下三种情况，不能使用if () {} else if () {} 这种形式判断
            // 因为存在可能有重复值的情况，所以通过每次仅限比较，可以去除重复值
            if (uglyList[i + 1] == n3) {
                i3++;
            }
            if (uglyList[i + 1] == n5) {
                i5++;
            }
            if (uglyList[i + 1] == n7) {
                i7++;
            }
        }
        return uglyList[k];
    }

    public static void main(String[] args) {
        new L_i_1709_kthNum().getKthMagicNumber(10);
    }
}