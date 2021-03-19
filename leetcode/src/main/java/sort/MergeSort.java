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

    private int[] temp;

    public void sortRecursive(int[] nums, int left, int right) {
        if (right <= left) {
            return;
        }
        int mid = (left + right) / 2;
        sortRecursive(nums, left, mid);
        sortRecursive(nums, mid+1, right);
        int left1 = left;
        int left2 = mid+1;
        int idx = left;
        while (left1 <= mid && left2 <= right) {
            temp[idx++] = nums[left1] < nums[left2] ? nums[left1++] : nums[left2++];
        }
        while (left1 <= mid) {
            temp[idx++] = nums[left1++];
        }
        while (left2 <= right) {
            temp[idx++] = nums[left2++];
        }
        System.arraycopy(temp, left, nums, left, right - left + 1);
    }


    public static void main(String[] args) {
        int[] nums = {10, 23, 3, 2, 7, 1, 3, 5};
        MergeSort mergeSort = new MergeSort();
        mergeSort.temp = new int[nums.length];
        mergeSort.sortRecursive(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }


}
