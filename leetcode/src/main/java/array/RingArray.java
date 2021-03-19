package array;

import java.util.concurrent.TimeUnit;

/**
 * @author guang.li
 * @version RingArray.java v 1.0 2020/9/30 12:13
 */
public class RingArray {

    public int maxSubarraySumCircular(int[] nums) {
        long sum = Integer.MIN_VALUE, maxSum = Integer.MIN_VALUE;
        for (int v : nums) {
            sum = Math.max(sum + v, v);
            maxSum = Math.max(maxSum, sum);
        }

        // prefix sum
        int[] rightsum = new int[nums.length];
        rightsum[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            rightsum[i] = rightsum[i + 1] + nums[i];
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            rightsum[i] = Math.max(rightsum[i], rightsum[i + 1]);
        }
        int leftSum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            leftSum += nums[i];
            maxSum = Math.max(maxSum, leftSum + rightsum[i + 1]);
        }

        return (int) maxSum;
    }


    public static void main(String[] args) {
        //        System.out.println(1 << 31);
        //        new RingArray().maxSubarraySumCircular(new int[]{5, -3, 5});

        System.out.println(0.1);
//        for (double balance = 10; balance != 0; balance -= 0.1) {
//            System.out.println(balance);
//            try {
//                TimeUnit.MILLISECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }

}
