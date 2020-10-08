package dp.prefixsum;

/**
 * @author guang.li
 * @version L_376_dangleArray.java v 1.0 2020/10/7 17:17
 */
public class L_376_dangleArray {

    //    https://leetcode-cn.com/problems/maximum-product-subarray/solution/dong-tai-gui-hua-li-jie-wu-hou-xiao-xing-by-liweiw/
    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int asc = 1, desc = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                asc = desc + 1;
            } else if (nums[i] < nums[i - 1]) {
                desc = asc + 1;
            }
        }
        return Math.max(asc, desc);
    }

    public static void main(String[] args) {
        new L_376_dangleArray().wiggleMaxLength(new int[]{1,7,4,9,2,5});
        new L_376_dangleArray().wiggleMaxLength(new int[]{1,17,5,10,13,15,10,5,16,8});
        new L_376_dangleArray().wiggleMaxLength(new int[]{1,2,3,4,5});
    }
}
