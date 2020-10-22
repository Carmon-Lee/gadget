package sort;

import java.util.Arrays;
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
        int start0 = start;
        int end0 = end;
        int pivot = nums[start];
        int empty = start++;
        while (start <= end) {
            // 5
            // 4 3 _ 8 7
            if (empty < start) {
                if (nums[end]<pivot) {
                    nums[empty] = nums[end];
                    empty = end;
                }
                end--;
            } else if (empty > end) {
                if (nums[start]>pivot) {
                    nums[empty] = nums[start];
                    empty = start;
                }
                start++;
            }
        }
        nums[empty] = pivot;
        sort(nums, start0, empty-1);
        sort(nums, empty+1, end0);
    }


    public static void main(String[] args) {

        Random random = new Random();
        int[] nums = new int[10];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(100);
        }
        System.out.println(Arrays.toString(nums));
        new QuickSort().quickSort(nums);
        System.out.println(Arrays.toString(nums));

    }
}
