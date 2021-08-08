import java.util.Arrays;
import java.util.BitSet;
import java.util.Calendar;

class Solution {
    // 1
    // 1 2
    // 1 2 2 3
    // 1 2 2 3 2 3 3 4
    //
    public int[] countBits(int n) {
        int[] res = new int[n+1];
        if (n==0) {
            return res;
        }
        res[1] = 1;
        int idx = 1;
        int lastStart = 1;
        for (int i=1;i<n;i++) {
            if (Math.pow(2, i+1)>n) {
                break;
            }
            int stepStart = (int) Math.pow(2, i);
            int halfStep = stepStart / 2;
            lastStart = halfStep;
            for (int j=0;j<halfStep;j++) {
                res[stepStart+j] = res[lastStart+j];
                res[stepStart+j+halfStep] = res[lastStart+j]+1;
                idx = stepStart+j+halfStep;
            }
            lastStart = stepStart;
        }
        for (int i=idx+1;i<=n;i++) {
            if (i<3*lastStart) {
                res[i] = res[i-lastStart];
            } else {
                res[i] = res[i-lastStart]+1;
            }
        }
        return res;
    }


    private String sum(String v1, String v2) {
        if (v1 == null || v2 == null) {
            return v1 == null ? v2 : v1;
        }
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int i = 0;
        for (; i<v1.length() && i<v2.length(); i++) {
            int s = (v1.charAt(v1.length() - 1 -i) - '0') + (v2.charAt(v2.length() - 1 -i) - '0') + carry;
            carry = s / 10;
            s = s % 10;
            sb.append(s);
        }
        for (; i<v1.length(); i++) {
            int s = (v1.charAt(v1.length() - 1 -i) - '0') + carry;
            carry = s / 10;
            s = s % 10;
            sb.append(s);
        }
        for (; i<v2.length(); i++) {
            int s = (v2.charAt(v2.length() - 1 -i) - '0') + carry;
            carry = s / 10;
            s = s % 10;
            sb.append(s);
        }
        if (carry != 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }


    private String larger(String s1, String s2) {

        if (s1 == null || s2 == null) {
            return s1 == null ? s2 : s1;
        }
        if (s1.length()!=s2.length()) {
            return s1.length() > s2.length() ? s1 : s2;
        }
        for (int i=0;i<s1.length();i++) {
            if (s1.charAt(i)!=s2.charAt(i)) {
                return s1.charAt(i)>s2.charAt(i) ? s1:s2;
            }
        }
        return s2;
    }

    public int reverseBits(int num) {
        char[] arr = Integer.toBinaryString(num).toCharArray();
        char[] fullArr = new char[32];
        Arrays.fill(fullArr, '0');
        System.arraycopy(arr, 0, fullArr, 0, arr.length);
        arr = fullArr;
        int maxLen = 0;
        for (int i=0; i<arr.length; i++) {
            if ((i==0 || (arr[i]=='1' && arr[i-1]=='0'))) {
                boolean filled = false;
                int tempLen = 0;
                for (int j=i;j<arr.length;j++) {
                    if (arr[j]=='0' && filled) {
                        break;
                    } else if (arr[j]=='0' && !filled) {
                        filled = true;
                    }
                    tempLen++;
                }
                maxLen = Math.max(maxLen, tempLen);
            }
        }

        return maxLen;
    }

    public int jump(int[] nums) {
        int[] jumpCounts = new int[nums.length];
        Arrays.fill(jumpCounts, -1);
        jumpCounts[0] = 0;

        // current
        int curJump = 1;
        int curBegin = 0, curEnd = 0;
        // next
        int nextBegin = -1;
        int nextEnd = -1;

        for (int i = curBegin; i >= 0 && i <= curEnd && i < nums.length; i++) {
            for (int j = i + 1; j <= i + nums[i] && j < nums.length; j++) {
                if (jumpCounts[j] == -1) {
                    jumpCounts[j] = curJump;
                } else if (jumpCounts[j] > curJump) {
                    jumpCounts[j] = curJump;
                }
                if (nextBegin == -1 && jumpCounts[j] == curJump) {
                    nextBegin = j;
                }
                if (jumpCounts[j] == curJump) {
                    nextEnd = Math.max(j, nextEnd);
                }
            }
            if (i == curEnd) {
                curJump++;
                i = nextBegin - 1;
                nextBegin = -1;
                curEnd = nextEnd;
                nextEnd = -1;
            }
        }

        System.out.println(Arrays.toString(jumpCounts));
        return jumpCounts[jumpCounts.length - 1];
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i=0;i<amount+1; i++) {
            int minCount = -1;
            // dp[i] = max(dp[i-c1], dp[i-c2], ...)+1
            for (int c:coins) {
                if (i<c) {
                    continue;
                }
                System.out.println(dp[i-c]==-1 ? -1: (dp[i-c]+1));
                if (minCount==-1) {
                    minCount = dp[i-c]==-1 ? -1: (dp[i-c]+1);
                } else {
                    minCount = Math.min(dp[i-c]==-1 ? -1: (dp[i-c]+1), minCount);
                }
            }
            if (minCount!=-1) {
                dp[i] = minCount;
            }
        }
        for (int i : dp) {
            if (i!=-1) {
                System.out.print(i+" ");
            }
        }

        System.out.println(Arrays.toString(dp));
        return dp[amount];
    }

    public int findMaxForm(String[] strs, int m, int n) {
        if (strs.length==0) {
            return 0;
        }
        int[][] dp = new int[m+1][n+1];
        for (int i=0; i<m+1; i++) {
            dp[i] = new int[n+1];
            Arrays.fill(dp[i], -1);
        }
        int zCount = zeroCount(strs[0]);
        dp[0][0] = 0;
        if (zCount<=m && strs[0].length()-zCount<=n) {
            dp[zCount][strs[0].length()-zCount] = 1;
        }
        for (int i = 1; i < strs.length; i++) {
            String s = strs[i];
            zCount = zeroCount(s);
            for (int j=m; j>=zCount; j--) {
                for (int k=n; k>=s.length()-zCount; k--) {
                    if (dp[j-zCount][k-(s.length()-zCount)]>=0) {
                        dp[j][k] = Math.max(dp[j][k], dp[j-zCount][k-(s.length()-zCount)]+1);
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));

        return dp[m][n];
    }

    private int zeroCount(String str) {
        int res = 0;
        for (char c:str.toCharArray()) {
            if (c=='0') {
                res++;
            }
        }
        return res;
    }

    public int minArray(int[] numbers) {
        if (numbers.length==0) {
            return 0;
        }
        if (numbers[0]<numbers[numbers.length-1]) {
            return numbers[0];
        }
        int begin = 0, end = numbers.length-1;
        int mid = begin + (end-begin)/2;
        while (begin<mid) {
            if (numbers[begin]<numbers[end]) {
                return numbers[begin];
            }
            if (numbers[mid]<numbers[mid+1] && numbers[mid]<numbers[mid-1]) {
                return numbers[mid];
            }
            if (numbers[mid]<numbers[begin]) {
                end = mid;
            } else if (numbers[mid]>numbers[end]) {
                begin = mid;
            } else {
                return numbers[mid];
            }
            mid = begin + (end-begin)/2;
        }
        return Math.min(numbers[mid], numbers[end]);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isNumber("."));
//        System.out.println(new Solution().minArray(new int[] {1,2,3})==1);
//        System.out.println(new Solution().minArray(new int[] {2,2,2})==2);
//        System.out.println(new Solution().minArray(new int[] {1})==1);
        System.out.println(new Solution().minArray(new int[] {2,2,1,2})==1);
//        System.out.println(new Solution().minArray(new int[] {3,3,2,2})==2);
    }
    public boolean isNumber(String s) {
        s = s.trim();
        s = s.replaceAll("E", "e");
        if (s.length()==0) {
            return false;
        }
        if (s.charAt(0)=='e' || s.charAt(s.length()-1)=='e') {
            return false;
        }
        if (s.indexOf('e')!=s.lastIndexOf('e')) {
            return false;
        }
        String[] parts = s.split("e");
        if (parts.length==1) {
            return isDecimal(parts[0]);
        } else if (parts.length==2) {
            return isDecimal(parts[0]) && isInteger(parts[1]);
        } else {
            return false;
        }
    }

    private boolean isInteger(String s) {
        if (s.length()==0) {
            return false;
        }
        if (s.charAt(0)=='+' || s.charAt(0)=='-') {
            s = s.substring(1);
        }
        if (s.length()==0 || s.charAt(s.length()-1)=='.') {
            return false;
        }
        for (char c:s.toCharArray()) {
            if (c<'0' || c>'9') {
                return false;
            }
        }
        return true;
    }

    private boolean isDecimal(String s) {
        if (s.length()==0) {
            return false;
        }
        if (s.charAt(0)=='+' || s.charAt(0)=='-') {
            s = s.substring(1);
        }
        if (s.length()==0) {
            return false;
        }
        if (s.equals(".")) {
            return false;
        }
        boolean containsDot = false;
        for (char c:s.toCharArray()) {
            if (c=='.') {
                if (containsDot) {
                    return false;
                }
                containsDot = true;
                continue;
            }
            if (c<'0' || c>'9') {
                return false;
            }
        }
        return true;
    }
}