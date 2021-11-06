import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    public int minDistance(String word1, String word2) {
        if (word1.length() == 0 || word2.length() == 0) {
            return word1.length() == 0 ? word2.length() : word1.length();
        }
        int[][] dp = new int[word1.length()][word2.length()];

        // [i][j] word1[i]--word2[j]
        for (int i=0; i<word1.length(); i++) {
            dp[i] = new int[word2.length()];
            boolean equal = word1.charAt(i) == word2.charAt(0);
            if (i == 0) {
                dp[i][0] = equal ? 1 : 0;
            } else {
                dp[i][0] = Math.max(dp[i-1][0], (equal ? 1 : 0));
            }
        }

        for (int i=1; i<word2.length(); i++) {
            boolean equal = word1.charAt(0) == word2.charAt(i);
            dp[0][i] = Math.max(dp[0][i-1], equal ? 1 : 0);
        }

        for (int i=1; i<word1.length(); i++) {
            for (int j=1; j<word2.length(); j++) {
                boolean equal = word1.charAt(i) == word2.charAt(j);
                if (equal) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        System.out.println();
        return word1.length() + word2.length() - 2*dp[word1.length()-1][word2.length()-1];
    }

    public int divide(int a, int b) {
        long aa = (long)a;
        long bb = (long)b;
        boolean positive = ((aa>=0 && bb>=0) || (aa<=0 && bb<=0));
        aa = aa > 0 ? aa : -aa;
        bb = bb > 0 ? bb : -bb;
        if (aa < bb) {
            return 0;
        }
        long result = 0;
        for (int i=31; i>=0; i--) {
            if (aa >= (bb << i)) {
                result+= 1L<<i;
                aa -= (bb << i);
            }
        }

        if (positive && result > ((1L<<31) - 1)) {
            return Integer.MAX_VALUE;
        }
        if (!positive && result > (1L<<31)) {
            return Integer.MAX_VALUE;
        }

        return positive ? (int)result : (int)-result;
    }

//    "The one-hour drama series Westworld is a dark odyssey about the dawn of artificial consciousness and the evolution of sin. Set at the intersection of the near future and the reimagined past, it explores a world in which every human appetite, no matter how noble or depraved, can be indulged."

    public static void main(String[] args) {
//        System.out.println((-1) & 1); // 111111
//        System.out.println((-2) >> 1); // 1111110
//        System.out.println((-4) >> 1); // 111111
        int[] arr = new int[10];
        List<Integer> collect = IntStream.of(arr).boxed().collect(Collectors.toList());
        System.out.println(Integer.toBinaryString((Integer.MIN_VALUE & 1)));
        System.out.println(Integer.toBinaryString((Integer.MIN_VALUE >> 1)));
        System.out.println(Integer.toBinaryString((Integer.MIN_VALUE >> 2)));
        System.out.println(Integer.toBinaryString((Integer.MIN_VALUE >> 3)));
        System.out.println(Integer.toBinaryString((Integer.MIN_VALUE >> 4)));
        System.out.println(new Solution().divide(-2147483648, -1));
        List<Integer> list;
        list.remove(10)
    }
}