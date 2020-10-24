/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author liguang
 * @version CountSort.java, v 0.1 2020年10月23日 16:39
 */
public class CountSort {


    public int[] sort(int[] arr) {
        int max = 0;
        for (int n : arr) {
            max = Math.max(n, max);
        }

        int[] bucket = new int[max+1];
        for (int n:arr) {
            bucket[n]++;
        }

        int arrIdx = 0;
        for (int i = 0; i <= max; i++) {
            while (bucket[i]>0) {
                bucket[i]--;
                arr[arrIdx++] = i;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(20);
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(new CountSort().sort(arr)));
    }
}