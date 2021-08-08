package timewheel;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liguang
 * Created on 2021-05-15
 */
class Solution {

    Map<String, Integer> baseMap;
    public Solution() {
        baseMap = new HashMap<>();
        baseMap.put("I", 1);
        baseMap.put("IV", 4);
        baseMap.put("V", 5);
        baseMap.put("IX", 9);
        baseMap.put("X", 10);
        baseMap.put("XL", 40);
        baseMap.put("L", 50);
        baseMap.put("XC", 90);
        baseMap.put("C", 100);
        baseMap.put("CD", 500);
        baseMap.put("D", 500);
        baseMap.put("CM", 900);
        baseMap.put("M", 1000);
    }
    public int romanToInt(String s) {
        if (baseMap.containsKey(s)) {
            return baseMap.get(s);
        }
        char[] arr = s.toCharArray();
        int result = 0;
        for (int i=0;i<arr.length;i++) {
            if (i<arr.length-1) {
                String sub = s.substring(i, i+2);
                if (baseMap.containsKey(sub)) {
                    result += baseMap.get(sub);
                    i++;
                } else {
                    result += baseMap.get(String.valueOf(arr[i]));
                }
            } else {
                result += baseMap.get(String.valueOf(arr[i]));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().romanToInt("III"));
        System.out.println(new Solution().romanToInt("XIII"));
        System.out.println(new Solution().romanToInt("LVIII"));
        System.out.println(new Solution().romanToInt("MCMXCIV"));
    }
}
