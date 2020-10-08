package dp.rob;

/**
 * @author guang.li
 * @version L_198_rob.java v 1.0 2020/10/7 17:29
 */
public class L_198_rob {

    //    [1,2,3,1]
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int robbed = nums[0], notrob = 0;
        for (int i = 1; i < nums.length; i++) {
            int t = robbed;
            robbed = notrob + nums[i];
            notrob = Math.max(t, notrob);
        }

        return Math.max(robbed, notrob);
    }

    public int rob1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int prev = 0, cur = 0, temp;
        for (int n : nums) {
            temp = cur;

            cur = Math.max(cur, prev + n);
            prev = temp;
        }
        return Math.max(prev, cur);
    }


    public static void main(String[] args) {

    }
}
