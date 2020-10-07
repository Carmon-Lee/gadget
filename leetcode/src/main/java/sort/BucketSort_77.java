package sort;

import java.util.Arrays;

/**
 * 75. 颜色分类
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 * <p>
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 * @author guang.li
 * @version BucketSort.java v 1.0 2020/10/7 9:03
 */
public class BucketSort_77 {

    /**
     * 1、单指针
     * 分批次完成，先交换0，再交换1
     */
    public void sortColors0(int[] nums) {
        int n = nums.length;
        int ptr = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                swap(nums, i, ptr);
                ptr++;
            }
        }
        for (int i = ptr; i < n; i++) {
            if (nums[i] == 1) {
                swap(nums, i, ptr);
                ptr++;
            }
        }
    }


    /**
     * 2、先统计各数字的数量，然后重新填充
     */
    public void sortColors1(int[] nums) {
        int zeros = 0, ones = 0, twos = 0;
        for (int n : nums) {
            switch (n) {
                case 0:
                    zeros++;
                    break;
                case 1:
                    ones++;
                    break;
                case 2:
                    twos++;
                    break;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (zeros-- > 0) {
                nums[i] = 0;
            } else if (ones-- > 0) {
                nums[i] = 1;
            } else {
                nums[i] = 2;
            }
        }
    }

    /**
     * 3、双指针
     */
    public void sortColors2(int[] nums) {
        int n = nums.length;
        int p0 = 0, p1 = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 1) {
                swap(nums, i, p1);
                ++p1;
            } else if (nums[i] == 0) {
                swap(nums, i, p0);
                if (p0 < p1) {
                    swap(nums, i, p1);
                }
                ++p0;
                ++p1;
            }
        }
    }


    /**
     * 方法2 双指针 将0移动到前面，2移动到后面
     */
    public void sortColors3(int[] nums) {
        int n = nums.length;
        int p0 = 0, p2 = n - 1;
        for (int i = 0; i <= p2; i++) {
            while (i < p2 && nums[i] == 2) {
                swap(nums, i, p2);
                p2--;
            }
            if (nums[i] == 0) {
                swap(nums, i, p0);
                p0++;
            }
        }
    }

    private void swap(int[] nums, int a, int b) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }


    public static void main(String[] args) {
        BucketSort_77 bucketSort_77 = new BucketSort_77();

        int[] arr = new int[]{1, 0, 1, 2, 0, 2, 1, 1, 0};
        bucketSort_77.sortColors2(arr);
        System.out.println(Arrays.toString(arr));
    }
}
