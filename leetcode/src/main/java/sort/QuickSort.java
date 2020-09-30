package sort;

import java.util.Random;

/**
 * @author guang.li
 * @version QuickSort.java v 1.0 2020/9/30 10:17
 */
public class QuickSort {

    public void quickSort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    private void sort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivot = start;

        sort(nums, start, end);
    }


    public static void main(String[] args) {

        Random random = new Random();
        int[] nums = new int[10];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt();
        }
        new QuickSort().quickSort(nums);
    }
}
