package sort;

import java.util.Arrays;

public class MergeSort {

    private int[] nums;

    void sort(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        int mid = (nums.length) / 2;
        int[] left = new int[mid];
        int[] right = new int[nums.length - mid];
        System.arraycopy(nums, 0, left, 0, left.length);
        System.arraycopy(nums, mid, right, 0, right.length);
        sort(left);
        sort(right);
        int idx = 0;
        int i = 0, j = 0;
        for (; i < left.length && j < right.length; ) {
            nums[idx++] = left[i] < right[j] ? left[i++] : right[j++];
        }
        while (i < left.length) {
            nums[idx++] = left[i++];
        }
        while (j < right.length) {
            nums[idx++] = right[j++];
        }
    }

    public static void main(String[] args) {
        System.out.println((int)Math.pow(2, 3));
        Arrays.copyOf(new String[]{}, 1);

        int[] nums = {10, 23, 3, 2, 7, 1, 3, 5};
        new MergeSort().sort(nums);
        System.out.println(Arrays.toString(nums));
    }


}
