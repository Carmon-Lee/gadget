package dp;

/**
 * @author guang.li
 * @version IncreasingSequence.java v 1.0 2020/10/9 12:46
 */
public class IncreasingSequence {

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length, res = 0, max_len = 0;
        int[] len = new int[n], cnt = new int[n];
        for (int i = 0; i < n; i++) {
            len[i] = cnt[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (len[i] == len[j] + 1) cnt[i] += cnt[j];
                    else if (len[i] < len[j] + 1) {
                        len[i] = len[j] + 1;
                        cnt[i] = cnt[j];
                    }
                }
            }
//            if (max_len == len[i]) res += cnt[i];
//            if (max_len < len[i]) {
//                max_len = len[i];
//                res = cnt[i];
//            }
        }
        return res;
    }


    public static void main(String[] args) {
        new IncreasingSequence().findNumberOfLIS(new int[]{1, 3, 5, 4, 7});
    }
}
