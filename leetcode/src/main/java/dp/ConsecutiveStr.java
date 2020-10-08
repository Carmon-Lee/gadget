package dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ConsecutiveStr {

    public int findSubstringInWraproundString(String p) {
        int[] chct = new int[26];
        for (int i = 0; i < p.length(); i++) {
            int end = i;
            chct[p.charAt(i) - 'a'] = Math.max(chct[p.charAt(i) - 'a'], 1);
            while (end > 0 && end < p.length() && (p.charAt(end)%26) == (p.charAt(end - 1) + 1)%26) {
                chct[p.charAt(i) - 'a' + end - i] = Math.max(chct[p.charAt(i) - 'a' + end - i], end - i+1);
                end++;
            }
        }
        System.out.println(Arrays.toString(chct));
        return 0;
    }


    public int subarraysWithKDistinct(int[] A, int K) {
        return subarraysWithK(A, K) - subarraysWithK(A, K-1);
    }

    private int subarraysWithK(int[] A, int K) {
        int start = 0, end = 0;
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (;end<A.length;end++) {
            int k = A[end];
            map.put(k, map.getOrDefault(k, 0)+1);
            while (map.keySet().size()>K) {
                k = A[start];
                map.put(k, map.get(k)-1);
                if (map.get(k)==0) {
                    map.remove(k);
                }
            }
            count += sum(map);
        }
        return count;
    }

    private int sum(Map<Integer, Integer> map) {
        int s = 0;
        for (int v:map.values()) {
            s+=v;
        }
        return s;
    }


    public static void main(String[] args) {
        new ConsecutiveStr().subarraysWithKDistinct(new int[]{1,2,1,2,3}, 2);
    }
}
