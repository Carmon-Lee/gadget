/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liguang
 * @version L_229_majorNum.java, v 0.1 2020年10月17日 16:32
 *
 * 给定一个大小为n的数组，找出其中所有出现超过⌊ n/3 ⌋次的元素。
 *
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。
 *
 *
 *
 * 示例1：
 *
 * 输入：[3,2,3]
 * 输出：[3]
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：[1]
 * 示例 3：
 *
 * 输入：[1,1,1,3,3,2,2,2]
 * 输出：[1,2]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L_229_majorNum {

    public List<Integer> majorityElement(int[] nums) {
        Arrays.sort(nums);
        List<Integer> res = new ArrayList<>();

        int oneThird = nums.length / 3;
        int prevIdx = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[prevIdx]) {
                if (i - prevIdx > oneThird) {
                    res.add(nums[prevIdx]);
                }
                prevIdx = i;
            }
        }
        if (nums.length - prevIdx > oneThird) {
            res.add(nums[prevIdx]);
        }
        return res;
    }

    public static void main(String[] args) {
//        System.out.println(new L_229_majorNum().majorityElement(new int[]{1,1,1,2,2,2,3}));;
        System.out.println(new L_229_majorNum().majorityElement(new int[]{3,2,3}));;
    }

}